package com.cmancode.excel.app.model.support;

import org.springframework.web.multipart.MultipartFile;

public class ExcelFileSupport {

	private MultipartFile nombre;
	
	private boolean estado;
	
	public ExcelFileSupport() {}
	
	public ExcelFileSupport(MultipartFile nombre, boolean estado) {
		this.nombre = nombre;
		this.estado = estado;
	}

	public MultipartFile getNombre() {
		return nombre;
	}

	public void setNombre(MultipartFile nombre) {
		this.nombre = nombre;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
}
