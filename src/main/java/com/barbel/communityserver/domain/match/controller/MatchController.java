package com.barbel.communityserver.domain.match.controller;

import com.barbel.communityserver.domain.match.dto.CurrentMatchDto;
import com.barbel.communityserver.domain.match.dto.SaveMatchDto;
import com.barbel.communityserver.domain.match.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/community/match")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService)
    {
        this.matchService = matchService;
    }

    @Operation(summary = "매칭 수락 : 매칭 id로")
    @GetMapping("/accept/match/{matchId}")
    public CurrentMatchDto acceptMatch(@PathVariable String matchId)
    {
        return matchService.acceptMatch(matchId);
    }

    @Operation(summary = "해당 멘티에게 온 매칭 정보 보기 : list")
    @GetMapping("/get/mentee/{menteeId}")
    public List<CurrentMatchDto> getMatchByMentee(@PathVariable String menteeId)
    {
        return matchService.getAllMatchToMentee(menteeId);
    }

    @Operation(summary = "해당 멘토에게 온 매칭 정보 보기 : list")
    @GetMapping("/get/mentor/{mentorId}")
    public List<CurrentMatchDto> getMatchByMentor(@PathVariable String mentorId)
    {
        return matchService.getAllMatchToMentor(mentorId);
    }

    @Operation(summary = "매칭 요청 보내기 : isMentor 로 보낸 사람이 멘토인지 멘티인지 구분 , swagger bug로 바디에 mentor 있는데 무시 (지우고 해야함) , date(ex) : 2023-03-01")
    @PostMapping("/save")
    public void matchSave(@RequestBody SaveMatchDto saveMatchDto) throws ParseException
    {
        matchService.saveMatch(saveMatchDto);
    }

    @Operation(summary = "id로 매칭 삭제")
    @GetMapping("/delete/{id}")
    public void matchDelete(@PathVariable String id)
    {
        matchService.deleteMatch(id);
    }


}
