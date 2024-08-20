package org.app.vip_rent.service.user;

import org.app.vip_rent.modal.entity.user.User;
import org.app.vip_rent.request.UserRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;

import java.util.List;

public interface IUserService {
    DataResult<List<User>> getAllUsers();
    DataResult<User> getUserById(Long id);

    Result saveUser(UserRequest user);
    Result updateUser(Long userId, UserRequest user);
    Result deleteUser(Long userId);
}
