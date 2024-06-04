package org.careerjump.server.goal.repository;


import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoalRepository extends JpaRepository<Goal, String> {

    Optional<Goal> findGoalByUser(User user);

}
