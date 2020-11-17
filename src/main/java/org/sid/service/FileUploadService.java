package org.sid.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	public void uploadFile(MultipartFile file, Long id) throws IllegalStateException, IOException {
		file.transferTo(new File(System.getProperty("user.home")+"/labo/rapports/"+id+".pdf"));
	}
}
