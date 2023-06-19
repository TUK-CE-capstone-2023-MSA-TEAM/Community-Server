package com.barbel.communityserver.domain.match.service;

import com.barbel.communityserver.domain.match.dto.CurrentMatchDto;
import com.barbel.communityserver.domain.match.dto.SaveMatchDto;
import com.barbel.communityserver.domain.match.entity.Match;
import com.barbel.communityserver.domain.match.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    MatchService(MatchRepository matchRepository)
    {
        this.matchRepository = matchRepository;
    }

    public List<CurrentMatchDto> getAllMatchToMentor(String mentorEmail)
    {
        List<CurrentMatchDto> list = new LinkedList<>();

        List<Match> matches = matchRepository.findAllByMentorEmail(mentorEmail);

        for(Match match : matches)
        {
            list.add(
                    new CurrentMatchDto(
                       match.getId(),match.getMentorEmail(),match.getMenteeEmail(),
                       match.getDate(),match.mentorOk,match.menteeOk
                    )
            );
        }

        return list;
    }

    public List<CurrentMatchDto> getAllMatchToMentee(String menteeEmail)
    {
        List<CurrentMatchDto> list = new LinkedList<>();

        List<Match> matches = matchRepository.findAllByMenteeEmail(menteeEmail);

        for(Match match : matches)
        {
            list.add(
                    new CurrentMatchDto(
                            match.getId(),match.getMentorEmail(),match.getMenteeEmail(),
                            match.getDate(),match.mentorOk,match.menteeOk
                    )
            );
        }

        return list;
    }

    public CurrentMatchDto acceptMatch(String matchId)
    {
        Optional<Match> matchTmp = matchRepository.findById(matchId);

        Match match = matchTmp.get();

        match.mentorOk = true;
        match.menteeOk = true;

        Match acceptMatch = matchRepository.save(match);

        CurrentMatchDto result = new CurrentMatchDto(
                acceptMatch.getId(),acceptMatch.getMentorEmail(),acceptMatch.getMenteeEmail(),
                acceptMatch.getDate(),acceptMatch.mentorOk,acceptMatch.menteeOk
        );

        return result;
    }

    public void saveMatch(SaveMatchDto saveMatchDto) throws ParseException
    {
        Date now = convertDate(saveMatchDto.matchDate);

        Match match = Match.builder().mentorEmail(saveMatchDto.mentorEmail)
                .menteeEmail(saveMatchDto.menteeEmail).date(now).mentorOk(saveMatchDto.isMentor).menteeOk(!saveMatchDto.isMentor).build();
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

}
