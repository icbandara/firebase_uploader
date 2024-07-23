package com.controlunion.service.impl;

import com.controlunion.service.FirebaseUploadService;
import com.google.auth.Credentials;
import com.google.auth.ServiceAccountSigner;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

@Service
public class FirebaseUploadServiceImpl implements FirebaseUploadService {

    @Autowired
    private Storage storage;

    @Override
    @SneakyThrows
    public String upload(MultipartFile multipartFile, String proName, String apikey, String firebaseURL) {

        String blobName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        Bucket bucket = storage.get(firebaseURL);

        BlobInfo blobInfo = BlobInfo.newBuilder(bucket.getName(), blobName).build();
        System.out.println(blobInfo);
        var saved = bucket.create(blobName, multipartFile.getInputStream(), multipartFile.getContentType());
        System.out.println(saved);
        ClassPathResource json = new ClassPathResource("test-project-dc8cd-firebase-adminsdk-2u2s9-467cf2b12c.json");
        System.out.println(saved.getMediaLink());
        System.out.println(saved.getSelfLink());
        Credentials credentials = GoogleCredentials.fromStream(json.getInputStream());
        System.out.println();
        String fileUrl = String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), saved.getName());
        saved.getMediaLink();
        System.out.println(saved.asBlobInfo());;
        return storage.get(saved.getBlobId()).signUrl(36500, TimeUnit.DAYS, Storage.SignUrlOption.signWith((ServiceAccountSigner) credentials)).toString();
    }

    @Override
    @SneakyThrows
    public byte[] downloadFile(String fileName, String firebaseURL) {
        Bucket bucket = storage.get(firebaseURL);
        var blob = bucket.get(fileName);

        if (blob == null) {
            throw new IOException("No such file in storage: " + fileName);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        blob.downloadTo(outputStream);
        System.out.println(blob.getMediaLink());
        return outputStream.toByteArray();
    }


}
