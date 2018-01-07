package com.cmancode.excel.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmancode.excel.app.dao.IExcelFileDao;
import com.cmancode.excel.app.model.ExcelFile;
import com.cmancode.excel.app.service.IExcelFileService;

@Service("excelFileSerice")
@Transactional
public class ExcelFileServiceImpl implements IExcelFileService {
	
	@Autowired
	private IExcelFileDao excelDao;
	
	@Override
	@Transactional
	public void save(ExcelFile excel) {
		excelDao.save(excel);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ExcelFile> findAll() {
		List<ExcelFile> files = (List<ExcelFile>) excelDao.findAll();
		return files;
	}

	@Override
	@Transactional
	public void delete(Long id) {
		ExcelFile file = excelDao.findById(id).orElse(null);
		if(file != null) {
			excelDao.deleteById(id);
		}

	}

}
