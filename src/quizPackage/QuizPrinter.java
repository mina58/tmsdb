package quizPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class QuizPrinter {

    public static void printQuiz(Quiz q) {

        try {
            File quizFile = new File( q.getQuizName() + ".txt");
            FileWriter fw = new FileWriter(quizFile);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(q.getQuizName());
            pw.println("\n");
            pw.println(q.toString());
            pw.close();
        } catch (IOException e){
            System.out.println("Could not print quiz: " + e.getMessage());
        }
    }
}
