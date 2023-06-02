package com.barbel.communityserver.domain.match.controller;

import com.barbel.communityserver.domain.match.dto.MatchDto;
import com.barbel.communityserver.domain.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.DispatcherServlet;

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

    @GetMapping
    public List<MatchDto> start()
    {
        List<MatchDto> list = matchService.findAll();

        return list;
    }

    @GetMapping("/get/{id}")
    public MatchDto matchGet(@PathVariable String id)
    {
        return matchService.getMatch(id);
    }

    @PostMapping("/save")
    public void matchSave(MatchDto matchDto) throws ParseException
    {
        matchService.saveMatch(matchDto);
    }

    @GetMapping("/delete/{id}")
    public void matchDelete(@PathVariable String id)
    {
        matchService.deleteMatch(id);
    }


}
