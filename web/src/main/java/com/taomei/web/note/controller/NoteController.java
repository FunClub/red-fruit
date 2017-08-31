package com.taomei.web.note.controller;

import com.taomei.dao.dtos.note.SelectCatalogNoteCondition;
import com.taomei.dao.dtos.note.SelectNoteDto;
import com.taomei.dao.entities.Note;
import com.taomei.dao.entities.ResultView;
import com.taomei.service.note.iservice.INoteService;
import com.taomei.web.share.anotaions.SetId;
import com.taomei.web.share.anotaions.SetUserId;
import com.taomei.web.share.utils.ResultViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/note")
public class NoteController {
    private final INoteService noteService;

    @Autowired
    public NoteController(@Qualifier("baseNoteService") INoteService noteService) {
        this.noteService = noteService;
    }

    /**
     * 查询一篇日志
     * @param userId 用户id
     * @param noteId 日志id
     * @return 统一数据对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @GetMapping("/{noteId}")
    @SetUserId
    public ResultView selectNote(String userId,@PathVariable("noteId") String noteId) throws InvocationTargetException, IllegalAccessException {
        SelectNoteDto dto = new SelectNoteDto();
        dto.setNoteId(noteId);
        dto.setUserId(userId);
        return ResultViewUtil.success(noteService.selectNote(dto));
    }

    /**
     * 查询日志目录
     * @param userId 用户id
     * @param halfId 情侣id
     * @param condition 分页查询条件
     * @return 统一数据对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/select")
    @SetId
    public ResultView selectCatalogNote(String userId,String halfId,@RequestBody SelectCatalogNoteCondition condition) throws InvocationTargetException, IllegalAccessException {
        condition.setUserId(userId);
        condition.setHalfId(halfId);
        return ResultViewUtil.success(noteService.selectCatalogNote(condition));
    }
    /**
     * 插入一条日志
     * @param note 日志文档
     * @return 统一数据对象
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("/")
    @SetId
    public ResultView insertNote(String userId,String halfId,@RequestBody Note note) throws InvocationTargetException, IllegalAccessException {
        note.setHalfId(halfId);
        note.setUserId(userId);
        return ResultViewUtil.success(noteService.inertNote(note));
    }
}
