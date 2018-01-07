package com.cmancode.excel.app.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	
	public String save(MultipartFile file) throws IOException;
	public boolean delete(String nombre);
	
}
