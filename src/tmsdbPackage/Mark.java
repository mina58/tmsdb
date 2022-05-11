package tmsdbPackage;

public class Mark {

    private String name;
    private int mark;
    private int maxMark;

    public Mark(String name, int mark, int maxMark) {
        this.name = name;
        this.mark = mark;
        this.maxMark = maxMark;
    }

    public String getName() {
        return name;
    }

    public int getMark() {
        return mark;
    }

    public int getMaxMark() {
        return maxMark;
    }

    @Override
    public String toString(){
        return name + ": " + mark + "/" + maxMark;
    }
}
