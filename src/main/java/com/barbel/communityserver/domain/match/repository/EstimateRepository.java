package com.barbel.communityserver.domain.match.repository;

import com.barbel.communityserver.domain.match.entity.Estimate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstimateRepository extends MongoRepository<Estimate,String> {
    Optional<Estimate> findById(String id);

    List<Estimate> findAllByUserEmail(String email);

    List<Estimate> findAllByType(String type);
}
