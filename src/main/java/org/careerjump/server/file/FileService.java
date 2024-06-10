package org.careerjump.server.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FileService {
    private final FileRepository fileRepository;

    public void addFile(File file) {
        fileRepository.save(file);
    }

    public void removeFile(File file) {
        fileRepository.delete(file);
    }
}
