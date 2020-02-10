package org.abondar.experimental.graphserver.dao;

import org.abondar.experimental.graphserver.model.Department;
import org.abondar.experimental.graphserver.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FakeDao {

    private Map<Integer, Employee> employees;
    private Map<Integer, Department> departments;
    private static final Logger logger = LogManager.getLogger();

    public FakeDao() {
        employees = new HashMap<>();
        departments = new HashMap<>();
    }


    public List<Employee> getEmployees(int count, int offset) {
        List<Employee> res = new ArrayList<>(employees.values());
        return res.subList(offset, offset + count);
    }


    public List<Department> getDepartments(int count, int offset) {
        List<Department> res = new ArrayList<>(departments.values());
        return res.subList(offset, offset + count);
    }


    public List<Employee> getEmployeesInDepartment(int depId, int count, int offset) {
        Department dep = departments.get(depId);
        List<Employee> employees = dep.getEmployees();

        return employees.subList(offset, offset + count);
    }

    public Employee addEmployee(String firstName, String lastName, String phone) {
        Employee emp = new Employee(firstName, lastName, phone);
        employees.put(emp.getId(), emp);
        logger.info("Employee created: " + emp);
        return emp;
    }

    public Department addDepartment(String name) {
        Department department = new Department(name);
        departments.put(department.getId(), department);
        logger.info("Department created: " + department);
        return department;
    }

    public boolean addEmployeeToDepartment(int empId, int depId) {
        Employee employee = employees.get(empId);

        if (employee == null) {
            logger.error("Employee not found");
            return false;
        }

        Department department = departments.get(depId);
        if (department == null){
            logger.error("Department not found");
            return false;
        }

        employee.setDepartment(department);
        department.getEmployees().add(employee);

        employees.put(empId,employee);
        departments.put(depId,department);

        logger.info("Added employee "+empId +" to department "+depId);
        return true;
    }

    public Department getDepartmentById(int depId){
        return departments.get(depId);
    }
}
