package org.careerjump.server.careerboard;


import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.careerjump.server.careerboard.domain.Career;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.careerboard.domain.Education;
import org.careerjump.server.careerboard.dto.*;
import org.careerjump.server.careerboard.service.CareerBoardService;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.user.service.UserService;
import org.careerjump.server.utils.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Timed("careerjump.careerboard")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/careerboard")
public class CareerBoardController {

    private final UserService userService;
    private final CareerBoardService careerBoardService;

    @PostMapping("/add/career")
    public void addCareer(@RequestBody AddCareerDto addCareerDto) {
        CareerBoard careerBoard = getCareerBoard();

        List<CareerDto> careerDtos = addCareerDto.getCareerDtoList();
        List<Career> careers = careerDtos.stream()
                .map(CareerDto::toEntity)
                .toList();

        for (Career career : careers) {
            careerBoardService.addCareer(careerBoard, career);
        }
    }

    @PostMapping("/add/education")
    public void addEducation(@RequestBody AddEducationDto addEducationDto) {
        CareerBoard careerBoard = getCareerBoard();

        List<EducationDto> educationDtos = addEducationDto.getEducationDtoList();
        List<Education> educations = educationDtos.stream()
                .map(EducationDto::toEntity)
                .toList();

        for (Education education : educations) {
            careerBoardService.addEducation(careerBoard, education);
        }
    }

    @PostMapping("/add/skill")
    public void addSkill(@RequestBody AddSkillDto addSkillDto) {
        CareerBoard careerBoard = getCareerBoard();
        List<String> skills = addSkillDto.getSkillList();
        careerBoardService.addStrings(careerBoard, skills, "skill");
    }

    @PostMapping("/add/link")
    public void addLink(@RequestBody AddLinkDto addLinkDto) {
        CareerBoard careerBoard = getCareerBoard();
        List<String> links = addLinkDto.getLinkList();
        careerBoardService.addStrings(careerBoard, links, "link");
    }


    @PostMapping("/add/strength")
    public void addStrength(@RequestBody AddStrengthDto addStrengthDto) {
        CareerBoard careerBoard = getCareerBoard();
        List<String> strengths = addStrengthDto.getStrengthList();
        careerBoardService.addStrings(careerBoard, strengths, "strength");
    }

    // =========================================================================================
    private CareerBoard getCareerBoard() {
        String userId = SecurityUtils.getCurrentUserId();
        User user = userService.getUserById(userId);

        return careerBoardService.getCareerBoardByUser(user);
    }
}
