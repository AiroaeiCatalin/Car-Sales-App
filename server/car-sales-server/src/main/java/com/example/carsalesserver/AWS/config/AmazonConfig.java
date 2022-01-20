package com.example.carsalesserver.AWS.config;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.example.carsalesserver.AWS.utils.AmazonCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(AmazonCredentials.AMAZON_ACCESS_KEY,
                AmazonCredentials.AMAZON_SECRET_KEY);

        return AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(

                        "https://s3.eu-central-1.amazonaws.com",

                        "eu-central-1"))
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }

}
