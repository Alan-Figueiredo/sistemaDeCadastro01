package org.example.utis;


import org.example.model.Person;
import org.example.questions.Questions;

import java.io.*;
import java.util.*;


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
                this.addQuestion();
                sc.close();
                break;
            case 4:
                this.deleteQuestion();
                sc.close();
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

    public void listUser() {

        File pathUsers = new File(PATH_DIRECTORY_USERS);

        // Lista os arquivos e transforma numa lista verificando se existe ou nao arquivos no diretorio
        List<String> arrFiles = Arrays.stream(Objects.requireNonNull(pathUsers.list())).toList();

        for (String arrFile : arrFiles) {
            File file = new File(PATH_DIRECTORY_USERS, arrFile);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                int linhaAtual = 1; // inicial do arquivo
                int linhaName = 2; // Segunda linha do arquivo
                String line;
                StringBuilder sb = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    if (linhaAtual == linhaName) {
                        sb.append(line);
                        System.out.println(sb);
                        break;
                    }
                sb.append(line).append(" - ");
                linhaAtual++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void addQuestion() {

        int linha = 0;
        Scanner sc = new Scanner(System.in);

        try (FileReader fr = new FileReader(PATH_FORMULARIO);
             BufferedReader br = new BufferedReader(fr); FileWriter fw = new FileWriter(PATH_FORMULARIO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            while (br.ready()) {
                br.readLine();
                linha++;
            }

            System.out.println("Digite a nova pergunta: ");
            String question = sc.nextLine();
            StringBuilder sb = new StringBuilder();
            sb.append(linha + 1).append(" - ").append(question);

            bw.newLine();
            bw.write(String.valueOf(sb));
            bw.flush();
            sc.close();

        } catch (IOException e) {
            e.printStackTrace();

        }


    }

    private void deleteQuestion() {

        List<String> arrQuestoes = new ArrayList<>();
        List<String> linhasAtualizadas;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_FORMULARIO))) {

            String lineArquivo;
            while ((lineArquivo = br.readLine()) != null) {
                arrQuestoes.add(lineArquivo);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        // Visualizar questoes
        System.out.println("-----------------------------");
        for (String perguntas : arrQuestoes) {
            System.out.println(perguntas);
        }

        // Receber numero do user
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha o número da pergunta que deseja deletar: ");
        int linha = sc.nextInt();
        sc.close();

        if (linha >= 5 && linha <= arrQuestoes.size()) {
            String linhaRemover = String.valueOf(linha);
            linhasAtualizadas = arrQuestoes.stream().filter(i -> !i.startsWith(linhaRemover)).toList();

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_FORMULARIO))) {
                for (String linhaAtual : linhasAtualizadas) {
                    bw.write(linhaAtual.trim());
                    bw.newLine();
                    bw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Não é possivel deletar essa pergunta.");
        }


    }

    public void searchUser(){


    }
}
