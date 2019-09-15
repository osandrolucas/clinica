package clinica.classes;

import java.util.Date;

public class Consulta {
    private Integer id;
    private Paciente paciente;
    private Medico medico;
    private Date dataHora;

    public Consulta() {
        id = null;
        paciente = new Paciente();
        medico = new Medico();
        dataHora = new Date();
    }

    public Consulta(Integer id, Paciente paciente, Medico medico, long dataHora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = new Date(dataHora);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }
}
