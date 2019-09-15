package clinica.dao;

import clinica.classes.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDao {
    protected int inserirUsuario(Usuario usuario) { //TODO: throws
        int chavePrimaria = -1;
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.INSERT_USUARIO.getSql(),
                        Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, null); //Data de nascimento
            stmt.setString(4, "senha");
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
    
    protected int atualizarUsuario(Usuario usuario) { //TODO: throws
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.UPDATE_USUARIO.getSql())) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, null); //dataNascimento
            stmt.setInt(3, usuario.getId());
            stmt.execute();
            return 1;
        } catch (SQLException e) {
            System.out.println("Exceção SQL: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exceção no código! - update");
        }
        return -1;
    }
    
    protected int excluirUsuario(Integer id) { //TODO: throws
        try (Connection connection = new ConnectionFactory().getConnection();
                PreparedStatement stmt
                = connection.prepareStatement(SQLs.DELETE_USUARIO.getSql())) {
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
}
