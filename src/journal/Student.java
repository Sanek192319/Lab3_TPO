package journal;

public class Student {
    public final int numberInGroup;
    public final Group group;
    public final String name;

    public Student(Group group, String name, int numberInGroup) {
        this.group = group;
        this.name = name;
        this.numberInGroup = numberInGroup;
    }

//    public String toString() {
//        return this.group.getGroupName() + " " + this.name;
//    }
}