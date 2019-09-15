package clinica.bean;

import clinica.classes.Paciente;
import clinica.dao.PacienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped

public class PacienteBean implements Serializable {
    private PrimeFaces current = PrimeFaces.current();
    private PacienteDao pacienteDao = new PacienteDao();
    private List<Paciente> pacientes;
    private Paciente paciente = new Paciente();
    
    public PacienteBean() {
        carregarDados();
    }

    public void novo() {
        paciente = new Paciente();
        current.executeScript("PF('dialogo').show();");
    }
    
    public void alterar(Integer id) {
        paciente = pacienteDao.findByID(id);
        current.executeScript("PF('dialogo').show();");
    }

    public void excluir(Integer id) {
        pacienteDao.deleteById(id);
        carregarDados();
    }
    
    public void salvar() {
        if (paciente.getId() == null) {
            pacienteDao.insert(paciente);
        } else {
            pacienteDao.update(paciente);
        }
        
        current.executeScript("PF('dialogo').hide();");
        paciente = new Paciente();
        carregarDados();
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    private void carregarDados() {
        pacientes = pacienteDao.listAll();
    }
}
