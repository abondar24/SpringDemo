package org.abondar.experimental.graphclient;


import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.InputFieldWriter;
import com.apollographql.apollo.exception.ApolloException;
import com.apollographql.apollo.internal.json.InputFieldJsonWriter;
import okhttp3.OkHttpClient;
import org.abondar.experimental.graphclient.client.AddDepartmentMutation;
import org.abondar.experimental.graphclient.client.AddEmployeeMutation;
import org.abondar.experimental.graphclient.client.GetDepartmentsQuery;
import org.abondar.experimental.graphclient.client.GetEmployeesQuery;
import org.abondar.experimental.graphclient.model.Department;
import org.abondar.experimental.graphclient.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {

    private static String ENDPOINT = "http://localhost:8080/qlendpoint";
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        ApolloClient client = ApolloClient.builder().serverUrl(ENDPOINT).okHttpClient(new OkHttpClient()).build();

        try {
            Employee employee = addEmployee(client);
            Department department = addDepartment(client);
            getEmployees(client);
            getDepartments(client,2,0);

        } catch (ApolloException ex) {
            logger.error(ex.getMessage());
        }


    }

    private static Employee addEmployee(ApolloClient client) throws ApolloException {
        Optional<AddEmployeeMutation.Data> data = client.mutate(new AddEmployeeMutation()).execute().data();
        Employee res = new Employee();

        if (data.isPresent()) {
            AddEmployeeMutation.AddEmployee emp = data.get().addEmployee();
            int id = Integer.valueOf(emp.id());
            String firstName = emp.firstName();
            String lastName = emp.lastName();
            String phone = emp.phone().orElse("");

            res.setId(id);
            res.setFirstName(firstName);
            res.setLastName(lastName);
            res.setPhone(phone);

            logger.info("Got a new employee: " + res.toString());
        }

        return res;

    }


    private static Department addDepartment(ApolloClient client) throws ApolloException {

        Optional<AddDepartmentMutation.Data> data = client.mutate(new AddDepartmentMutation()).execute().data();
        Department res = new Department();

        if (data.isPresent()) {
            AddDepartmentMutation.AddDepartment dep = data.get().addDepartment();
            int id = Integer.valueOf(dep.id());
            String name = dep.name();

            res.setId(id);
            res.setName(name);

            logger.info("Got a new department: " + res.toString());
        }

        return res;

    }

    private static void getEmployees(ApolloClient client) throws ApolloException {

        Optional<GetEmployeesQuery.Data> data = client.query(new GetEmployeesQuery()).execute().data();

        if (data.isPresent()) {
            List<Employee> emps = data.get().getEmployees().stream()
                    .map(Main::extractEmployee).collect(Collectors.toList());

            emps.forEach(e -> logger.info("Fetched employee: " + e));
        }
    }

    private static void getDepartments(ApolloClient client,int count,int offset) throws ApolloException {
        Optional<GetDepartmentsQuery.Data> data = client.query(new GetDepartmentsQuery()).execute().data();

        if (data.isPresent()) {
            List<Department> deps = data.get().getDepartments().stream()
                    .map(Main::extractDepartment).collect(Collectors.toList());

            deps.forEach(d -> logger.info("Fetched department " + d));
        }
    }


    private static Employee extractEmployee(GetEmployeesQuery.GetEmployee ge) {
        Employee res = new Employee();

        res.setId(Integer.valueOf(ge.id()));
        res.setFirstName(ge.firstName());
        res.setLastName(ge.lastName());
        res.setPhone(ge.phone().orElse(""));

        String depName = ge.department().name();
        res.setDepartment(new Department(depName));

        return res;
    }

    private static Department extractDepartment(GetDepartmentsQuery.GetDepartment gd) {
        Department res = new Department();

        res.setName(gd.name());
        List<Employee> emps = gd.employees()
                .stream().map(Main::extractDepartmentEmployee).collect(Collectors.toList());
        res.setEmployees(emps);
        return res;
    }


    private static Employee extractDepartmentEmployee(GetDepartmentsQuery.Employee em) {
        Employee res = new Employee();
        res.setId(Integer.valueOf(em.id()));

        return res;
    }
}
