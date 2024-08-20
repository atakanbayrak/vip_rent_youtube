package org.app.vip_rent.controller.user;

import lombok.RequiredArgsConstructor;
import org.app.vip_rent.modal.entity.user.User;
import org.app.vip_rent.request.UserRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;
import org.app.vip_rent.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/v1")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //This is getmapping for getting all users
    @GetMapping("getAllUsers")
    public DataResult<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("getUserById")
    public DataResult<User> getUserById(@RequestParam Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("saveUser")
    public Result saveUser(@RequestBody UserRequest user) {
        return userService.saveUser(user);
    }

    @PostMapping("updateUser")
    public Result updateUser(@RequestParam Long userId,@RequestBody UserRequest user) {
        return userService.updateUser(userId, user);
    }

    @PostMapping("deleteUser")
    public Result deleteUser(@RequestParam Long userId) {
        return userService.deleteUser(userId);
    }
}
