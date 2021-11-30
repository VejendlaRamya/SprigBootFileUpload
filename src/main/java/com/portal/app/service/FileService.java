package com.portal.app.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.portal.app.model.FileDao;
import com.portal.app.model.User;
import com.portal.app.repository.FileRepository;

@Service
public class FileService {
	@Autowired
	private FileRepository fileRepository;
	@Autowired
	private UserService userService;

	public FileDao uploadFile(MultipartFile file, String status, String comments) throws IOException {
		if (status == null) {
			status = "Pending";
		}
		if (comments == null || comments == "") {
			comments = "No Comments";
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		FileDao fileDao = null;
		System.out.println(status);

		FileDao saveFile = null;
		User user = userService.getUserByName(userService.getUserName());

		try {
			if (userService.getUserRole().equalsIgnoreCase("USER")) {

				fileDao = new FileDao(fileName, null, userService.getUserName(), false, file.getBytes(), new Date(),
						file.getContentType(), user, status, comments);
			} else if (userService.getUserRole().equalsIgnoreCase("ADMIN")) {
				System.out.println("ADMIN");
				FileDao filePresent = fileRepository.findByFileNameAndSignStatus(fileName);

				System.out.println("outside if" + filePresent.getUploadBy());
				System.out.println(fileName);

				fileDao = new FileDao(fileName, userService.getUserName(), filePresent.getUploadBy(), true,
						file.getBytes(), new Date(), file.getContentType(), user, status, comments);
				fileRepository.updateFileByStatus(filePresent.getId(), status);
				fileRepository.updateFileByCOmments(filePresent.getId(), comments);
			}

			saveFile = fileRepository.save(fileDao);

			System.out.println(saveFile);
		} catch (Exception e) {
			System.out.println(e);
		}
		return saveFile;

	}

	public Stream<FileDao> fetchFiles() {
		Stream<FileDao> file = null;
		if (userService.getUserRole().equalsIgnoreCase("USER")) {
			System.out.println("fileRepository");
			file = fileRepository.findAllByUploadByAndSignedStatus(userService.getUserName());
		} else if (userService.getUserRole().equalsIgnoreCase("ADMIN")) {

			file = fileRepository.findAllBySignedStatusAndUploadedBY();
		}
		return file;
	}

	public FileDao fetchFile(Integer id) {

		return fileRepository.findById(id).get();
	}

	public Stream<FileDao> getAllfiles() {
		return fileRepository.findByAll();

	}

	public void deleteFile(Integer id) {
		fileRepository.deleteById(id);

	}

	public Stream<FileDao> fetchUserUploadFiles() {
		return fileRepository.findAllByUploadedByAndSignedStatus(userService.getUserName());

	}

}
