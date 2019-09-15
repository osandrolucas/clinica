package clinica.bean;

import clinica.classes.Consulta;
import clinica.classes.Medico;
import clinica.classes.Paciente;
import clinica.dao.ConsultaDao;
import clinica.dao.MedicoDao;
import clinica.dao.PacienteDao;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.PrimeFaces;

@ManagedBean
@SessionScoped

public class ConsultaBean implements Serializable {
    private PrimeFaces current = PrimeFaces.current();
    private ConsultaDao consultaDao = new ConsultaDao();
    private PacienteDao pacienteDao = new PacienteDao();
    private MedicoDao medicoDao = new MedicoDao();
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;
    private Consulta consulta = new Consulta();
    
    private Integer idPaciente = null;
    private Integer idMedico = null;
    
    public ConsultaBean() {
        carregarDados();
    }
    
    public void novo() {
        consulta = new Consulta();
        idPaciente = null;
        idMedico = null;
        
        current.executeScript("PF('dialogo').show();");
    }
    
    public void carregar(Integer id) {
        consulta = consultaDao.findByID(id);
        idPaciente = consulta.getPaciente().getId();
        idMedico = consulta.getMedico().getId();
        current.executeScript("PF('dialogo').show();");
    }
    
    public void cancelar(Integer id) {
        consultaDao.deleteById(id);
        carregarDados();
    }
    
    public void salvar() {
        consulta.setPaciente(pacienteDao.findByID(idPaciente));
        consulta.setMedico(medicoDao.findByID(idMedico));
        consultaDao.insert(consulta);
        current.executeScript("PF('dialogo').hide();");
        carregarDados();
    }

    public Integer getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Integer idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Integer idMedico) {
        this.idMedico = idMedico;
    }
    
    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    public List<Medico> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<Medico> medicos) {
        this.medicos = medicos;
    }
    
    private void carregarDados() {
        pacientes = pacienteDao.listAll();
        medicos = medicoDao.listAll();
        consultas = consultaDao.listAll();
    }
}
