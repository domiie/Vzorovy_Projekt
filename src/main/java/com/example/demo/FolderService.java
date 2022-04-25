package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService {

    private FolderRepository folderRepository;

    public FolderService(FolderRepository folderRepository){
        this.folderRepository = folderRepository;
    }

    public FolderDto mapToFolderDto(FolderEntity folderEntity){
        FolderDto folderDTO = new FolderDto();
        folderDTO.setFolderName(folderEntity.getFolderName());
        folderDTO.setId(folderEntity.getFolderId());
        return folderDTO;
    }

    @Transactional
    public FolderDto getFolderById(Long id){
        Optional<FolderEntity> byId = folderRepository.findById(id);
        if(byId.isPresent()){
            return mapToFolderDto(byId.get());
        }
        return null;
    }

    @Transactional
    public Long createFolder(FolderDto folderDto){
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setFolderName(folderDto.getFolderName());
        this.folderRepository.save(folderEntity);
        return folderEntity.getFolderId();
    }

    @Transactional
    public List<FolderDto> getFolders(){
        List<FolderDto> folders = new LinkedList<>();
        for(FolderEntity f1 : folderRepository.findAll()){
            FolderDto f2 = mapToFolderDto(f1);
            folders.add(f2);
        }
        return folders;
    }

    @Transactional
    public String deleteFolderById(Long id){
        Optional<FolderEntity> folder = folderRepository.findById(id);
        if(folder.isPresent()){
            folderRepository.delete(folder.get());
            return "Folder " +id+ " was deleted";
        }
        return "Folder doesn't exist";
    }
}
