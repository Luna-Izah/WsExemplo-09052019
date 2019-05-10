/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsconsumer.models;

/**
 *
 * @author drink
 */
public class Produto {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    private int id;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 

    public Produto() {
        this.id = 0;
    }
    
}
