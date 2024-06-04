package org.careerjump.server.careerboard.dto;

import lombok.Data;
import org.careerjump.server.careerboard.domain.Career;
import org.careerjump.server.utils.LocalDateUtils;

@Data
public class CareerDto {

    private String companyName;
    private String job;
    private String startDate;
    private String endDate;
    private String description;
    private boolean isWorking;

    public Career toEntity() {
        return Career.builder()
                .companyName(companyName)
                .job(job)
                .startDate(LocalDateUtils.parse(startDate))
                .endDate(LocalDateUtils.parse(endDate))
                .isWorking(isWorking)
                .build();
    }
}
