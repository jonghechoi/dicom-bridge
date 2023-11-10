package com.example.dicombridge.service.image;

import com.example.dicombridge.domain.study.Study;
import com.example.dicombridge.domain.study.StudyResponseDto;
import com.example.dicombridge.repository.StudyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudyService {
    private final StudyRepository studyRepository;

//    public StudyService(StudyRepository studyRepository) { this.studyRepository = studyRepository; }

    public List<StudyResponseDto> getStudies() {
        List<Study> studyList = studyRepository.findAll();
        return studyList.stream()
                        .map(StudyResponseDto::new)
                        .collect(Collectors.toList());
    }
}
