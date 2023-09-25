package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.User;

public class UserUpdateDto extends UserDto {
    private static final long serialVersionUID = 1L;

    public UserUpdateDto() {
    }

    public UserUpdateDto(User user) {
        super(user);
    }
}
