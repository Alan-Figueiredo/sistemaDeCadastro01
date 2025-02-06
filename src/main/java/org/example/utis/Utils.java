package org.example.utis;


import org.example.model.Person;
import org.example.questions.Questions;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Utils {

    private Person person;
    public static final String PATH_DIRECTORY_USERS = "D:\\BACKUP\\Arquivos\\Progamming\\ESTUDO_JAVA\\code\\sistemaDeCadastro01\\src\\main\\java\\org\\example\\arquivo\\users";
    public static final String PATH_FORMULARIO = "D:\\BACKUP\\Arquivos\\Progamming\\ESTUDO_JAVA\\code\\sistemaDeCadastro01\\src\\main\\java\\org\\example\\arquivo\\formulario.txt";

    public Utils() {
    }

    public Utils(Person person) {
        this.person = person;
    }

    private void savePersonOnDirectory(Person person) throws IndexOutOfBoundsException {

        String namePerson = person.getNome().toUpperCase();
        String[] split = namePerson.split(" ");
        // adicionar exception para caso o user envie somente uma palavra
        String firstName = split[0];
        String lastName = split[1];
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(lastName);

        File file = new File(PATH_DIRECTORY_USERS, sb + ".txt");

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("Menu principal do sistema");
        System.out.println("1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome ou idade ou email");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1:
                this.registerUser();
                sc.close();
                break;
            case 2:
                this.listUser();
                sc.close();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
        }
    }

    private void registerUser() {

        Questions qt = new Questions();
        Scanner sc = new Scanner(System.in);
        Person person = new Person();

        for (String question : qt.getQuestions()) {
            System.out.println(question);
            if (question.contains("idade")) {
                int idade = sc.nextInt();
                person.setIdade(idade);
            } else if (question.contains("altura")) {
                float altura = sc.nextFloat();
                person.setAltura(altura);
            } else if (question.contains("nome")) {
                String nome = sc.nextLine();
                person.setNome(nome);
            } else {
                String email = sc.nextLine();
                person.setEmail(email);
            }

        }
        sc.close();

        System.out.println(person);
        this.savePersonOnDirectory(person);
    }

    private void listUser() {

        File pathUsers = new File(PATH_DIRECTORY_USERS);

        // Lista os arquivos e transforma numa lista verificando se existe ou nao arquivos no diretorio
        List<String> arrFiles = Arrays.stream(Objects.requireNonNull(pathUsers.list())).toList();

        for (String arrFile : arrFiles) {
            File file = new File(PATH_DIRECTORY_USERS, arrFile);
            try (FileReader fr = new FileReader(file); BufferedReader br = new BufferedReader(fr)) {
               int linha = 1;
               int linhaName = 2;
               String line;
               while ((line = br.readLine()) != null) {
                   if(linha == linhaName){
                       System.out.println(linha+" - "+line);
                       break;
                   }
                   linha++;
               }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void addQuestion() {


        try (FileReader fr = new FileReader(PATH_FORMULARIO);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }


    }
}
