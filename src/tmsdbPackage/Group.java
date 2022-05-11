package tmsdbPackage;

public class Group {

    private int id;
    private String groupName;
    private int numberOfStudents;
    private int maxStudents;
    private String assistant;
    private int pin;

    public Group(int id, String groupName, int numberOfStudents, int maxStudents, String assistant, int pin) {
        this.id = id;
        this.groupName = groupName;
        this.numberOfStudents = numberOfStudents;
        this.maxStudents = maxStudents;
        this.assistant = assistant;
        this.pin = pin;
    }

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getNumberOfStudents() {
        return numberOfStudents;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public String getAssistant() {
        return assistant;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public String toString(){
        return "Name: " + groupName +
                "\nID: " + id +
                "\nStudents: " + numberOfStudents + "/" + maxStudents +
                "\nassistant: " + assistant;
    }
}
