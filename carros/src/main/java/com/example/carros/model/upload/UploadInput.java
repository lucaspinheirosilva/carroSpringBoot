package com.example.carros.model.upload;

import lombok.Data;

@Data
public class UploadInput {
    private String fileName;
    private String mimeType;
    private String base64;
}
