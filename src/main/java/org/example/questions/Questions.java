package org.example.questions;

import org.example.utis.Utils;

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

        try (FileReader fr = new FileReader(Utils.PATH_FORMULARIO);
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

