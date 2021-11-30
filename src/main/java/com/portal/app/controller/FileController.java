package com.portal.app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
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

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	@PostMapping(value = "/upload")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file,
			@RequestParam(value = "status", required = false) String status,
			@RequestParam(value = "comments", required = false) String comments, HttpServletRequest request) {
		String message = "";
		try {
			logger.info("Inside Upload file");
			String parameterStatus = request.getParameter("status");
			String parameterComment = request.getParameter("comments");
			fileService.uploadFile(file, parameterStatus, parameterComment);
			String parameter = request.getParameter("status");

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@GetMapping("/fetchUserUploadFiles")
	@Transactional(readOnly = true)
	public ResponseEntity<List<FileDto>> fetchUserUploadFiles() {
		logger.info("Inside fetch user uploaded files");
		List<FileDto> files = null;
		try {
			files = fileService.fetchUserUploadFiles().map(dbFile -> {
				return new FileDto(dbFile.getId(), dbFile.getFilename(), dbFile.getUploadBy(), dbFile.getSignedBy(),
						dbFile.getSignedStatus(), dbFile.getUploadDateTime(), dbFile.getUser(), dbFile.getStatus(),
						dbFile.getCommetns());
			}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(files);
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.OK).body(files);
		}
	}

	@GetMapping("/fetchFiles")
	@Transactional(readOnly = true)
	public ResponseEntity<List<FileDto>> fetchFiles() {
		logger.info("Inside frtch files");
		List<FileDto> files = null;
		try {
			files = fileService.fetchFiles().map(dbFile -> {
				return new FileDto(dbFile.getId(), dbFile.getFilename(), dbFile.getUploadBy(), dbFile.getSignedBy(),
						dbFile.getSignedStatus(), dbFile.getUploadDateTime(), dbFile.getUser(), dbFile.getStatus(),
						dbFile.getCommetns());
			}).collect(Collectors.toList());
			return ResponseEntity.status(HttpStatus.OK).body(files);
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.OK).body(files);
		}
	}

	// download afile
	@GetMapping("/files/{id}")

	public ResponseEntity<Resource> getFile(@PathVariable("id") String id) {
		logger.info("Inside get file meythod");
		FileDao file = fileService.fetchFile(Integer.valueOf(id));
		logger.info("File Getting By Id", file);
		String fileType = file.getFileType();

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.contentType(MediaType.parseMediaType(fileType)).body(new ByteArrayResource(file.getFileData()));
	}

	@GetMapping("/deleteFile/{id}")
	public ResponseEntity<?> deleteFile(@PathVariable("id") String id) {
		logger.info("Inside Delete file");
		fileService.deleteFile(Integer.valueOf(id));
		return ResponseEntity.ok().body("Deleted Successfully");
	}

	@GetMapping("/allFiles")
	public ResponseEntity<List<FileDto>> getAllfiles() {
		logger.info("Inside get all files method");
		List<FileDto> files = fileService.getAllfiles().map(dbFile -> {
			return new FileDto(dbFile.getId(), dbFile.getFilename(), dbFile.getUploadBy(), dbFile.getSignedBy(),
					dbFile.getSignedStatus(), dbFile.getUploadDateTime(), dbFile.getUser(), dbFile.getStatus(),
					dbFile.getCommetns());
		}).collect(Collectors.toList());
		return ResponseEntity.status(HttpStatus.OK).body(files);

	}

}
