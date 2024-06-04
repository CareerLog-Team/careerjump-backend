package org.careerjump.server.goal;

import lombok.RequiredArgsConstructor;
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
        goalService.updateGoal(user, updateGoal);
    }
}
