package com.cmancode.excel.app.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmancode.excel.app.model.Datos;
import com.cmancode.excel.app.service.IDatosService;
import com.cmancode.excel.app.service.IReadExcelFileService;

@Service("readExcelService")
@Transactional
public class ReadExcelFileSeriviceImpl implements IReadExcelFileService {
	
	@Autowired
	private IDatosService datoService;
	
	@Override
	public boolean read(String nombre) throws IOException {
		FileInputStream file = new FileInputStream(new File(Paths.get("files").resolve(nombre).toAbsolutePath().toUri()));
		String ext = getExtension(nombre);
		if(ext.equals("xlsx")) {
			return xssf(file);
		} else if(ext.equals("xls")) {
			return hssf(file);
		}
		file.close();
		return false;
	}

	
	public boolean xssf(FileInputStream file) throws IOException {
		boolean estado = false;
		Workbook libro = new XSSFWorkbook(file);
		Sheet hoja = libro.getSheetAt(0);
		Iterator<Row> filas = hoja.iterator();
		int k = 0;
		while(filas.hasNext()) {
			if(k > 0) {
				List<Cell> celdas = IteratorUtils.toList(filas.next().iterator());
				int c = 0;
				Datos datos = new Datos();
				for(Cell cell: celdas) {
					switch(c) {
						case 0:
							datos.setTipoDoc((cell.getStringCellValue()));
							break;
						case 1:
							datos.setIdentificacion(cell.getStringCellValue());
							break;
						case 2:
							datos.setNombres(cell.getStringCellValue());
							break;
						case 3:
							datos.setApellidos(cell.getStringCellValue());
							break;
						case 4:
							datos.setEdad(cell.getStringCellValue());
							break;
						default:
							break;
					}
					datoService.save(datos);
					estado = true;
					c++;
				}
			}
			k++;
		}
		libro.close();
		return estado;
	}
	
	
	/*Para archivos de Excel 2003 e inferiores*/
	public boolean hssf(FileInputStream file) throws IOException {
		boolean estado = false;
		
		return estado;
	}
	
	public String getExtension(String nombre) {
		int i = nombre.lastIndexOf('.');
		if(i > 0) {
			return nombre.substring(i+1);
		}
		return "";
	}
	
}
