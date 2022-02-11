package com.pheonix.pheonix.services.cloud;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
@Service
public class CloudinaryServiceImpl implements CloudinaryService {
    @Autowired
    Cloudinary cloudinary;

    @Override
    public Map<?, ?> upload(File file, Map<?, ?> params) throws IOException {

        return cloudinary.uploader().upload(file,params);


    }
}
