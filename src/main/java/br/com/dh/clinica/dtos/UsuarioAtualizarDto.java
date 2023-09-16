package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Usuario;

import java.io.Serial;

public class UsuarioAtualizarDto extends UsuarioDto{
    @Serial
    private static final long serialVersionUID = 1L;

    public UsuarioAtualizarDto() {
    }
    public UsuarioAtualizarDto(Usuario usuario) {
        super(usuario);
    }

}
