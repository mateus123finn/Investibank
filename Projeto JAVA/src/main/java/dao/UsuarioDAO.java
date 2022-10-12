package dao;

import model.Usuario;

import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO extends DAO{
    public UsuarioDAO() {
        super();
        conectar();
    }
    public void finalize(){
        close();
    }

    public boolean insert (Usuario usuario){
        boolean status = false;
        try{
            Statement st = conexao.createStatement();
            String parse = "INSERT INTO USUARIO (cpf, pnome, unome, dt_nascimento, email, senha) VALUES ('"+usuario.getCpf()+"','"+usuario.getP_nome()+"','"+usuario.getU_nome()+"','"+usuario.getDt_nascimento()+"','"+usuario.getEmail()+"','"+usuario.getSenha()+"')";
            st.executeUpdate(parse);
            st.close();
            status = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }
}
