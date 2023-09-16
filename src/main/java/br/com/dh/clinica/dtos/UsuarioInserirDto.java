package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Usuario;

import java.io.Serial;

public class UsuarioInserirDto extends UsuarioDto{
    @Serial
    private static final long serialVersionUID = 1L;

    private String senha;

    public UsuarioInserirDto() {
       super();
    }

    public UsuarioInserirDto(Usuario usuario) {
        super();
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
