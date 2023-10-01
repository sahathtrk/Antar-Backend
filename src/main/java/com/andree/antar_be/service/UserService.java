package com.andree.antar_be.service;

import com.andree.antar_be.dto.response.ResponseCheckUser;
import com.andree.antar_be.models.User;
import com.andree.antar_be.repository.UserRepository;
import com.andree.antar_be.utils.IException;
import com.andree.antar_be.utils.JWTToken;
import com.andree.antar_be.utils.Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JWTToken jwtToken;

    @Autowired
    public UserService(UserRepository userRepository, JWTToken jwtToken) {
        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
    }

    public User findByEmail(String email) {
        Optional<User> userOptional = this.userRepository.
                findByEmail(email);
        return userOptional.orElseThrow();
    }

    public User finByID(String id) {
        Optional<User> userOptional = this.userRepository.findById(id);
        return userOptional.orElseThrow();
    }

    public User authentication(String email, String password) throws IException {
        try {
            Optional<User> userOptional = this.userRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                if (Password.ComparePassword(password, userOptional.get().getPassword())) {
                    return userOptional.get();
                }
            }
            throw new IException("401001", "check your password or email", 401);
        } catch (Exception e) {
            if (e instanceof IException) {
                throw e;
            }
            throw new IException("500000", e.getLocalizedMessage(), 500);
        }
    }

    public Map<String, Object> createToken(User user) {
        Map<String, Object> respond = new HashMap<>();
        respond.put("user_id", user.getId());
        Map<String, Object> token = jwtToken.generateToken(user);
        String refreshToken = jwtToken.generateRefreshToken(user);
        respond.put("access_token", token.get("token"));
        respond.put("expired_at", token.get("expired"));
        respond.put("refresh_token", refreshToken);
        return respond;
    }

    public void registerUser(User user) throws IException {
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IException("400000", "body in bad request", 400);
        } catch (Exception e) {
            throw new IException("500000", e.getLocalizedMessage(), 500);
        }
    }

    public void updateRole(String role, String userID) throws IException {
        try {
            Optional<User> userOptional = this.userRepository.findById(userID);
            if (!userOptional.isPresent()) {
                throw new IException("404000", "User not found", 404);
            }
            User user = userOptional.get();
            user.setRole(role);
            this.userRepository.save(user);
        } catch (Exception e) {
            throw new IException("500000", e.getLocalizedMessage(), 500);
        }
    }

    public ResponseCheckUser checkUserExits(String email) {
        Optional<User> userOptional = this.userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            return ResponseCheckUser.builder()
                    .isRegister(true)
                    .userId(userOptional.get().getId())
                    .build();
        } else {
            return ResponseCheckUser.builder().isRegister(false).build();
        }
    }
}
