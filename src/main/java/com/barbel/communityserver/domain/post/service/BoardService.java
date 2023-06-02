package com.barbel.communityserver.domain.post.service;

import com.barbel.communityserver.domain.post.dto.BoardDto;
import com.barbel.communityserver.domain.post.dto.BoardReplyDto;
import com.barbel.communityserver.domain.post.entity.Board;
import com.barbel.communityserver.domain.post.entity.Comment;
import com.barbel.communityserver.domain.post.repository.BoardRepository;
import com.barbel.communityserver.domain.post.repository.CommentRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class BoardService {

    private BoardRepository boardRepository;
    private CommentRepository commentRepository;

    private GridFsTemplate gridFsTemplate;
    @Autowired
    public BoardService(BoardRepository boardRepository,CommentRepository commentRepository,GridFsTemplate gridFsTemplate)
    {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
        this.gridFsTemplate = gridFsTemplate;
    }

    public BoardReplyDto convert(Board board)
    {
        BoardReplyDto boardDto = new BoardReplyDto();
        boardDto.setTitle(board.getTitle());
        boardDto.setLocation(board.getLocation());
        boardDto.setContent(board.getContent());
        boardDto.setUserId(board.getUserId());
        boardDto.setViews(board.getViews());
        boardDto.setComments(board.getComments());
        return boardDto;
    }

    public List<BoardReplyDto> getAll()
    {
        List<Board> list =  boardRepository.findAll();

        List<BoardReplyDto> boardDtoList = new LinkedList<>();

        for (Board board : list)
        {
            boardDtoList.add(convert(board));
        }

        return boardDtoList;
    }

    public void saveBoard(BoardDto boardDto)
    {
        Board board = Board.builder().content(boardDto.content).
                location(boardDto.location).title(boardDto.title)
                .userId(boardDto.userId).views(boardDto.views).build();

        boardRepository.save(board);
    }

    public void deleteBoardById(String userId,String id)
    {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent())
        {
            Board cur = board.get();
            if(cur.getUserId().equals(userId))
            {
                List<String> commentList = cur.getComments();
                for(String commentId : commentList)
                {
                    commentRepository.deleteById(commentId);
                }

                boardRepository.deleteById(id);
            }
        }
    }

    public BoardReplyDto getBoard(String id)
    {
        Optional<Board> board = boardRepository.findById(id);

        if(board.isPresent())
        {
            return convert(board.get());
        }

        else{
            throw new RuntimeException("해당 내용의 게시판은 존재하지 않습니다.");
        }
    }

    public List<Comment> getAllComments(String boardId)
    {
        Board board = boardRepository.getById(boardId);
        List<Comment> commentList = new ArrayList<>();

        for(String commentId : board.getComments())
        {
            commentList.add(commentRepository.getById(commentId));
        }

        return commentList;
    }

    public void uploadFile(MultipartFile file,String userEmail) throws IOException {
        DBObject dbObject = new BasicDBObject();
        dbObject.put("fileName",file.getOriginalFilename());
        dbObject.put("contentType",file.getContentType());
        dbObject.put("size",file.getSize());
        dbObject.put("userId",userEmail);
        ObjectId objectId = gridFsTemplate.store(file.getInputStream(),file.getOriginalFilename(),dbObject);
        System.out.println(objectId);
    }

    public List<String> get() {
        List<String> fileNames = new ArrayList<>();
        gridFsTemplate
                .find(new Query())
                .map(GridFSFile::getFilename)
                .into(fileNames);
        return fileNames;
    }

}
