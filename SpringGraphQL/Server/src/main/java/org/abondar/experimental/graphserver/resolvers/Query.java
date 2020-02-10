package org.abondar.experimental.graphserver.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.abondar.experimental.graphserver.dao.FakeDao;
import org.abondar.experimental.graphserver.model.Department;
import org.abondar.experimental.graphserver.model.Employee;

import java.util.List;

public class Query implements GraphQLQueryResolver {
    private FakeDao dao;

    public Query(FakeDao dao) {
        this.dao = dao;
    }

    public List<Employee> getEmployees(int count, int offset){
        return dao.getEmployees(count,offset);
    }

    public List<Department> getDepartments(int count, int offset) {
        return dao.getDepartments(count,offset);
    }

    public List<Employee> getEmployeesInDepartment(int depId, int count, int offset){
        return dao.getEmployeesInDepartment(depId,count,offset);
    }
}
