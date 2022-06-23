package com.springbootproject.project.controller;

import com.springbootproject.project.ResponseData;
import com.springbootproject.project.entity.Attachment;
import com.springbootproject.project.service.AttachmentService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AttachmentController {
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }


    private AttachmentService attachmentService;

    @PostMapping("/upload")
    public ResponseData uploadFile(@RequestParam("file")MultipartFile file) throws Exception {
        Attachment attachment=null;
        String downloadUrl="";
        attachment=attachmentService.saveAttachment(file);
        downloadUrl= ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/download/")
                .path(attachment.getId())
                .toUriString();

        return new ResponseData(attachment.getFilename(),
                downloadUrl,
                file.getContentType(),
                file.getSize());
    }

    @GetMapping("/download/{fieldId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String field) throws Exception {
        Attachment attachment=null;
        attachment=attachmentService.getAttachment(field);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getFiletype()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" +attachment.getFilename()
                +"\"")
                .body(new ByteArrayResource(attachment.getData()));
    }

}
