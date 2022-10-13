package service;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;
import spark.Request;
import spark.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class UsuarioService {

    private UsuarioDAO usuDAO = new UsuarioDAO();
    public UsuarioService() {
    }

    public Object LoginUsuario (Request request, Response response) throws Exception {

        String usuario, senha;

        usuario = request.queryParams("Usuario");
        senha = request.queryParams("Senha");

        Usuario user = usuDAO.getUsuarioLogin(usuario, DAO.toMD5(senha));

        if(user != null){
            response.cookie("usuario", user.getCpf(), 480);
            response.cookie("nome",user.getP_nome(),480);
            response.redirect("/");
        } else {
            response.cookie("erro","1");
            response.redirect("/front-end/pages/login.html");
        }

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
            response.redirect("/front-end/pages/login.html");
        } else {
            System.out.println("Algo deu errado !!!!");
        }

        return "";
    }

    public String MakeForm(String values) throws FileNotFoundException {
        String form = "";
        Scanner sc = new Scanner(new File("./src/main/resources/public/front-end/pages/infopessoal.html"));

        while (sc.hasNext()) {
            form += sc.nextLine();
        }
        sc.close();

        form = form.replaceFirst("<valores>",values);
        return form;
    }
    public Object FormVer(Request request, Response response) throws FileNotFoundException {

        String cpf = request.cookie("usuario");
        if(cpf == null){
            response.redirect("/");
            return "";
        }
        Usuario user = usuDAO.getCpf(cpf);

        String values = "<label class=\"form-label\" for=\"form3Example1c\">CPF</label>" +
                "<div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-id-card fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input type=\"text\" id=\"form3Example1c\" class=\"form-control\" value=\""+user.getCpf()+"\" disabled/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4c\">Primeiro Nome</label>" +
                "                        <label class=\"form-label\" for=\"form3Example3c\" style=\"margin-left: 65px;\">Último Nome</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-user fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input type=\"text\" id=\"form3Example4c\" class=\"form-control\" value=\""+user.getP_nome()+"\" disabled/>" +
                "                          </div>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input type=\"text\" id=\"form3Example3c\" class=\"form-control\" style=\"margin-left: 5px;\" value=\""+user.getU_nome()+"\" disabled/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4cd\">Data de Nascimento</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-key fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input type=\"date\" id=\"form3Example4cd\" class=\"form-control\" value=\""+user.getDt_nascimento()+"\" disabled/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4cd\">Email</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-envelope fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input type=\"email\" id=\"form3Example4cd\" class=\"form-control\" value=\""+user.getEmail()+"\" disabled/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <div class=\"d-flex justify-content-center mx-4 mb-3 mb-lg-4\">" +
                "                          <button type=\"button\" class=\"btn btn-primary btn-lg\" onclick=\"window.location.href = '/editaConta'\">Editar Dados</button>" +
                "                          <button type=\"button\" class=\"btn btn-danger btn-lg\" style=\"margin-left: 10px;\" onclick=\"deletaConta()\">Excluir Conta</button>" +
                "                        </div>";
        return MakeForm(values);
    }
    public Object FormEditar(Request request, Response response) throws FileNotFoundException {

        String cpf = request.cookie("usuario");
        if(cpf == null){
            response.redirect("/");
            return "";
        }
        Usuario user = usuDAO.getCpf(cpf);

        String values = "<label class=\"form-label\" for=\"form3Example1c\">CPF</label>" +
                "<div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-id-card fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input name=\"Cpf\" type=\"text\" id=\"form3Example1c\" class=\"form-control\" value=\""+user.getCpf()+"\"/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4c\">Primeiro Nome</label>" +
                "                        <label class=\"form-label\" for=\"form3Example3c\" style=\"margin-left: 65px;\">Último Nome</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-user fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input name=\"Pnome\" type=\"text\" id=\"form3Example4c\" class=\"form-control\" value=\""+user.getP_nome()+"\"/>" +
                "                          </div>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input name=\"Unome\" type=\"text\" id=\"form3Example3c\" class=\"form-control\" style=\"margin-left: 5px;\" value=\""+user.getU_nome()+"\"/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4cd\">Data de Nascimento</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-key fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input name=\"DtNasc\" type=\"date\" id=\"form3Example4cd\" class=\"form-control\" value=\""+user.getDt_nascimento()+"\"/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <label class=\"form-label\" for=\"form3Example4cd\">Email</label>" +
                "                        <div class=\"d-flex flex-row align-items-center mb-4\">" +
                "                          <i class=\"fas fa-envelope fa-lg me-3 fa-fw\"></i>" +
                "                          <div class=\"form-outline flex-fill mb-0\">" +
                "                            <input name=\"Usuario\" type=\"email\" id=\"form3Example4cd\" class=\"form-control\" value=\""+user.getEmail()+"\"/>" +
                "                          </div>" +
                "                        </div>" +
                "                        <div class=\"d-flex justify-content-center mx-4 mb-3 mb-lg-4\">" +
                "                          <button type=\"submit\" class=\"btn btn-success btn-lg\">Salvar</button>\n" +
                "                          <button type=\"button\" class=\"btn btn-danger btn-lg\" style=\"margin-left: 10px;\" onclick=\"window.location.href = '/infoConta'\">Cancelar</button>" +
                "                        </div>";
        return MakeForm(values);
    }

    public Object deletaConta(Request request, Response response){
        if(usuDAO.deleta(request.cookie("usuario"))){
            response.removeCookie("usuario");
            response.removeCookie("nome");
            response.redirect("/");
        } else {
            System.out.println("Algo deu errado !!!!");
        }

        return "";
    }

    public Object updateConta(Request request, Response response){

        String cpf = request.queryParams("Cpf");
        String p_nome = request.queryParams("Pnome");
        String u_nome = request.queryParams("Unome");
        String email = request.queryParams("Usuario");
        LocalDate dt_nascimento = LocalDate.parse(request.queryParams("DtNasc"));

        Usuario user = usuDAO.getCpf(request.cookie("usuario"));
        user.setCpf(cpf);
        user.setP_nome(p_nome);
        user.setU_nome(u_nome);
        user.setEmail(email);
        user.setDt_nascimento(dt_nascimento);

        if(usuDAO.update(request.cookie("usuario"),user)){
            response.removeCookie("usuario");
            response.cookie("usuario",user.getCpf(),480);
            response.removeCookie("nome");
            response.cookie("nome",user.getP_nome(),480);
            response.redirect("/infoConta");
        } else {
            System.out.println("Algo deu errado !!!!");
        }

        return "";
    }

    public Object LogoutConta(Request request, Response response){
        response.removeCookie("usuario");
        response.removeCookie("nome");
        response.redirect("/");
        return "";
    }
}
