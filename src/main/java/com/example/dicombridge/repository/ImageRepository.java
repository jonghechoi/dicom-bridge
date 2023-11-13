package com.example.dicombridge.repository;

import com.example.dicombridge.domain.image.Image;
import com.example.dicombridge.domain.image.ImageId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, ImageId> {
    List<Image> findByImageIdStudykey(int studykey);
    List<Image> findByseriesinsuid(String seriesinsuid);//studyinsuid로 imagetab 조회

    int countByseriesinsuid(String seriesinsuid); // seriesinsuid로 갯수 확인



}
