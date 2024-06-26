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
public class Education extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String educationId;

    private String educationName;
    private String major;
    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAREER_BOARD_ID")
    private CareerBoard careerBoard;

    @Override
    public String toString() {
        return "Education{" +
                "educationId='" + educationId + '\'' +
                ", educationName='" + educationName + '\'' +
                ", major='" + major + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
