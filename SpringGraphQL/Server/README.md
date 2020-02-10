# Demo GraphQL Server

This a demo GraphQL server based on Spring boot.

Server GraphQL endpoint: http://localhost:8080/qlendpoint
Server GraphiQL endpoint for query testing: http://localhost:8080/graphiql

## Build and run
```yaml
mvn clean install
java -jar target/graph-server.jar or mvn spring-boot:run
 
```

## Example queries
```yaml
mutation {
  addEmployee(firstName: "Alex", lastName:"Bondar", phone: "+7923221213"){
    firstName
    lastName
    phone
  }
}
```
```yaml
mutation {
  addDepartment(name:"Experimental development"){
    name
  }
}

```

```yaml
mutation {
  addEmployeeToDepartment(empId:-560557355, depId:832753966)
}

```
```yaml
{
  getEmployees(count:2,offset:0){
    id,
    firstName,
    lastName,
    phone,
    department{
      name
    }
  }
}

```
```yaml
{
  getDepartments(count:2,offset:0){
   name,
    employees{
      id
    }
  }
}

```
```yaml
{
  getEmployeesInDepartment(depId:832753966, count:2,offset:0){
   id,
    firstName
  }
}

```




