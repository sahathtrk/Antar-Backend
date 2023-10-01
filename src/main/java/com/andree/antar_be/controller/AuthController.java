package com.andree.antar_be.controller;

import com.andree.antar_be.dto.request.*;
import com.andree.antar_be.dto.response.ResponseCheckUser;
import com.andree.antar_be.models.User;
import com.andree.antar_be.service.CustomerService;
import com.andree.antar_be.service.DriverService;
import com.andree.antar_be.service.UserService;
import com.andree.antar_be.shared.BaseResponse;
import com.andree.antar_be.utils.IException;
import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final CustomerService customerService;
    private final DriverService driverService;



    @Autowired
    public AuthController(UserService service, CustomerService customerService, DriverService driverService){
        this.userService = service;
        this.customerService = customerService;
        this.driverService = driverService;
    }

    @PostMapping("register")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody RequestRegister request) throws IException {
        User user = request.toUser();
        this.userService.registerUser(user);
        Map<String, Object> respond = this.userService.createToken(user);
        return BaseResponse.responseSuccess(respond,"Success to insert data", 201);
    }

    @PostMapping("check")
    public ResponseEntity<Object> checkUser(@Valid @RequestBody RequestCheckUser requestCheckUser){
        ResponseCheckUser responseCheckUser = this.userService.checkUserExits(requestCheckUser.getEmail());
        return BaseResponse.responseSuccess(responseCheckUser, "success to check user");
    }

    @PostMapping("register/customer")
    public ResponseEntity<Object> registerCustomer(@Valid @RequestBody RequestRegisterCustomer request) throws IException, ParseException {
        User user = this.userService.finByID(request.getUserID());
        if(user.getRole() != null){
            throw new IException("400001", "User already registered", 400);
        }
        this.customerService.registerCustomer(request.toCustomer(user));
        this.userService.updateRole("Customer", request.getUserID());
        return BaseResponse.responseSuccess("success to register customer", 201);
    }

    @PostMapping("register/driver")
    public ResponseEntity<Object> registerDriver(@Valid @RequestBody RequestRegisterDriver request) throws IException, ParseException {
        User user = this.userService.finByID(request.getUserID());
        if(user.getRole() != null){
            throw new IException("400001", "User already registered", 400);
        }
        this.driverService.registerDriver(request.toDriver(user));
        this.userService.updateRole("Customer", request.getUserID());
        return BaseResponse.responseSuccess("success to register driver", 201);
    }

    @PostMapping("login")
    public ResponseEntity<Object> loginRegister(@Valid @RequestBody RequestLogin request) throws IException {
        User user = this.userService.authentication(request.getEmail(), request.getPassword());
        Map<String, Object> respond = this.userService.createToken(user);
        return BaseResponse.responseSuccess(respond, "Success to login", 200);
    }

    @PostMapping("refresh")
    public ResponseEntity<Object> refreshToken(@RequestAttribute("claims")Claims claims){
        User user = this.userService.finByID(claims.get("ID").toString());
        Map<String, Object> respond = this.userService.createToken(user);
        return BaseResponse.responseSuccess(respond, "Success to refresh", 200);
    }

}
