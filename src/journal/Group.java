package journal;

public class Group {
    private final String name;
    private final Student[] students;
    public final int size;

    public Group(String name, int groupSize) {
        this.name = name;
        this.size = groupSize;
        this.students = new Student[groupSize];
        for (int i = 0; i < groupSize; i++) {
            this.students[i] = new Student(this, "student" + i, i);
        }
    }

    public Student[] getStudents() {
        return this.students;
    }

    public String getGroupName() {
        return this.name;
    }
}