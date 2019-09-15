package clinica.bean;

import clinica.classes.Atendente;
import clinica.dao.AtendenteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped

public class AtendenteBean implements Serializable {
    private PrimeFaces current = PrimeFaces.current();
    private AtendenteDao atendenteDao = new AtendenteDao();
    private List<Atendente> atendentes;
    private Atendente atendente = new Atendente();

    public AtendenteBean() {
        carregarDados();
    }

    public void novo() {
        atendente = new Atendente();
        current.executeScript("PF('dialogo').show();");
    }
    
    public void alterar(Integer id) {
        atendente = atendenteDao.findByID(id);
        current.executeScript("PF('dialogo').show();");
    }

    public void excluir(Integer id) {
        atendenteDao.deleteById(id);
        carregarDados();
    }
    
    public void salvar() {
        if (atendente.getId() == null) {
            atendenteDao.insert(atendente);
        } else {
            atendenteDao.update(atendente);
        }
        
        current.executeScript("PF('dialogo').hide();");
        atendente = new Atendente();
        carregarDados();
    }

    public Atendente getAtendente() {
        return atendente;
    }

    public void setAtendente(Atendente atendente) {
        this.atendente = atendente;
    }

    public List<Atendente> getAtendentes() {
        return atendentes;
    }

    public void setAtendentes(List<Atendente> atendentes) {
        this.atendentes = atendentes;
    }
    
    private void carregarDados() {
        atendentes = atendenteDao.listAll();
    }
}
