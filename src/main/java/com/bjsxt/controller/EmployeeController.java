package com.bjsxt.controller;

import com.bjsxt.dao.DepartmentDao;
import com.bjsxt.dao.EmployeeDao;
import com.bjsxt.entities.Department;
import com.bjsxt.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Date;

@Controller
public class EmployeeController {

    //注入dao属性
    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    //跳转到添加员工页面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        //查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //跳转到员工添加页面
        return "emp/add";
    }

    //查询所有员工
    @RequestMapping("/emps")
    public String showEmp(Model model){
        Collection<Employee> emps = employeeDao.getAll();
        model.addAttribute("emps",emps);
        return "emp/list";
    }


    //添加员工
    @PostMapping("/emp")
    public String addEmp(Employee employee){

        //保存员工信息
        System.out.println("保存员工信息："+employee);
        //添加员工
        employeeDao.save(employee);
        //返会页面
        return "redirect:/emps";
    }

    //来到修改页面,(add是修改添加二合一)
    @GetMapping("/emp/{id}")
    public String toUpdatePage(@PathVariable Integer id,Model model){
        //查出所有的部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //查询员工
        Employee employee = employeeDao.get(id);
        //存到作用域里面
        model.addAttribute("emp",employee);
        System.out.println("跳到update页面");

        return "emp/update";
    }
    //修改员工
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        //修改的员工信息
        System.out.println("-------------------------"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //删除员工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable Integer id){
        employeeDao.delete(id);
        //返回显示页面
        return "redirect:emps";
    }
}
