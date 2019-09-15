package clinica.classes;

public class Medico extends Usuario {
    private String registro;
    private String especialidade;

    public Medico() {
        super();
        registro = null;
        especialidade = null;
    }
    
    public Medico(Integer id, String nome, String cpf, String registro, String especialidade) {
        super(id, cpf, nome);
        this.registro = registro;
        this.especialidade = especialidade;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
