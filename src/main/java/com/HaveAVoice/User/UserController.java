package com.HaveAVoice.User;

import com.HaveAVoice.User.BLL.UserService;
import com.HaveAVoice.Vote.Vote;
import com.HaveAVoice.shared.Response.ResponseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    public UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<ResponseService<List<UserDB>>> getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ResponseService<UserDB>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ResponseService<UserDB>> getUserByName(@PathVariable String name) {
        return ResponseEntity.ok().body(userService.getUserByUsername(name));
    }
    @GetMapping("/user/{id}/votes")
    public ResponseEntity<ResponseService<List<Vote>>> getUserVotesByUserId(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.getVotesForUser(id));
    }
    @PostMapping
    public ResponseEntity<ResponseService<UserDB>> addUser(@RequestBody @Valid UserDB user) {
        ResponseService<UserDB> response = userService.addUser(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PutMapping
    public ResponseEntity<ResponseService<UserDB>> updateUser(@RequestBody @Valid UserDB user) {
        return ResponseEntity.ok().body(userService.updateUser(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseService<Void>> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}
