package com.barbel.communityserver.domain.match.controller;

import com.barbel.communityserver.domain.match.dto.CurrentMatchDto;
import com.barbel.communityserver.domain.match.dto.SaveMatchDto;
import com.barbel.communityserver.domain.match.service.MatchService;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService)
    {
        this.matchService = matchService;
    }

    @Description("매칭 수락 : 매칭 id로")
    @GetMapping("/accept/match/{matchId}")
    public CurrentMatchDto acceptMatch(@PathVariable String matchId)
    {
        return matchService.acceptMatch(matchId);
    }

    @Description("해당 멘티에게 온 매칭 정보 보기 : list")
    @GetMapping("/get/mentee/{menteeId}")
    public List<CurrentMatchDto> getMatchByMentee(@PathVariable String menteeId)
    {
        return matchService.getAllMatchToMentee(menteeId);
    }

    @Description("해당 멘토에게 온 매칭 정보 보기 : list")
    @GetMapping("/get/mentor/{mentorId}")
    public List<CurrentMatchDto> getMatchByMentor(@PathVariable String mentorId)
    {
        return matchService.getAllMatchToMentor(mentorId);
    }

    @Description("매칭 요청 보내기 : isMentor 로 보낸 사람이 멘토인지 멘티인지 구분")
    @PostMapping("/save")
    public void matchSave(SaveMatchDto saveMatchDto) throws ParseException
    {
        matchService.saveMatch(saveMatchDto);
    }

    @Description("id로 매칭 삭제")
    @GetMapping("/delete/{id}")
    public void matchDelete(@PathVariable String id)
    {
        matchService.deleteMatch(id);
    }


}
