package com.mfq.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface OssService {
    String upLoadFileAvatar(MultipartFile file) throws IOException;
}
