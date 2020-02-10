package org.abondar.experimental.graphserver.resolvers;

import com.coxautodev.graphql.tools.GraphQLResolver;
import org.abondar.experimental.graphserver.dao.FakeDao;
import org.abondar.experimental.graphserver.model.Department;
import org.abondar.experimental.graphserver.model.Employee;

public class EmployeeResolver implements GraphQLResolver<Employee> {

    private FakeDao dao;

    public EmployeeResolver(FakeDao dao) {
        this.dao = dao;
    }

    public Department getDepartment(Employee employee){
        return dao.getDepartmentById(employee.getDepartment().getId());
    }
}
