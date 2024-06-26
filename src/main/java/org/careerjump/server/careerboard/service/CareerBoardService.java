package org.careerjump.server.careerboard.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.careerjump.server.careerboard.domain.Career;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.careerboard.domain.Education;
import org.careerjump.server.careerboard.repository.CareerBoardRepository;
import org.careerjump.server.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CareerBoardService {

    private final CareerBoardRepository careerBoardRepository;

    @Transactional(readOnly = true)
    public CareerBoard getCareerBoardByUser(User user) {
        log.info("사용자 정보로 CareerBoard를 조회합니다.");

        return careerBoardRepository.findCareerBoardByUser(user)
                .orElseThrow(() -> new RuntimeException("Not found careerboard by user"));
    }

    public void addCareer(CareerBoard careerBoard, Career career) {
        log.debug("""
                CareerBoardId : {}
                Career : {}""", careerBoard.getCareerBoardId(), career.toString());
        careerBoard.addCareer(career);
    }

    public void addEducation(CareerBoard careerBoard, Education education) {
        log.debug("""
                CareerBoardId : {}
                Education : {}""", careerBoard.getCareerBoardId(), education.toString());
        careerBoard.addEducation(education);
    }

    public void addStrings(CareerBoard careerBoard, List<String> strings, String type) {
        log.debug("""
                CareerBoardId : {}
                addStrings Type : {}
                Strings : {}
                """, careerBoard.getCareerBoardId(), type, strings);

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
        int strengthSize = strengths.size();
        log.debug("strengths size : {}", strengthSize);

        if (strengthSize < 5 || 10 < strengthSize) {
            throw new RuntimeException("선택된 강점의 개수를 확인하세요. 강점은 5 ~ 10개 선택해야합니다.");
        }
    }
}
