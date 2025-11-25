package org.example;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.crypto.bcrypt.BCrypt;


@Service
public class ProfService {

    private Map<String, ProfLogin> profLoginMap = new HashMap<>();

    public ProfService() {
        // Create a sample user with username "user" and password "password123"
        // Password is hashed using BCrypt
        String hashed = BCrypt.hashpw("password123", BCrypt.gensalt());
        profLoginMap.put("user", new ProfLogin("user", hashed));
    }

    public boolean authenticate(String username, String password) {
        ProfLogin user = profLoginMap.get(username);
        if (user == null) return false;

        // Check password against stored hashed password
        return BCrypt.checkpw(password, user.getPassword());
    }
}
