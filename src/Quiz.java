import java.util.ArrayList;

public class Quiz {

    ArrayList<Question> questions = new ArrayList<>();

    public void addQuestion(Question q){
        questions.add(q);
    }

    public void printQuiz(){
        for(Question q : questions){
            System.out.println(q + "\n");
        }
    }
}
