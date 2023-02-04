package com.worksafe.backend.file.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Configuration
public class FileConfiguration {


    @Bean
    public Storage getStorageBean() {
      return StorageOptions.getDefaultInstance().getService();
    }


}
