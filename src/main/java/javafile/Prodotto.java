/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package javafile;
import java.io.Serializable;
/**
 *
 * @author Alessandro
 */
public class Prodotto implements Serializable {
    private String nome;
    private Double prezzo;
    private int codice;
    private String img;

   
    public String getNome(){
        return nome;
    }
    public int getCodice(){
        return codice;
    }
    public Double getPrezzo(){
        return prezzo;
    }
    public void setPrezzo(Double prezzo){
        this.prezzo = prezzo;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public void setCodice(int codice){
        this.codice = codice;
    }
    
     public String getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = img;
    }
    @Override public String toString(){
        return "Codice = " + codice + "\n" + "Nome = " + nome + "\n" + "Prezzo = " + prezzo + "\n" + "Img = " + img + "\n";
    }
}