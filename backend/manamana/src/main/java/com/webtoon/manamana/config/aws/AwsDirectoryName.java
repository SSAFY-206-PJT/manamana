package com.webtoon.manamana.config.aws;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//AWS S3 디렉토리 이름.

@Getter
@Component
public class AwsDirectoryName {

    @Value("${cloud.aws.s3.directory.profile}")
    private String profileImage;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

}
