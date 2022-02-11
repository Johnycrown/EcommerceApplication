package com.pheonix.pheonix.services.cloud;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


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

        File file = new File("src/test/resources/2022-01-19-112257.jpg");
        assertThat(file).isNotNull();
     Map<?,?> uploadResult =  cloudinaryService.upload(file, ObjectUtils.emptyMap());

    }



}