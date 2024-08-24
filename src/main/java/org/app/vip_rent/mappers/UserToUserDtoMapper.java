package org.app.vip_rent.mappers;

import org.app.vip_rent.modal.dto.UserDto;
import org.app.vip_rent.modal.entity.user.User;

public class UserToUserDtoMapper {

    public static UserDto mapToUserDtoMapper(User user){
        return UserDto.builder()
                .userName(user.getUserName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}
