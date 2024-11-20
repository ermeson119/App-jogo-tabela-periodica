package com.example.ermesonbalbinotandrade.model.entity;

import java.io.Serializable;

public class TabelaPeriodica implements Serializable {
    private String nome;
    private String sigla;
    private String massaAtomica;
    private int imagemEle;
    private int numeroAtomico;


    public TabelaPeriodica(String nome, int imagemEle) {
        this.nome = nome;
        this.imagemEle = imagemEle;
    }

    public TabelaPeriodica(String nome, String sigla, String massaAtomica) {
        this.nome = nome;
        this.sigla = sigla;
        this.massaAtomica = massaAtomica;
    }

    public TabelaPeriodica(String nome, int imagemEle, String sigla) {
        this.nome = nome;
        this.imagemEle = imagemEle;
        this.sigla = sigla;
    }

    public TabelaPeriodica(String nome, String sigla, String massaAtomica, int imagemEle) {
        this.nome = nome;
        this.sigla = sigla;
        this.massaAtomica = massaAtomica;
        this.imagemEle = imagemEle;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getMassaAtomica() {
        return massaAtomica;
    }

    public void setMassaAtomica(String massaAtomica) {
        this.massaAtomica = massaAtomica;
    }

    public int getImagemEle() {
        return imagemEle;
    }

    public void setImagemEle(int imagemEle) {
        this.imagemEle = imagemEle;
    }

    public int getNumeroAtomico() {
        return numeroAtomico;
    }

    public void setNumeroAtomico(int numeroAtomico) {
        this.numeroAtomico = numeroAtomico;
    }
}
