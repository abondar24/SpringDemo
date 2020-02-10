package org.abondar.experimental.graphserver.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.abondar.experimental.graphserver.dao.FakeDao;
import org.abondar.experimental.graphserver.model.Department;
import org.abondar.experimental.graphserver.model.Employee;

public class Mutation implements GraphQLMutationResolver {
    private FakeDao dao;

    public Mutation(FakeDao dao) {
        this.dao = dao;
    }

    public Employee addEmployee(String firstName, String lastName, String phone){
        return dao.addEmployee(firstName,lastName,phone);
    }

    public Department addDepartment(String name){
        return dao.addDepartment(name);
    }

    public boolean addEmployeeToDepartment(int empId, int depId){
       return dao.addEmployeeToDepartment(empId, depId);
    }
}
