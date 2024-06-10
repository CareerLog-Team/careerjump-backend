package org.careerjump.server.file;

import org.careerjump.server.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<File, String> {

    List<File> findFilesByUser(User user);
    Optional<File> findFileByFileId(String fileId);

}
