package org.example;


import org.example.model.Person;
import org.example.questions.Questions;
import org.example.utis.Utils;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Questions qt = new Questions();
        Scanner sc = new Scanner(System.in);
        Person person = new Person();

        for (String question : qt.getQuestions()) {
            System.out.println(question);
            if(question.contains("idade")) {
                int idade = sc.nextInt();
                person.setIdade(idade);
            } else if (question.contains("altura")) {
                float altura = sc.nextFloat();
                person.setAltura(altura);
            } else if(question.contains("nome")) {
                String nome = sc.nextLine();
                person.setNome(nome);
            } else {
                String email = sc.nextLine();
                person.setEmail(email);
            }

        }
        sc.close();

        System.out.println(person);
        Utils.savePersonOnDirectory(person);
    }
}