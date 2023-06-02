package com.barbel.communityserver.domain.post.controller;

import com.barbel.communityserver.domain.post.dto.BoardDto;
import com.barbel.communityserver.domain.post.dto.BoardReplyDto;
import com.barbel.communityserver.domain.post.entity.Comment;
import com.barbel.communityserver.domain.post.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("community/board")
public class BoardController {

    private BoardService boardService;


    @Autowired
    public BoardController(BoardService boardService)
    {
        this.boardService = boardService;
    }

    // 후에 page 단위로 분리할 수 있도록 수정 필요
    @GetMapping()
    public List<BoardReplyDto> getAll()
    {
        return boardService.getAll();
    }

    @GetMapping("/get/{id}")
    public BoardReplyDto getBoard(@PathVariable  String id) throws RuntimeException
    {
        return boardService.getBoard(id);
    }

    @PostMapping("/save")
    public void saveBoard(@RequestBody BoardDto boardDto)
    {
        boardService.saveBoard(boardDto);
    }

    // userId를 개인 정보창에서 가져 올지 , 회원 디비에서 가져 올지 결정 필요
    @DeleteMapping("/delete/{id}/{userId}")
    public void deleteBoard(@PathVariable String id,@PathVariable String userId)
    {
        boardService.deleteBoardById(userId,id);
    }

    @PostMapping("/file/{userEmail}")
    public void fileUpload(@RequestPart MultipartFile file,@PathVariable String userEmail) throws IOException {
        boardService.uploadFile(file,userEmail);
    }
    @GetMapping("/get/comment/{boardId}")
    public List<Comment> getAllComments(@PathVariable String boardId)
    {
        return boardService.getAllComments(boardId);
    }

    @GetMapping("/get/image")
    public List<String> get() {
        List<String> fileNames = boardService.get();
        return fileNames;
    }

}
