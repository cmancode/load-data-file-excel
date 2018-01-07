package com.cmancode.excel.app.service;

import java.util.List;

import com.cmancode.excel.app.model.ExcelFile;

public interface IExcelFileService {
	
	public void save(ExcelFile excel);
	public List<ExcelFile> findAll();
	public void delete(Long id);
	
}
