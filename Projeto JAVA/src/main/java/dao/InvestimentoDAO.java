package dao;

import model.Investimento;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Vector;

public class InvestimentoDAO extends DAO
{
    public InvestimentoDAO()
    {
        super();
        conectar();
    }

    public boolean insert ( Investimento acao )
    {

        boolean status = false;

        try
        {
            Statement st = this.conexao.createStatement();
            String parse = "INSERT INTO INVESTIMENTO (Nome, Sigla, Preco, Moeda) VALUES ('" + acao.getNome() + "','" + acao.getSigla() + "'," + acao.getPreco() + ",'" + acao.getMoeda() + "')";
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

    public boolean updatePreco ( double price, String sig )
    {

        boolean status = false;

        try
        {
            Statement st = this.conexao.createStatement();
            String parse = "UPDATE INVESTIMENTO SET Preco = " + price + " WHERE Sigla = '" + sig + "'";
            st.executeUpdate(parse);
            st.close();
            status = true;
        }
        catch ( SQLException exce2 )
        {
            System.out.println(exce2.getMessage());
        }

        return status;

    }

    public Vector<Investimento> getInvestimentos(){
        Vector<Investimento> aux = new Vector<>();
        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM INVESTIMENTO";
            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                aux.add(new Investimento(rs.getString("Nome"),rs.getString("Sigla"),Float.parseFloat(rs.getString("Preco")), rs.getString("Moeda")));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aux;
    }

    public Investimento getInvestimentoSigla(String sigla){
        Investimento aux = null;

        try {
            Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            String sql = "SELECT * FROM INVESTIMENTO WHERE Sigla = '"+sigla+"'";

            //System.out.println(sql);
            ResultSet rs = st.executeQuery(sql);
            if(rs.next()){

                aux = (new Investimento(rs.getString("Nome"),rs.getString("Sigla"),Float.parseFloat(rs.getString("Preco")),rs.getString("Moeda")));
            }
            st.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return aux;
    }


}
