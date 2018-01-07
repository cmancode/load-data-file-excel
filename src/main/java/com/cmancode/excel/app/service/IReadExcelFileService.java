package com.cmancode.excel.app.service;

import java.io.IOException;

public interface IReadExcelFileService {
	
	public boolean read(String nombre) throws IOException;
}
