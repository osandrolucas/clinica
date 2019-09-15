package clinica.dao;

import clinica.classes.Consulta;
import clinica.classes.Medico;
import clinica.classes.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

public class ConsultaDao implements GenericDao<Consulta> {
    @Override
    public int insert(Consulta consulta) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_CONSULTA.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, consulta.getPaciente().getId());
            stmt.setInt(2, consulta.getMedico().getId());
            stmt.setTimestamp(3, new Timestamp(consulta.getDataHora().getTime()));
            stmt.execute();
            
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("exceção com recursos: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");
        }

        return chavePrimaria;
    }
    
    public int deleteById(Integer id) {
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_CONSULTA.getSql())) {
            stmt.setInt(1, id);

            if (stmt.executeUpdate() > 0) {
                return 1;
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código! - delete");
        }
        return -1;
    }

    @Override
    public List<Consulta> listAll() {
        List<Consulta> lista = new LinkedList<>();
        PacienteDao pacienteDao = new PacienteDao();
        MedicoDao medicoDao = new MedicoDao();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_CONSULTA.getSql())) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idConsulta = rs.getInt("id_consulta");
                int idPaciente = rs.getInt("id_paciente");
                int idMedico = rs.getInt("id_medico");
                Timestamp dataHora = rs.getTimestamp("data_hora");

                Paciente paciente = pacienteDao.findByID(idPaciente);
                Medico medico = medicoDao.findByID(idMedico);

                lista.add(new Consulta(idConsulta, paciente, medico, dataHora.getTime()));
            }

            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!");
        }
        return null;
    }

    @Override
    public Consulta findByID(Integer id) {
        Consulta consulta = null;
        PacienteDao pacienteDao = new PacienteDao();
        MedicoDao medicoDao = new MedicoDao();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FIND_CONSULTA.getSql())) {
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int idPaciente = rs.getInt("id_paciente");
                int idMedico = rs.getInt("id_medico");
                Timestamp dataHora = rs.getTimestamp("data_hora");

                Paciente paciente = pacienteDao.findByID(idPaciente);
                Medico medico = medicoDao.findByID(idMedico);

                consulta = new Consulta(id, paciente, medico, dataHora.getTime());
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        return consulta;
    }
}
