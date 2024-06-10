package org.careerjump.server.goal.domain;

import jakarta.persistence.*;
import lombok.*;
import org.careerjump.server.common.entity.BaseTimeEntity;
import org.careerjump.server.user.domain.User;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Goal extends BaseTimeEntity {
    @Id
    @Column(name = "GOAL_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String goalId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "SALARY")
    private Integer salary;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "JOB")
    private String job;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void updateGoal(Goal updateGoal) {
        this.companyName = updateGoal.getCompanyName();
        this.salary = updateGoal.getSalary();
        this.industry = updateGoal.getIndustry();
        this.job = updateGoal.getJob();
    }
}
