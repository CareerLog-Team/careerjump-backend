package org.careerjump.server.careerboard.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.careerjump.server.common.entity.BaseTimeEntity;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Career extends BaseTimeEntity {

    @Id
    @Column(name = "CAREER_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String careerId;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "JOB")
    private String job;

    @Column(name = "START_DATE")
    private LocalDate startDate;

    @Column(name = "END_DATE")
    private LocalDate endDate;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "IS_WORKING")
    private boolean isWorking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAREER_BOARD_ID")
    private CareerBoard careerBoard;

}
