package quizPackage;

import java.util.ArrayList;

public class Quiz {

    private String quizName;
    ArrayList<Question> questions = new ArrayList<>();

    public void setQuizName(String quizName){
        this.quizName = quizName;
    }

    public String getQuizName(){
        return this.quizName;
    }

    public void addQuestion(Question q){
        questions.add(q);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("");
        for(Question q : questions){
            sb.append(q + "\n");
            sb.append("\n");
        }
        return sb.toString();
    }
}
