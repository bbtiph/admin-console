package kaz.post.crmserver.dto;

import lombok.Data;

@Data
public class ChangePasswordDto {
    private String password;
    private UserDTO userDTO;
}
