package model;

import java.time.LocalDate;

public class Faz
{

    private String investimento;
    private String usuario;
    private int nmr_transacao;
    private LocalDate dt_transacao;
    private double qnt_adquirida;
    private double Valor_Total;
    private boolean ativo;

    public Faz(String investimento, String usuario, int nmr_transacao, LocalDate dt_transacao, double qnt_adquirida, double valor_Total, boolean ativo)
    {
        this.investimento = investimento;
        this.usuario = usuario;
        this.nmr_transacao = nmr_transacao;
        this.dt_transacao = dt_transacao;
        this.qnt_adquirida = qnt_adquirida;
        Valor_Total = valor_Total;
        this.ativo = ativo;
    }

    public String getInvestimento ()
    {
        return investimento;
    }

    public void setInvestimento (String investimento)
    {
        this.investimento = investimento;
    }

    public String getUsuario ()
    {
        return usuario;
    }

    public void setUsuario (String usuario)
    {
        this.usuario = usuario;
    }

    public int getNmr_transacao ()
    {
        return nmr_transacao;
    }

    public void setNmr_transacao (int nmr_transacao)
    {
        this.nmr_transacao = nmr_transacao;
    }

    public LocalDate getDt_transacao ()
    {
        return dt_transacao;
    }

    public void setDt_transacao (LocalDate dt_transacao)
    {
        this.dt_transacao = dt_transacao;
    }

    public double getQnt_adquirida ()
    {
        return qnt_adquirida;
    }

    public void setQnt_adquirida (double qnt_adquirida)
    {
        this.qnt_adquirida = qnt_adquirida;
    }

    public double getValor_Total ()
    {
        return Valor_Total;
    }

    public void setValor_Total(double valor_Total)
    {
        Valor_Total = valor_Total;
    }

    public boolean isAtivo()
    {
        return ativo;
    }

    public void setAtivo(boolean ativo)
    {
        this.ativo = ativo;
    }

}
