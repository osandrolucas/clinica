package clinica.dao;

public enum SQLs {
    INSERT_USUARIO("insert into usuario(cpf, nome, data_nascimento, senha) values(?, ?, ?, ?)"),
    UPDATE_USUARIO("update usuario set nome=?, data_nascimento=? where id_usuario=?"),
    DELETE_USUARIO("delete from usuario where id_usuario=?"),
    FIND_USUARIO("select * from usuario where id_usuario=?"),
    CHECK_LOGIN("select cpf, senha from usuario where cpf=? and senha=?"),
    UPDATE_SENHA("update usuario set senha=? where id_usuario=?"),
    
    INSERT_PACIENTE("insert into paciente(id_usuario, end_logradouro, end_numero, end_complemento, end_municipio, telefone1, telefone2) values(?, ?, ?, ?, ?, ?, ?)"),
    UPDATE_PACIENTE("update paciente set end_logradouro=?, end_numero=?, end_complemento=?, end_municipio=?, telefone1=?, telefone2=? where id_usuario=?"),
    LISTALL_PACIENTE("select * from paciente inner join usuario on paciente.id_usuario=usuario.id_usuario"),
    FIND_PACIENTE("select * from paciente inner join usuario on paciente.id_usuario=usuario.id_usuario where usuario.id_usuario=?"),
    
    INSERT_MEDICO("insert into medico(id_usuario, registro, especialidade) values(?, ?, ?)"),
    LISTALL_MEDICO("select * from medico inner join usuario on medico.id_usuario=usuario.id_usuario"),
    UPDATE_MEDICO("update medico set registro=?, especialidade=? where id_usuario=?"),
    FIND_MEDICO("select * from medico inner join usuario on medico.id_usuario=usuario.id_usuario where usuario.id_usuario=?"),
    
    INSERT_ATENDENTE("insert into atendente(id_usuario, administrador) values(?, ?)"),
    LISTALL_ATENDENTE("select * from atendente inner join usuario on atendente.id_usuario=usuario.id_usuario"),
    UPDATE_ATENDENTE("update atendente set administrador=? where id_usuario=?"),
    FIND_ATENDENTE("select * from atendente inner join usuario on atendente.id_usuario=usuario.id_usuario where usuario.id_usuario=?"),

    INSERT_CONSULTA("insert into consulta(id_paciente, id_medico, data_hora) values (?, ?, ?)"), 
    LISTALL_CONSULTA("select * from consulta"),
    DELETE_CONSULTA("delete from consulta where id_consulta=?"),
    FIND_CONSULTA("select * from consulta where id_consulta=?");
   
    private final String sql;
    
    SQLs(String sql){
        this.sql = sql; 
    }

    public String getSql() {
        return sql;
    }    
}
