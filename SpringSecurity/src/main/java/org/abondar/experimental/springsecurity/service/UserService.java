package org.abondar.experimental.springsecurity.service;

import org.abondar.experimental.springsecurity.model.UserData;
import org.abondar.experimental.springsecurity.model.UserCreateRequest;
import org.abondar.experimental.springsecurity.model.UserResponse;
import org.abondar.experimental.springsecurity.util.PasswordUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final Map<String, UserData> userDataStore;

    public UserService() {
        this.userDataStore = new HashMap<>();
    }

    public UserResponse addOrUpdateStore(UserCreateRequest userCreateRequest) {
        var hash = PasswordUtil.createHash(userCreateRequest.password());
        var id = UUID.randomUUID().toString();
        userDataStore.put(id,new UserData(userCreateRequest.login(),hash, userCreateRequest.roles()));

        return new UserResponse(id)
;    }


    public Optional<UserData> find(String id) {
        var data = userDataStore.get(id);
        if (data==null){
            return Optional.empty();
        } else {
            return Optional.of(data);
        }

    }

    public Optional<UserData> findByUsername(String username){
        return  userDataStore.values()
                .stream()
                .filter(ud-> ud.login().equals(username))
                .findFirst();
    }

    public void delete(String id){
        userDataStore.remove(id);
    }
}
