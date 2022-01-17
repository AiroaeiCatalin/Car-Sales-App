package com.example.carsalesserver.AWS.bucket;

public enum BucketName {
    PROFILE_IMAGE("car-sales-images-handler-4769");
    private final String bucketName;


    BucketName(String bucketName){
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}

