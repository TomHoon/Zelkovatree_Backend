package com.zelkovatree.controller;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zelkovatree.dao.BoardDao;
import com.zelkovatree.entity.BoardEntity;

@RestController
public class BoardController {
	
	@Autowired
	BoardDao bDao;
	
	@PostMapping("/addBoard")
	public String pushImage(@RequestPart(required = false)  MultipartFile uploadFile, @RequestPart String param) throws JsonMappingException, JsonProcessingException {
        // 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        
        ObjectMapper mapper = new ObjectMapper();
        BoardEntity bEnt;
		bEnt = mapper.readValue(param, BoardEntity.class);
		
        try {
        	uploadFile.isEmpty();
        	sb.append(date.getTime());
        	sb.append(uploadFile.getOriginalFilename());
        	
//        	◆◆ 로컬
//    	    File dest = new File("C://Users//gnsdl//Desktop//test//public/" + sb.toString());
        	
        	// 피시방 임시
//        	File dest = new File("C://Users//Administrator//Downloads//CommunityProject//public/" + sb.toString());
        	bEnt.setFile_path(sb.toString());

//        	◆◆운영서버
//        	File dest = new File("/gnsdl2846/tomcat/webapps/upload/" + sb.toString());
        	
//        	C:\Users\gnsdl\Documents\ZelkovaTree\zelkovatree-project\public\image
        	File temp = new File("C:/Users/gnsdl/Documents/Zelkovatree/zelkovatree-project/public/image/" + sb.toString());
        	bEnt.setFile_path("/upload/" + sb.toString());        	
        	
        	uploadFile.transferTo(temp);

        } catch (Exception e) {
        	System.out.println(e);
        }
        int result = bDao.addBoard(bEnt);
		return sb.toString();
    }
}
