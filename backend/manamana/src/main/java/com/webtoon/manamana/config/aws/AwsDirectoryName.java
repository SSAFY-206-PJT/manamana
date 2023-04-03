package com.webtoon.manamana.config.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//AWS S3 디렉토리 이름.

@Component
public class AwsDirectoryName {

    public static String PROFILE_IMAGE;

    @Value("${cloud.aws.s3.directory.profile}")
    public void setProfileImage(String profileImage){
        PROFILE_IMAGE = profileImage;
    }
}
