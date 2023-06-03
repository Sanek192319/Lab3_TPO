package journal;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final int GROUPS_NUM = 3;
        final int WEEKS_NUM = 15;
        final int TEACHER_NUM = 4;

        Group[] groups = new Group[GROUPS_NUM];
        for (int i = 0; i < GROUPS_NUM; i++) {
            groups[i] = new Group("IP-0" + (i+1), 20);
        }
        Journal journal = new Journal(groups, WEEKS_NUM);

        ScheduleThread schedule = new ScheduleThread(WEEKS_NUM);
        ArrayList<TeacherThread> teachers = new ArrayList<>(TEACHER_NUM);
        teachers.add(new TeacherThread("Adolf", journal, groups, schedule));
        teachers.add(new TeacherThread("Luka", journal, new Group[]{groups[0]}, schedule));
        teachers.add(new TeacherThread("Alex", journal, new Group[]{groups[1]}, schedule));
        teachers.add(new TeacherThread("Petro", journal, new Group[]{groups[2]}, schedule));

        teachers.forEach(Thread::start);
        schedule.start();


        // Wait until all weeks are finished
        try {
            for (TeacherThread thread : teachers){
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        journal.printScores();
    }
}
