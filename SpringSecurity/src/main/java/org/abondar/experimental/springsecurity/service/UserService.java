package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserData;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    private Map<String,String> userDataStore;

    public UserService() {
        this.userDataStore = new HashMap<>();
    }

    public void addOrUpdateStore(UserData userRequest) {
        var hash = PasswordUtil.createHash(userRequest.password());
        userDataStore.put(userRequest.login(),hash);
    }


    public Optional<UserData> find(String username) {
        var pwd = userDataStore.get(username);
        if (pwd==null){
            return Optional.empty();
        } else {
            return Optional.of(new UserData(username,pwd));
        }

    }

    public void delete(String username){
        userDataStore.remove(username);
    }
}
