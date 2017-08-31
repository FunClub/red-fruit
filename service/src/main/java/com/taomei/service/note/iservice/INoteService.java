package com.taomei.service.note.iservice;

import com.taomei.dao.dtos.base.PageRequestDto;
import com.taomei.dao.dtos.base.ShowPagedArtDto;
import com.taomei.dao.dtos.note.SelectCatalogNoteCondition;
import com.taomei.dao.dtos.note.SelectNoteDto;
import com.taomei.dao.dtos.note.ShowCatalogNoteDto;
import com.taomei.dao.dtos.note.ShowNoteDto;
import com.taomei.dao.entities.Note;

import java.lang.reflect.InvocationTargetException;

/**
 * 日志服务接口
 */
public interface INoteService {
    /**
     * 查询一篇日志
     * @param dto 查询一篇日志的dto
     * @return 日志内容
     */
    ShowNoteDto selectNote(SelectNoteDto dto) throws InvocationTargetException, IllegalAccessException;
    /**
     *查询日志目录
     * @param condition 日志查询条件
     * @return  分页结果dto
     */
    ShowPagedArtDto<ShowCatalogNoteDto>  selectCatalogNote(SelectCatalogNoteCondition condition) throws InvocationTargetException, IllegalAccessException;
    /**
     *插入一条日志
     * @param note 日志文档
     * @return 日志id
     */
    String inertNote(Note note) throws InvocationTargetException, IllegalAccessException;
}
