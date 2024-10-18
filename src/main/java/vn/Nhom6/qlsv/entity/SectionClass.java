
package vn.Nhom6.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "sectionclass")
@XmlAccessorType(XmlAccessType.FIELD)
public class SectionClass {
    private int id;
    private String classType; // ENUM: 'LT', 'TH'
    private int maxStudents;
    @XmlElement(name = "course")
    private Course course;
    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    private List<Student> students;

    public SectionClass() {
    }

    public SectionClass(int id, String classType, int maxStudents, Course course, List<Student> students) {
        this.id = id;
        this.classType = classType;
        this.maxStudents = maxStudents;
        this.course = course;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
