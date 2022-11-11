package dao;

import model.Faz;
import model.Investimento;
import model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Vector;

public class FazDAO extends DAO
{

    public FazDAO()
    {
        super();
        conectar();
    }

    public boolean insert (Faz fz)
    {

        boolean status = false;

        try
        {
            Statement st = this.conexao.createStatement();
            String parse = "INSERT INTO FAZ (Investimento, Usuario, Nmr_Transacao, Dt_Transacao, Qnt_adquirida, Valor_Total, Ativo) VALUES ('" + fz.getInvestimento() + "','" + fz.getUsuario() + "'," + fz.getNmr_transacao() + ",'" + fz.getDt_transacao() + "'," + fz.getQnt_adquirida() + "," + fz.getValor_Total() + ",'" + fz.isAtivo() + "')";
            st.executeUpdate(parse);
            st.close();
            status = true;
        }
        catch ( SQLException exce1 )
        {
            System.out.println( exce1.getMessage() );
        }

        return (status);
    }

    public boolean updatePreco(String usu, String aca){
        boolean aux = false;
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM USUARIO INNER JOIN FAZ ON Cpf = Usuario INNER JOIN INVESTIMENTO ON Sigla = Investimento WHERE Cpf = '"+usu+"' AND Sigla = '"+aca+"'";
            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            System.out.println("teste");
            if(rs.next()){
                System.out.println("teste");
                Statement stu = this.conexao.createStatement();
                String parse = "UPDATE USUARIO SET Valor = Valor + "+ (Double.parseDouble(rs.getString("Valor_total")) - (Double.parseDouble(rs.getString("Preco"))) * Integer.parseInt(rs.getString("Qnt_adquirida")))+" WHERE Cpf = '"+usu+"'";
                stu.executeUpdate(parse);
                stu.close();
            }
            st.close();
            aux = true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aux;
    }

    public boolean vende(String usu, String aca){
        boolean status = false;
        try
        {
            updatePreco(usu,aca);
            Statement st = this.conexao.createStatement();
            String parse = "DELETE FROM FAZ WHERE Investimento = '"+aca+"' AND Usuario = '"+usu+"'";
            st.executeUpdate(parse);
            st.close();
            status = true;

        }
        catch ( SQLException exce1 )
        {
            System.out.println( exce1.getMessage() );
        }

        return (status);
    }

    public Vector<Investimento> getInvestimentos(String usu){
        Vector<Investimento> aux = new Vector<>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM USUARIO INNER JOIN FAZ ON Usuario = Cpf INNER JOIN INVESTIMENTO ON Sigla = Investimento WHERE Ativo = 'true' AND Usuario = '"+usu+"'";
            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            System.out.println("teste");
            while(rs.next()){
                aux.add(new Investimento(rs.getString("Nome"),rs.getString("Sigla"),Float.parseFloat(rs.getString("Preco")), rs.getString("Moeda")));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aux;
    }

}
