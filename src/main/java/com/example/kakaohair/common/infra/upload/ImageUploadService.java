package com.example.kakaohair.common.infra.upload;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.kakaohair.shop.domain.Photo;

public interface ImageUploadService {

    List<Photo> upload(List<MultipartFile> multipartFiles);
}
