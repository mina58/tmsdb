package tmsdbPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TmsDb {

    private static final String DB_NAME = "tms.db";

    private static final String CONNECTION_STRING = "jdbc:sqlite:" +
            System.getProperty("user.dir") + "\\" + DB_NAME;

    private Connection conn;

    public void open(){
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
        } catch (SQLException e){
            System.out.println("Could not open db: " + e.getMessage());
        }
    }

    public void close(){
        try {
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e){
            System.out.println("Could not close db: " + e.getMessage());
        }
    }

    public void create(){
        try(Statement statement = conn.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS students (_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " student_name TEXT, student_number TEXT," +
                    " parent_number TEXT, attendance INTEGER, grp INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS assistants (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "assistant_name TEXT, assistant_number" +
                    " TEXT, salary INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS grps (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "group_name INTEGER, number_of_students INTEGER, max_students INTEGER, assistant INTEGER, pin INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS marks (mark_name TEXT, mark INTEGER, max_mark INTEGER," +
                    " student INTEGER )");
            statement.execute("CREATE TABLE IF NOT EXISTS homework (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, grp INT)");
        } catch (SQLException e){
            System.out.println("Error creating database: " + e.getMessage());
        }
    }

    public int generatePin(){
        Random random = new Random();
        return Math.abs(random.nextInt() % 10000);
    }

    public void newStudent (String studentName, String studentNumber, String parentNumber){
        try(Statement statement = conn.createStatement()){
            String s = "INSERT INTO students (student_name, student_number, parent_number, attendance) " +
                    "VALUES ('" + studentName + "', '" + studentNumber + "', '" + parentNumber
                    + "', 0)";
            statement.execute(s);
        } catch (SQLException e){
            System.out.println("Error adding student" + e.getMessage());
        }
    }

    public void newAssistant (String assistantName, String assistantNumber, int salary) {
        try (Statement statement = conn.createStatement()) {
            String s = "INSERT INTO assistants (assistant_name, assistant_number, salary) " +
                    "VALUES ('" + assistantName + "', '" + assistantNumber + "', " + salary
                    + " )";
            statement.execute(s);
        } catch (SQLException e) {
            System.out.println("Error adding assistant" + e.getMessage());
        }
    }

    public int newGrp(String groupName, int maxStudents){
        int pin = generatePin();
        try (Statement statement = conn.createStatement()) {
            String s = "INSERT INTO grps (group_name,number_of_students, max_students, pin) " +
                    "VALUES ('" + groupName + "', 0" + ", " + maxStudents + ", " + pin + ")";
            statement.execute(s);
        } catch (SQLException e) {
            System.out.println("Error adding group" + e.getMessage());
            return -1;
        }
        return pin; //this function returns the pin of the group created
    }

    public void newHomework(String name, String groupName){
        int groupId = getGroupId(groupName);
        try (Statement statement = conn.createStatement()) {
            String s = "INSERT INTO homework (name, grp)" +
                    "VALUES ('" + name + "', " + groupId + ")";
            statement.execute(s);
        } catch (SQLException e) {
            System.out.println("Error adding homework" + e.getMessage());
        }
    }

    public void newMark (String markName, int mark, int maxMark, String studentName){
        int student = getStudentId(studentName);
        try (Statement statement = conn.createStatement()) {
            String s = "INSERT INTO marks (mark_name, mark, max_mark, student) " +
                    "VALUES ('" + markName + "', " + mark + ", " + maxMark + ", " + student + ")";
            statement.execute(s);
        } catch (SQLException e) {
            System.out.println("Error adding mark" + e.getMessage());
        }
    }

    private boolean validateGroupAvailability(int groupId){
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM grps WHERE _id = " + groupId)){
            resultSet.next();
            if(resultSet.getInt(3) >= resultSet.getInt(4)){
                System.out.println("model.Group is full");
                return false;
            } else{
                return true;
            }
        } catch (SQLException e){
            return false;
        }
    }

    private boolean checkIfStudentAlreadyInGroup(int studentId, int groupId){
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT grp FROM students WHERE _id = " + studentId)){
            resultSet.next();
            if(resultSet.getInt(1) == groupId){
                System.out.println("student already in group");
                return true;
            } else {
                return false;
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
            return true;
        }
    }

    private int getGroupId(String groupName){
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT _id FROM grps WHERE group_name = '" + groupName + "'")){
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e){
            e.getMessage();
            return -1;
        }
    }

    private int getAssistantId(String assistantName){
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT _id FROM assistants WHERE assistant_name = '" + assistantName + "'")){
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e){
            e.getMessage();
            return -1;
        }
    }

    private int getStudentId(String studentName){
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT _id FROM students WHERE student_name = '" + studentName + "'")){
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e){
            e.getMessage();
            return -1;
        }
    }

    public int getGroupPin(String groupName){
        int groupId = getGroupId(groupName);
        String s = "SELECT pin FROM grps WHERE _id = " + groupId;

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            return resultSet.getInt(1);
        } catch (SQLException e){
            System.out.println("Failed to get group pin: " + e.getMessage());
            return -1;
        }
    }

    public List<String> queryGroupHomework(String groupName){
        int groupId = getGroupId(groupName);
        String s = "SELECT name FROM homework WHERE grp = " + groupId;
        List<String> homework = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            while (resultSet.next()){
                homework.add(resultSet.getString(1));
            }
            return homework;
        } catch (SQLException e){
            System.out.println("Failed to query group homework: " + e.getMessage());
            return null;
        }
    }

    public String getGroupAssistant(String groupName){
        int groupId = getGroupId(groupName);
        String s = "SELECT assistant_name FROM assistants INNER JOIN grps ON grps.assistant = assistants._id WHERE grps._id = "
                + groupId;

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            if (resultSet.getString(1) == null) {
                return "none";
            } else{
                return resultSet.getString(1);
            }
        } catch (SQLException e){
            System.out.println("Failed to get group assistant: " + e.getMessage());
            return "none";
        }
    }

    public String getStudentGroup(String studentName){
        String s = "SELECT group_name FROM grps INNER JOIN students ON grps._id = students.grp WHERE" +
                " students.student_name = '" + studentName + "'";
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            return resultSet.getString(1);
        }catch (SQLException e){
            System.out.println("Failed to get student group: " + e.getMessage());
            return "none";
        }
    }

    public void assignStudentToGroup(String studentName, String groupName){
        int groupId = getGroupId(groupName);
        int studentId = getStudentId(studentName);
        if(validateGroupAvailability(groupId) && !checkIfStudentAlreadyInGroup(studentId, groupId)) {
            try (Statement statement = conn.createStatement()) {
                String s = "UPDATE students SET grp = " + groupId + " WHERE students._id = " + studentId;
                statement.execute(s);
                s = "UPDATE grps SET number_of_students = number_of_students + 1 WHERE _id = " + groupId;
                statement.execute(s);
            } catch (SQLException e) {
                System.out.println("Error assigning student to group" + e.getMessage());
            }
        }
    }

    public void assignStudentsToGroup(List<String> studentsNames, String groupName){
        for(String s : studentsNames){
            assignStudentToGroup(s, groupName);
        }
    }

    public void assignAssistantToGroup(String assistantName, String groupName){
        int assistantId = getAssistantId(assistantName);
        int groupId = getGroupId(groupName);
        try (Statement statement = conn.createStatement()){
            String s = "UPDATE grps SET assistant = " + assistantId + " WHERE grps._id = " + groupId;
            statement.execute(s);
        } catch (SQLException e){
            System.out.println("Error assigning assistant to group" + e.getMessage());
        }
    }

    public List<Student> queryAllStudents(){
        String s = "SELECT * FROM students ORDER BY student_name COLLATE NOCASE";
        List<Student> students = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){

            while (resultSet.next()){
                Student student = new Student(resultSet.getString(2), resultSet.getInt(1),
                        resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5));
                students.add(student);
            }
        } catch (SQLException e){
            System.out.println("Failed to query students: " + e.getMessage());
            return null;
        }
        return students;
    }

    public List<String> queryStudentsNamesInAGroup(String groupName){
        List<String> students = new ArrayList<>();
        String s = "SELECT student_name FROM students INNER JOIN grps ON students.grp = grps._id WHERE group_name = '" + groupName + "'";
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)) {
            while(resultSet.next()){
                students.add(resultSet.getString(1));
            }
        } catch (SQLException e){
            System.out.println("Failed to query students for group: " + e.getMessage());
            return null;
        }
        return students;
    }

    public List<Assistant> queryAllAssistants(){
        String s = "SELECT * FROM assistants ORDER BY assistant_name";
        List<Assistant> assistants = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){

            while (resultSet.next()){
                Assistant assistant = new Assistant(resultSet.getString(2),  resultSet.getInt(1),
                        resultSet.getDouble(4), resultSet.getString(3));
                assistants.add(assistant);
            }
            return assistants;

        } catch (SQLException e){
            System.out.println("Failed to query assistants: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAllAssistantsNames(){
        String s = "SELECT assistant_name FROM assistants ORDER BY assistant_name";
        List<String> assistants = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){

            while (resultSet.next()){
                assistants.add(resultSet.getString(1));
            }
            return assistants;

        } catch (SQLException e){
            System.out.println("Failed to query assistants names: " + e.getMessage());
            return null;
        }
    }

    public List<Group> queryAllGroups(){
        String s = "SELECT * FROM grps ORDER BY group_name";
        List<Group> groups = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            while (resultSet.next()){
                String assistant = getGroupAssistant(resultSet.getString(2));
                groups.add(new Group(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)
                , resultSet.getInt(4), assistant, resultSet.getInt(6)));
            }
            return groups;
        }catch (SQLException e){
            System.out.println("Failed to query groups: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAllStudentsNames(){
        String s = "SELECT student_name FROM students ORDER BY student_name";
        List<String> students = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){

            while (resultSet.next()){
                students.add(resultSet.getString(1));
            }
            return students;

        } catch (SQLException e){
            System.out.println("Failed to query students names: " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAllGroupsNames(){
        String s = "SELECT group_name FROM grps ORDER BY group_name";
        List<String> groups = new ArrayList<>();

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){

            while (resultSet.next()){
                groups.add(resultSet.getString(1));
            }
            return groups;

        } catch (SQLException e){
            System.out.println("Failed to query students names: " + e.getMessage());
            return null;
        }
    }

    public List<Mark> queryStudentMarks(String studentName){
        int studentId = getStudentId(studentName);
        String s = "SELECT * FROM marks WHERE student = " + studentId;
        List<Mark> marks = new ArrayList<>();
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            while (resultSet.next()){
                Mark m = new Mark(resultSet.getString(1), resultSet.getInt(2)
                        , resultSet.getInt(3));
                marks.add(m);
            }
            return marks;
        } catch (SQLException e){
            System.out.println("Failed to query student marks: " + e.getMessage());
            return null;
        }
    }

    public void editAssistantSalary(String assistantName, int newSalary){
        String s = "UPDATE assistants SET salary = " + newSalary + " WHERE assistant_name = '" + assistantName + "'";

        try(Statement statement = conn.createStatement()){
            statement.execute(s);
        } catch(SQLException e){
            System.out.println("Failed to edit assistant salary: " + e.getMessage());
        }
    }

    public int countStudents(){
        String s = "SELECT COUNT(*) FROM students";

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            return resultSet.getInt(1);
        } catch (SQLException e){
            System.out.println("failed to count students: " + e.getMessage());
            return -1;
        }

    }

    public int countAssistants(){
        String s = "SELECT COUNT(*) FROM assistants";

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            return resultSet.getInt(1);
        } catch (SQLException e){
            System.out.println("failed to count assistants: " + e.getMessage());
            return -1;
        }

    }

    public Assistant selectAssistant(String assistantName){
        String s = "SELECT * FROM assistants WHERE assistant_name = '" + assistantName + "'";

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            Assistant assistant = new Assistant(resultSet.getString(2), resultSet.getInt(1),
                    resultSet.getInt(4), resultSet.getString(3));
            return assistant;
        } catch (SQLException e){
            System.out.println("Failed to select assistant: " + e.getMessage());
            return null;
        }

    }

    public Group selectGroup(String groupName){
        String s = "SELECT * FROM grps WHERE group_name = '" + groupName + "'";

        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            String assistant = getGroupAssistant(groupName);
            Group group = new Group(resultSet.getInt(1), resultSet.getString(2),
                    resultSet.getInt(3), resultSet.getInt(4), assistant, resultSet.getInt(6));
            return group;
        } catch (SQLException e){
            System.out.println("Failed to select group: " + e.getMessage());
            return null;
        }
    }

    public Student selectStudent(String studentName){
        String s = "SELECT * FROM students WHERE student_name = '" + studentName + "'";
        String group = getStudentGroup(studentName);
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(s)){
            Student student = new Student(resultSet.getString(2), resultSet.getInt(1),
                    resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), group);
            return student;
        } catch (SQLException e){
            System.out.println("Failed to select student: " + e.getMessage());
            return null;
        }
    }

    public void incrementAttendance(String studentName){
        String s = "UPDATE students SET attendance = attendance + 1 WHERE student_name = '" + studentName + "'";
        try(Statement statement = conn.createStatement()){
            statement.execute(s);
        } catch (SQLException e){
            System.out.println("Failed to update student mark: " + e.getMessage());
        }
    }





}