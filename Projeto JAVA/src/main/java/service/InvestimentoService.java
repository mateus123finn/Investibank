package service;

import dao.InvestimentoDAO;
import dao.UsuarioDAO;
import model.Investimento;
import spark.Request;
import spark.Response;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

import org.json.*;


public class InvestimentoService {

    private InvestimentoDAO invDao = new InvestimentoDAO();
    private UsuarioDAO usu = new UsuarioDAO();

    public InvestimentoService(){}

    public static String getJSON(String url) {
        HttpsURLConnection con = null;
        try {
            URL u = new URL(url);
            con = (HttpsURLConnection) u.openConnection();

            con.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();


        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.disconnect();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return null;
    }

    public String MakeForm(String values) throws FileNotFoundException {
        String form = "";
        Scanner sc = new Scanner(new File("./src/main/resources/public/front-end/pages/investimentos.html"));

        while (sc.hasNext()) {
            form += sc.nextLine();
        }
        sc.close();

        form = form.replaceFirst("<valores>",values);

        return form;
    }

    public Object mostraInvestimento(Request request, Response response) throws FileNotFoundException{

        String[] acoesboas = {"NTDOY","SGAMY","UGP","PETR3.SA","CNK","F","GM","SHCAY","POSI3.SA","CSMG3.SA","CMIG3.SA", "ABEV", "VALE3.SA", "ITUB3.SA", "SONY", "NVDA", "AMD", "BBDC3.SA", "TSLA", "AAPL", "GOLL4.SA", "AMZN", "INTC", "GOOGL", "META", "ATVI", "TTWO"};

        for (int i = 0; i < acoesboas.length; i++) {

            String json = getJSON("https://query1.finance.yahoo.com/v7/finance/quote?symbols="+acoesboas[i]);
            JSONObject jo = new JSONObject(json);


            String sigla = jo.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("symbol");
            String nome = jo.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("longName");
            double preco = jo.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getDouble("regularMarketPrice");
            String moeda = jo.getJSONObject("quoteResponse").getJSONArray("result").getJSONObject(0).getString("currency");



            Investimento criar = invDao.getInvestimentoSigla(sigla);

            if (criar != null) {
                invDao.updatePreco(preco, sigla);
            } else {
                Investimento novo = new Investimento(nome, sigla, preco, moeda);
                invDao.insert(novo);
            }

        }

        String cpf = request.cookie("usuario");
        if (cpf == null) {
            response.redirect("/");
            return "";
        }


        Vector<Investimento> invs = invDao.getInvestimentos();


        String bacana = "";
        for (int i = 0; i < invs.size(); i++) {
            bacana += "<div class = card1 style=\"width: 100%; overflow: hidden;\">" +
                    "      <h1>" + invs.get(i).getNome() + "</h1>" +
                    "      <p class=\"sigla\">" + invs.get(i).getSigla() + "</p>" +
                    "      <p class=\"price\">" + Math.round(invs.get(i).getPreco() * 100.0) / 100.0 + " " + invs.get(i).getMoeda() + "</p>" +
                    "      <button id=\""+invs.get(i).getSigla()+"\" onclick=\"openNav(this.id);\">Comprar</button>" +
                    "    </div>";
        }
        return MakeForm(bacana);
    }
}
