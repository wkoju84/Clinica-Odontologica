package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Usuario;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String primeroNome;
    private String ultimoNome;
    private String email;

    Set<RoleDto> roles = new HashSet<>();

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, String primeroNome, String ultimoNome, String email) {
        this.id = id;
        this.primeroNome = primeroNome;
        this.ultimoNome = ultimoNome;
        this.email = email;
    }

    public UsuarioDto(Usuario entidade) {
        id = entidade.getId();
        primeroNome = entidade.getPrimeroNome();
        ultimoNome = entidade.getUltimoNome();
        email = entidade.getEmail();
        entidade.getRoles().forEach(x -> this.roles.add(new RoleDto(x)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrimeroNome() {
        return primeroNome;
    }

    public void setPrimeroNome(String primeroNome) {
        this.primeroNome = primeroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDto> getRoles() {
        return roles;
    }
}
