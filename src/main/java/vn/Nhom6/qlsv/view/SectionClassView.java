/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vn.Nhom6.qlsv.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.Nhom6.qlsv.dao.CourseDao;
import vn.Nhom6.qlsv.dao.SectionClassDao;
import vn.Nhom6.qlsv.entity.Course;
import vn.Nhom6.qlsv.entity.SectionClass;
import vn.Nhom6.qlsv.entity.Student;


public class SectionClassView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addSectionClassBtn;
    private JButton editSectionClassBtn;
    private JButton deleteSectionClassBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneSectionClassTable;
    private JTable sectionClassTable;
    private JButton searchBtn;
    private JButton assignmentBtn;

    private JLabel idLabel;
    private JLabel classTypeLabel;
    private JLabel maxStudentsLabel;
    private JLabel courseLabel;
    private JLabel sectionClassSearchLabel;

    private JTextField idField;
    private JComboBox<String> classTypeCombobox;
    private JTextField maxStudentsField;
    private JComboBox courseCbb;
    private JTextField sectionClassSearchField;

    private String[] classTypeData = new String[]{"Lý thuyết", "Thực hành"};
    private String[] columnNames = new String[]{"ID", "Loại lớp", "Số sinh viên tối đa", "Khóa học", "Số sinh viên hiện tại"};
    // định nghĩa dữ liệu mặc định của bảng sectionclass là rỗng
    private Object data = new Object[][]{};
    
    private List<Course> listCourse;
    private CourseDao courseDao;
    private SectionClassDao classDao;

    public SectionClassView() {
        initComponents();
        loadCourse();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // khởi tạo các phím chức năng
        addSectionClassBtn = new JButton("Thêm");
        editSectionClassBtn = new JButton("Sửa");
        deleteSectionClassBtn = new JButton("Xóa");
        clearBtn = new JButton("Làm mới");
        searchBtn = new JButton("Tìm");
        assignmentBtn = new JButton("Phân lớp");
        // khởi tạo bảng sectionclass
        jScrollPaneSectionClassTable = new JScrollPane();
        sectionClassTable = new JTable();
        
        listCourse = new ArrayList<>();
        courseDao = new CourseDao();
        classDao = new SectionClassDao();

        // khởi tạo các label
        idLabel = new JLabel("Id");
        classTypeLabel = new JLabel("Loại lớp");
        maxStudentsLabel = new JLabel("Số sinh viên tối đa");
        courseLabel = new JLabel("Khóa học");
        sectionClassSearchLabel = new JLabel("Tìm kiếm");

        // khởi tạo các trường nhập dữ liệu cho sectionclass
        idField = new JTextField(6);
        idField.setEditable(false);
        classTypeCombobox = new JComboBox<>(classTypeData);
        classTypeCombobox.setPreferredSize(new Dimension(190, 25));
        maxStudentsField = new JTextField(15);
        courseCbb = new JComboBox();
        courseCbb.setPreferredSize(new Dimension(190, 25));
        sectionClassSearchField = new JTextField(15);

        // cài đặt các cột và data cho bảng sectionclass
        sectionClassTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneSectionClassTable.setViewportView(sectionClassTable);
        jScrollPaneSectionClassTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý SectionClass
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneSectionClassTable);

        panel.add(addSectionClassBtn);
        panel.add(editSectionClassBtn);
        panel.add(deleteSectionClassBtn);
        panel.add(clearBtn);
        panel.add(searchBtn);
        panel.add(assignmentBtn);

        panel.add(idLabel);
        panel.add(classTypeLabel);
        panel.add(maxStudentsLabel);
        panel.add(courseLabel);
        panel.add(sectionClassSearchLabel);

        panel.add(idField);
        panel.add(classTypeCombobox);
        panel.add(maxStudentsField);
        panel.add(courseCbb);
        panel.add(sectionClassSearchField);

        // cài đặt vị trí các thành phần trên màn hình quản lý SectionClass
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, classTypeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classTypeLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maxStudentsLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maxStudentsLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseLabel, 100, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, classTypeCombobox, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, classTypeCombobox, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, maxStudentsField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, maxStudentsField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseCbb, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseCbb, 100, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, sectionClassSearchLabel, 355, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sectionClassSearchLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sectionClassSearchField, 420, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sectionClassSearchField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchBtn, 600, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchBtn, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneSectionClassTable, 350, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneSectionClassTable, 40, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addSectionClassBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addSectionClassBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editSectionClassBtn, 60, SpringLayout.WEST, addSectionClassBtn);
        layout.putConstraint(SpringLayout.NORTH, editSectionClassBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteSectionClassBtn, 60, SpringLayout.WEST, editSectionClassBtn);
        layout.putConstraint(SpringLayout.NORTH, deleteSectionClassBtn, 240, SpringLayout.NORTH, panel);


        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 75, SpringLayout.WEST, deleteSectionClassBtn);

        layout.putConstraint(SpringLayout.WEST, assignmentBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, assignmentBtn, 280, SpringLayout.NORTH, panel);
        this.add(panel);
        this.pack();
        this.setTitle("Thông tin lớp học");
        this.setSize(890, 420);
        // disable Edit and Delete buttons
        editSectionClassBtn.setEnabled(false);
        deleteSectionClassBtn.setEnabled(false);
        // enable Add button
        addSectionClassBtn.setEnabled(true);
        assignmentBtn.setEnabled(false);
    }
    
    private void loadCourse() {
        listCourse = courseDao.getListCourses();
        DefaultComboBoxModel model = (DefaultComboBoxModel) courseCbb.getModel();
        model.removeAllElements();

        for (Course c : listCourse) {
            model.addElement(c);
        }
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list sectionclass vào bảng sectionClassTable
     *
     * @param list
     */
    public void showListSectionClasses(List<SectionClass> list) {
        int size = list.size();
        Object[][] sectionClasses = new Object[size][5];
        for (int i = 0; i < size; i++) {
            sectionClasses[i][0] = list.get(i).getId();
            sectionClasses[i][1] = list.get(i).getClassType();
            sectionClasses[i][2] = list.get(i).getMaxStudents();
            sectionClasses[i][3] = list.get(i).getCourse().getCourseName();
            int countStudent = (list.get(i).getStudents() != null && list.get(i).getStudents().size() > 0) ? list.get(i).getStudents().size() : 0;
            sectionClasses[i][4] = countStudent;
        }
        sectionClassTable.setModel(new DefaultTableModel(sectionClasses, columnNames));
    }


    public void fillSectionClassFromSelectedRow() {
        int row = sectionClassTable.getSelectedRow();
        if (row >= 0) {
            String id = sectionClassTable.getModel().getValueAt(row, 0).toString();
            idField.setText(id);
            classTypeCombobox.setSelectedItem(sectionClassTable.getModel().getValueAt(row, 1).toString());
            maxStudentsField.setText(sectionClassTable.getModel().getValueAt(row, 2).toString());
            Course course = (classDao.getDataById(Integer.parseInt(id))).getCourse();
            courseCbb.setSelectedItem(course);
            // enable Edit and Delete buttons
            editSectionClassBtn.setEnabled(true);
            deleteSectionClassBtn.setEnabled(true);
            // disable Add button
            addSectionClassBtn.setEnabled(false);
            assignmentBtn.setEnabled(true);
        }
    }

    /**
     * xóa thông tin sectionclass
     */
    public void clearSectionClassInfo() {
        idField.setText("");
        maxStudentsField.setText("");
        // disable Edit and Delete buttons
        editSectionClassBtn.setEnabled(false);
        deleteSectionClassBtn.setEnabled(false);
        // enable Add button
        addSectionClassBtn.setEnabled(true);
        assignmentBtn.setEnabled(false);
    }

    /**
     * hiện thị thông tin sectionclass
     *
     * @param sectionclass
     */
    public void showSectionClass(SectionClass sectionClass) {
        idField.setText("" + sectionClass.getId());
        classTypeCombobox.setSelectedItem(sectionClass.getClassType());
        maxStudentsField.setText(String.valueOf(sectionClass.getMaxStudents()));
        courseCbb.setSelectedItem(sectionClass.getCourse());
        // enable Edit and Delete buttons
        editSectionClassBtn.setEnabled(true);
        deleteSectionClassBtn.setEnabled(true);
        // disable Add button
        addSectionClassBtn.setEnabled(false);
        assignmentBtn.setEnabled(true);
    }

    /**
     * lấy thông tin sectionclass
     *
     * @return
     */
    public SectionClass getSectionClassInfo() {
        if(!validateStudentMax()) {
            return null;
        }
        try {
            SectionClass sectionClass = new SectionClass();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                sectionClass.setId(Integer.parseInt(idField.getText()));
            }
            sectionClass.setClassType(classTypeCombobox.getSelectedItem().toString());
            sectionClass.setMaxStudents(Integer.parseInt(maxStudentsField.getText().trim()));
            Course course = (Course) courseCbb.getSelectedItem();
            sectionClass.setCourse(course);
            return sectionClass;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public void filterSectionClass(List<SectionClass> list) {
        String keySearch = sectionClassSearchField.getText();
        
        List<SectionClass> filter = new ArrayList<>();
        for (SectionClass sc : list) {
            if (String.valueOf(sc.getId()).contains(keySearch.toLowerCase())) {
                filter.add(sc);
            }
        }
        showListSectionClasses(filter);
    }
    
    private boolean validateStudentMax() {
        try {
            int count = Integer.parseInt(maxStudentsField.getText().trim());
            if (count < 0) {
                maxStudentsField.requestFocus();
                showMessage("Số sinh viên tối đa không hợp lệ, phải lớn hơn 0.");
                return false;
            }
        } catch (Exception e) {
            maxStudentsField.requestFocus();
            showMessage("Số sinh viên tối đa không hợp lệ!");
            return false;
        }
        return true;
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddSectionClassListener(ActionListener listener) {
        addSectionClassBtn.addActionListener(listener);
    }

    public void addEditSectionClassListener(ActionListener listener) {
        editSectionClassBtn.addActionListener(listener);
    }

    public void addDeleteSectionClassListener(ActionListener listener) {
        deleteSectionClassBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addListSectionClassSelectionListener(ListSelectionListener listener) {
        sectionClassTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchSectionClassListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }
    
    public void addAssignStudentListener(ActionListener listener) {
        assignmentBtn.addActionListener(listener);
    }
}
