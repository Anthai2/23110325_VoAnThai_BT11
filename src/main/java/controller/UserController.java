package controller;

import entity.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.UserInfoRepository;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserInfoRepository repository;

    public UserController(UserInfoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/new")
    public ResponseEntity<String> addUser(@RequestBody UserInfo user) {
        repository.save(user);
        return ResponseEntity.ok("Thêm user thành công!");
    }
}
