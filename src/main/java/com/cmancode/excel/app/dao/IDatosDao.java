package com.cmancode.excel.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.cmancode.excel.app.model.Datos;

public interface IDatosDao extends CrudRepository<Datos, Long> {
	
}
