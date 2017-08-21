package com.taomei.service.album.service;

import com.taomei.dao.dtos.album.AddPhotoDto;
import com.taomei.dao.dtos.album.SelectHalfAlbumDto;
import com.taomei.dao.dtos.album.ShowAllAlbumDto;
import com.taomei.dao.dtos.base.IdsDto;
import com.taomei.dao.entities.Settings;
import com.taomei.dao.entities.album.Album;
import com.taomei.dao.entities.album.Photo;
import com.taomei.dao.repository.AlbumRepository;
import com.taomei.dao.repository.PhotoRepository;
import com.taomei.dao.repository.SettingsRepository;
import com.taomei.service.album.iservice.IAlbumService;
import com.taomei.service.share.enums.AlbumSortType;
import com.taomei.service.share.enums.AlbumViewType;
import com.taomei.service.share.utils.TimeUtil;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 相册服务的基本实现
 */
@Service
public class BaseAlbumService implements IAlbumService{
    private final AlbumRepository albumRepository;
    private final SettingsRepository settingsRepository;
    private final MongoOperations  mongoOperations;
    private final PhotoRepository photoRepository;
    @Autowired
    public BaseAlbumService(AlbumRepository albumRepository, SettingsRepository settingsRepository, MongoOperations mongoOperations, PhotoRepository photoRepository) {
        this.albumRepository = albumRepository;
        this.settingsRepository = settingsRepository;
        this.mongoOperations = mongoOperations;
        this.photoRepository = photoRepository;
    }

    /**
     * 添加相片
     * @param dto 添加相片dto
     * @return
     */
    @Override
    public boolean insertPhotos(AddPhotoDto dto) {
        //插入相片
        String albumId = dto.getAlbumId();
        List<Photo> photos = dto.getPhotos();
        photos=photoRepository.insert(photos);
        //更新相册更新时间
        boolean updateDateSuccess = updateDate(albumId);
        //更新相册照片数量
        boolean updatePhotoCountSuccess = updatePhotoCount(albumId,photos.size());

        return updateDateSuccess&&updatePhotoCountSuccess;
    }

    /**
     * 更新相册更新时间
     * @param albumId 相册id
     * @return
     */
    public boolean updateDate(String albumId){
        //更新相册更新时间
        Query query = Query.query(where("albumId").is(albumId));
        Update update = new Update();
        update.set("updateDate",TimeUtil.getSimpleTime());
        int count=mongoOperations.updateFirst(query,update,Album.class,"album").getN();
        return count>0;
    }

    /**
     * 更新相册照片数量
     * @param number 变化量
     * @param albumId 相册id
     * @return
     */
    public boolean updatePhotoCount(String albumId,Integer number){
        Query query = Query.query(where("albumId").is(albumId));
        Update update = new Update();
        update.inc("photoCount",number);
        int count=mongoOperations.updateFirst(query,update,Album.class,"album").getN();
        return count>0;
    }
    /**
     * 创建相册
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

    public ShowAllAlbumDto selectHalfAlbum(IdsDto dto){
        Settings settings =settingsRepository.findByUserId(dto.getUserId());
        SelectHalfAlbumDto selectHalfAlbumDto = new SelectHalfAlbumDto();
        selectHalfAlbumDto.setSettings(settings);
        selectHalfAlbumDto.setHalfId(dto.getHalfId());
        return selectHalfAlbum(selectHalfAlbumDto);
    }

    @Override
    public ShowAllAlbumDto selectHalfAlbum(SelectHalfAlbumDto dto) {
        Settings settings = dto.getSettings();
        String halfId = dto.getHalfId();
        int albumViewType = settings.getAlbumViewType();
        int albumSortType = settings.getAlbumSortType();

        //普通相册视图
        if(albumViewType== AlbumViewType.COMMON.getType()){
            Sort sort=null;
            List<Album> albums=null;
            //判断排序类型
            if(albumSortType== AlbumSortType.LATEST_UPLOAD.getType()){
                //最近更新相册在前
                /*sort = new Sort(Sort.Direction.DESC,"updateDate");*/
                albums = albumRepository.findByHalfIdOrderByUpdateDateDesc(halfId);
            }else if(albumSortType== AlbumSortType.LATEST_CREATE.getType()){
                //最近创建在前
                sort = new Sort(Sort.Direction.DESC,"createDate");
            }else {
                //最近创建在后
                sort = new Sort(Sort.Direction.DESC,"createDate");
            }
            Album selectAlbum = new Album();
            selectAlbum.setHalfId(halfId);
           /* List<Album> albums= albumRepository.findAll(Example.of(selectAlbum),sort);*/

            //填充数据
            long totalPhoto=0,totalThumbsUp=0;
            for (Album album:albums){
                if(album.getPhotoCount()!=null){
                    totalPhoto+=album.getPhotoCount();
                }
                if(album.getThumbsUpCount()!=null){
                    totalThumbsUp+=album.getThumbsUpCount();
                }
            }
            ShowAllAlbumDto showAllAlbumDto = new ShowAllAlbumDto();
            showAllAlbumDto.setAlbums(albums);
            showAllAlbumDto.setTotalPhoto(totalPhoto);
            showAllAlbumDto.setTotalThumbsUp(totalThumbsUp);
            return  showAllAlbumDto;
        }
        return null;
    }

   /* public static void main(String[] args) throws UnsupportedEncodingException {
        String data="static/water-logo.png?x-oss-process=image/resize,p_40";
        String txt = "红果情侣";
        byte[] b= UrlBase64.encode(data.getBytes("UTF-8"));
        System.out.println(new String(b,"UTF-8"));;
    }*/
}
