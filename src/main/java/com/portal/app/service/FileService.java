package com.portal.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portal.app.model.FileDao;
import com.portal.app.repository.FileRepository;

@Service
public class FileService {
@Autowired
private FileRepository fileRepository;
@Autowired
private UserService userService;

	public FileDao uploadFile(MultipartFile file) throws IOException {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDao fileDao = null;
	
		FileDao saveFile = null;
		
		try{
		if(userService.getUserName().equalsIgnoreCase("USER")){
			
			
			 fileDao = new FileDao(fileName,null,userService.getUserName(),false, file.getBytes(),new Date(),file.getContentType());
		}else if(userService.getUserName().equalsIgnoreCase("ADMIN")){
			System.out.println("ADMIN");
			FileDao	filePresent=fileRepository.findByFileNameAndSignStatus(fileName);
			System.out.println("outside if"+filePresent.getUploadBy());
			//if(filePresent != null){
			//	System.out.println(filePresent);
			
				fileDao = new FileDao(fileName,userService.getUserName(),filePresent.getUploadBy(),true,file.getBytes(),new Date(),file.getContentType());
				fileRepository.deleteById(filePresent.getId());
		//	}
			
		}
		
		saveFile=fileRepository.save(fileDao);
		
		System.out.println(saveFile);
		}
		catch(Exception e){
			System.out.println(e);
		}
		return saveFile;
		
	}
	public Stream<FileDao> fetchUnsignedFile() {
		if(userService.getUserName().equalsIgnoreCase("USER")){
			System.out.println("fileRepository");
			return fileRepository.findAllByUploadByAndSignedStatus(userService.getUserName());
		}
		else
		return fileRepository.findAllBySignedStatusAndUploadedBY();
	}
	public FileDao fetchFile(Integer id) {
		
		// TODO Auto-generated method stub
		return fileRepository.findById(id).get();
	}

}
