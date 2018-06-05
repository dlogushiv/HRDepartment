package logic;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ManagementSystem {
    private List<Group> groups;
    private Collection<Student> students;

    private static ManagementSystem instance;

    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    public void loadGroups() {
        groups = new ArrayList<Group>();

        Group group = new Group();

        group.setGroupId(1);
        group.setNameGroup("Первая");
        group.setCurator("Доктор Борменталь");
        group.setSpeciality("Создание собачек из человеков");
        groups.add(group);

        group = new Group();
        group.setGroupId(2);
        group.setNameGroup("Вторая");
        group.setCurator("Профессор Преображенский");
        group.setSpeciality("Создание человеков из собачек");
        groups.add(group);
    }

    public void loadStudents() {
        students = new TreeSet<Student>();

        Student student = new Student();
        Calendar calendar = Calendar.getInstance();

        // Вторая группа
        student.setStudentId(1);
        student.setFirstName("Иван");
        student.setPatronymic("Сергеевич");
        student.setSurName("Степанов");
        student.setSex('М');
        calendar.set(1990, 3, 20);
        student.setDateOfBirth(calendar.getTime());
        student.setGroupId(2);
        student.setEducationYear(2006);
        students.add(student);

        student = new Student();
        student.setStudentId(2);
        student.setFirstName("Наталья");
        student.setPatronymic("Андреевна");
        student.setSurName("Чичикова");
        student.setSex('Ж');
        calendar.set(1990, 6, 10);
        student.setDateOfBirth(calendar.getTime());
        student.setGroupId(2);
        student.setEducationYear(2006);
        students.add(student);

        // Первая группа
        student = new Student();
        student.setStudentId(3);
        student.setFirstName("Петр");
        student.setPatronymic("Викторович");
        student.setSurName("Сушкин");
        student.setSex('М');
        calendar.set(1991, 3, 12);
        student.setDateOfBirth(calendar.getTime());
        student.setEducationYear(2006);
        student.setGroupId(1);
        students.add(student);

        student = new Student();
        student.setStudentId(4);
        student.setFirstName("Вероника");
        student.setPatronymic("Сергеевна");
        student.setSurName("Ковалева");
        student.setSex('Ж');
        calendar.set(1991, 7, 19);
        student.setDateOfBirth(calendar.getTime());
        student.setEducationYear(2006);
        student.setGroupId(1);
        students.add(student);
    }

    // get all groups
    public List<Group> getGroups() {
        return groups;
    }

    // get all students
    public Collection<Student> getAllStudents() {
        return students;
    }

    // get all students from some group
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> studentsFromGroup = new TreeSet<Student>();
        for (Student st : students) {
            if (st.getGroupId() == group.getGroupId() && st.getEducationYear() == year) {
                studentsFromGroup.add(st);
            }
        }
        return studentsFromGroup;
    }

    // move students from one group with some education year to another group with other education year
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear){
        for (Student st:students) {
            if (st.getGroupId()==oldGroup.getGroupId() && st.getEducationYear()==oldYear){
                st.setGroupId(newGroup.getGroupId());
                st.setEducationYear(newYear);
            }
        }
    }



    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    public static void printString(Object s) {
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    public static void printString() {
        System.out.println();
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream("out.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        ManagementSystem ms = ManagementSystem.getInstance();
    }

}
