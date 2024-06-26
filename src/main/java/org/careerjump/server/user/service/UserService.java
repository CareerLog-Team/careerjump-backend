package org.careerjump.server.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String userId) {
        log.info("사용자를 userId를 기반으로 조회합니다.");
        log.debug("userId = {}", userId);

        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("NOT FOUND USER EXCEPTION / from : UserService.getUserById"));

        log.debug("사용자 정보 : {}", user.toString());
        return user;
    }

    public void addUser(User user) {
        log.info("사용자를 추가합니다.");
        userRepository.save(user);

        CareerBoard careerBoard = new CareerBoard();
        Goal goal = new Goal();

        User findUser = getUserById(user.getUserId());

        log.info("이력 관리 보드 & 목표를 유저에 적용합니다.");
        findUser.updateCareerBoard(careerBoard);
        findUser.updateGoal(goal);
    }

    public void removeUser(User user) {
        log.info("사용자를 삭제합니다 : {}", user.getEmail());
        log.debug("Remove User : {}", user);

        userRepository.delete(user);
    }
}
