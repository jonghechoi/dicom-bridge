package com.example.dicombridge.util;

import com.example.dicombridge.domain.dto.thumbnail.ThumbnailDto;
import com.example.dicombridge.domain.dto.thumbnail.ThumbnailWithFileDto;
import com.example.dicombridge.domain.image.Image;
import com.example.dicombridge.domain.PathAndName;
import lombok.NoArgsConstructor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

@NoArgsConstructor(force = true)
public class FileRead<T> {
    private final ImageConvert imageConvert;
    public FileRead(ImageConvert imageConvert) {
        this.imageConvert = imageConvert;
    }
    public <T extends PathAndName> File getFile(List<T> image, int idx) throws IOException {
        FileInputStream fileInputStream = imageConvert.getFileInputStream(image.get(idx));
        byte[] byteArray = imageConvert.convert2ByteArray(fileInputStream);
        File tempDcmFile = imageConvert.convert2DcmFile(byteArray);
        return tempDcmFile;
    }
    public Callable<ThumbnailWithFileDto> getFileStringThread(ThumbnailDto thumbnailDto) {
        Callable<ThumbnailWithFileDto> task = () -> {
            ThumbnailWithFileDto thumbnailWithFileDto = new ThumbnailWithFileDto(thumbnailDto);
            FileInputStream fileInputStream = imageConvert.getFileInputStream(thumbnailDto);
            byte[] byteArray = imageConvert.convert2ByteArray(fileInputStream);
            File dcmFile = imageConvert.convert2DcmFile(byteArray);
            String imgString = imageConvert.convertDcm2Jpg(dcmFile);

            thumbnailWithFileDto.setImage(imgString);
            return thumbnailWithFileDto;
        };
        return task;
    }
    public <T extends PathAndName> ByteArrayOutputStream getBaos(T t) throws IOException {
        FileInputStream fileInputStream = imageConvert.getFileInputStream(t);
        ByteArrayOutputStream byteArrayOutputStream = imageConvert.convert2ByteArrayOutputStream(fileInputStream);
        return byteArrayOutputStream;
    }
}