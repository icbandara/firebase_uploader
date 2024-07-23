package com.controlunion.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

public interface FirebaseUploadService {
    String upload(MultipartFile multipartFile, String proName, String apikey, String firebaseURL);

    byte[] downloadFile(String fileName, String firebaseURL);
}
