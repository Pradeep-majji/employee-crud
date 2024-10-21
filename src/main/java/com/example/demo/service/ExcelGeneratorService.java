package com.example.demo.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.EmployeeRepository;

@Service
public class ExcelGeneratorService {
	
	
    EmployeeRepository employeeRepository; // Inject your employee repository

   
    public void createExcelWithHeaders() throws IOException {
        String excelFilePath = "./employeefile.xlsx"; // Replace with desired path

        File file = new File(excelFilePath);
        // Create a new workbook
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Employees"); // Replace "Employees" with your desired sheet name

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("EmpID");
        headerRow.createCell(1).setCellValue("EmpName");
        headerRow.createCell(2).setCellValue("Salary");
        headerRow.createCell(3).setCellValue("DeptNo");


        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
