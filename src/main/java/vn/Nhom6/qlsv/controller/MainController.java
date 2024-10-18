/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.Nhom6.qlsv.view.ClassStatsView;
import vn.Nhom6.qlsv.view.CourseView;
import vn.Nhom6.qlsv.view.MainView;
import vn.Nhom6.qlsv.view.SectionClassView;
import vn.Nhom6.qlsv.view.StudentView;


public class MainController {
    private MainView view;
    
    private MainController() {}
    
    public MainController(MainView view) {
        this.view = view;
        
        view.addManagementStudentListener(new ManagementStudentListener());
        view.addCategoryCourseListener(new CategoryCourseListener());
        view.addManageSectionClassListener(new ManagementSectionClassListener());
        view.addClassStatsListener(new ClassStatsListener());
    }
    
    public void showMainView() {
        view.setVisible(true);
    }
    
    class ManagementStudentListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            StudentView view = new StudentView();
            StudentController controller = new StudentController(view);
            controller.showStudentView();
        }
        
    }
    
    class CategoryCourseListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            CourseView view = new CourseView();
            CourseController controller = new CourseController(view);
            controller.showCourseView();
        }
        
    }
    
    class ManagementSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SectionClassView view = new SectionClassView();
            SectionClassController controller = new SectionClassController(view);
            controller.showSectionClassView();
        }
        
    }
    
    class ClassStatsListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ClassStatsView view = new ClassStatsView();
            ClassStatsController controller = new ClassStatsController(view);
            controller.showClassStatsView();
        }
        
    }
    
}
