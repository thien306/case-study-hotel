package com.codegym.service.impl;

import com.codegym.model.User;
import com.codegym.model.dto.UpdatePasswordRequest;
import com.codegym.model.dto.UserDto;
import com.codegym.repository.IUserRepository;
import com.codegym.service.Interface.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserRepository userRepository;
    private final ModelMapper modelMapper;

//    public UserServiceImpl(IUserRepository userRepository, ModelMapper modelMapper) {
//        this.userRepository = userRepository;
//        this.modelMapper = modelMapper;
//    }

    @Override
    public Iterable<UserDto> findAll() {
        Iterable<User> entities = userRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), true)
                .map(entity -> modelMapper.map(entity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return Optional.ofNullable(modelMapper.map(user, UserDto.class));
    }

    @Override
    public void save(User user) {
        if(!user.getPassword().isEmpty()){
            String hashedPassword = BCrypt.hashpw(user.getPassword(),BCrypt.gensalt(10));
            user.setPassword(hashedPassword);
        }
        userRepository.save(user);

    }

//    @Override
//    public void save(UserDto userDto) {
//        User user = modelMapper.map(userDto, User.class);
//        if (!userDto.getPassword().isEmpty()) {
//            String hashedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10));
//            user.setPassword(hashedPassword);
//        }
//        userRepository.save(user);
//    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean changePassword(UpdatePasswordRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        if (username != null){
            User user = userRepository.findByUsername(username);
            if(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        return modelMapper.map(user, UserDto.class);
    }
}