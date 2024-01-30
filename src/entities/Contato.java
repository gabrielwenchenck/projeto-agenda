package entities;

import java.util.List;
import java.util.Objects;

public class Contato {
    private static long proximoIdContato = 1;

    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;

    public Contato(String nome, String sobreNome, List<Telefone> telefones) {
        this.id = proximoIdContato++;
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.telefones = telefones;
    }

    public Contato() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public void setSobreNome(String sobreNome) {
        this.sobreNome = sobreNome;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(telefones, contato.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, telefones);
    }

    @Override
    public String toString() {
        return id + " | " + nome + " " + sobreNome;
    }
}
