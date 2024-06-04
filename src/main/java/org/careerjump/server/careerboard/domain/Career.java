package org.careerjump.server.careerboard.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String careerId;

    private String companyName;
    private String job;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private boolean isWorking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAREER_BOARD_ID")
    private CareerBoard careerBoard;

}
