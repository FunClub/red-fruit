package com.taomei.service.album.service;

import com.taomei.dao.dtos.album.*;
import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.entities.NoticeArt;
import com.taomei.dao.entities.Settings;
import com.taomei.dao.entities.album.Album;
import com.taomei.dao.entities.album.Photo;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.AlbumRepository;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.dao.repository.PhotoRepository;
import com.taomei.dao.repository.SettingsRepository;
import com.taomei.service.album.iservice.IAlbumService;
import com.taomei.service.share.anotaions.InsertArtThumbsUpNoticeArt;
import com.taomei.service.share.enums.AlbumSortType;
import com.taomei.service.share.enums.AlbumViewType;
import com.taomei.service.share.utils.TimeUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * 相册服务的基本实现
 */
@Service
public class BaseAlbumService implements IAlbumService {
    private final AlbumRepository albumRepository;
    private final SettingsRepository settingsRepository;
    private final MongoOperations mongoOperations;
    private final PhotoRepository photoRepository;
    private final UserMapper userMapper;
    private final DiscussionRepository discussionRepository;
    @Autowired
    public BaseAlbumService(AlbumRepository albumRepository, SettingsRepository settingsRepository, MongoOperations mongoOperations, PhotoRepository photoRepository, UserMapper userMapper, DiscussionRepository discussionRepository) {
        this.albumRepository = albumRepository;
        this.settingsRepository = settingsRepository;
        this.mongoOperations = mongoOperations;
        this.photoRepository = photoRepository;
        this.userMapper = userMapper;
        this.discussionRepository = discussionRepository;
    }

    /**
     * 查询编辑器使用的相片
     * @param halfId 情侣id
     * @return 编辑器相片dto集合
     */
    public List<EditorPhotoDto> selectEditorPhotoDto(String halfId){
        List<Album> albums = albumRepository.findByHalfIdOrderByUpdateDateDesc(halfId);
        List<EditorPhotoDto> editorPhotoDtos = new ArrayList<>();
        EditorPhotoDto editorPhotoDto=null;
        for(Album album:albums){
            String albumId = album.getAlbumId();
            List<Photo> photos = photoRepository.findByAlbumIdOrderByUploadDateDesc(albumId);
            for(Photo photo:photos){
                editorPhotoDto = new EditorPhotoDto();
                editorPhotoDto.setUrl("http://taomei1314.com/"+photo.getPath());
                editorPhotoDto.setThumb("http://taomei1314.com/"+photo.getPath()+"?x-oss-process=style/album-cover");
                editorPhotoDto.setTag(album.getAlbumName());
                editorPhotoDtos.add(editorPhotoDto);
            }
        }
        return editorPhotoDtos;
    }

    /**
     * 删除相片
     * @param dto 删除的相片DTO
     * @return 成功与否
     */
    @Override
    public boolean deletePhotos(DeletePhotoDto dto) {
        List<Photo> photos = dto.getPhotos();
        String albumId = dto.getAlbumId();
        /*删除相片*/
        photoRepository.delete(photos);
        /*删除相片评论*/
        Query query=null;
        for(Photo photo:photos){
            query=Query.query(where("artId").is(photo.getPhotoId()));
            mongoOperations.remove(query,"discussion");
        }
        //更新相册相片数量
        updatePhotoCount(albumId,-photos.size());
        return true;
    }

    /**
     * 更新水印
     * @param photos 相片列表
     * @return
     */
    @Override
    public boolean updateWaterMark(List<Photo> photos) {
        int count=0;
        for (Photo photo :photos){
            Query query = Query.query(where("photoId").is(photo.getPhotoId()));
            Update update = Update.update("waterMark",photo.getWaterMark())
                    .set("bright",photo.getBright())
                    .set("contrast",photo.getContrast())
                    .set("sharpen",photo.getSharpen())
                    .set("blurR",photo.getBlurR())
                    .set("blurS",photo.getBlurS());
            count+=mongoOperations.upsert(query,update,Photo.class,"photo").getN();
        }
        return count==photos.size();
    }

    /**
     * 点赞
     * @param noticeArt 点赞dto
     * @return
     */
    @Override
    @InsertArtThumbsUpNoticeArt
    public boolean thumbsUp(NoticeArt noticeArt) {
        Query query =Query.query(where("photoId").is(noticeArt.getArtId()));
        Update update = new Update();
        update.addToSet("thumbsUpUserIds",noticeArt.getGenerateUserId());
        int count= mongoOperations.updateFirst(query,update,Photo.class,"photo").getN();
        return count>0;
    }

    /**
     * 移动相片到其他相册
     * @param dto MoveAlbumPhotoDto
     * @return 成功与否
     */
    @Override
    public boolean moveAlbumPhoto(MoveAlbumPhotoDto dto) {
        List<String> photoIds = dto.getPhotoIds();
        String movedAlbumId = dto.getMovedAlbumId();
        String targetAlbumId = dto.getTarGetAlbumId();
        Query query = Query.query(where("photoId").in(photoIds));
        Update update =Update.update("albumId",targetAlbumId);
        int count =mongoOperations.updateFirst(query,update,Photo.class,"photo").getN();
        //更新相册数量
        boolean updateMovedAlbumPhotoCount=updatePhotoCount(movedAlbumId,-(photoIds.size()));
        boolean updateTargetAlbumPhotoCount=updatePhotoCount(targetAlbumId,(photoIds.size()));
        return count==photoIds.size()&&updateTargetAlbumPhotoCount&&updateMovedAlbumPhotoCount;
    }

    /**
     * 更新相册封面
     * @param dto 更新相册封面dto
     * @return 成功与否
     */
    @Override
    public boolean updateAlbumCover(UpdateAlbumCoverDto dto) {
        Query query = Query.query(where("albumId").is(dto.getAlbumId()));
       Update update= Update.update("coverImg",dto.getCoverImg());
       int count= mongoOperations.updateFirst(query,update,Album.class,"album").getN();
        return count>0;
    }

    /**
     * 修改相片信息
     * @param dto 相册信息DTO
     * @return 成功与否
     */
    @Override
    public boolean updatePhotoInfo(UpdatePhotoInfoDto dto) {
        Query query = Query.query(where("photoId").is(dto.getPhotoId()));
        Update update = new Update();
        update.set("name",dto.getName());
        update.set("description",dto.getDescription());
        int count = mongoOperations.updateFirst(query,update,Photo.class,"photo").getN();
        return count>0;
    }

    /**
     * 查询一个相册及其相片
     *
     * @param selectDto 查询条件dto
     * @return 显示一个相册及其相片的dto
     */
    @Override
    public ShowAlbumPhotoDto selectPhotos(SelectAlbumPhotoDto selectDto) {
        String albumId = selectDto.getAlbumId();
        String userId = selectDto.getUserId();
        ShowAlbumPhotoDto showDto = new ShowAlbumPhotoDto();

        //查询全部相册信息,用于上传相片
        List<Album> albums = albumRepository.findByHalfIdOrderByUpdateDateDesc(selectDto.getHalfId());
        List<ShowHalfAlbumDto> albumsDto = new ArrayList<>();
        ShowHalfAlbumDto showHalfAlbumDto = null;
        ShowHalfAlbumDto currentAlbum=null;
        for (Album album : albums) {
            showHalfAlbumDto = new ShowHalfAlbumDto();
            //填充相册信息
            showHalfAlbumDto.setAlbumId(album.getAlbumId());
            showHalfAlbumDto.setAlbumName(album.getAlbumName());
            showHalfAlbumDto.setCoverImg(album.getCoverImg());
            showHalfAlbumDto.setLimit(album.getLimit());
            showHalfAlbumDto.setPhotoCount(album.getPhotoCount());
            if (album.getAlbumId().equals(albumId)) {
                currentAlbum = showHalfAlbumDto;
            }
            albumsDto.add(showHalfAlbumDto);
        }
        showDto.setAlbums(albumsDto);
        showDto.setCurrentAlbum(currentAlbum);
        //查询相片
        List<Photo> photos = photoRepository.findByAlbumIdOrderByUploadDateDesc(albumId);
        //填充相片数据
        List<ShowPhotoDto> showPhotoDtos = new ArrayList<>();
        ShowPhotoDto showPhotoDto;
        Long thumbsUpCount=0L,discussionCount=0L;
        for (Photo photo : photos) {
            showPhotoDto = generateShowPhotoDto(photo,userId);
            showPhotoDtos.add(showPhotoDto);
            if(showPhotoDto.getThumbsUpCount()!=null){
                thumbsUpCount+=showPhotoDto.getThumbsUpCount();
            }
            if(showPhotoDto.getDiscussionCount()!=null){
                discussionCount+=showPhotoDto.getDiscussionCount();
            }
        }
        currentAlbum.setThumbsUpCount(thumbsUpCount);
        currentAlbum.setDiscussionCount(discussionCount);
        showDto.setPhotos(showPhotoDtos);
        return showDto;
    }

    /**
     * 通过photo生成可展示的photoDto
     * @param photo 相片文档
     * @param userId 用户id
     * @return
     */
    private ShowPhotoDto generateShowPhotoDto(Photo photo,String userId){
        ShowPhotoDto photoDto = new ShowPhotoDto();
        try {
            BeanUtils.copyProperties(photoDto,photo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> upUserIds = photo.getThumbsUpUserIds();
        UserNPInfoDto dto = userMapper.selectUserNPInfo(photo.getUserId());
        photoDto.setNickname(dto.getNickname());
        photoDto.setProfile(dto.getProfileImg());

        ParentDiscussion parentDiscussion = new ParentDiscussion();
        parentDiscussion.setArtId(photo.getPhotoId());

        Long discussionCount = discussionRepository.count(Example.of(parentDiscussion));
        photoDto.setDiscussionCount(discussionCount);
        if (upUserIds != null) {
            photoDto.setThumbsUpCount((long) upUserIds.size());
            photoDto.setThumbsUpAble(!upUserIds.contains(userId));
        } else {
            photoDto.setThumbsUpAble(true);
        }
        photoDto.setPhotoId(photo.getPhotoId());
        return photoDto;
    }
    /**
     * 添加相片
     *
     * @param dto 添加相片dto
     * @return 展示相片信息的dto
     */
    @Override
    public  List<ShowPhotoDto> insertPhotos(AddPhotoDto dto) throws Exception {
        //插入相片
        String albumId = dto.getAlbumId();
        String userId = dto.getUserId();
        List<Photo> photos = dto.getPhotos();
        Photo photo=null;
        for(int i=photos.size()-1;i>=0;i--){
            photo= photos.get(i);
            photo.setUploadDate(TimeUtil.getSimpleTime());
            photo.setUserId(userId);
            /*效果默认值不保存*/
            if(photo.getBlurR()==0){
                photo.setBlurR(null);
            }
            if(photo.getBlurS()==0){
                photo.setBlurS(null);
            }
            if(photo.getContrast()==0){
                photo.setContrast(null);
            }
            if(photo.getSharpen()==50){
                photo.setSharpen(null);
            }
            if(photo.getBright()==0){
                photo.setBright(null);
            }
        }
        photos = photoRepository.insert(photos);
        //更新相册更新时间
        boolean updateDateSuccess = updateDate(albumId);
        //更新相册照片数量
        boolean updatePhotoCountSuccess = updatePhotoCount(albumId, photos.size());
        if(!(updateDateSuccess&&updatePhotoCountSuccess)){throw new Exception("添加相片失败");}
        List<ShowPhotoDto> showPhotoDtos = new ArrayList<>();
        for (Photo photo1 : photos) {
            showPhotoDtos.add(generateShowPhotoDto(photo1,null));
        }
        return showPhotoDtos;
    }

    /**
     * 更新相册更新时间
     *
     * @param albumId 相册id
     * @return
     */
    public boolean updateDate(String albumId) {
        //更新相册更新时间
        Query query = Query.query(where("albumId").is(albumId));
        Update update = new Update();
        update.set("updateDate", TimeUtil.getSimpleTime());
        int count = mongoOperations.updateFirst(query, update, Album.class, "album").getN();
        return count > 0;
    }

    /**
     * 更新相册照片数量
     *
     * @param number  变化量
     * @param albumId 相册id
     * @return
     */
    public boolean updatePhotoCount(String albumId, Integer number) {
        Query query = Query.query(where("albumId").is(albumId));
        Update update = new Update();
        update.inc("photoCount", number);
        int count = mongoOperations.updateFirst(query, update, Album.class, "album").getN();
        return count > 0;
    }

    /**
     * 创建相册
     *
     * @param album 相册文档
     * @return 添加相册文档
     */
    @Override
    public Album insertAlbum(Album album) {
        album.setCreateDate(TimeUtil.getSimpleTime());
        album.setUpdateDate(album.getCreateDate());
        album.setCoverImg("static/pic-none.png");
        return albumRepository.insert(album);
    }


    public ShowAllAlbumDto selectHalfAlbum(IdsDto dto) {
        Settings settings = settingsRepository.findByUserId(dto.getUserId());
        SelectHalfAlbumDto selectHalfAlbumDto = new SelectHalfAlbumDto();
        selectHalfAlbumDto.setSettings(settings);
        selectHalfAlbumDto.setHalfId(dto.getHalfId());
        return selectHalfAlbum(selectHalfAlbumDto);
    }

    /**
     * 查询情侣相册
     *
     * @param dto 查询条件
     * @return
     */
    @Override
    public ShowAllAlbumDto selectHalfAlbum(SelectHalfAlbumDto dto) {
        Settings settings = dto.getSettings();
        String halfId = dto.getHalfId();
        int albumViewType = settings.getAlbumViewType();
        int albumSortType = settings.getAlbumSortType();

        //普通相册视图
        if (albumViewType == AlbumViewType.COMMON.getType()) {
            Sort sort = null;
            List<Album> albums = null;
            //判断排序类型
            if (albumSortType == AlbumSortType.LATEST_UPLOAD.getType()) {
                //最近更新相册在前
                /*sort = new Sort(Sort.Direction.DESC,"updateDate");*/
                albums = albumRepository.findByHalfIdOrderByUpdateDateDesc(halfId);
            } else if (albumSortType == AlbumSortType.LATEST_CREATE.getType()) {
                //最近创建在前
                sort = new Sort(Sort.Direction.DESC, "createDate");
            } else {
                //最近创建在后
                sort = new Sort(Sort.Direction.DESC, "createDate");
            }
            Album selectAlbum = new Album();
            selectAlbum.setHalfId(halfId);
           /* List<Album> albums= albumRepository.findAll(Example.of(selectAlbum),sort);*/

            //填充数据
            long totalPhoto = 0;
            List<ShowHalfAlbumDto> dtos = new ArrayList<>();
            ShowHalfAlbumDto albumDto = null;
            for (Album album : albums) {
                albumDto = new ShowHalfAlbumDto();
                if (album.getPhotoCount() != null) {
                    totalPhoto += album.getPhotoCount();
                }

                albumDto.setAlbumId(album.getAlbumId());
                albumDto.setAlbumName(album.getAlbumName());
                albumDto.setCoverImg(album.getCoverImg());
                albumDto.setLimit(album.getLimit());
                albumDto.setPhotoCount(album.getPhotoCount());
                dtos.add(albumDto);
            }

            ShowAllAlbumDto showAllAlbumDto = new ShowAllAlbumDto();
            showAllAlbumDto.setTotalPhoto(totalPhoto);
            showAllAlbumDto.setAlbums(dtos);
            return showAllAlbumDto;
        }
        return null;
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String data="static/water-logo.png?x-oss-process=image/resize,P_3";
        String txt = "红果情侣";
        byte[] b= UrlBase64.encode(data.getBytes("UTF-8"));
        System.out.println(new String(b,"UTF-8"));;
    }
}
