package br.com.dh.clinica.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private Integer cro;
    private boolean atendeConvenio;

    public Dentista() {
    }

    public Dentista(Integer id, String nome, String email, Integer cro, boolean atendeConvenio) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cro = cro;
        this.atendeConvenio = atendeConvenio;
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

    public Integer getCro() {
        return cro;
    }

    public void setCro(Integer cro) {
        this.cro = cro;
    }

    public boolean isAtendeConvenio() {
        return atendeConvenio;
    }

    public void setAtendeConvenio(boolean atendeConvenio) {
        this.atendeConvenio = atendeConvenio;
    }
}
