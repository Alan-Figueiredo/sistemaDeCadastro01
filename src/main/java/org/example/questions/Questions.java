package org.example.questions;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Questions {

    private List<String> questions = new ArrayList<>();

    public Questions() {
        try {
            this.readQuestions();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void readQuestions() throws FileNotFoundException {
        String path = "D:\\BACKUP\\Arquivos\\Progamming\\ESTUDO_JAVA\\code\\sistemaDeCadastro01\\src\\main\\java\\org\\example\\arquivo\\formulario.txt";
        try (FileReader fr = new FileReader(path);
             BufferedReader br = new BufferedReader(fr)) {
            while (br.ready()) {
                String line = br.readLine();
                this.questions.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();

        }

    }


    public List<String> getQuestions() {
        return questions;
    }
}

