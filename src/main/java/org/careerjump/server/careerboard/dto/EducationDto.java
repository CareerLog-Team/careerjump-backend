package org.careerjump.server.careerboard.dto;


import lombok.Data;
import org.careerjump.server.careerboard.domain.Education;
import org.careerjump.server.utils.LocalDateUtils;

@Data
public class EducationDto {

    private String educationName;
    private String major;
    private String startDate;
    private String endDate;

    public Education toEntity() {
        return Education.builder()
                .educationName(educationName)
                .major(major)
                .startDate(LocalDateUtils.parse(startDate))
                .endDate(LocalDateUtils.parse(endDate))
                .build();
    }

}
