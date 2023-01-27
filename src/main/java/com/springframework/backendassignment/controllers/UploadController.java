package com.springframework.backendassignment.controllers;

import com.springframework.backendassignment.helpers.CSVHelper;
import com.springframework.backendassignment.services.CSVService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/csv")
public class UploadController {
    CSVService csvService;

    public UploadController(CSVService csvService) {
        this.csvService = csvService;
    }

    @PostMapping("upload")
    public String uploadFile(@RequestParam MultipartFile file){
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                csvService.save(file);
                return "Uploaded the file successfully: ";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "Error: File Not Uploaded";
    }

}
