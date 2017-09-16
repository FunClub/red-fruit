package com.taomei.service.note.service;

import com.taomei.dao.dtos.share.PageRequestDto;
import com.taomei.dao.dtos.share.ShowPagedArtDto;
import com.taomei.dao.dtos.share.UserNPInfoDto;
import com.taomei.dao.dtos.note.SelectCatalogNoteCondition;
import com.taomei.dao.dtos.note.SelectNoteDto;
import com.taomei.dao.dtos.note.ShowCatalogNoteDto;
import com.taomei.dao.dtos.note.ShowNoteDto;
import com.taomei.dao.entities.Note;
import com.taomei.dao.entities.discussion.ParentDiscussion;
import com.taomei.dao.mapper.UserMapper;
import com.taomei.dao.repository.DiscussionRepository;
import com.taomei.dao.repository.NoteRepository;
import com.taomei.service.note.iservice.INoteService;
import com.taomei.service.share.enums.SortBy;
import com.taomei.service.share.utils.TimeUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * 日志服务基本实现
 */
@Service
public class BaseNoteService implements INoteService {
    private final NoteRepository noteRepository;
    private final UserMapper userMapper;
    private final DiscussionRepository discussionRepository;
    private final MongoOperations mongoOperations;
    @Autowired
    public BaseNoteService(NoteRepository noteRepository, UserMapper userMapper, DiscussionRepository discussionRepository, MongoOperations mongoOperations) {
        this.noteRepository = noteRepository;
        this.userMapper = userMapper;
        this.discussionRepository = discussionRepository;
        this.mongoOperations = mongoOperations;
    }

    /**
     * 修改日志
     * @param note 日志文档
     * @return 成功与否
     */
    @Override
    public boolean updateNote(Note note) {
        Query query =Query.query(where("noteId").is(note.getNoteId()));
        Update update = Update.update("title",note.getTitle())
                .set("content",note.getContent())
                .set("limit",note.getLimit())
                .set("type",note.getType());
       int count= mongoOperations.upsert(query,update,Note.class,"note").getN();
       return count>0;
    }

    /**
     * 查询一篇日志
     * @param dto 查询一篇日志的dto
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public ShowNoteDto selectNote(SelectNoteDto dto) throws InvocationTargetException, IllegalAccessException {
        String userId = dto.getUserId();
        String noteId = dto.getNoteId();
        ShowNoteDto showNoteDto = new ShowNoteDto();
        Note note = noteRepository.findOne(noteId);
        ShowCatalogNoteDto catalogNoteDto = generateCatalogNote(note,userId);
        BeanUtils.copyProperties(showNoteDto,catalogNoteDto);
        showNoteDto.setContent(note.getContent());
        return showNoteDto;
    }

    /**查询日志目录
     * @param condition 分页查询条件
     * @return
     */
    @Override
    public ShowPagedArtDto<ShowCatalogNoteDto> selectCatalogNote(SelectCatalogNoteCondition condition) throws InvocationTargetException, IllegalAccessException {
        PageRequestDto pageRequestDto = condition.getPageRequestDto();
        String userId = condition.getUserId();
        ShowPagedArtDto showPagedArtDto = new ShowPagedArtDto();
        List<ShowCatalogNoteDto> showCatalogNoteDtos = new ArrayList<>();
        Boolean byHalf = condition.getByHalf();
        String sortBy = pageRequestDto.getSortBy();
        Sort sort = null;
        //排序类型
        if (sortBy.equals(SortBy.LATEST.getSort())) {
            sort = new Sort(Sort.Direction.DESC, "date");
        } else {

        }
        PageRequest pageRequest = new PageRequest(pageRequestDto.getPageIndex(), pageRequestDto.getPageSize(), sort);

        //构建查询样例
        Note note = new Note();
        if (byHalf) {  //情侣间查询
            Boolean showHalfNote = condition.getShowHalfNote();
            if (showHalfNote) {//显示情侣日志
                note.setHalfId(condition.getHalfId());
            } else {
                note.setHalfId(condition.getUserId());
            }
        }else{

        }
        //根据条件查询日志
        Page<Note> notePage=noteRepository.findAll(Example.of(note),pageRequest);
        showPagedArtDto.setTotalElements(notePage.getTotalElements());
        List<Note> notes = notePage.getContent();


        for (Note note1:notes){
            showCatalogNoteDtos.add(generateCatalogNote(note1,userId));
        }
        showPagedArtDto.setContent(showCatalogNoteDtos);
        return showPagedArtDto;
    }

    /**
     * 生成目录日志DTO
     * @param note 日志文档
     * @param userId 用户id
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    private ShowCatalogNoteDto generateCatalogNote(Note note, String userId) throws InvocationTargetException, IllegalAccessException {
        ShowCatalogNoteDto showCatalogNoteDto = new ShowCatalogNoteDto();
        BeanUtils.copyProperties(showCatalogNoteDto,note);
        //填充数据
        if(note.getThumbsUpUserIds()!=null){
            showCatalogNoteDto.setThumbsUpCount((long) note.getThumbsUpUserIds().size());
            showCatalogNoteDto.setThumbsUpAble(!note.getThumbsUpUserIds().contains(userId));
        }else{
            showCatalogNoteDto.setThumbsUpCount(0L);
            showCatalogNoteDto.setThumbsUpAble(true);
        }
        UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(note.getUserId());
        showCatalogNoteDto.setNickname(npInfoDto.getNickname());
        showCatalogNoteDto.setProfile(npInfoDto.getProfileImg());
        long discussionCount=mongoOperations.count(Query.query(where("artId").is(note.getNoteId())), ParentDiscussion.class);
        showCatalogNoteDto.setDiscussionCount(discussionCount);
        return showCatalogNoteDto;
    }
    /**
     * 插入一条日志
     *
     * @param note 日志文档
     * @return 日志id
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public String inertNote(Note note) throws InvocationTargetException, IllegalAccessException {
        note.setDate(TimeUtil.getSimpleTime());
        note = noteRepository.insert(note);

        return note.getNoteId();
    }

   /*public ShowCatalogNoteDto generateShowCatalogNoteDto(Note note){
       ShowCatalogNoteDto dto = new ShowCatalogNoteDto();
       try {
           BeanUtils.copyProperties(dto,note);
       } catch (IllegalAccessException | InvocationTargetException e) {
           e.printStackTrace();
       }
       return dto;
   }*/
}
