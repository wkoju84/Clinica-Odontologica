package br.com.dh.clinica.dtos;

import br.com.dh.clinica.entities.Endereco;
import br.com.dh.clinica.entities.Paciente;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PacienteDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String nome;
    private String email;
    private String cpf;

    private LocalDate dataCadastro;

    private Set<EnderecoDto> enderecos = new HashSet<>();

    public PacienteDto() {
    }

    public PacienteDto(Integer id, String nome, String email, String cpf, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
    }

    public PacienteDto(Paciente paciente) {
        id = paciente.getId();
        nome = paciente.getNome();
        email = paciente.getEmail();
        cpf = paciente.getCpf();
        dataCadastro = paciente.getDataCadastro();
        paciente.getEnderecos().forEach(end -> this.enderecos.add(new EnderecoDto(end)));
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Set<EnderecoDto> getEnderecos() {
        return enderecos;
    }
}
