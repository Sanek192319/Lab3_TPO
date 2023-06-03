package journal;

import java.util.*;
public class Journal {
    private final HashMap<Student, HashMap<String, Integer>[]> studentGrades;

    public Journal(Group[] groups, int weeks) {
        studentGrades = new HashMap<>();
        for (Group group : groups) {
            for (Student student : group.getStudents()) {
                var grades = new HashMap[weeks];
                for (int i = 0; i < weeks; i++) {
                    grades[i] = new HashMap<String, Integer>(4);
                }
                studentGrades.put(student, grades);
            }
        }
    }

    public void addGrade(Student student, int week, String teacherName, int grade) {
        if (grade < 0 || grade > 100) {
            throw new RuntimeException("Invalid grade");
        }
        studentGrades.get(student)[week].put(teacherName, grade);
    }

    public void printScores() {
        ArrayList<Student> students = new ArrayList<>(studentGrades.keySet());
        students.sort(Comparator.comparing((Student s) -> s.group.getGroupName()).thenComparingInt(s -> s.numberInGroup));

        for (Student student : students) {
            System.out.println("Information about " + student.name + " from "+student.group.getGroupName()+":");
            var grades = studentGrades.get(student);

            System.out.print("Grades obtained: [ ");
            int sum = 0;
            int counter = 0;
            for (int i = 0; i < grades.length; i++) {
                for (var grade : grades[i].values()) {
                    System.out.print(grade + ", ");
                    sum += grade;
                    counter++;
                }
            }
            System.out.print("\b\b ]\n");
            System.out.println("Grades sum: "+sum+"; Average grade: "+((float)(sum*100/counter)/100));
        }
    }
}