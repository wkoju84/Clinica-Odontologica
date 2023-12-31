package br.com.dh.clinica.entities;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Paciente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String cpf;

    private LocalDate dataCadastro;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "paciente_endereco",
        joinColumns = @JoinColumn(name = "paciente_id"),
        inverseJoinColumns = @JoinColumn(name = "endereco_id"))
    private Set<Endereco> enderecos = new HashSet<>();

    public Paciente() {
    }

    public Paciente(Integer id, String nome, String email, String cpf, LocalDate dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataCadastro = dataCadastro;
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

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }
}
