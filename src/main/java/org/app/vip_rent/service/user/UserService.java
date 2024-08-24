package org.app.vip_rent.service.user;

import lombok.RequiredArgsConstructor;
import org.app.vip_rent.mappers.UserToUserDtoMapper;
import org.app.vip_rent.modal.dto.UserDto;
import org.app.vip_rent.modal.entity.user.User;
import org.app.vip_rent.repository.user.UserRepository;
import org.app.vip_rent.request.UserRequest;
import org.app.vip_rent.result.DataResult;
import org.app.vip_rent.result.Result;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService{
    private final UserRepository userRepository;
    @Override
    public DataResult<List<UserDto>> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            return new DataResult<>(null, Result.showMessage(Result.SUCCESS,"Users not found"));

        List<UserDto> dtoUsers = new ArrayList<>();
        for(User user: users)
        {
            dtoUsers.add(UserToUserDtoMapper.mapToUserDtoMapper(user));
        }
        return new DataResult<>(dtoUsers, Result.showMessage(Result.SUCCESS,"Users listed successfully"));
    }

    @Override
    public DataResult<User> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            return new DataResult<>(null, Result.showMessage(Result.SERVER_ERROR,"User not found"));
        return new DataResult<>(user.get(), Result.showMessage(Result.SUCCESS,"User found successfully"));
    }

    @Override
    public Result saveUser(UserRequest user) {


        User createdUser = User.builder()
                .userName(user.getUserName())
                .identityNumber(user.getIdentityNumber())
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();

        try
        {
            userRepository.save(createdUser);
        }
        catch (Exception e)
        {
            return null;
        }
        return Result.showMessage(Result.SUCCESS, "User saved successfully");
    }

    @Override
    public Result updateUser(Long userId, UserRequest user) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty())
            return null;

        User updatedUser = userOptional.get();
        updatedUser.setUserName(user.getUserName());
        updatedUser.setIdentityNumber(user.getIdentityNumber());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setPhoneNumber(user.getPhoneNumber());
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());

        try
        {
            userRepository.save(updatedUser);
        }
        catch (Exception e)
        {
            return null;
        }
        return Result.showMessage(Result.SUCCESS, "User updated successfully");
    }

    @Override
    public Result deleteUser(Long userId) {
        Boolean isExist = userRepository.existsById(userId);
        if(!isExist)
            Result.showMessage(Result.SERVER_ERROR,"User not found");
        try{
            userRepository.deleteById(userId);
        }
        catch (Exception e)
        {
            return Result.showMessage(Result.SERVER_ERROR,"User delete failed");
        }
        return Result.showMessage(Result.SUCCESS,"User deleted successfully");
    }

}
