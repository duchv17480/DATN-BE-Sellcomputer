package com.example.udpm14sellcomputerpartsbackend.controller;


import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ForgotPassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.LoginRequest;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        description = "Authentication controller",
        name = "Các api về đăng nhập, đăng xuất"
)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Đăng ký tài khoản", description = "Đăng ký tài khoản")
    @PostMapping("/signup")
    public ResponseEntity<?> registerAccount(@RequestBody UserRegister userRegister) throws MessagingException {
        SampleResponse response = SampleResponse.builder().success(true).message("Đăng ký thành công").data(userService.registerAccount(userRegister, new StringBuffer("http://localhost:8080/api/v1/auth/register/verifi?code="))).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/register/verify")
    public ResponseEntity<?> verifiCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(userService.verifiCode(code));
    }

    @Operation(summary = "Đổi mật khẩu", description = "Đổi mật khẩu")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
        return ResponseEntity.ok(userService.changePassword(changePassword));
    }

    @Operation(summary = "Quên mật khẩu", description = "Quên mật khẩu")
    public ResponseEntity<?> verifiCode(@RequestParam("code") String code){
        return ResponseEntity.ok(userService.verifiCode(code));
    }

    @Operation(summary = "Đổi mật khẩu",description = "Đổi mật khẩu")
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePassword changePassword) {
       return ResponseEntity.ok(userService.changePassword(changePassword));
    }

    @Operation(summary = "Quên mật khẩu",description = "Quên mật khẩu")

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        return ResponseEntity.ok(userService.forgotPassword(forgotPassword));
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("Dang nhap thanh cong");
    }
}
