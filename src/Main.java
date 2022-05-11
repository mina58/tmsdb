import tmsdbPackage.TmsDb;

public class Main {

    public static void main(String[] args) {
        TmsDb tmsDb = new TmsDb();
        tmsDb.open();
        tmsDb.create();
//        tmsDb.newStudent("Jimmy", "01234", "0122");
//        tmsDb.newStudent("Malak", "01234", "0122");
//        tmsDb.newStudent("Sarah", "01234", "0122");
//        tmsDb.newStudent("Amr", "01234", "0122");
//        tmsDb.newStudent("Mina Shawket", "1112", "ss");
//        tmsDb.newAssistant("Yassin", "01210", 1000000);
//        tmsDb.newGrp("el_2osood", 20);
//        List<String> s = new ArrayList<>();
//        s.add("Amr");
//        s.add("Malak");
//        s.add("Jimmy");
//        s.add("Sarah");
//        s.add("Mina Shawket");
//        tmsDb.assignStudentsToGroup(s, "el_2osood");
//        tmsDb.newMark("quiz 1", 5, 5, 1);
//        tmsDb.assignStudentToGroup(3, "el_2osood");
//        tmsDb.assignStudentToGroup(2, "el_2osood");
//        tmsDb.assignAssistantToGroup(1, "el_2osood");
//        tmsDb.assignStudentToGroup(1, "el_2osood");
//        tmsDb.newHomework("page 1 to 10000000", "el_2osood");
//        tmsDb.newAssistant("Cathrine", "1122", 2);

//

//        Question q1 = new Question("2+2", "1", "2", "3", "5");
//        Question q2 = new Question("2+3", "1", "2", "3", "6");
//        Quiz quiz = new Quiz();
//        quiz.addQuestion(q1);
//        quiz.setQuizName("olo");
//        quiz.addQuestion(q1);
//        QuizPrinter.printQuiz(quiz);
//        quiz.printQuiz();
//        List<Student> s = tmsDb.queryStudentsInAGroup("el_2osood");
//        for(Student student : s){
//            System.out.println(student);

//        tmsDb.editAssistantSalary("Cathrine", 1000);
//
//        List<Assistant> assistants = tmsDb.queryAllAssistants();
//        List<String> assistantsNames = tmsDb.queryAllAssistantsNames();
//        for (Assistant a : assistants){
//            System.out.println(a);
//        }

//        for(String s : assistantsNames){
//            System.out.println(s);
//        }
//
//        System.out.println(tmsDb.countStudents());
//        System.out.println(tmsDb.countAssistants());
//        System.out.println(tmsDb.getGroupPin("el_2osood"));
//        tmsDb.newGrp("el_nmoor", 20);
//        tmsDb.assignAssistantToGroup(2, "el_nmoor");
//        tmsDb.newGrp("el_2rood", 30);
//        List<Group> groups = tmsDb.queryAllGroups();
//        for (Group g : groups){
//            System.out.println(g);
//        }

//        List<String> studentNames = tmsDb.queryAllStudentsNames();
//        for(String s : studentNames){
//            System.out.println(s);
//        }
//
//        List<String> groupsNames = tmsDb.queryAllGroupsNames();
//        for(String s : groupsNames){
//            System.out.println(s);
//        }
//
//        System.out.println(tmsDb.selectAssistant("Yassin"));
//       tmsDb.assignAssistantToGroup("Yassin", "el_2osood");
//        tmsDb.newStudent("mnmn", "11", "11");
//        System.out.println(tmsDb.getStudentGroup("mnmn"));
//        System.out.println(tmsDb.getStudentGroup("Sarah"));
//        System.out.println(tmsDb.selectGroup("el_2osood"));
//        System.out.println(tmsDb.selectStudent("mnmn"));
//        System.out.println(tmsDb.selectStudent("Sarah"));

//        List<String> students = tmsDb.queryStudentsNamesInAGroup("el_2osood");
//        for(String s: students){
//            System.out.println(s);
//        }
//        System.out.println(tmsDb.getGroupPin("el_2osood"));
//        tmsDb.newMark("quiz 1", 5, 5, "Malak");
//        tmsDb.newMark("quiz 2", 5, 5, "Malak");
//        tmsDb.newMark("quiz 3", 5, 5, "Malak");
//        List<Mark> marks = tmsDb.queryStudentMarks("Malak");

//        for(Mark m : marks){
//            System.out.println(m);
//        }
//        tmsDb.newHomework("page 1 to 3", "el_2osood");
//        tmsDb.newHomework("page 4 to 6", "el_2osood");
//        tmsDb.newHomework("page 7 to 9", "el_2osood");
//        tmsDb.newHomework("page 10 to 12", "el_2osood");
//
//        List<String> homework = tmsDb.queryGroupHomework("el_2osood");
//        System.out.println(homework);

        tmsDb.incrementAttendance("Malak");








        tmsDb.close();
    }
}
