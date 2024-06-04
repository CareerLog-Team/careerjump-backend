package org.careerjump.server.goal.service;

import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.user.service.UserService;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.utils.UserUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
@Rollback(value = false)
class GoalServiceTest {

    @Autowired
    UserService userService;


    @Test
    void Goal_등록() {
        User user = UserUtils.getUser("test@test.com", "kakao", "test", "test", "test.com");
        userService.addUser(user);

        User findUser = userService.getUserById(user.getUserId());
        Goal goal = Goal.builder()
                .companyName("company")
                .salary(3000)
                .industry("industry")
                .job("programmer")
                .build();

        findUser.updateGoal(goal);
    }
}