package com.springbootproject.project.service;

import com.springbootproject.project.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment saveAttachment(MultipartFile file) throws Exception;

    Attachment getAttachment(String field) throws Exception;
}
