package com.example.demo.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.MimeRepository;
import com.example.demo.entities.Mime;
import com.example.demo.entities.MimeFileInfo;
import com.example.demo.utils.MimeChecked;

@RestController
public class MimeRestService {
	protected Logger logger = LoggerFactory.getLogger(MimeRestService.class);
	@Autowired
	MimeRepository mimeRepository;
	
	@GetMapping(path="/checkmime")
	public boolean checkMime(@RequestParam(value="mimeType") String mimeType) {
	   return !mimeRepository.findByMimeType(mimeType).isEmpty();
	}
	
	@GetMapping(path="/check/mime/file")
	public Map<String, Boolean> checkMimeOnDirectory(@RequestParam(value="directory") String directory) {
		Map<String, Boolean> mapresult = new HashMap<>();
		try(Stream<Path> dir = Files.walk(Paths.get("C:\\Users\\Asus\\Desktop\\Sky\\CDN\\CDN-293")) ){
		List<MimeFileInfo> result = dir.filter(Files::isRegularFile)
					.map(x -> {
						try {
		                    
							return new MimeFileInfo(x.getFileName().toString(),Files.probeContentType(x));
						} catch (IOException e) {							
							logger.error("Error{}",e);
							return null;
						}
					} 
					
						).collect(Collectors.toList());
		logger.debug("RRRRR{}",result);
		result.forEach(x -> mapresult.put(x.getName() ,!mimeRepository.findByMimeType(x.getMimeType()).isEmpty()));					
		
		} catch (IOException e) {
		logger.error("Error during check mime on directory {}",e);
	}
		return mapresult;
	}
	
	@GetMapping(path="/check/mime/directory")
	public Map<String, String> checkMimeOnDir(@RequestParam(value="directory") String directory) {
		Map<String, String> mapresult = new HashMap<>();
		try(Stream<Path> dir = Files.walk(Paths.get(directory)) ){
		List<MimeFileInfo> result = dir.filter(Files::isRegularFile)
					.map(x -> {
						try {
		                    
							return new MimeFileInfo(x.getFileName().toString(),Files.probeContentType(x));
						} catch (IOException e) {							
							logger.error("Error{}",e);
							return null;
						}
					} 
					
						).collect(Collectors.toList());
		logger.info("RRRRR{}",result);
		result.forEach(x -> mapresult.put(x.getName() ,!mimeRepository.findByMimeType(x.getMimeType()).isEmpty()?"OK":"KO"));					
		
		} catch (IOException e) {
		logger.error("Error during check mime on directory {}",e);
	}
		return mapresult;
	}
	
	@GetMapping(path="/check/mime/directory2")
	public Page<MimeChecked> checkMimeOnDir2(@RequestParam(value="directory") String directory,@PageableDefault(sort = { "fileName" }, value = 10000) Pageable pageable) {		
		List<MimeChecked> result = new ArrayList<>();
		try(Stream<Path> dir = Files.walk(Paths.get(directory)) ){
		 result = dir.filter(Files::isRegularFile)
					.map(x -> {
						try {
							
		                    return new MimeChecked(x.getFileName().toString(), !mimeRepository.findByMimeType(Files.probeContentType(x)).isEmpty());							
						} catch (IOException e) {							
							logger.error("Error{}",e);
							return null;
						}
					} 
					
						).collect(Collectors.toList());
		logger.info("RRRRR{}",result);
		} catch (IOException e) {
		logger.error("Error during check mime on directory {}",e);
	}
		return new PageImpl<>(result, pageable, result.size());
	}
	
}
