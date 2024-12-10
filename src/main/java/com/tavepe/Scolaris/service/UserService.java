package com.tavepe.Scolaris.service;



import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.tavepe.Scolaris.dto.UserDTO;
import com.tavepe.Scolaris.model.UserEntity;
import com.tavepe.Scolaris.repository.UserRepository;
import com.tavepe.Scolaris.utils.Cryptography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository usersRepository) {
        this.userRepository = usersRepository;
    }

    public UserEntity createUser(UserEntity user) {
        user.setId(null);
        user.setPassword(Cryptography.getMD5(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    public UserEntity updateUser(Integer userId, UserEntity userRequest) {

        UserEntity user = getUserById(userId);
        user.setName(userRequest.getName());
        user.setPassword(Cryptography.getMD5(userRequest.getPassword()));
        user.setType(userRequest.getType());
        user.setUser(userRequest.getUser());
        userRepository.save(user);
        return user;

    }

    private List<UserEntity> listAllUsers() {
        return userRepository.findAll();
    }
    public List<UserDTO> listAllUsersDTO() {
        List<UserEntity> users = listAllUsers();

        return getUserDTOS(users);
    }

    private List<UserEntity> listByType(String type) {
        return userRepository.findByType(type);
    }

    public List<UserDTO> getUsersDTOByType(String type) {
        List<UserEntity> users = listByType(type);

        return getUserDTOS(users);
    }


    public UserEntity getUserById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }
    public UserDTO getUserDTOById(Integer userId) {
        UserEntity userEntity=getUserById(userId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUser(userEntity.getUser());
        userDTO.setName(userEntity.getName());
        userDTO.setType(userEntity.getType());
        return userDTO;
    }
    public List<UserEntity> getAllUsersById(Set<Integer> userIds) {
        return userRepository.findAllById(userIds);
    }

    public void deleteUser(Integer userId) {
        UserEntity user = getUserById(userId);
        userRepository.deleteById(user.getId());
    }

    private List<UserDTO> getUserDTOS(List<UserEntity> users) {
        return users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setName(user.getName());
            userDTO.setType(user.getType());
            userDTO.setUser(user.getUser());
            return userDTO;

        }).collect(Collectors.toList());
    }

}