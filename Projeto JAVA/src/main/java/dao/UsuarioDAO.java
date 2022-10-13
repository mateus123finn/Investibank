package dao;

import model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

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

    public Usuario getUsuarioLogin (String email, String senha) {
        Usuario usuario = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM USUARIO WHERE email = '"+email+"' AND senha = '"+senha+"'";
            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                usuario = new Usuario(rs.getString("cpf"),rs.getString("pnome"),rs.getString("unome"),rs.getString("email"), LocalDate.parse(rs.getString("dt_nascimento")),rs.getString("senha"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    public Usuario getCpf (String cpf){
        Usuario usuario = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM USUARIO WHERE cpf = '"+cpf+"'";
            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){
                usuario = new Usuario(rs.getString("cpf"),rs.getString("pnome"),rs.getString("unome"),rs.getString("email"), LocalDate.parse(rs.getString("dt_nascimento")),rs.getString("senha"));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return usuario;
    }

    public boolean deleta (String cpf){
        boolean status = false;
        try{
            Statement st = conexao.createStatement();
            String parse = "DELETE FROM USUARIO WHERE cpf = '"+cpf+"'";
            st.executeUpdate(parse);
            st.close();
            status = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }

    public boolean update (String cpf, Usuario user){
        boolean status = false;
        try{
            Statement st = conexao.createStatement();
            String parse = "UPDATE USUARIO SET cpf = '"+user.getCpf()+"', pnome = '"+user.getP_nome()+"', unome = '"+user.getU_nome()+"', dt_nascimento = '"+user.getDt_nascimento()+"', email = '"+user.getEmail()+"' WHERE cpf = '"+cpf+"'";
            st.executeUpdate(parse);
            st.close();
            status = true;
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return status;
    }
}
