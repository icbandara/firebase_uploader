package com.controlunion.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author : Isuru Bandara <icbandara@controlunion.com>
 * @since : 01/11/2023
 **/

@Configuration
public class FirebaseConfig {

    @Bean
    public Storage storage() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("src/main/resources/test-project-dc8cd-firebase-adminsdk-2u2s9-467cf2b12c.json");

        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);

        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
