package org.example.utis;

import org.example.model.Person;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Utils {

    private Person person;
    private static final String PATH_DIRECTORY = "D:\\BACKUP\\Arquivos\\Progamming\\ESTUDO_JAVA\\code\\sistemaDeCadastro01\\src\\main\\java\\org\\example\\arquivo\\users";

    public Utils() {
    }

    public Utils(Person person) {
        this.person = person;
    }

    public static void savePersonOnDirectory(Person person) throws IndexOutOfBoundsException{

        String namePerson = person.getNome().toUpperCase();
        String[] split = namePerson.split(" ");
        // adicionar exception para caso o user envie somente uma palavra
        String firstName = split[0];
        String lastName = split[1];
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(lastName);

        File file = new File(PATH_DIRECTORY, sb + ".txt");

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(person.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu() {
        System.out.println("Menu principal do sistema");
        System.out.println("1 - Cadastrar o usuário");
        System.out.println("2 - Listar todos usuários cadastrados");
        System.out.println("3 - Cadastrar nova pergunta no formulário");
        System.out.println("4 - Deletar pergunta do formulário");
        System.out.println("5 - Pesquisar usuário por nome ou idade ou email");

    }
}
