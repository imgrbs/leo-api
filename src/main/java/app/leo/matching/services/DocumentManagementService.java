package app.leo.matching.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class DocumentManagementService {

    @Autowired
    private AmazonS3 amazonS3Client;

    @Value("${app.awsServices.bucketName}")
    private String bucketName;

    private Logger logger;

    public List<String> uploadMultipleFiles(List<MultipartFile> files) {
        List<String> nameList = new ArrayList<>();
        if (files != null) {
            files.forEach(multipartFile -> {
                File file = convertMultiPartFileToFile(multipartFile);
                String uniqueFileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
                uploadFileToS3bucket(uniqueFileName, file, bucketName);
                nameList.add(uniqueFileName);
            });
        }
        return nameList;
    }

    private void uploadFileToS3bucket(String fileName, File file, String bucketName) {
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, file));

    }

    public S3ObjectInputStream getObjectInputStream(String fileName){
        S3Object object = amazonS3Client.getObject(this.bucketName,fileName);
        return object.getObjectContent();
    }

    private void deleteFileFromS3bucket(String fileName, String bucketName) {
        amazonS3Client.deleteObject(bucketName, fileName);
    }

    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            logger.warn("Error converting multipartFile to file", e);
        }
        return convertedFile;
    }
}
