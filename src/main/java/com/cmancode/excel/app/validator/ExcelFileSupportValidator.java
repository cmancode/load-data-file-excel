package com.cmancode.excel.app.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.cmancode.excel.app.model.support.ExcelFileSupport;

@Component
public class ExcelFileSupportValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return ExcelFileSupport.class.isAssignableFrom(clase);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ExcelFileSupport file = (ExcelFileSupport) obj;
	
		if(file.getNombre() != null) {
			if(file.getNombre().isEmpty()) {
				errors.rejectValue("nombre", "file.nombre", "No puede estar vacio");
			}
			if(!file.getNombre().getContentType().contains("vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
				errors.rejectValue("nombre", "file.nombre", "Debe ser formato excel");
			}
			if(file.getNombre().getSize() > 20*1024*1024) {
				errors.rejectValue("nombre", "file.nombre", "El archivo excede los 10MB");
			}
			
		}
		
	}

}
