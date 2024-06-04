package org.careerjump.server.user.service;

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
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void 유저_생성테스트() {
        //given
        User user = UserUtils.getUser(
                "hello@test.com",
                "kakao",
                "hello",
                "world",
                "helloworld"
        );

        userService.addUser(user);
    }

}