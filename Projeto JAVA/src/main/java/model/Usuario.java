package model;

import java.time.LocalDate;
import java.util.Date;

public class Usuario {
    private String cpf;
    private String p_nome;
    private String u_nome;
    private String email;
    private LocalDate dt_nascimento;
    private String senha;

    public Usuario(String cpf, String p_nome, String u_nome, String email, LocalDate dt_nascimento, String senha) {
        this.cpf = cpf;
        this.p_nome = p_nome;
        this.u_nome = u_nome;
        this.email = email;
        this.dt_nascimento = dt_nascimento;
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getP_nome() {
        return p_nome;
    }

    public void setP_nome(String p_nome) {
        this.p_nome = p_nome;
    }

    public String getU_nome() {
        return u_nome;
    }

    public void setU_nome(String u_nome) {
        this.u_nome = u_nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDt_nascimento() {
        return dt_nascimento;
    }

    public void setDt_nascimento(LocalDate dt_nascimento) {
        this.dt_nascimento = dt_nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
