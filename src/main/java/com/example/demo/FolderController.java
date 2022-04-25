package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FolderController {

    FolderService folderService;

    public FolderController(FolderService folderService){
        this.folderService = folderService;
    }


    @GetMapping("/api/folders")
    public List<FolderDto> getAllFolder(){
        return folderService.getFolders();
    }

    @GetMapping("/api/folders/{folderId}")
    public List<BookDto> getBooksByFolderId(Long folderId){
        return null;
    }

    @PostMapping("/api/folders")
    public Long createFolder(@RequestBody FolderDto folderDto){
        return folderService.createFolder(folderDto);
    }

    @DeleteMapping("/api/folders/{folderId}")
    public String deleteFolder(@PathVariable Long folderId){
        return folderService.deleteFolderById(folderId);
    }

}
