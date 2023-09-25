package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.User;

public class UserInsertDto extends UserDto {
    private static final long serialVersionUID = 1L;

    private String password;

    public UserInsertDto() {
        super();
    }

    public UserInsertDto(User user) {
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
