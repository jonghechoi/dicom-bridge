package com.example.dicombridge.service.image;

import com.example.dicombridge.domain.study.Study;
import com.example.dicombridge.domain.dto.study.StudyResponseDto;
import com.example.dicombridge.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

    public List<StudyResponseDto> getStudies() {
        List<Study> studyList = studyRepository.findAllByOrderByStudyDate();
        return studyList.stream()
                        .map(StudyResponseDto::new)
                        .collect(Collectors.toList());
    }

    public List<Integer> getReportStatusByStudyKey(int studykey) {
        List<Study> studies = studyRepository.findByStudykey(studykey);
        return studies.stream().map(Study::getReportstatus).collect(Collectors.toList());
    }

    public List<StudyResponseDto> getStudiesByStudyKey(int studykey) {
        List<Study> studyList = studyRepository.findByStudykey(studykey); // studykey에 해당하는 studies를 가져오는 로직 추가
        System.out.println("studyList : "+studyList);
        return studyList.stream()
                .map(StudyResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<StudyResponseDto> getSearch(String pid, String pname, Integer reportstatus) {
        List<Study> studyList = studyRepository.findByPidAndPnameAndReportstatus(pid, pname, reportstatus);
        return studyList.stream()
                .map(StudyResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<StudyResponseDto> getComparison(String modality){
        List<Study> comparisonStudyList = studyRepository.findByModality(modality);
        return comparisonStudyList.stream()
                .map(StudyResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<String> getSameModalStudyinsuid(String modality){ //같은 modal의 studyinsuid 종류
        return studyRepository.findDistinctStudyinsuidByModality(modality);
    }
}
