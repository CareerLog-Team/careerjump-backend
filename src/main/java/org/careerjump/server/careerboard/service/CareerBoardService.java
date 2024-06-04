package org.careerjump.server.careerboard.service;


import lombok.RequiredArgsConstructor;
import org.careerjump.server.careerboard.domain.Career;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.careerboard.domain.Education;
import org.careerjump.server.careerboard.repository.CareerBoardRepository;
import org.careerjump.server.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CareerBoardService {

    private final CareerBoardRepository careerBoardRepository;

    @Transactional(readOnly = true)
    public CareerBoard getCareerBoardByUser(User user) {
        return careerBoardRepository.findCareerBoardByUser(user)
                .orElseThrow(() -> new RuntimeException("Not found careerboard by user"));
    }

    public void addCareer(CareerBoard careerBoard, Career career) {
        careerBoard.addCareer(career);
    }

    public void addEducation(CareerBoard careerBoard, Education education) {
        careerBoard.addEducation(education);
    }

    public void addStrings(CareerBoard careerBoard, List<String> strings, String type) {
        switch (type) {
            case "skill" -> careerBoard.updateSkills(strings);
            case "link" -> careerBoard.updateLinks(strings);
            case "strength" -> {
                checkStrengthsSize(strings);
                careerBoard.updateStrengths(strings);
            }
        }

        throw new RuntimeException("type을 잘못 작성했습니다.");
    }

    private void checkStrengthsSize(List<String> strengths) {
        if (strengths.isEmpty() || strengths.size() > 5) {
            throw new RuntimeException("선택된 강점의 개수를 확인하세요. 강점은 1 ~ 5개 선택해야합니다.");
        }
    }
}
