package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Role;

import java.io.Serializable;

public class RoleDto implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer id;
    public String authority;

    public RoleDto() {
    }

    public RoleDto(Integer id, String authority) {
        this.id = id;
        this.authority = authority;
    }

    public RoleDto(Role entity) {
        this.id = entity.getId();
        this.authority = entity.getAuthority();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
