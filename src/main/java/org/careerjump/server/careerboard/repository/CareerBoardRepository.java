package org.careerjump.server.careerboard.repository;

import org.careerjump.server.careerboard.domain.CareerBoard;
import org.careerjump.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerBoardRepository extends JpaRepository<CareerBoard, String> {

    Optional<CareerBoard> findCareerBoardByUser(User user);

}
