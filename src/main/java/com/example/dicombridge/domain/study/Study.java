package com.example.dicombridge.domain.study;

import com.example.dicombridge.domain.dto.study.StudyRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "STUDYTAB")
public class Study {
    @Id
    private Integer studykey;
    private String studyinsuid;
    private String studydate;
    private String studytime;
    private String studyid;
    private String studydesc;
    private String modality;
    private String pid;
    private String pname;
    private String psex;
    private String pbirthdatetime;
    private String patage;
    private Integer patientkey;
    private Integer seriescnt;
    private Integer imagecnt;
    private Integer del_flag;
    private Integer reportstatus;
    private Integer verifyflag;
//    private String accessnum;
//    private String bodypart;
//    private Integer nonseriescount;
//    private Integer nonimagecount;
//    private Integer examstatus;
//    private String dept;
//    private String refphysicianname;
//    private String operatorsname;
//    private String insname;
//    private String stationname;
//    private String protocolname;
//    private String viewposition;
//    private String laterality;
//    private String insertdate;
//    private String inserttime;
//    private Integer filesize;
//    private Integer inserted;
//    private Integer updated;
//    private String ai_company;
//    private String ai_model_name;
//    private Long ai_score;
//    private Integer ai_priority;
//    private Integer ai_number_of_findings;
//    private String ai_abnormal_yn;
//    private String ai_finding;
//    private Integer delflag;
}
