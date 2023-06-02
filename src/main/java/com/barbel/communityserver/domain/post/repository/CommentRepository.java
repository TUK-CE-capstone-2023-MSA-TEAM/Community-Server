package com.barbel.communityserver.domain.post.repository;

import com.barbel.communityserver.domain.post.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {

    Optional<Comment> findById(String id);

    Optional<Comment> findByBoardId(String id);

    Optional<Comment> findByUserId(String id);

    void deleteByBoardIdAndUserId(String bid, String uid);

    Comment getById(String id);
}
