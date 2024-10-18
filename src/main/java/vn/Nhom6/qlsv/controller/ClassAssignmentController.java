/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import vn.Nhom6.qlsv.dao.SectionClassDao;
import vn.Nhom6.qlsv.dao.StudentDao;
import vn.Nhom6.qlsv.entity.Course;
import vn.Nhom6.qlsv.entity.SectionClass;
import vn.Nhom6.qlsv.entity.Student;
import vn.Nhom6.qlsv.view.ClassAssignmentView;


public class ClassAssignmentController {
    private StudentDao studentDao;
    private ClassAssignmentView view;
    private List<Student> listStudent;
    private List<Student> listAssignStudent;
    private SectionClass sectionClass;
    private SectionClassDao classDao;
    private SectionClassController parentController;
    
    private ClassAssignmentController() {
    }
    
    public ClassAssignmentController(ClassAssignmentView view,
            SectionClass sectionClass,
            SectionClassController classController) {
        this.view = view;
        studentDao = new StudentDao();
        listStudent = new ArrayList<>();
        listAssignStudent = new ArrayList<>();
        this.sectionClass = sectionClass;
        classDao = new SectionClassDao();
        this.parentController = classController;
        
        
        view.assignStudentListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                assignSelectedStudents();
            }
        });
        
        view.removeStudentListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeSelectedStudents();
            }
        });
        
        view.saveAssignListener(new SaveAssignmentListener());
    }
    
    public void showAssignView() {
        listStudent = studentDao.getListStudents();
        listAssignStudent = sectionClass.getStudents();
        if(listAssignStudent == null)
            listAssignStudent = new ArrayList<Student>();
        else
            listStudent.removeAll(listAssignStudent);
        view.setVisible(true);
        view.showListStudent(listStudent);
        view.showListAssignStudent(listAssignStudent);
        Course course = sectionClass.getCourse();
        view.setInfoSectionClass("Học phần: " + course.getId() + " - " + course.getCourseName() + " - " + course.getCourseType());
    }
    
    private void assignSelectedStudents() {
        int[] selectedRows = view.getStudentsTable().getSelectedRows();
        List<Student> studentsToAssign = new ArrayList<>();

        for (int row : selectedRows) {
            Student student = view.getStudentFromTable(view.getStudentsTable(), row);
            listAssignStudent.add(student);
            studentsToAssign.add(student);
        }

        listStudent.removeAll(studentsToAssign);
        view.showListStudent(listStudent);
        view.showListAssignStudent(listAssignStudent);
    }

    private void removeSelectedStudents() {
        int[] selectedRows = view.getAssignedStudentsTable().getSelectedRows();
        List<Student> studentsToRemove = new ArrayList<>();

        for (int row : selectedRows) {
            Student student = view.getStudentFromTable(view.getAssignedStudentsTable(), row);
            listStudent.add(student);
            studentsToRemove.add(student);
        }

        listAssignStudent.removeAll(studentsToRemove);
        view.showListStudent(listStudent);
        view.showListAssignStudent(listAssignStudent);
    }
    
    class SaveAssignmentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(listAssignStudent.size() > sectionClass.getMaxStudents()) {
                view.showMessage("Số sinh viên đăng ký đã quá số lượng tối đa");
                return;
            }
            sectionClass.setStudents(listAssignStudent);
            classDao.edit(sectionClass);
            parentController.showSectionClassView();
            view.closeForm();
        }
    }
}
