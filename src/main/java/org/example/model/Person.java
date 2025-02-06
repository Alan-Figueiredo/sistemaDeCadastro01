package org.example.model;


import org.example.utis.Utils;

import java.io.File;
import java.util.Arrays;

public class Person {

    private String nome;
    private String email;
    private int idade;
    private float altura;
    private long id = 1;


    public Person(String nome, String email, int idade, float altura) {

        this.id = this.generateNumber(new File(Utils.PATH_DIRECTORY_USERS)) + 1;
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.altura = altura;
    }

    public Person() {
        this.id = this.generateNumber(new File(Utils.PATH_DIRECTORY_USERS)) + 1;
    }

    public String getNome() {
        return nome;
    }

    public long generateNumber(File path){
        long quantilyFile = Arrays.stream(path.listFiles()).filter(file -> file.isFile()).count();
        return quantilyFile;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    @Override
    public String toString() {
        return  this.id+ "\n"+
                this.nome + "\n" +
                this.email + "\n" +
                this.idade + "\n" +
                this.altura;
    }


}
