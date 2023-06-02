package com.barbel.communityserver.domain.post.repository;

import com.barbel.communityserver.domain.post.entity.Board;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends MongoRepository<Board,String> {
    Optional<Board> findById(String id);

    List<Board> findAllByUserId(String userId);

    List<Board> findAllByTitle(String title);

    void deleteById(String id);

    Board getById(String id);
}
