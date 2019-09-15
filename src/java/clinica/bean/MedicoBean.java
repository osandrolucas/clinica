package clinica.bean;

import clinica.classes.Medico;
import clinica.dao.MedicoDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped

public class MedicoBean implements Serializable {
    private PrimeFaces current = PrimeFaces.current();
    private MedicoDao medicoDao = new MedicoDao();
    private List<Medico> medicos;
    private Medico medico = new Medico();
    
    public MedicoBean() {
        carregarDados();
    }

    public void novo() {
        medico = new Medico();
        current.executeScript("PF('dialogo').show();");
    }
    
    public void alterar(Integer id) {
        medico = medicoDao.findByID(id);
        current.executeScript("PF('dialogo').show();");
    }

    public void excluir(Integer id) {
        medicoDao.deleteById(id);
        carregarDados();
    }

    public void salvar() {
        if (medico.getId() == null) {
            medicoDao.insert(medico);
        } else {
            medicoDao.update(medico);
        }
        
        current.executeScript("PF('dialogo').hide();");
        medico = new Medico();
        carregarDados();
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
    
    private void carregarDados() {
        medicos = medicoDao.listAll();
    }
}
