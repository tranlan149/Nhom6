/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.Nhom6.qlsv.dao.CourseDao;
import vn.Nhom6.qlsv.entity.Course;
import vn.Nhom6.qlsv.view.CourseView;


public class CourseController {
    private CourseDao courseDao;
    private CourseView courseView;
    
    private CourseController() {
    }

    public CourseController(CourseView view) {
        this.courseView = view;
        courseDao = new CourseDao();

        view.addAddCourseListener(new AddCourseListener());
        view.addEditCourseListener(new EditCourseListener());
        view.addDeleteCourseListener(new DeleteCourseListener());
        view.addClearListener(new ClearCourseListener());
        view.addListCourseSelectionListener(new ListCourseSelectionListener());
        view.addSearchCourseListener(view);
        view.addSearchCourseListener(new SearchCourseListener());
    }

    public void showCourseView() {
        List<Course> courseList = courseDao.getListCourses();
        courseView.setVisible(true);
        courseView.showListCourses(courseList);
    }

    /**
     * Lớp AddCourseListener 
     * chứa cài đặt cho sự kiện click button "Thêm"
     */
    class AddCourseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Course course = courseView.getCourseInfo();
            if (course != null) {
                courseDao.add(course);
                courseView.showCourse(course);
                courseView.showListCourses(courseDao.getListCourses());
                courseView.showMessage("Thêm khóa học thành công!");
            }
        }
    }

    /**
     * Lớp EditCourseListener 
     * chứa cài đặt cho sự kiện click button "Sửa"
     */
    class EditCourseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Course course = courseView.getCourseInfo();
            if (course != null) {
                courseDao.edit(course);
                courseView.showCourse(course);
                courseView.showListCourses(courseDao.getListCourses());
                courseView.showMessage("Cập nhật khóa học thành công!");
            }
        }
    }

    /**
     * Lớp DeleteCourseListener 
     * chứa cài đặt cho sự kiện click button "Xóa"
     */
    class DeleteCourseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Course course = courseView.getCourseInfo();
            if (course != null) {
                courseDao.delete(course);
                courseView.clearCourseInfo();
                courseView.showListCourses(courseDao.getListCourses());
                courseView.showMessage("Xóa khóa học thành công!");
            }
        }
    }

    /**
     * Lớp ClearCourseListener 
     * chứa cài đặt cho sự kiện click button "Làm mới"
     */
    class ClearCourseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            courseView.clearCourseInfo();
        }
    }

    /**
     * Lớp ListCourseSelectionListener 
     * chứa cài đặt cho sự kiện chọn khóa học trong bảng
     */
    class ListCourseSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            courseView.fillCourseFromSelectedRow();
        }
    }
    
    class SearchCourseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            courseView.filterCourse(courseDao.getListCourses());
        }
    }
}
