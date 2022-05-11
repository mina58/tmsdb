package tmsdbPackage;

public class Assistant {

    private String name;
    private int id;
    private double salary;
    private String assistantNumber;

    public Assistant(String name, int id, double salary, String assistantNumber) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        this.assistantNumber = assistantNumber;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public String getAssistantNumber() {
        return assistantNumber;
    }

    @Override
    public String toString(){
        return "Name: " + name +
                "\nID: " + id +
                "\nAssistant Number: " + assistantNumber +
                "\nSalary: " + salary;
    }
}
