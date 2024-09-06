package com.harolrodriguez.FutecaManagger.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class CloudinaryConfig {

    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dakf5lkas",
            "api_key", "657231367291166",
            "api_secret", "4Uo_3TCggg1uIzJc_jjmJJTZzCk"
        ));
    }
}
