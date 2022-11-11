package app;

import service.FazService;
import service.InvestimentoService;
import service.UsuarioService;
import spark.Spark;

import java.io.File;

import static spark.Spark.*;

public class Aplication {

    private static UsuarioService us = new UsuarioService();
    private static InvestimentoService is = new InvestimentoService();
    private static FazService fs = new FazService();

    public static void main(String[] args) {

        port(15694);
        staticFileLocation("/public");

        //Index.html
        Spark.get("/", (request, response) -> {
            response.redirect("/front-end/index.html");
            return "";
        });

        //Usuario
        post("/login", ((request, response) -> us.LoginUsuario(request,response)));
        post("/cadastro",((request, response) -> us.CadastroUsuario(request, response)));
        get("/infoConta", ((request, response) -> us.FormVer(request, response)));
        get("/deletaConta", ((request, response) -> us.deletaConta(request, response)));
        get("/editaConta", ((request, response) -> us.FormEditar(request, response)));
        post("/editaConta",((request, response) -> us.updateConta(request, response)));
        get("/sair", ((request, response) -> us.LogoutConta(request, response)));

        //Investimentos
        get("/investimentos",(((request, response) -> is.mostraInvestimento(request, response))));

        //Faz
        post("/investimentos",(((request, response) -> fs.comprarInvestimento(request, response))));
        get("/meu",(((request, response) -> fs.mostraInvestimento(request, response))));
        post("/vender",(((request, response) -> fs.vendeInvestimento(request, response))));

    }

}
