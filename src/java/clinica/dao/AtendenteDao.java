package clinica.dao;

import clinica.classes.Atendente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AtendenteDao extends UsuarioDao implements GenericDao<Atendente> {
    @Override
    public int insert(Atendente atendente) {
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_ATENDENTE.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {

            int idUsuario = inserirUsuario(atendente);
            
            stmt.setInt(1, idUsuario);
            stmt.setBoolean(2, atendente.isAdmin());
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
    
    public int update(Atendente atendente) {
        if (atualizarUsuario(atendente) == -1) {
            return -1;
        }
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_ATENDENTE.getSql())) {

            stmt.setBoolean(1, atendente.isAdmin());
            stmt.setInt(2, atendente.getId());
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
    public List<Atendente> listAll() {
        List<Atendente> lista = new LinkedList<>();

        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.LISTALL_ATENDENTE.getSql())) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id_usuario");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                boolean admin = rs.getBoolean("administrador");
                lista.add(new Atendente(id, nome, cpf, admin));
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
    public Atendente findByID(Integer id) {
        Atendente atendente = null;
        
        if (id == null) {
            return null;
        }
        
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.FIND_ATENDENTE.getSql())) {

            System.out.println("Conexão aberta!");

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                boolean admin = rs.getBoolean("administrador");
                
                atendente = new Atendente(id, nome, cpf, admin);
            }

        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código!- findById");
        }
        
        return atendente;
    }
}
