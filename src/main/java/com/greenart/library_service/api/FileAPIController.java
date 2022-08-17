package com.greenart.library_service.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileAPIController {
    // localhost:8080/imges/actor/default.jpg
    @Value("${spring.servlet.multipart.location}") String path;
    @GetMapping("/images/{type}/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String type, @PathVariable String filename , HttpServletRequest request) {
        Path folderLocation = Paths.get(path+"/"+type); // movie/actor = d:/movie/actor
        Path filePath = folderLocation.resolve(filename); // movie/actor//default.jpg = d:/movie/actor.default.jpg
        Resource r = null;
        try {
            r = new UrlResource(filePath.toUri());
            
        } catch (Exception e) {
            System.out.println("파일을 찾을 수 없거나, 잘못된 파일 경로입니다.");
        }
        // 파일의 실제 경로에 찾아가서 파일의 유형을 가져온다.
        String contentType = null;
        
        try {
            request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if(contentType == null)
            contentType = "application/octet-stream";
        }
        catch(Exception e){
            System.out.println("파일을 찾을 수 없거나, 잘못된 파일 경로입니다.");
        }
        return ResponseEntity.ok() // 결과로 200 ok를 설정
            .contentType(MediaType.parseMediaType(contentType)) // 파일의 타입을 Spring 프레임 워크를 통해 파일 유형을 결정
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=\""+r.getFilename()+"\"") // 파일 이름의 표시 방법을 설정 (다운로드 되는 파일의 이름 설정)
            .body(r); // 실제 리소스를 Body에 포함 
    }

    @PutMapping("/{filetype}/upload/{type}")
    public Map<String,Object> putImageUpload(@PathVariable String filetype,@PathVariable String type, @RequestPart MultipartFile file) {
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();
        // 업로드 할 파일의 경로 생성
        Path folderLocation = Paths.get(path+"/"+type); 
        String fileName = file.getOriginalFilename();
        String[] fileNameSplit = fileName.split("\\.");
        String ext = fileNameSplit[fileNameSplit.length -1];
        if(filetype.equals("images")){
            if(!ext.equalsIgnoreCase("jpg") && !ext.equalsIgnoreCase("png") && !ext.equalsIgnoreCase("gif")) {
                resultMap.put("status", false);
                resultMap.put("message", "이미지 파일 확장자는 jpg, png, gif만 허용합니다");
                return resultMap;
            }
        }

        else if(filetype.equals("movies")){
            if(!ext.equalsIgnoreCase("mp4")) {
                resultMap.put("status", false);
                resultMap.put("message", "영상 파일 확장자는 mp4만 허용합니다");
                return resultMap;
            }
        }
        else if(filetype.equals("music")){
            if(!ext.equalsIgnoreCase("mp3")) {
                resultMap.put("status", false);
                resultMap.put("message", "영상 파일 확장자는 mp3만 허용합니다");
                return resultMap;
            }
        }

        else {
            resultMap.put("status", false);
            resultMap.put("message", "Invalid filetype : "+filetype+"\n use: [images, movies]");
            return resultMap;
        }

        Calendar c = Calendar.getInstance();

        String saveFileName = StringUtils.cleanPath(type+"_"+c.getTimeInMillis()+"."+ext);
        Path target = folderLocation.resolve(saveFileName);
        // 파일 업로드
        try {
            Files.copy(file.getInputStream(), target , StandardCopyOption.REPLACE_EXISTING);
        }
        catch(IOException e){
            // 파일 업로드 실패
            resultMap.put("status", false);
            resultMap.put("message", e.getMessage());
            return resultMap;
        }
        // 파일 업로드 성공
        Long fileSize = file.getSize();
        resultMap.put("status", true);
        resultMap.put("message", "파일 업로드 완료");
        resultMap.put("file",  saveFileName);
        resultMap.put("ext",  ext);
        resultMap.put("fileSize", fileSize);
        return resultMap;
    }
    @DeleteMapping("{filetype}/delete/{type}/{filename}")
    public Map<String, Object> deleteImageFile(
        @PathVariable String filetype,
        @PathVariable String type , @PathVariable String filename) {
        Map<String, Object> resultMap = new LinkedHashMap<String,Object>();
        String filePath = path+"/"+type+"/"+filename;
        File deleteFile = new File(filePath);

        if(deleteFile.exists()) {
            deleteFile.delete();
        }
        else {
            resultMap.put("status", false);
            resultMap.put("message", "파일이 존재하지 않습니다.");
            resultMap.put("path", filePath);
            return resultMap;
        }

        resultMap.put("status", true);
        resultMap.put("message", "파일을 삭제 했습니다.");
        return resultMap;
    }

    @DeleteMapping("/images/delete/{type}/list")
    public Map<String, Object> deleteImageList(@PathVariable String type, @RequestBody List<String> imgList){
        Map<String,Object> resultMap = new LinkedHashMap<String,Object>();

        for(String filename : imgList) {
            String filePath = path+"/"+type+"/"+filename;
            File deleteFile = new File(filePath);
            if(deleteFile.exists()) {
                deleteFile.delete();
            }
        }
        resultMap.put("status", true);
        resultMap.put("message", "파일을 삭제 했습니다.");
        return resultMap;
    }
}