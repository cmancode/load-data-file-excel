package com.cmancode.excel.app.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.cmancode.excel.app.model.ExcelFile;
import com.cmancode.excel.app.model.support.ExcelFileSupport;
import com.cmancode.excel.app.service.IExcelFileService;
import com.cmancode.excel.app.service.IReadExcelFileService;
import com.cmancode.excel.app.service.IUploadService;
import com.cmancode.excel.app.validator.ExcelFileSupportValidator;

@Controller
public class LoadFileController {
	
	private static Logger log = LoggerFactory.getLogger(LoadFileController.class);
	
	@Autowired
	private ExcelFileSupportValidator validator;
	
	@Autowired
	private IExcelFileService excerlFileService;
	
	@Autowired
	private IReadExcelFileService readFileService;
	
	@Autowired
	private IUploadService uploadService;
	
	@GetMapping("/")
	public String index(Model model) {
		ExcelFileSupport fileSuport = new ExcelFileSupport();
		//ExcelFile file = new ExcelFile();
		model.addAttribute("excel", fileSuport);
		return "index";
	}
	
	@InitBinder("excel")
	public void initBinderExcel(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@PostMapping("/inscripcion")
	public String cargarArchivo(@Valid @ModelAttribute("excel") ExcelFileSupport file, BindingResult result) throws IOException {
		validator.validate(file, result);
		if(!result.hasErrors()) {
			log.info(file.getNombre().getContentType());
			String nombre = uploadService.save(file.getNombre());
			ExcelFile excelFile = new ExcelFile();
			excelFile.setNombre(nombre);
			excelFile.setType(file.getNombre().getContentType());
			excelFile.setEstado(readFileService.read(nombre));
			excerlFileService.save(excelFile);
			return "redirect:/";
		}
		return "index";
	}
	
}
