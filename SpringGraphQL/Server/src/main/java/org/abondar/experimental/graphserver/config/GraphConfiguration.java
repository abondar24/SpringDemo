package org.abondar.experimental.graphserver.config;

import org.abondar.experimental.graphserver.dao.FakeDao;
import org.abondar.experimental.graphserver.resolvers.EmployeeResolver;
import org.abondar.experimental.graphserver.resolvers.Mutation;
import org.abondar.experimental.graphserver.resolvers.Query;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphConfiguration {

    @Bean
    public FakeDao dao(){
        return new FakeDao();
    }

    @Bean
    public  Query queryResolver(FakeDao dao){
        return new Query(dao);
    }

    @Bean
    public Mutation mutationResolver(FakeDao dao){
        return new Mutation(dao);
    }

    @Bean
    public EmployeeResolver employeeResolver(FakeDao dao){
        return new EmployeeResolver(dao);
    }

}
