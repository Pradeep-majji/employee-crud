package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/app")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping(value="/employees")
    public ResponseEntity<Page<EmployeeEntity>> getAllEmployees(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size) {
        Page<EmployeeEntity> employees = employeeService.getEmployees(page, size);
        return ResponseEntity.ok(employees);
    }

	
	@PostMapping(value="/adduser",consumes="application/json")
	public HttpStatus addUser(@RequestBody EmployeeEntity ur)
	{
		if(employeeService.insertUser(ur))
			return HttpStatus.OK;
		return HttpStatus.NOT_FOUND;
	}
	/*
	@PostMapping(value="/generateexcel")
	public HttpStatus generator() {
		try {
			employeeService.createExcelWithHeaders();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return HttpStatus.OK;
	}
	*/
	
	@GetMapping(value="/users",produces="application/json")
	public  ResponseEntity<List<EmployeeEntity>> getAllotedClasses(){
		return new ResponseEntity<List<EmployeeEntity>>(employeeService.getUsers(),HttpStatus.OK);
	}
	@GetMapping(value="/{id}",produces="application/json")
	public  ResponseEntity<EmployeeEntity> getId(@PathVariable int id){
		return new ResponseEntity<EmployeeEntity>(employeeService.getOneUser(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/employeedelete/{id}")
	public HttpStatus deleteUser(@PathVariable int id)
	{	//System.out.println("in delete");
		if(employeeService.deleteUser(id))
			return HttpStatus.OK;
		return HttpStatus.NOT_FOUND;
	}
	@PutMapping(value="/update",consumes="application/json")
	public HttpStatus modifyUser(@RequestBody EmployeeEntity ur)
	{
		if(employeeService.modifyUser(ur))
			return HttpStatus.OK;
		return HttpStatus.NOT_MODIFIED;
	}
	
	@PatchMapping("/employees/{id}")
    public ResponseEntity<EmployeeEntity> patchEmployeeSalary(@PathVariable int id, @RequestBody Map<String, Object> patchValues) {

        if (!patchValues.containsKey("salary")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Only accept salary updates
        }

        EmployeeEntity employee = employeeService.getOneUser(id);
        if (employee == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        employee.setSalary((int) patchValues.get("salary")); 
        employeeService.modifyUser(employee);

        return new ResponseEntity<>(employee, HttpStatus.OK);
	}
}
