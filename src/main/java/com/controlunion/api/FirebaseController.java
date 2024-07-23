package com.controlunion.api;

import com.controlunion.service.FirebaseUploadService;
import com.controlunion.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/
@RestController
@RequestMapping("api/v1/firebase")
public class FirebaseController {

    @Autowired
    private FirebaseUploadService firebaseUploadService;

    @PostMapping("/upload")
    public ResponseEntity<StandardResponse> upload(@RequestParam("file") MultipartFile multipartFile,
                                                   @RequestParam String proName,
                                                   @RequestParam String firebaseURL,
                                                   @RequestParam String apiKey) {
        return new ResponseEntity<>(
                new StandardResponse("Success", firebaseUploadService.upload(multipartFile, proName, apiKey, firebaseURL)),
                HttpStatus.OK
        );
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam String filename,
                           @RequestParam String firebaseURL) {
        return firebaseUploadService.downloadFile(filename, firebaseURL);
    }

}
