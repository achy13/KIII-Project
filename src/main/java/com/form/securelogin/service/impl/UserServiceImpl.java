package com.form.securelogin.service.impl;

import com.form.securelogin.model.Role;
import com.form.securelogin.model.User;
import com.form.securelogin.model.UserDto;
import com.form.securelogin.repository.RoleRepository;
import com.form.securelogin.repository.UserRepository;
import com.form.securelogin.service.UserService;
import lombok.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    final private UserRepository userRepository;
    final private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                            @NonNull PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER");
        if(role == null){
            role = checkRoleExist();
        }

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
    }

    // abstraction
    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_USER");
        return roleRepository.save(role);
    }

    public void updateUserRole(Long userId, String roleName) {
        // Retrieve user from the database based on userId
        User user = userRepository.findById(userId).orElse(null);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        System.out.println(user.getName());

        // Retrieve the role from the database based on the role name
        Role role = roleRepository.findByName(roleName);
        if (role == null){
            Role newRoleToSave = new Role(roleName);
            newRoleToSave.setUsers(userList);
            role = newRoleToSave;
            roleRepository.save(newRoleToSave);
        }

        user.getRoles().clear();
        user.getRoles().add(role);
        userRepository.save(user);
    }


}