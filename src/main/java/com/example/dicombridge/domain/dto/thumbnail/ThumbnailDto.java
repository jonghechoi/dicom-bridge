package com.example.dicombridge.domain.dto.thumbnail;

import com.example.dicombridge.domain.PathAndName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
public class ThumbnailDto implements PathAndName {
    private Integer imagekey;
    private Integer serieskey;
    private String studyinsuid;
    private String seriesinsuid;
    private String sopinstanceuid;
    private String sopclassuid;
    private String path;
    private String fname;
    private Integer delflag;
    private String seriesdesc;

    public ThumbnailDto(Integer imagekey, Integer serieskey, String studyinsuid, String seriesinsuid, String sopinstanceuid,
                        String sopclassuid, String path, String fname, Integer delflag, String seriesdesc) {
        this.imagekey = imagekey;
        this.serieskey = serieskey;
        this.studyinsuid = studyinsuid;
        this.seriesinsuid = seriesinsuid;
        this.sopinstanceuid = sopinstanceuid;
        this.sopclassuid = sopclassuid;
        this.path = path;
        this.fname = fname;
        this.delflag = delflag;
        this.seriesdesc = seriesdesc;
    }
}
