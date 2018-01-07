package com.cmancode.excel.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmancode.excel.app.dao.IDatosDao;
import com.cmancode.excel.app.model.Datos;
import com.cmancode.excel.app.service.IDatosService;

@Service("datosService")
@Transactional
public class DatosServiceImpl implements IDatosService {

	@Autowired
	private IDatosDao datosDao;
	
	@Override
	@Transactional
	public void save(Datos datos) {
		datosDao.save(datos);
	}

	@Override
	@Transactional
	public void update(Datos datos) {
		datosDao.save(datos);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Datos> findAll() {
		List<Datos> datos = (List<Datos>) datosDao.findAll();
		return datos;
	}

	@Override
	public Datos findByIdDatos(Long id) {
		Datos dato = datosDao.findById(id).orElse(null);
		return dato;
	}

}
