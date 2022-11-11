package service;

import dao.FazDAO;
import dao.InvestimentoDAO;
import dao.UsuarioDAO;
import model.Faz;
import model.Investimento;
import model.Usuario;
import org.json.JSONObject;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Vector;

public class FazService {
    private FazDAO fzDao = new FazDAO();
    private InvestimentoDAO inDao = new InvestimentoDAO();
    private UsuarioDAO usDao = new UsuarioDAO();

    public FazService() {
    }

    public Object comprarInvestimento(Request request, Response response){
        int qntd = Integer.parseInt(request.queryParams("quant"));
        String acao = request.queryParams("sigla");

        Investimento inv = inDao.getInvestimentoSigla(acao);
        Usuario usu = usDao.getCpf(request.cookie("usuario"));

        Faz fz = new Faz(inv.getSigla(), usu.getCpf(), 2222, LocalDate.now(),qntd,qntd*inv.getPreco(),true);

        fzDao.insert(fz);

        response.redirect("/meu");
        return "";

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


        String cpf = request.cookie("usuario");
        if (cpf == null) {
            response.redirect("/");
            return "";
        }

        Vector<Investimento> invs = fzDao.getInvestimentos(cpf);

        System.out.println(Math.round(usDao.getCarteira(cpf)));
        String bacana = "";
        for (int i = 0; i < invs.size(); i++) {
            bacana += "<div class = card1 style=\"width: 100%; overflow: hidden;\">" +
                    "      <h1>" + invs.get(i).getNome() + "</h1>" +
                    "      <p class=\"sigla\">" + invs.get(i).getSigla() + "</p>" +
                    "      <p class=\"price\">" + Math.round(invs.get(i).getPreco() * 100.0) / 100.0 + " " + invs.get(i).getMoeda() + "</p>" +
                    "      <button id=\""+invs.get(i).getSigla()+"\" onclick=\"vende(this.id);\">Vender</button>" +
                    "    </div>";
        }
        return MakeForm(bacana);
    }

    public Object vendeInvestimento(Request request, Response response){

        String acao = request.queryParams("Sigla");
        System.out.println(acao);
        String cpf = request.cookie("usuario");
        fzDao.vende(cpf, acao);
        response.redirect("/meu");

        return "";

    }

}
