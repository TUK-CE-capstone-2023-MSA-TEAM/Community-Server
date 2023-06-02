package com.barbel.communityserver.match.repository;

import com.barbel.communityserver.domain.match.entity.Match;
import com.barbel.communityserver.domain.match.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTypeExcludeFilter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.web.WebAppConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@SpringBootTest
@WebAppConfiguration
public class MatchRepositoryTest {
    @Autowired
    public MatchRepository matchRepository;

    @Test
    public void test1()
    {
        matchRepository.deleteAll();
    }
}
