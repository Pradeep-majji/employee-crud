package com.example.demo.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Transactional
	public boolean insertUser(EmployeeEntity ur)
	{
		return employeeRepository.save(ur)!=null;
	}
	
	@Transactional
	public boolean deleteUser(int id)
	{
		long count=employeeRepository.count();
		employeeRepository.deleteById(id);
		return count>employeeRepository.count();
	}
	
	@Transactional(readOnly=true)
	public List<EmployeeEntity> getUsers() {
		return employeeRepository.findAll();	
	}
	
	@Transactional(readOnly=true)
	public EmployeeEntity getOneUser(int cid) {
		Optional<EmployeeEntity> u=employeeRepository.findById(cid);
		if(u.isPresent())
			return u.get();
		return null;
	}
	public boolean modifyUser(EmployeeEntity ur)
	{
		return employeeRepository.save(ur)!=null;
	}
	
	 public Page<EmployeeEntity> getEmployees(int page, int size) {
	        Pageable pageable = PageRequest.of(page, size);
	        return employeeRepository.findAll(pageable);
	    }
	 
	 public void createExcelWithHeaders() throws IOException {

         //System.out.println("hello1");
	        String excelFilePath = "./EmployeeData.xlsx";
	        File file = new File(excelFilePath);
	        file.createNewFile();

	        Workbook workbook = new XSSFWorkbook();
	        String sheetName = EmployeeEntity.class.getAnnotation(Table.class).name();
	        Sheet sheet = workbook.createSheet(sheetName);

	        // Create header row

            //System.out.println("hello2");
	        Row headerRow = sheet.createRow(0);
	        int cellIndex = 0;
	        for (Field field : EmployeeEntity.class.getDeclaredFields()) {
	            field.setAccessible(true);
	            Column columnAnnotation = field.getAnnotation(Column.class);
	            String columnName = columnAnnotation != null ? columnAnnotation.name() : field.getName();
	            headerRow.createCell(cellIndex++).setCellValue(columnName);
	        }

	        // Fetch employee data
	        List<EmployeeEntity> employees = employeeRepository.findAll();

            //System.out.println("hello3"+employees.size());
	        // Populate data rows
	        int rowIndex = 1;
	        for (EmployeeEntity employee : employees) {
                //System.out.println("inside the for");
	            Row dataRow = sheet.createRow(rowIndex++);
	            int cellIndex1 = 0;
	            for (Field field : EmployeeEntity.class.getDeclaredFields()) {
	                System.out.println("inside the for loop 2");
	                field.setAccessible(true);
	                try {
	                    Object value = field.get(employee);
	                    //System.out.println("inside the try");
	                    dataRow.createCell(cellIndex1++).setCellValue(value != null ? value.toString() : "");
	                } catch (IllegalAccessException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        // Write the workbook to the file

            //System.out.println("hello4");
	        FileOutputStream outputStream = new FileOutputStream(file);
	        workbook.write(outputStream);
	        workbook.close();
	        outputStream.close();

	    }

	
}
