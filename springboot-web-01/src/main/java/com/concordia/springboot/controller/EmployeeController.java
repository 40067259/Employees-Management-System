package com.concordia.springboot.controller;

import com.concordia.springboot.dao.DepartmentDao;
import com.concordia.springboot.dao.EmployeeDao;
import com.concordia.springboot.entities.Department;
import com.concordia.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

/**
 * @author Fred Zhang
 * @create 2020-03-20 12:44 PM
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    //list all employees
    @GetMapping("/emps")
    public String list(Model model){
       Collection<Employee> employees = employeeDao.getAll();
       //fang zai qing qiu yu zhong gong xiang
        model.addAttribute("emps",employees);
        //thymeleaf combine the classpath
        //classpath:/templates/
        return "emp/list";

    }
    //to employees add page
    @GetMapping("/emp")
    public String toAddPage(Model model){
      // come to the add page and display on the page
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }

    //add-employee function
    @PostMapping("/emp")
    public String addEmp(Employee employee){
         //go to emp-list page
        System.out.println("Employee information: "+employee);
        employeeDao.save(employee);
        //redirect: represent relocation an address
        //forward: transfer to an address
        return "redirect:/emps";

    }

    //come to the current employees and display the information
    // go back to edit page(add + modify)
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        // show all departments
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){

        System.out.println("Modified employee information: "+employee);
        return"redirect:/emps";
    }

    //delete employee
    @PostMapping("/emp/{id}")
    public String delete(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";

    }
}
