package org.careerjump.server.user.service;

import lombok.RequiredArgsConstructor;
import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.goal.domain.Goal;
import org.careerjump.server.user.domain.User;
import org.careerjump.server.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("NOT FOUND USER EXCEPTION / from : UserService.getUserById"));
    }

    public void addUser(User user) {
        userRepository.save(user);

        CareerBoard careerBoard =  new CareerBoard();
        Goal goal = new Goal();

        User findUser = getUserById(user.getUserId());
        findUser.updateCareerBoard(careerBoard);
        findUser.updateGoal(goal);

    }

    public void removeUser(User user) {
        userRepository.delete(user);
    }
}
