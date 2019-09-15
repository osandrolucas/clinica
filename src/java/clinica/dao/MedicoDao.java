package clinica.dao;

import clinica.classes.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class MedicoDao extends UsuarioDao implements GenericDao<Medico>  {
    @Override
    public int insert(Medico medico) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_MEDICO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {

            int idUsuario = inserirUsuario(medico);
            
            stmt.setInt(1, idUsuario);
            stmt.setString(2, medico.getRegistro());
            stmt.setString(3, medico.getEspecialidade());
            stmt.execute();
            ResultSet chaves = stmt.getGeneratedKeys();
            if (chaves.next()) {
                chavePrimaria = chaves.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");
        }
        
        return chavePrimaria;
    }
    
    public int update(Medico medico) {
        if (atualizarUsuario(medico) == -1) {
            return -1;
        }
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_MEDICO.getSql())) {

            stmt.setString(1, medico.getRegistro());
            stmt.setString(2, medico.getEspecialidade());
            stmt.setInt(3, medico.getId());
            stmt.execute();
            return 1;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código! - update");
        }
        return -1;
    }
    
    public int deleteById(Integer id) {
        return excluirUsuario(id);
    }

    @Override
    public List<Medico> listAll() {
        List<Medico> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_MEDICO.getSql())) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String registro = rs.getString("registro");
                String especialidade = rs.getString("especialidade");
                lista.add(new Medico(id, nome, cpf, registro, especialidade));
            }

            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!");
        }
        return null;
    }

    public Medico findByID(Integer id) {
        Medico medico = null;
        
        if (id == null) {
            return null;
        }
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FIND_MEDICO.getSql())) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String registro = rs.getString("registro");
                String especialidade = rs.getString("especialidade");
                
                medico = new Medico(id, nome, cpf, registro, especialidade);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        
        return medico;
    }
}
