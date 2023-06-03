package journal;

import java.util.ArrayList;
import java.util.Random;

public class TeacherThread extends Thread {
    protected final String teacherName;
    protected final Journal journal;
    protected final Group[] groups;
    protected final ScheduleThread schedule;

    protected final Random gradeGenerator = new Random();

    public TeacherThread(String name, Journal journal, Group[] groups, ScheduleThread scheduleThread) {
        this.teacherName = name;
        this.journal = journal;
        this.groups = groups;
        this.schedule = scheduleThread;
    }

    @Override
    public void run() {
        for (int w = 0; w < schedule.WeeksNumber; w++){
            schedule.lock.lock();
            try {
                while (schedule.CurrentWeek < w) {
                    schedule.TimeToSetRates.await();
                }

                for (Group group : groups) {
                    for (Student student : group.getStudents()) {
                        journal.addGrade(student, w, teacherName, gradeGenerator.nextInt(60,101));
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                schedule.lock.unlock();
            }
        }
    }

}