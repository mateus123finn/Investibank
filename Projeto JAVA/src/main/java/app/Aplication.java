package app;

import service.UsuarioService;
import spark.Spark;

import static spark.Spark.*;

public class Aplication {

    private static UsuarioService us = new UsuarioService();

    public static void main(String[] args) {

        port(15694);
        staticFileLocation("/public");

        Spark.get("/", (request, response) -> {
            response.redirect("/front-end/index.html");
            return "";
        });

        post("/login", ((request, response) -> us.LoginUsuario(request,response)));
        post("/cadastro",((request, response) -> us.CadastroUsuario(request, response)));
        get("/infoConta", ((request, response) -> us.FormVer(request, response)));
        get("/deletaConta", ((request, response) -> us.deletaConta(request, response)));
        get("/editaConta", ((request, response) -> us.FormEditar(request, response)));
        post("/editaConta",((request, response) -> us.updateConta(request, response)));
        get("/sair", ((request, response) -> us.LogoutConta(request, response)));


    }

}
