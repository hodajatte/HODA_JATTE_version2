package com.example.hoda_jatte_anissa.Service;

import com.example.hoda_jatte_anissa.DAO.UserDao;
import com.example.hoda_jatte_anissa.Entity.Role;
import com.example.hoda_jatte_anissa.Entity.User;
import com.example.hoda_jatte_anissa.Repository.RoleRepository;
import com.example.hoda_jatte_anissa.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

   /* @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }*/

    /*@Override
    public void saveUser(UserDto userDto, String roleName) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        //encrypt the password once we integrate spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // Check the selected role
        String selectedRole = userDto.getSelectedRole();
        Role role;
        if ("Admin".equals(selectedRole)) {
            role = roleRepository.findByName("ROLE_ADMIN");
        } else {
            role = roleRepository.findByName("ROLE_USER");
        }

        if (role == null) {
            role = new Role();
            role.setName(selectedRole);
            role = roleRepository.save(role);
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }*/







    @Override
    public void saveUser(UserDao userDao, String roleName) {
        User user = new User();
        user.setName(userDao.getFirstName() + " " + userDao.getLastName());
        user.setEmail(userDao.getEmail());

        //encrypt the password once we integrate spring security
        user.setPassword(passwordEncoder.encode(userDao.getPassword()));

        // Check the selected role
        String selectedRole = userDao.getSelectedRole();
        Role role = roleRepository.findByName(selectedRole);

        if (role == null) {
            role = new Role();
            role.setName(selectedRole);
            role = roleRepository.save(role);
        }

        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }














    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDao> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDao(user))
                .collect(Collectors.toList());
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    private UserDao convertEntityToDao(User user){
        UserDao userDao = new UserDao();
        String[] name = user.getName().split(" ");
        userDao.setFirstName(name[0]);
        userDao.setLastName(name[1]);
        userDao.setEmail(user.getEmail());
        return userDao;
    }



  /* private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }*/
}
