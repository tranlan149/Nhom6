/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vn.Nhom6.qlsv.dao.SectionClassDao;
import vn.Nhom6.qlsv.view.ClassStatsView;


public class ClassStatsController {
    private SectionClassDao sectionClassDao;
    private ClassStatsView view;
    
    private ClassStatsController() {
    }
    
    public ClassStatsController(ClassStatsView view) {
        this.view = view;
        sectionClassDao = new SectionClassDao();
        
        view.addFilterSectionClassListener(new FilterSectionListener());
    }
    
    public void showClassStatsView() {
        view.setVisible(true);
        view.filterClassStats(sectionClassDao.getListSectionClasses());
    }
    
    class FilterSectionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            view.filterClassStats(sectionClassDao.getListSectionClasses());
        }
    }
}
