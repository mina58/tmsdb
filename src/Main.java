import java.util.List;

public class Main {

    public static void main(String[] args) {
        TmsDb tmsDb = new TmsDb();
        tmsDb.open();
        tmsDb.create();
//        tmsDb.newStudent("Jimmy", "01234", "0122");
//        tmsDb.newStudent("Malak", "01234", "0122");
//        tmsDb.newStudent("Sarah", "01234", "0122");
//        tmsDb.newStudent("Amr", "01234", "0122");
//        tmsDb.newAssistant("Yassin", "01210", 1000000);
//        tmsDb.newGrp("el_2osood", 20);
//        tmsDb.newMark("quiz 1", 5, 5, 1);
//        tmsDb.assignStudentToGroup(3, "el_2osood");
//        tmsDb.assignStudentToGroup(2, "el_2osood");
//        tmsDb.assignAssistantToGroup(1, "el_2osood");
//        tmsDb.assignStudentToGroup(1, "el_2osood");
//        tmsDb.newHomework("page 1 to 10000000", "el_2osood");
//        tmsDb.newAssistant("Cathrine", "1122", 2);

//

//        Question q1 = new Question("What is love?", "baby don't hurt me",
//                "chemical reaction that compels animals to breed", "masla7a", "life", 'b');
//        Question q2 = new Question("What came first?", "the eqq", "the chick",
//                "the chicken", "3sam el 7adary", 'd');
//        Quiz quiz = new Quiz();
//        quiz.addQuestion(q1);
//        quiz.addQuestion(q2);
//        quiz.printQuiz();
//        List<Student> s = tmsDb.queryStudentsInAGroup("el_2osood");
//        for(Student student : s){
//            System.out.println(student);

        tmsDb.editAssistantSalary("Cathrine", 1000);

        List<Assistant> assistants = tmsDb.queryAllAssistants();
        for (Assistant a : assistants){
            System.out.println(a);
        }







        tmsDb.close();
    }
}
