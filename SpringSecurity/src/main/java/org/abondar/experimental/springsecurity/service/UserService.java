package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private List<UserData> userDataStore;

    public UserService() {
        this.userDataStore = new ArrayList<>();
    }

    public void add(UserData userData) {
        userDataStore.add(userData);
    }

    public Optional<UserData> find(String username) {
        return userDataStore.stream()
                .filter(cr -> cr.login().equals(username))
                .findFirst();
    }

    public void delete(String username){
        var creds = find(username);
        creds.ifPresent(userData -> userDataStore.remove(userData));

    }
}
