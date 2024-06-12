import java.util.*;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Jan", "Kowalski", "123456", "jan.kowalski@gmail.com", "ul. Złota 1");
        Student student2 = new Student("Anna", "Nowak", "654321", "anna.nowak@gmail.com", "ul. Złota 2");

        student1.addGrade(3.0);
        student1.addGrade(4.0);
        student1.addGrade(5.0);

        student2.addGrade(2.5);
        student2.addGrade(3.5);
        student2.addGrade(4.5);

        System.out.println("Średnia ocen studenta 1: " + student1.calculateAverageGrade());
        System.out.println("Średnia ocen studenta 2: " + student2.calculateAverageGrade());

        StudentGroup group = new StudentGroup("Grupa A");

        group.addStudent(student1);
        group.addStudent(student2);

        try {
            group.addStudent(student1);
        } catch (IllegalStateException e) {
            System.out.println("Błąd: " + e.getMessage());
        }
    }
}

class Student {
    public String fname;
    public String lname;
    public String indexNumber;
    public String email;
    public String address;
    public List<Double> grades;

    public Student(String fname, String lname, String indexNumber, String email, String address) {
        this.fname = fname;
        this.lname = lname;
        this.indexNumber = indexNumber;
        this.email = email;
        this.address = address;
        this.grades = new ArrayList<>();
    }

    public void addGrade(double grade) {
        if (this.grades.size() >= 20) {
            throw new IllegalArgumentException("Student może mieć maksymalnie 20 ocen.");
        }
        this.grades.add(grade);
    }

    public double calculateAverageGrade() {
        if (this.grades.isEmpty()) {
            throw new IllegalArgumentException("Student nie ma żadnej oceny.");
        }

        double sum = 0;
        for (double grade : this.grades) {
            sum += grade;
        }
        double average = sum / this.grades.size();
        return roundGrade(average);
    }

    private double roundGrade(double average) {
        double[] possibleGrades = {2.0, 2.5, 3.0, 3.5, 4.0, 4.5, 5.0};
        double closest = possibleGrades[0];
        for (double grade : possibleGrades) {
            if (Math.abs(grade - average) < Math.abs(closest - average)) {
                closest = grade;
            }
        }
        return closest;
    }
}

class StudentGroup {
    public String name;
    public Set<Student> students;

    public StudentGroup(String name) {
        this.name = name;
        this.students = new HashSet<>();
    }

    public void addStudent(Student student) {
        if (this.students.size() >= 15) {
            throw new IllegalStateException("Maksymalna liczba osób w grupie to 15.");
        }
        if (!this.students.add(student)) {
            throw new IllegalStateException("Student został już dodany do grupy.");
        }
    }
}

