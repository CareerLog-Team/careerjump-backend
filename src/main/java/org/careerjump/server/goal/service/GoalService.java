package org.careerjump.server.goal.service;


import lombok.RequiredArgsConstructor;
import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.goal.repository.GoalRepository;
import org.careerjump.server.user.domain.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;

    @Transactional(readOnly = true)
    public Goal getGoalByUser(User user) {
        return goalRepository.findGoalByUser(user)
                .orElseThrow(() -> new RuntimeException("Goal이 없습니다."));
    }

    public void updateGoal(User user, Goal updateGoal) {
        Goal goal = getGoalByUser(user);
        goal.updateGoal(updateGoal);
    }
}
