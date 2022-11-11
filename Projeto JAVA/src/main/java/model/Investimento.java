package model;

public class Investimento
{
    private String nome;
    private String sigla;
    private double preco;
    private String moeda;

    public Investimento (String nome, String sigla, double preco, String moeda)
    {
        this.nome = nome;
        this.sigla = sigla;
        this.preco = preco;
        this.moeda = moeda;
    }

    public String getNome () {
        return (this.nome);
    }

    public void setNome ( String Nome ) {
        this.nome = Nome;
    }

    public String getSigla () { return (this.sigla); }

    public void setSigla ( String Sigla ) { this.sigla = Sigla; }

    public double getPreco () { return (this.preco); }

    public void setPreco ( int Preco ) { this.preco = Preco; }

    public String getMoeda () { return (this.moeda); }

    public void setMoeda ( String Moeda ) { this.moeda = Moeda; }

}
