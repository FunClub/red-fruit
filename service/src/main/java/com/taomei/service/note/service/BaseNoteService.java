package com.taomei.service.note.service;

import com.taomei.dao.dtos.base.PageRequestDto;
import com.taomei.dao.dtos.base.ShowPagedArtDto;
import com.taomei.dao.dtos.base.UserNPInfoDto;
import com.taomei.dao.dtos.note.SelectCatalogNoteCondition;
import com.taomei.dao.dtos.note.ShowCatalogNoteDto;
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
     * @param condition 分页查询条件
     * @return
     */
    @Override
    public ShowPagedArtDto<ShowCatalogNoteDto> selectCatalogNote(SelectCatalogNoteCondition condition) throws InvocationTargetException, IllegalAccessException {
        PageRequestDto pageRequestDto = condition.getPageRequestDto();
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
        ShowCatalogNoteDto showCatalogNoteDto;

        for (Note note1:notes){
            //填充数据
            showCatalogNoteDto = new ShowCatalogNoteDto();
            BeanUtils.copyProperties(showCatalogNoteDto,note1);
            if(note1.getThumbsUpUserIds()!=null){
                showCatalogNoteDto.setThumbsUpCount((long) note1.getThumbsUpUserIds().size());
            }else{
                showCatalogNoteDto.setThumbsUpCount(0L);
            }
            UserNPInfoDto npInfoDto = userMapper.selectUserNPInfo(note1.getUserId());
            showCatalogNoteDto.setNickname(npInfoDto.getNickname());
            showCatalogNoteDto.setProfile(npInfoDto.getProfileImg());
            long discussionCount=mongoOperations.count(Query.query(where("artId").is(note1.getNoteId())), ParentDiscussion.class);
            showCatalogNoteDto.setDiscussionCount(discussionCount);
            showCatalogNoteDtos.add(showCatalogNoteDto);
        }
        showPagedArtDto.setContent(showCatalogNoteDtos);
        return showPagedArtDto;
    }

    /**
     * 插入一条日志
     *
     * @param note 日志文档
     * @return 显示单个目录日志的dto
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public ShowCatalogNoteDto inertNote(Note note) throws InvocationTargetException, IllegalAccessException {
        note.setDate(TimeUtil.getSimpleTime());
        note = noteRepository.insert(note);
        ShowCatalogNoteDto dto = new ShowCatalogNoteDto();
        BeanUtils.copyProperties(dto, note);
        return dto;
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
