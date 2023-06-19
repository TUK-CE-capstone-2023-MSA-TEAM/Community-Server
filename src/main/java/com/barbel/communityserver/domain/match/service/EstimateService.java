package com.barbel.communityserver.domain.match.service;

import com.barbel.communityserver.domain.match.dto.GetEstimateDto;
import com.barbel.communityserver.domain.match.dto.SaveEstimateDto;
import com.barbel.communityserver.domain.match.entity.Estimate;
import com.barbel.communityserver.domain.match.repository.EstimateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class EstimateService {

    private final EstimateRepository estimateRepository;

    @Autowired
    EstimateService(EstimateRepository estimateRepository)
    {
        this.estimateRepository = estimateRepository;
    }

    public void saveEstimate(SaveEstimateDto saveEstimateDto)
    {
        Estimate estimate = Estimate.builder()
                .content(saveEstimateDto.getContent())
                .cost(saveEstimateDto.getCost())
                .userEmail(saveEstimateDto.getUserId())
                .type(saveEstimateDto.getType())
                .build();

        estimateRepository.save(estimate);
    }

    public List<GetEstimateDto> getEstimateByEmail(String email)
    {
        List<GetEstimateDto> list = new LinkedList<>();

        List<Estimate> estimates = estimateRepository.findAllByUserEmail(email);

        for(Estimate estimate : estimates)
        {
            list.add(convert(estimate));
        }

        return list;
    }

    public GetEstimateDto getEstimateById(String id)
    {
        Optional<Estimate> estimate = estimateRepository.findById(id);

        return convert(estimate.get());
    }

    public GetEstimateDto convert(Estimate estimate)
    {
        GetEstimateDto dto = new GetEstimateDto(
                estimate.getId(),estimate.cost,estimate.content,estimate.userEmail, estimate.getType()
        );

        return dto;
    }


    public List<GetEstimateDto> getEstimateByType(String type)
    {
        List<Estimate> list = estimateRepository.findAllByType(type);

        List<GetEstimateDto> estimates = new LinkedList<>();

        for(Estimate estimate : list)
        {
            estimates.add(convert(estimate));
        }

        return estimates;
    }
}
