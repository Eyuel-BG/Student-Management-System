import java.io.Serializable;

public class Student implements Serializable {
    public int id;
    public String name;
    public String dept;
    public double gpa;

    public Student(int id, String name, String dept, double gpa) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.gpa = gpa;
    }
}
