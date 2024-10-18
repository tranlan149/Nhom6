/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.Nhom6.qlsv.entity.Student;


public class ClassAssignmentView extends JFrame implements ActionListener, ListSelectionListener {
    private JTable studentsTable;
    private JTable assignedStudentsTable;
    private JScrollPane scrollPaneStudentTable;
    private JScrollPane scrollPaneAssignedStudentTable;
    private JButton assignButton;
    private JButton removeButton;
    
    private JLabel titleFormLabel;
    private JLabel infoClassLabel;
    
    private JButton saveBtn;
    
    private String[] columnNames = new String[] {"ID", "Tên", "Tuổi", "Địa chỉ", "GPA"};
    
    private Object dataStudent = new Object[][]{};
    private Object dataAssignStudent = new Object[][]{};
    
    public ClassAssignmentView() {
        initComponents();
    }
    
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        titleFormLabel = new JLabel("PHÂN LỚP CHO SINH VIÊN");
        titleFormLabel.setFont(new Font("Serif", Font.BOLD, 20));
        infoClassLabel = new JLabel();
        infoClassLabel.setFont(new Font("Serif", Font.ITALIC, 16));
        
        saveBtn = new JButton("Lưu");
        assignButton = new JButton(">>");
        removeButton = new JButton("<<");
        
        scrollPaneStudentTable = new JScrollPane();
        studentsTable = new JTable();
        scrollPaneAssignedStudentTable = new JScrollPane();
        assignedStudentsTable = new JTable();
        
        studentsTable.setModel(new DefaultTableModel((Object[][]) dataStudent, columnNames));
        scrollPaneStudentTable.setViewportView(studentsTable);
        scrollPaneStudentTable.setPreferredSize(new Dimension(480, 300));
        
        assignedStudentsTable.setModel(new DefaultTableModel((Object[][]) dataAssignStudent, columnNames));
        scrollPaneAssignedStudentTable.setViewportView(assignedStudentsTable);
        scrollPaneAssignedStudentTable.setPreferredSize(new Dimension(480, 300));
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(titleFormLabel);
        panel.add(infoClassLabel);
        
        panel.add(scrollPaneStudentTable);
        panel.add(scrollPaneAssignedStudentTable);
        
        panel.add(saveBtn);
        panel.add(assignButton);
        panel.add(removeButton);
        
        layout.putConstraint(SpringLayout.WEST, titleFormLabel, 430, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, titleFormLabel, 15, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, infoClassLabel, 450, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, infoClassLabel, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, saveBtn, 950, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, saveBtn, 90, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, scrollPaneStudentTable, 15, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneStudentTable, 120, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, assignButton, 515, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, assignButton, 195, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, removeButton, 515, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, removeButton, 245, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, scrollPaneAssignedStudentTable, 575, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, scrollPaneAssignedStudentTable, 120, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Phân lớp cho sinh viên");
        this.setSize(1100, 620);
        
        assignButton.setEnabled(false);
        removeButton.setEnabled(false);
        
        studentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                assignButton.setEnabled(studentsTable.getSelectedRowCount() > 0);
            }
        });

        assignedStudentsTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                removeButton.setEnabled(assignedStudentsTable.getSelectedRowCount() > 0);
            }
        });
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    private void showDataTable(List<Student> list, JTable table) {
        int size = list.size();
        Object [][] students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getAge();
            students[i][3] = list.get(i).getAddress();
            students[i][4] = list.get(i).getGpa();
        }
        table.setModel(new DefaultTableModel(students, columnNames));
    }
    
    public void showListStudent(List<Student> list) {
        showDataTable(list, studentsTable);
    }
    
    public void showListAssignStudent(List<Student> list) {
        showDataTable(list, assignedStudentsTable);
    }
    
    public void setInfoSectionClass(String info) {
        infoClassLabel.setText(info);
    }
    
    public Student getStudentFromTable(JTable table, int row) {
        Student student = new Student();
        String id = table.getModel().getValueAt(row, 0).toString();
        student.setId(Integer.parseInt(id));
        student.setName(table.getModel().getValueAt(row, 1).toString());
        String age = table.getModel().getValueAt(row, 2).toString();
        student.setAge(Byte.parseByte(age));
        student.setAddress(table.getModel().getValueAt(row, 3).toString());
        String gpa = table.getModel().getValueAt(row, 4).toString();
        student.setGpa(Float.parseFloat(gpa));
        return student;
    }
    
    public JTable getStudentsTable() {
        return studentsTable;
    }

    public JTable getAssignedStudentsTable() {
        return assignedStudentsTable;
    }
    
    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void assignStudentListener(ActionListener listener) {
        assignButton.addActionListener(listener);
    }
    
    public void removeStudentListener(ActionListener listener) {
        removeButton.addActionListener(listener);
    }
    
    public void saveAssignListener(ActionListener listener) {
        saveBtn.addActionListener(listener);
    }

    public void closeForm() {
        dispose();
    }
}
