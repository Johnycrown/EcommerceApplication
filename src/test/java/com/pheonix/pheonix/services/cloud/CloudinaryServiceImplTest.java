package com.pheonix.pheonix.services.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
class CloudinaryServiceImplTest {

    @Autowired
     CloudinaryService cloudinaryService;
    @Autowired
    Cloudinary cloudinary;
    @BeforeEach
    void setUp(){

    }
    @Test
    void testCloudinary(){
        assertThat(cloudinary).isNotNull();

    }

    @Test
    void UploadToCloudinaryTest() throws IOException {

        Path file =  Paths.get("src/test/resources/2022-01-19-112257.jpg");
        assertThat(file.toFile().exists()).isTrue();
     Map<?,?> uploadResult =  cloudinaryService.upload(Files.readAllBytes(file), ObjectUtils.emptyMap());
     log.info("upload result to cloud ->{}", uploadResult);
     assertThat(uploadResult.get("url")).isNotNull();

    }
    @Test
    void uploadMultipartToCloudinary() throws IOException{
        // load the file
        Path path = Paths.get("src/test/resources/2022-01-19-112257.jpg");// get file location
        assertThat(path.toFile().exists());
        assertThat(path.getFileName().toString()).isEqualTo("2022-01-19-112257.jpg");
        //load multipart
        MultipartFile multipartFile = new MockMultipartFile(path.getFileName().toString(),path.toString().toString(),"img/jpg", Files.readAllBytes(path));
        assertThat(multipartFile).isNotNull();
        assertThat(multipartFile.isEmpty()).isFalse();

        //upload to cloud
        Map <?,?> uploadResult = cloudinaryService.upload(multipartFile.getBytes(), ObjectUtils.asMap("overwrite", true));
      assertThat(uploadResult.get("url")).isNotNull();
    }



}