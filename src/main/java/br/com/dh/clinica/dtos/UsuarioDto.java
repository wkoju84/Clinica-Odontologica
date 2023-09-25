package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Usuario;

import javax.persistence.Column;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String sobrenome;
    private String email;

    Set<RoleDto> roles = new HashSet<>();

    public UsuarioDto() {
    }

    public UsuarioDto(Integer id, String nome, String sobrenome, String email) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
    }

    public UsuarioDto(Usuario entidade) {
        id = entidade.getId();
        nome = entidade.getNome();
        sobrenome = entidade.getSobrenome();
        email = entidade.getEmail();
        entidade.getRoles().forEach(x -> this.roles.add(new RoleDto(x)));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
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
