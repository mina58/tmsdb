package tmsdbPackage;

public class Student {
    private String name;
    private int id;
    private String studentNumber;
    private String parentNumber;
    private int attendance;
    private String grp;

    public Student(String name, int id, String studentNumber, String parentNumber, int attendance) {
        this.name = name;
        this.id = id;
        this.studentNumber = studentNumber;
        this.parentNumber = parentNumber;
        this.attendance = attendance;
    }

    public Student(String name, int id, String studentNumber, String parentNumber, int attendance, String group) {
        this.name = name;
        this.id = id;
        this.studentNumber = studentNumber;
        this.parentNumber = parentNumber;
        this.attendance = attendance;
        this.grp = group;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public int getAttendance() {
        return attendance;
    }

    public String getGrp() {
        return grp;
    }

    @Override
    public String toString() {
        if(grp == null){
            return "Name: " + name +
                    "\nID: " + id +
                    "\nStudent Number: " + studentNumber +
                    "\nParent Number: " + parentNumber;
        }
        else{
            return "Name: " + name +
                    "\nID: " + id +
                    "\nStudent Number: " + studentNumber +
                    "\nParent Number: " + parentNumber +
                    "\nGroup: " + grp;
        }

    }
}
