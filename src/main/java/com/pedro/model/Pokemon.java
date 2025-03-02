package com.pedro.model;

public class Pokemon {

    private String name;
    private String[] tipo;
    private int nivel;
    private String[] habilidades;
    private int id_adestrador;

    public Pokemon() {
    }

    public Pokemon(String name, String[] tipo, int nivel, String[] habilidades, int id_adestrador) {
        this.name = name;
        this.tipo = tipo;
        this.nivel = nivel;
        this.habilidades = habilidades;
        this.id_adestrador = id_adestrador;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getTipo() {
        return tipo;
    }

    public void setTipo(String[] tipo) {
        this.tipo = tipo;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String[] getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String[] habilidades) {
        this.habilidades = habilidades;
    }

    public int getId_adestrador() {
        return id_adestrador;
    }

    public void setId_adestrador(int id_adestrador) {
        this.id_adestrador = id_adestrador;
    }
}
