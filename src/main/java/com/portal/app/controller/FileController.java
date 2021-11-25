package com.portal.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.portal.app.dto.FileDto;
import com.portal.app.model.FileDao;
import com.portal.app.service.FileService;
import com.portal.app.service.UserService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FileController {
	@Autowired
	private FileService fileService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/upload")
	public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file){
		String message = "";
		try {
			System.out.println("upload file");
			fileService.uploadFile(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@GetMapping("/fetchFiles")
	@Transactional(readOnly = true)
	public ResponseEntity<List<FileDto>> fetchUnsignedFile(){
		System.out.println("files");
		List<FileDto> files = null ;
		if(userService.getUserRole().equalsIgnoreCase("ADMIN")){
		 files = fileService.fetchUnsignedFile().map(dbFile -> {
			return new FileDto(dbFile.getFilename(),dbFile.getId(),dbFile.getUploadBy(),dbFile.getUploadDateTime().toString()
					);
	}).collect(Collectors.toList());
		}
		else if(userService.getUserRole().equalsIgnoreCase("USER")){
			 files = fileService.fetchUnsignedFile().map(dbFile -> {
					return new FileDto(dbFile.getFilename(),dbFile.getId(),dbFile.getSignedBy(),dbFile.getUploadDateTime().toString()
							);
			}).collect(Collectors.toList());
		}

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	//download afile
	  @GetMapping("/files/{id}")
	  public ResponseEntity<Resource> getFile(@PathVariable("id") String id) {	
		  System.out.println(id);
	    FileDao fileDB = fileService.fetchFile(Integer.valueOf(id));
	    String fileType=fileDB.getFileType();
	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFilename() + "\"").contentType(MediaType.parseMediaType(fileType))
	        .body(new ByteArrayResource(fileDB.getFileData()));
	  }
	

}
