/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame implements ActionListener {
    private JButton manageStudentsBtn;
    private JButton courseCatalogBtn;
    private JButton manageSectionClassBtn;
    private JButton statisticsClassBtn;

    public MainView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        manageStudentsBtn = new JButton("Quản lý sinh viên");
        courseCatalogBtn = new JButton("Danh mục môn học");
        manageSectionClassBtn = new JButton("Quản lý học phần");
        statisticsClassBtn = new JButton("Thống kê lớp");

        manageStudentsBtn.addActionListener(this);
        courseCatalogBtn.addActionListener(this);
        manageSectionClassBtn.addActionListener(this);
        statisticsClassBtn.addActionListener(this);

        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(400, 300);
        panel.setLayout(layout);

        panel.add(manageStudentsBtn);
        panel.add(courseCatalogBtn);
        panel.add(manageSectionClassBtn);
        panel.add(statisticsClassBtn);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, manageStudentsBtn, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, manageStudentsBtn, 20, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, courseCatalogBtn, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, courseCatalogBtn, 20, SpringLayout.SOUTH, manageStudentsBtn);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, manageSectionClassBtn, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, manageSectionClassBtn, 20, SpringLayout.SOUTH, courseCatalogBtn);

        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, statisticsClassBtn, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, statisticsClassBtn, 20, SpringLayout.SOUTH, manageSectionClassBtn);

        this.add(panel);
        this.pack();
        this.setTitle("Trang chủ");
        this.setSize(400, 300);
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public void addManagementStudentListener(ActionListener listener) {
        manageStudentsBtn.addActionListener(listener);
    }
    
    public void addCategoryCourseListener(ActionListener listener) {
        courseCatalogBtn.addActionListener(listener);
    }
    
    public void addManageSectionClassListener(ActionListener listener) {
        manageSectionClassBtn.addActionListener(listener);
    }
    
    public void addClassStatsListener(ActionListener listener) {
        statisticsClassBtn.addActionListener(listener);
    }
}
