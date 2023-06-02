package com.barbel.communityserver.domain.match.service;

import com.barbel.communityserver.domain.match.dto.MatchDto;
import com.barbel.communityserver.domain.match.entity.Match;
import com.barbel.communityserver.domain.match.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    MatchService(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    public List<MatchDto> findAll()
    {
        List<MatchDto> list = new ArrayList<>();

        matchRepository.findAll().forEach(
                x->list.add(convert(x)));

        return list;
    }

    public MatchDto getMatch(String id)
    {
        Optional<Match> match = matchRepository.findById(id);
        if(match.isPresent())
        {
            return convert(match.get());
        }
        else{
            throw new IllegalArgumentException("해당 id의 매칭 정보가 존재하지 않습니다 : "+ id);
        }
    }

    public void saveMatch(MatchDto matchDto) throws ParseException
    {
        Date now = convertDate(matchDto.matchDate);

        Match match = Match.builder().mentorEmail(matchDto.mentorEmail)
                .menteeEmail(matchDto.menteeEmail).date(now).build();
        matchRepository.save(match);

    }

    public Date convertDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(date);
    }

    public void deleteMatch(String id)
    {
        matchRepository.deleteById(id);
    }
    public MatchDto convert(Match match)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(match.getDate());

        MatchDto dto = new MatchDto(match.getMentorEmail(), match.getMenteeEmail(),strDate);

        return dto;
    }
}
