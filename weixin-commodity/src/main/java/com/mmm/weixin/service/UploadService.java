package com.mmm.weixin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    String uploadImage(MultipartFile file);
}
