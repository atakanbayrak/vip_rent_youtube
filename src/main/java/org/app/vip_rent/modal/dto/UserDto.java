package org.app.vip_rent.modal.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String userName;

    private String email;

    private String phoneNumber;

    private String firstName;

    private String lastName;

}
