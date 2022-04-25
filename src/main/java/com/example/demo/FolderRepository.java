package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FolderRepository extends CrudRepository<FolderEntity, Long> {
    List<FolderEntity> findByFolderName(String folderName);

    @Override
    Optional<FolderEntity> findById(Long folderId);
}
