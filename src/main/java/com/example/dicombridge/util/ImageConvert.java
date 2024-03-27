package com.example.dicombridge.util;

import com.example.dicombridge.domain.PathAndName;
import jcifs.CIFSException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import org.dcm4che3.tool.dcm2jpg.Dcm2Jpg;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ImageConvert<T> {
    private final Storage storage;

    private final String newStorage = "C:/dev/backend_deep_course/dicom/storage";

    /** CIFS Approach **/
//    public <T extends PathAndName> SmbFileInputStream getSmbFileInputStream(T t) throws MalformedURLException, SmbException {
//        SmbFile file = new SmbFile(String.join("/",
//                            storage.getPROTOCOL(),
//                            storage.getHOST(),
//                            storage.getSHARED_NAME(),
//                            t.getPath().replace('\\', '/') + "/" + t.getFname()),
//                            storage.getCifsContext());
//        return new SmbFileInputStream(file);
//    }

    /** Local Approach **/
    public <T extends PathAndName> FileInputStream getFileInputStream(T t) throws CIFSException, FileNotFoundException {
        FileInputStream file = new FileInputStream(String.join("/",
                newStorage,
                t.getPath().replace('\\', '/'),
                t.getFname()));
        return file;
    }

    public byte[] convert2ByteArray(FileInputStream fileInputStream) {
        byte[] buffer = new byte[1024 * 1024];
        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File convert2DcmFile(byte[] fileBytes) throws IOException {
        File tempFile = File.createTempFile("tempfile", ".dcm");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(fileBytes);
        } catch (Exception e) {
            System.err.println(e);
        }
        return tempFile;
    }

    public String convertDcm2Jpg(File file) {
        try {
            Dcm2Jpg dcm2Jpg = new Dcm2Jpg();
            BufferedImage image = dcm2Jpg.readImageFromDicomInputStream(file);

            if (image != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                byte[] imageBytes = baos.toByteArray();
                return Base64.getEncoder().encodeToString(imageBytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayOutputStream convert2ByteArrayOutputStream(FileInputStream fileInputStream) {
        ByteArrayOutputStream byteArrayOutputStream;
        byte[] buffer = new byte[1024 * 1024];
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            fileInputStream.close();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream;
    }
}