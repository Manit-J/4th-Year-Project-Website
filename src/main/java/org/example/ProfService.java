package org.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCrypt;


@Service
public class ProfService {

    //store professor username and password
    private Map<String, ProfLogin> profLoginMap = new HashMap<>();

    public ProfService() {
        //hash the password and store both in hashmap
        String hashed = BCrypt.hashpw("password123", BCrypt.gensalt());
        profLoginMap.put("user", new ProfLogin("user", hashed));
    }

    //authenticate by checking if password is the same as stored
    public boolean authenticate(String username, String password) {
        ProfLogin user = profLoginMap.get(username);
        if (user == null) {
            return false;
        }

        return BCrypt.checkpw(password, user.getPassword());
    }
}
