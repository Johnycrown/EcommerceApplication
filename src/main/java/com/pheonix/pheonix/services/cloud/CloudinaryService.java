package com.pheonix.pheonix.services.cloud;

import java.io.File;
import java.util.Map;

public interface CloudinaryService {
    Map<?,?> upload(File file, Map<?,?> paramd);
}
