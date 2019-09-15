package clinica.classes;

public class Paciente extends Usuario {
    private Endereco endereco;
    private String telefone1, telefone2;

    public Paciente() {
        super();
        endereco = new Endereco();
    }
    
    public Paciente(Integer id, String nome, String cpf, Endereco endereco, String telefone1, String telefone2) {
        super(id, cpf, nome);
        this.endereco = endereco;
        this.telefone1 = telefone1;
        this.telefone2 = telefone2;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone) {
        this.telefone1 = telefone;
    }

    public String getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(String telefone) {
        this.telefone2 = telefone;
    }
}
