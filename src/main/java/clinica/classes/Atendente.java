package clinica.classes;

public class Atendente extends Usuario {
    private boolean admin;

    public Atendente() {
        super();
        admin = false;
    }
    
    public Atendente(Integer id, String nome, String cpf, boolean admin) {
        super(id, cpf, nome);
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
