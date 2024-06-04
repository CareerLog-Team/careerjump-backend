package org.careerjump.server.goal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.careerjump.server.goal.domain.Goal;

@Data
@AllArgsConstructor
public class GoalRequestDto {
    private String companyName;
    private Integer salary;
    private String industry;
    private String job;

    public Goal toEntity() {
        return Goal.builder()
                .companyName(companyName)
                .salary(salary)
                .industry(industry)
                .job(job)
                .build();
    }
}
