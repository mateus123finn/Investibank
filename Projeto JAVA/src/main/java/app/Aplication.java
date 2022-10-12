package app;

import service.UsuarioService;
import static spark.Spark.*;

public class Aplication {

    private static UsuarioService us = new UsuarioService();

    public static void main(String[] args) {

        port(15694);
        staticFileLocation("/public");

        get("/", (request, response) -> {
            response.redirect("/front-end/index.html");
            return "";
        });

        post("/login", ((request, response) -> us.LoginUsuario(request,response)));
        post("/cadastro",((request, response) -> us.CadastroUsuario(request, response)));

    }

}
