package com.barbel.communityserver.domain.post.service;

import com.barbel.communityserver.domain.post.dto.CommentDto;
import com.barbel.communityserver.domain.post.dto.CommentSaveDto;
import com.barbel.communityserver.domain.post.entity.Board;
import com.barbel.communityserver.domain.post.entity.Comment;
import com.barbel.communityserver.domain.post.repository.BoardRepository;
import com.barbel.communityserver.domain.post.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CommentService {

    private BoardRepository boardRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(BoardRepository boardRepository,CommentRepository commentRepository)
    {
        this.boardRepository = boardRepository;
        this.commentRepository = commentRepository;
    }

    public CommentDto convert(Comment comment)
    {
        CommentDto commentDto = new CommentDto();

        commentDto.boardId = comment.getBoardId();
        commentDto.content = comment.getContent();
        commentDto.childList = comment.getChildList();
        commentDto.good = comment.getGood();
        commentDto.userId = comment.getUserId();

        return commentDto;
    }

    @Transactional
    public void saveComment(CommentSaveDto commentDto)
    {

        Comment comment = Comment.builder().boardId(commentDto.boardId).userId(commentDto.userId)
                .content(commentDto.content).build();

        Comment saveComment = commentRepository.save(comment);

        Optional<Board> cur = boardRepository.findById(saveComment.getBoardId());

        if(cur.isPresent())
        {
            Board board = cur.get();
            board.addComment(saveComment.getId());
            boardRepository.save(board);
        }
        else{
            throw new RuntimeException("해당 게시판은 존재하지 않습니다.");
        }
    }

    public void saveReComment(String parentId,CommentSaveDto commentDto)
    {
        Optional<Comment> comment = commentRepository.findById(parentId);

        if(comment.isPresent())
        {
            Comment cur = comment.get();

            Comment reComment = Comment.builder().boardId(commentDto.boardId)
                    .userId(commentDto.userId).content(commentDto.getContent())
                    .build();

            cur.addReComment(reComment);

            commentRepository.save(cur);
        }
    }


    public void updateComment(String id,String content)
    {
        Optional<Comment> cur = commentRepository.findById(id);

        if(cur.isPresent())
        {
            Comment comment = cur.get();
            comment.setContent(content);
            commentRepository.save(comment);
        }
    }

    public void updateGood(String id)
    {
        Comment comment = commentRepository.getById(id);
        comment.setGood(comment.getGood()+1);
    }
}
