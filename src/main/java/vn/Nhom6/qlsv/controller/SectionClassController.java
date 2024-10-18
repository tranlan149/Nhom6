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

import vn.Nhom6.qlsv.dao.SectionClassDao;
import vn.Nhom6.qlsv.entity.SectionClass;
import vn.Nhom6.qlsv.view.ClassAssignmentView;
import vn.Nhom6.qlsv.view.SectionClassView;


public class SectionClassController {
    private SectionClassDao sectionClassDao;
    private SectionClassView sectionClassView;

    private SectionClassController() {
    }

    public SectionClassController(SectionClassView view) {
        this.sectionClassView = view;
        sectionClassDao = new SectionClassDao();

        view.addAddSectionClassListener(new AddSectionClassListener());
        view.addEditSectionClassListener(new EditSectionClassListener());
        view.addDeleteSectionClassListener(new DeleteSectionClassListener());
        view.addClearListener(new ClearSectionClassListener());
        view.addListSectionClassSelectionListener(new ListSectionClassSelectionListener());
        view.addSearchSectionClassListener(new SearchSectionClassListener());
        view.addAssignStudentListener(new AssignmentStudentListener());
    }

    public void showSectionClassView() {
        List<SectionClass> sectionClassList = sectionClassDao.getListSectionClasses();
        sectionClassView.setVisible(true);
        sectionClassView.showListSectionClasses(sectionClassList);
    }
    
    public void openAssignmentForm(SectionClass sClass) {
        ClassAssignmentView view = new ClassAssignmentView();
        ClassAssignmentController controller = new ClassAssignmentController(view, sClass, this);
        controller.showAssignView();
    }

    /**
     * Lớp AddSectionClassListener 
     * chứa cài đặt cho sự kiện click button "Thêm"
     */
    class AddSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SectionClass sectionClass = sectionClassView.getSectionClassInfo();
            if (sectionClass != null) {
                sectionClassDao.add(sectionClass);
                sectionClassView.showSectionClass(sectionClass);
                sectionClassView.showListSectionClasses(sectionClassDao.getListSectionClasses());
                sectionClassView.showMessage("Thêm lớp học thành công!");
            }
        }
    }

    /**
     * Lớp EditSectionClassListener 
     * chứa cài đặt cho sự kiện click button "Sửa"
     */
    class EditSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SectionClass sectionClass = sectionClassView.getSectionClassInfo();
            if (sectionClass != null) {
                sectionClassDao.editNoStudent(sectionClass);
                sectionClassView.showSectionClass(sectionClass);
                sectionClassView.showListSectionClasses(sectionClassDao.getListSectionClasses());
                sectionClassView.showMessage("Cập nhật lớp học thành công!");
            }
        }
    }

    /**
     * Lớp DeleteSectionClassListener 
     * chứa cài đặt cho sự kiện click button "Xóa"
     */
    class DeleteSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SectionClass sectionClass = sectionClassView.getSectionClassInfo();
            if (sectionClass != null) {
                sectionClassDao.delete(sectionClass);
                sectionClassView.clearSectionClassInfo();
                sectionClassView.showListSectionClasses(sectionClassDao.getListSectionClasses());
                sectionClassView.showMessage("Xóa lớp học thành công!");
            }
        }
    }

    /**
     * Lớp ClearSectionClassListener 
     * chứa cài đặt cho sự kiện click button "Làm mới"
     */
    class ClearSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sectionClassView.clearSectionClassInfo();
        }
    }

    /**
     * Lớp ListSectionClassSelectionListener 
     * chứa cài đặt cho sự kiện chọn lớp học trong bảng
     */
    class ListSectionClassSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            sectionClassView.fillSectionClassFromSelectedRow();
        }
    }

    class SearchSectionClassListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            sectionClassView.filterSectionClass(sectionClassDao.getListSectionClasses());
        }
    }
    
    class AssignmentStudentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SectionClass sectionClass = sectionClassDao.getDataById(sectionClassView.getSectionClassInfo().getId());
            openAssignmentForm(sectionClass);
        }
    }
}
