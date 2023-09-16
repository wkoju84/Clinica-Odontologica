package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Role;

import java.io.Serial;
import java.io.Serializable;

public class RoleDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String autoridade;

    public RoleDto() {
    }

    public RoleDto(Integer id, String autoridade) {
        this.id = id;
        this.autoridade = autoridade;
    }

    public RoleDto(Role entidade) {
        this.id = entidade.getId();
        this.autoridade = entidade.getAutoridade();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAutoridade() {
        return autoridade;
    }

    public void setAutoridade(String autoridade) {
        this.autoridade = autoridade;
    }
}
