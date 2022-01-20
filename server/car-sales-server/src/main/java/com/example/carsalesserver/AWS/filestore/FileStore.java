package com.example.carsalesserver.AWS.filestore;


import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Map;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FileStore {

    private final AmazonS3 s3;

//    @Autowired
//    public FileStore(AmazonS3 s3){
//        this.s3 = s3;
//    }





//aici sa mai intrb una alta
    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {

        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if(!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });

        try {
            s3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }

//    public void save2(){
//        String content = new String("cracra");
//        s3.putObject("car-sales-images-handler-4769", "my-other-key", content);
//    }

}
