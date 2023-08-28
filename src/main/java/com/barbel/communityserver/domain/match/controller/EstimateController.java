package com.barbel.communityserver.domain.match.controller;

import com.barbel.communityserver.domain.match.dto.GetEstimateDto;
import com.barbel.communityserver.domain.match.dto.SaveEstimateDto;
import com.barbel.communityserver.domain.match.entity.Estimate;
import com.barbel.communityserver.domain.match.repository.EstimateRepository;
import com.barbel.communityserver.domain.match.service.EstimateService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community/estimate")
public class EstimateController {

    private final EstimateService estimateService;

    @Autowired
    EstimateController(EstimateService estimateService)
    {
        this.estimateService = estimateService;
    }

    @Operation(summary = "견적서 작성 : 금액 , 작성자 id , 내용 , type(운동 or 커피 등등)")
    @PostMapping("/save")
    public void saveEstimate(@RequestBody SaveEstimateDto saveEstimateDto)
    {
        estimateService.saveEstimate(saveEstimateDto);
    }

    @Operation(summary = "견적서 정보를 작성자 id로 가져오기 : List")
    @GetMapping("/get/user/{userId}")
    public List<GetEstimateDto> getEstimateByUserEmail(@PathVariable  String userId)
    {
        return estimateService.getEstimateByEmail(userId);
    }

    @Operation(summary = "견적서 정보를 견적서 id로 가져오기 : 단일 개체")
    @GetMapping("/get/id/{id}")
    public GetEstimateDto getEstimateById(@PathVariable String id)
    {
        return estimateService.getEstimateById(id);
    }

    @Operation(summary = "견적서 정보들을 type(클래스 : 운동인지 커피 등등) 으로 가져오기 : List")
    @GetMapping("/get/type/{type}")
    public List<GetEstimateDto> getEstimateByType(@PathVariable String type)
    {
        return estimateService.getEstimateByType(type);
    }

}
