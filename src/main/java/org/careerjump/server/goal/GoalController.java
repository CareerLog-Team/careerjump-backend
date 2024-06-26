package org.careerjump.server.goal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.goal.dto.GoalRequestDto;
import org.careerjump.server.goal.service.GoalService;
import org.careerjump.server.user.service.UserService;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.utils.SecurityUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goal")
public class GoalController {

    private final GoalService goalService;
    private final UserService userService;

    @PostMapping
    public void setGoal(@RequestBody GoalRequestDto goalRequestDto) {
        String userId = SecurityUtils.getCurrentUserId();
        User user = userService.getUserById(userId);
        Goal updateGoal = goalRequestDto.toEntity();

        log.info("사용자의 이직 목표를 설정합니다.");
        log.debug("""
                userId : {}
                Goal : {}
                """, userId, updateGoal.toString());

        goalService.updateGoal(user, updateGoal);
    }
}
