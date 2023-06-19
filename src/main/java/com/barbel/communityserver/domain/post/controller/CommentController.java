package com.barbel.communityserver.domain.post.controller;

import com.barbel.communityserver.domain.post.dto.CommentDto;
import com.barbel.communityserver.domain.post.dto.CommentSaveDto;
import com.barbel.communityserver.domain.post.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("community/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService)
    {
        this.commentService = commentService;
    }

    @Description("댓글 달기")
    @PostMapping("/save")
    public void saveComment(@RequestBody CommentSaveDto commentDto)
    {
        commentService.saveComment(commentDto);
    }

    @Description("대댓글 달기")
    @PostMapping("/save/{parentId}")
    public void saveReComment(@PathVariable String parentId,@RequestBody CommentSaveDto commentSaveDto)
    {
        commentService.saveReComment(parentId, commentSaveDto);
    }

    @Description("좋아요 누르기")
    @GetMapping("/good/{id}")
    public void updateGood(@PathVariable String id)
    {
        commentService.updateGood(id);
    }

    @Description("댓글 아이디로 댓글 문자열 얻어오기")
    @GetMapping("/get/{commentId}")
    public String getCommentString(@PathVariable String commentId)
    {
        return commentService.commentString(commentId);
    }
}
