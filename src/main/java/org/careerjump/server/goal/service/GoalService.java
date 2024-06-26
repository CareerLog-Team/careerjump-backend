package org.careerjump.server.goal.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.goal.repository.GoalRepository;
import org.careerjump.server.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    @Transactional(readOnly = true)
    public Goal getGoalByUser(User user) {
        log.info("User를 기반으로 Goal 정보를 조회합니다.");

        Goal goal = goalRepository.findGoalByUser(user)
                .orElseThrow(() -> new RuntimeException("Goal이 없습니다."));

        log.debug("Goal : {}", goal.toString());
        return goal;
    }

    public void updateGoal(User user, Goal updateGoal) {
        Goal goal = getGoalByUser(user);
        goal.updateGoal(updateGoal);
    }
}
