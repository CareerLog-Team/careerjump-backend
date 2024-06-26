package org.careerjump.server.careerboard.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.careerjump.server.common.entity.BaseTimeEntity;
import org.careerjump.server.user.domain.User;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CareerBoard extends BaseTimeEntity {

    @Id
    @Column(name = "CAREER_BOARD_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String careerBoardId;

    @OneToMany(
            mappedBy = "careerBoard",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<Career> careerList = new ArrayList<>();

    @OneToMany(
            mappedBy = "careerBoard",
            cascade = CascadeType.PERSIST,
            orphanRemoval = true
    )
    private List<Education> educationList = new ArrayList<>();

    @Column(name = "SKILLS")
    private String skills;
    @Column(name = "LINKS")
    private String links;
    @Column(name = "STRENGTHS")
    private String strengths;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void addCareer(Career career) {
        this.careerList.add(career);
    }

    public void addEducation(Education education) {
        this.educationList.add(education);
    }

    public List<String> getSkills() {
        return splitString(skills);
    }
    public List<String> getLinks() {
        return splitString(links);
    }
    public List<String> getStrengths() {
        return splitString(strengths);
    }

    public void updateSkills(List<String> skills) {
        this.skills = joinString(skills);
    }
    public void updateLinks(List<String> links) {
        this.links = joinString(links);
    }
    public void updateStrengths(List<String> strengths) {
        this.strengths = joinString(strengths);
    }


    private String joinString(List<String> strings) {
        return String.join(",", strings);
    }
    private List<String> splitString(String joinedString) {
        if (!joinedString.isEmpty()) {
            return List.of(joinedString.split(","));
        }
        return null;
    }
}
