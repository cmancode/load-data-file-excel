package com.cmancode.excel.app.service;

import java.util.List;

import com.cmancode.excel.app.model.Datos;

public interface IDatosService {
	
	public void save(Datos datos);
	public void update(Datos datos);
	public List<Datos> findAll();
	public Datos findByIdDatos(Long id);
	
}
