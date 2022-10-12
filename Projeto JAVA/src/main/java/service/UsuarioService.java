package service;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.util.Date;
public class UsuarioService {

    private UsuarioDAO usuDAO = new UsuarioDAO();
    public UsuarioService() {
    }

    public Object LoginUsuario (Request request, Response response) {

        System.out.println(request.queryParams("Usuario"));
        System.out.println(request.queryParams("Senha"));
        response.redirect("/front-end/pages/login.html");

        return "";
    }

    public Object CadastroUsuario (Request request, Response response) throws Exception {

        String cpf = request.queryParams("Cpf");
        String p_nome = request.queryParams("Pnome");
        String u_nome = request.queryParams("Unome");
        String email = request.queryParams("Usuario");
        System.out.println(request.queryParams("DtNasc"));
        LocalDate dt_nascimento = LocalDate.parse(request.queryParams("DtNasc"));
        String senha = request.queryParams("Senha");

        if(usuDAO.insert(new Usuario(cpf, p_nome, u_nome, email, dt_nascimento, DAO.toMD5(senha)))){
            response.redirect("/");
        } else {
            System.out.println("Algo deu errado !!!!");
        }


        return "";
    }
}
