package com.cmancode.excel.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.cmancode.excel.app.model.ExcelFile;

public interface IExcelFileDao extends CrudRepository<ExcelFile, Long>{

}
