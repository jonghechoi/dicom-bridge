package com.example.dicombridge.controller.image;


import com.example.dicombridge.domain.common.ThumbnailDto;
import com.example.dicombridge.domain.common.ThumbnailWithFileDto;
import com.example.dicombridge.domain.image.Image;
import com.example.dicombridge.repository.ImageRepository;
import com.example.dicombridge.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/studies")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService imageService;

    /** ThumbNale **/
    @PostMapping("/getThumbnail/{studyKey}")
    public ResponseEntity<Map<String, ThumbnailWithFileDto>> getThumbnailData(@PathVariable String studyKey) throws IOException {
        Map<String, ThumbnailWithFileDto> images = imageService.getThumbnail(Integer.valueOf(studyKey));

        if (!images.isEmpty()) {
            return new ResponseEntity<>(images, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /*****************************************************************************************
     ***************리스트에 들어가면 studyinsuid가 전부 동일, studyinsuid가 같은 파일 찾기**********
     ****************리스트에서 클릭시 출력되는 전체 image*****************************************
     *****************************************************************************************/

    @PostMapping("/takeuidgiveseriesnum/{seriesinsuid}")
    public ResponseEntity<byte[]> getSeriesNum(@PathVariable String seriesinsuid, Model model) throws IOException{
        //List list = imageService.getSeriesNum(studyinsuid);
        //System.out.println(list.size());
       File file = imageService.getSeriesNum(seriesinsuid);
        // 파일을 byte 배열로 읽기
        Path path = file.toPath();
        byte[] data = Files.readAllBytes(path);

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", seriesinsuid + ".jpg");

        // 파일을 byte 배열로 응답
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    @PostMapping("/takeuidgiveseriesnum2/{seriesinsuid}")
    public ResponseEntity<List<byte[]>> getSeriesNum2(@PathVariable String seriesinsuid, Model model) throws IOException{
        List<File> files = imageService.getSeriesNum2(seriesinsuid);

        List<byte[]> byteArrayList = new ArrayList<>();

        for (File file : files) {
            // 각 파일을 byte 배열로 변환합니다.
            Path path = file.toPath();
            byte[] data = Files.readAllBytes(path);

            // byte 배열을 리스트에 추가합니다.
            byteArrayList.add(data);
        }

//        for (File file : files) {
//            Path path = file.toPath();
//            byte[] data = Files.readAllBytes(path);
//            baos.write(data);
//        }
//        System.out.println(baos.toByteArray().toString());
        System.out.println("진입 후 진행중..");
        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentType(MediaType.APPLICATION_JSON);  // JSON으로 응답합니다.
        headers.setContentDispositionFormData("attachment", seriesinsuid + ".jpg");

        // 파일을 byte 배열로 응답
        return new ResponseEntity<>(byteArrayList, headers, HttpStatus.OK);
    }


    /*****************************************************************************************
     ************************************StudyKey로 조회***************************************
     *****************************************************************************************/
    @PostMapping("/getFile/{studyKey}")
    public ResponseEntity<byte[]> getFile(@PathVariable String studyKey) throws IOException {
        // imageService.getFile 메서드로부터 파일을 읽어들임
        File file = imageService.getFile(Integer.valueOf(studyKey));

        // 파일을 byte 배열로 읽기
        Path path = file.toPath();
        byte[] data = Files.readAllBytes(path);

        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", studyKey + ".jpg");

        // 파일을 byte 배열로 응답
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    /*****************************************************************************************
     ****************************Seriesinsuid count 조회***************************************
     *****************************************************************************************/
    @GetMapping("/getSeriesInsUidCount/{seriesInsUid}")
    public ResponseEntity<Integer> seriesinsuidCount(@PathVariable String seriesInsUid) throws IOException{
        int count = imageService.seriesinsuidCount(seriesInsUid);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return ResponseEntity.ok().headers(headers).body(count);
    }

    /*****************************************************************************************
     ****************************Seriesinsuid count 별 이미지***********************************
     *****************************************************************************************/
    @GetMapping("/getSeriesInsUidIndex/{seriesInsUid}/{order}")
    public ResponseEntity<byte[]> getSeriesInsUidIndex(@PathVariable String seriesInsUid,
                                                       @PathVariable int order) throws IOException {
        File file = imageService.getFileByseriesinsuidNcount(seriesInsUid, order);

        byte[] data = Files.readAllBytes(file.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", seriesInsUid + ".jpg");

        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }
    /*****************************************************************************************
     ***********************************Series의 갯수******************************************
     *****************************************************************************************/
    @PostMapping("/seriescount/{studyinsuid}")
    public int seriesCount(@PathVariable("studyinsuid") String studyinsuid){

        return imageService.findMaxStudyKeyByStudyKey(studyinsuid);
    }
    @PostMapping("/download/{studyKey}")
    public ResponseEntity<byte[]> downloadImages(@PathVariable int studyKey) throws IOException {
        System.out.println("studyKey : "+studyKey);

        // 여러 파일을 하나의 byte 배열로 합치기
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        List<File> fileList = imageService.getFiles(studyKey);
        for (File file : fileList) {
            Path path = file.toPath();
            byte[] data = Files.readAllBytes(path);
            System.out.println("data:"+data);
            baos.writeBytes(data);
        }
        // 합쳐진 byte 배열
        byte[] mergedData = baos.toByteArray();
        System.out.println("mergedData:" + mergedData);
        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", studyKey + ".dcm");

        // 파일을 byte 배열로 응답
        return new ResponseEntity<>(mergedData, headers, HttpStatus.OK);
    }


    /**************************
     ******redis test**********
     ***************************/

    @PostMapping("/saveredis/{studyKey}")
    public ResponseEntity<byte[]> saveRedis(@PathVariable String studyKey) throws IOException {
        // imageService.getFile 메서드로부터 파일을 읽어들임
        File file = imageService.getFile(Integer.valueOf(studyKey));

        // 파일을 byte 배열로 읽기
        Path path = file.toPath();
        byte[] data = Files.readAllBytes(path);
        String keyname = "key";
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set(keyname.getBytes(),data);

//        System.out.println("1"+jedis.get(keyname.getBytes()));
//        System.out.println("2"+data);
//        System.out.println("1====" + new String(jedis.get(keyname.getBytes())));
//        System.out.println("2====" + new String(data));
        // HTTP 응답 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", studyKey + ".jpg");

        // 파일을 byte 배열로 응답
        //return new ResponseEntity<>(data, headers, HttpStatus.OK);
        return new ResponseEntity<>(jedis.get(keyname.getBytes()), headers, HttpStatus.OK);
    }

    @PostMapping("/saveRedisValSeriesinsuid/{studyinsuid}")
    public List<String> saveRedisValSeriesinsuid (@PathVariable String studyinsuid) throws IOException {
        List<String> list = imageService.saveRedisValSeriesinsuid(studyinsuid);
        String keyname = studyinsuid;
        Jedis jedis = new Jedis("localhost", 6379);
        // 하나의 키-값 쌍 저장(,로 나눠둠)
        jedis.set(keyname, String.join(",", list));
        for(int i=0; i<list.size(); i++){
            List<File> image = imageService.getComparisonImage(list.get(i));
            String key = list.get(i); // seriesinsuid
            jedis.set(key, String.valueOf(image.size()));
            for(int j=0; j<image.size();j++){
                String uniqueKey = key + ":" + j;
                File file = image.get(j);
                byte[] data = Files.readAllBytes(file.toPath());
                jedis.set(uniqueKey.getBytes(),data);
            }
        }
        //키:studyinsuid 벨류:seriesinsuid의 종류를 ,로 나눠서 저장
        // 저장된 값을 다시 리스트로 변환
        //List<String> retrievedList = Arrays.asList(storedValue.split(","));
        //키:seriesinsuid 벨류:seriesinsuid의 사진 갯수
        //키:seriesinsuid:이미지번호.getBytes() 벨류:이미지 바이트

        return null;
    }

}
