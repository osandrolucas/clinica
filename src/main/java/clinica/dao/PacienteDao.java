package clinica.dao;

import clinica.classes.Paciente;
import clinica.classes.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PacienteDao extends UsuarioDao implements GenericDao<Paciente>  {

    @Override
    public int insert(Paciente paciente) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_PACIENTE.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {

            int idUsuario = inserirUsuario(paciente);
	    if (idUsuario == -1) {
	        return -1;
	    }
            
            Endereco end = paciente.getEndereco();
            
            stmt.setInt(1, idUsuario);
            stmt.setString(2, end.getLogradouro());
            stmt.setString(3, end.getNumero());
            stmt.setString(4, end.getComplemento());
            stmt.setString(5, end.getMunicipio());
            stmt.setString(6, paciente.getTelefone1());
            stmt.setString(7, paciente.getTelefone2());
            stmt.execute();

	    chavePrimaria = idUsuario;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("Classe não encontrada!");
        }
        
        return chavePrimaria;
    }
    
    public int update(Paciente paciente) {
        if (atualizarUsuario(paciente) == -1) {
            return -1;
        }
        
        Endereco end = paciente.getEndereco();
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_PACIENTE.getSql())) {

            stmt.setString(1, end.getLogradouro());
            stmt.setString(2, end.getNumero());
            stmt.setString(3, end.getComplemento());
            stmt.setString(4, end.getMunicipio());
            stmt.setString(5, paciente.getTelefone1());
            stmt.setString(6, paciente.getTelefone2());
            stmt.setInt(7, paciente.getId());
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
    public List<Paciente> listAll() {
        List<Paciente> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_PACIENTE.getSql())) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                Endereco endereco = null;
                lista.add(new Paciente(id, nome, cpf, endereco, null, null));
            }

            return lista;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!");
        }
        return null;
    }

    public Paciente findByID(Integer id) {
        Paciente paciente = null;
        
        if (id == null) {
            return null;
        }
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FIND_PACIENTE.getSql())) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone1 = rs.getString("telefone1");
                String telefone2 = rs.getString("telefone2");
                
                String logradouro = rs.getString("end_logradouro");
                String numero = rs.getString("end_numero");
                String complemento = rs.getString("end_complemento");
                String municipio = rs.getString("end_municipio");
                Endereco endereco = new Endereco(logradouro, numero, complemento, municipio);
                
                paciente = new Paciente(id, nome, cpf, endereco, telefone1, telefone2);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        
        return paciente;
    }
}
