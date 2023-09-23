package com.example.hoda_jatte_anissa.Service;

import com.example.hoda_jatte_anissa.DAO.UserDao;
import com.example.hoda_jatte_anissa.Entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDao userDao, String roleName);

    User findByEmail(String email);

    List<UserDao> findAllUsers();
    public boolean checkPassword(User user, String password);
}
