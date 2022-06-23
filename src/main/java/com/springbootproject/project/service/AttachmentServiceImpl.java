package com.springbootproject.project.service;

import com.springbootproject.project.entity.Attachment;
import com.springbootproject.project.repository.AttachmentRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AttachmentServiceImpl implements AttachmentService{

    private AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment saveAttachment(MultipartFile file) throws Exception {

        String filename= StringUtils.cleanPath(file.getOriginalFilename());

        try{
            if(filename.contains("..")){
                throw new Exception("Filename contains invalid path " +filename);
            }
            Attachment attachment=new Attachment(filename,file.getContentType(),file.getBytes());
            return attachmentRepository.save(attachment);

        }catch (Exception e){

            throw new Exception("Could not save File" +filename);
        }
//        return null;
    }

    @Override
    public Attachment getAttachment(String fileId) throws Exception{

        return attachmentRepository
                .findById(fileId)
                .orElseThrow(
                        ()->new Exception("File not found" + fileId));
    }
}
