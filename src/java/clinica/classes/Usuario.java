package clinica.classes;

import java.util.Calendar;

public class Usuario {
    private Integer id;
    private String cpf;
    private String nome;
    private Calendar dataNascimento;
    
    public Usuario() {
        this.id = null;
        this.cpf = null;
        this.nome = null;
    }
    
    public Usuario(Integer id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
