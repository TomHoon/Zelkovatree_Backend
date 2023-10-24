package com.zelkovatree.controller;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	ObjectMapper mapper = new ObjectMapper();
	
	@PostMapping("/addBoard")
	public String pushImage(@RequestPart(required = false)  MultipartFile uploadFile, @RequestPart String param) throws JsonMappingException, JsonProcessingException {
        // 시간과 originalFilename으로 매핑 시켜서 src 주소를 만들어 낸다.
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        
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
        	
        	File temp = new File("C:/Users/gnsdl/Documents/Zelkovatree/zelkovatree-project/public/image/" + sb.toString());
        	bEnt.setFile_path("/upload/" + sb.toString());        	
        	
        	uploadFile.transferTo(temp);

        } catch (Exception e) {
        	System.out.println(e);
        }
        int result = bDao.addBoard(bEnt);
		return sb.toString();
    }
	
	@PostMapping("/enrollBoard")
	public String enrollBoard(@RequestPart String param) throws JsonMappingException, JsonProcessingException {
		BoardEntity bEnt;
		bEnt = mapper.readValue(param, BoardEntity.class);
		bDao.addBoard(bEnt);
		return null;
	}
	
	@GetMapping("/getBoard")
	public List<BoardEntity> getBoard(){
		List<BoardEntity> list =  bDao.getBoard();
		return list;
	}
}
