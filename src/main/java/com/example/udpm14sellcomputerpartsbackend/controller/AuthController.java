package com.example.udpm14sellcomputerpartsbackend.controller;

import com.example.udpm14sellcomputerpartsbackend.model.entity.UserEntity;
import com.example.udpm14sellcomputerpartsbackend.payload.request.ChangePassword;
import com.example.udpm14sellcomputerpartsbackend.payload.request.UserRegister;
import com.example.udpm14sellcomputerpartsbackend.payload.response.BaseResponse;
import com.example.udpm14sellcomputerpartsbackend.payload.response.SampleResponse;
import com.example.udpm14sellcomputerpartsbackend.repository.UserRepository;
import com.example.udpm14sellcomputerpartsbackend.service.UserService;
import com.example.udpm14sellcomputerpartsbackend.ultil.HashUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;

    public AuthController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/singup")
    public ResponseEntity<?> registerAccount(@RequestBody UserRegister userRegister) throws MessagingException {
        SampleResponse response = SampleResponse.builder().status(true).message("Đăng ký thành công").data(userService.registerAccount(userRegister, new StringBuffer("http://localhost:8080/api/v1/auth/register/verifi?code="))).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/regiter/verifi")
    public ResponseEntity<?> verifiCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(userService.verifiCode(code));
    }

    @PostMapping("/change-password")
    public @ResponseBody BaseResponse changePassword(@RequestBody ChangePassword changePassword) {
        Optional<UserEntity> user = userRepository.findByUsername(changePassword.getUserName());
        UserEntity userEntity = user.get();
        if (HashUtil.verify(changePassword.getPassOld(), userEntity.getPassword()) == false) {
            return BaseResponse.error("Mật khẩu cũ không chính xác");
        } else if (!changePassword.getPassConfirm().equals(changePassword.getPassNew())) {
            return BaseResponse.error("Mật khẩu xác nhận không khớp");
        } else if (HashUtil.verify(changePassword.getPassNew(), userEntity.getPassword())) {
            return BaseResponse.error("Mật khẩu mới trùng với mật khẩu cũ");
        } else {
            if (changePassword.getPassNew().length() <= 6) {
                return BaseResponse.error("Mật khẩu phải lớn hơn 6 kí tự");
            } else {
                userEntity.setPassword(HashUtil.hash(changePassword.getPassNew()));
                UserEntity userResponse = userRepository.save(userEntity);
                return BaseResponse.success("Đổi mật khẩu thành công").withData(userResponse);
            }
        }
    }


}
