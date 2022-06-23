package com.springbootproject.project.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
@Entity
@NoArgsConstructor
public class Attachment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    private String filename;
    private String filetype;
    @Lob
    private byte[] data;


    public Attachment(String id, String filename, String filetype, byte[] data) {
        this.id = id;
        this.filename = filename;
        this.filetype = filetype;
        this.data = data;
    }

    public Attachment(String filename, String contentType, byte[] bytes) {
    }
}
