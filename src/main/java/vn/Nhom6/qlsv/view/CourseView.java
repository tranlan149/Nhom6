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

import vn.Nhom6.qlsv.entity.Course;

public class CourseView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addCourseBtn;
    private JButton editCourseBtn;
    private JButton deleteCourseBtn;
    private JButton clearBtn;
    private JScrollPane jScrollPaneCourseTable;
    private JTable courseTable;
    private JButton searchBtn;

    private JLabel idLabel;
    private JLabel courseNameLabel;
    private JLabel courseTypeLabel;
    private JLabel courseSearchLabel;

    private JTextField idField;
    private JTextField courseNameField;
    private JComboBox courseTypeCombobox;
    private JTextField courseSearchField;

    private String[] courseTypeData = new String[] {"Lý thuyết", "Thực hành", "Lý thuyết và thực hành"};
    private String[] columnNames = new String[]{"ID", "Tên môn học", "Loại môn học"};
    // định nghĩa dữ liệu mặc định của bảng course là rỗng
    private Object data = new Object[][]{};

    public CourseView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // khởi tạo các phím chức năng
        addCourseBtn = new JButton("Thêm");
        editCourseBtn = new JButton("Sửa");
        deleteCourseBtn = new JButton("Xóa");
        clearBtn = new JButton("Làm mới");
        searchBtn = new JButton("Tìm");
        // khởi tạo bảng course
        jScrollPaneCourseTable = new JScrollPane();
        courseTable = new JTable();

        // khởi tạo các label
        idLabel = new JLabel("Id");
        courseNameLabel = new JLabel("Tên môn học");
        courseTypeLabel = new JLabel("Loại môn học");
        courseSearchLabel = new JLabel("Tìm kiếm");

        // khởi tạo các trường nhập dữ liệu cho course
        idField = new JTextField(6);
        idField.setEditable(false);
        courseNameField = new JTextField(15);
        courseTypeCombobox = new JComboBox<>(courseTypeData);
        courseSearchField = new JTextField(15);

        // cài đặt các cột và data cho bảng course
        courseTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneCourseTable.setViewportView(courseTable);
        jScrollPaneCourseTable.setPreferredSize(new Dimension(480, 300));

        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Course
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneCourseTable);

        panel.add(addCourseBtn);
        panel.add(editCourseBtn);
        panel.add(deleteCourseBtn);
        panel.add(clearBtn);
        panel.add(searchBtn);

        panel.add(idLabel);
        panel.add(courseNameLabel);
        panel.add(courseTypeLabel);
        panel.add(courseSearchLabel);

        panel.add(idField);
        panel.add(courseNameField);
        panel.add(courseTypeCombobox);
        panel.add(courseSearchField);

        // cài đặt vị trí các thành phần trên màn hình quản lý Course
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseNameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseNameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseTypeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseTypeLabel, 70, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseNameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseNameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseTypeCombobox, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseTypeCombobox, 70, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, courseSearchLabel, 325, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseSearchLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, courseSearchField, 390, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, courseSearchField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchBtn, 570, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchBtn, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneCourseTable, 315, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneCourseTable, 40, SpringLayout.NORTH, panel);

        layout.putConstraint(SpringLayout.WEST, addCourseBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addCourseBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editCourseBtn, 60, SpringLayout.WEST, addCourseBtn);
        layout.putConstraint(SpringLayout.NORTH, editCourseBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteCourseBtn, 60, SpringLayout.WEST, editCourseBtn);
        

        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 75, SpringLayout.WEST, deleteCourseBtn);

        layout.putConstraint(SpringLayout.NORTH, deleteCourseBtn, 240, SpringLayout.NORTH, panel);

        this.add(panel);
        this.pack();
        this.setTitle("Thông tin môn học");
        this.setSize(850, 420);
        // disable Edit and Delete buttons
        editCourseBtn.setEnabled(false);
        deleteCourseBtn.setEnabled(false);
        // enable Add button
        addCourseBtn.setEnabled(true);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * hiển thị list course vào bảng courseTable
     *
     * @param list
     */
    public void showListCourses(List<Course> list) {
        int size = list.size();
        // với bảng courseTable có 3 cột,
        // khởi tạo mảng 2 chiều courses, trong đó:
        // số hàng: là kích thước của list course
        // số cột: là 3
        Object[][] courses = new Object[size][3];
        for (int i = 0; i < size; i++) {
            courses[i][0] = list.get(i).getId();
            courses[i][1] = list.get(i).getCourseName();
            courses[i][2] = list.get(i).getCourseType();
        }
        courseTable.setModel(new DefaultTableModel(courses, columnNames));
    }

    /**
     * điền thông tin của hàng được chọn từ bảng course
     * vào các trường tương ứng của course.
     */
    public void fillCourseFromSelectedRow() {
        // lấy chỉ số của hàng được chọn
        int row = courseTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(courseTable.getModel().getValueAt(row, 0).toString());
            courseNameField.setText(courseTable.getModel().getValueAt(row, 1).toString());
            courseTypeCombobox.setSelectedItem(courseTable.getModel().getValueAt(row, 2).toString());
            // enable Edit and Delete buttons
            editCourseBtn.setEnabled(true);
            deleteCourseBtn.setEnabled(true);
            // disable Add button
            addCourseBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin course
     */
    public void clearCourseInfo() {
        idField.setText("");
        courseNameField.setText("");
        // disable Edit and Delete buttons
        editCourseBtn.setEnabled(false);
        deleteCourseBtn.setEnabled(false);
        // enable Add button
        addCourseBtn.setEnabled(true);
    }

    /**
     * hiện thị thông tin course
     *
     * @param course
     */
    public void showCourse(Course course) {
        idField.setText("" + course.getId());
        courseNameField.setText(course.getCourseName());
        courseTypeCombobox.setSelectedItem(course.getCourseType());
        // enable Edit and Delete buttons
        editCourseBtn.setEnabled(true);
        deleteCourseBtn.setEnabled(true);
        // disable Add button
        addCourseBtn.setEnabled(false);
    }

    /**
     * lấy thông tin course
     *
     * @return
     */
    public Course getCourseInfo() {
        // validate course
        if (!validateCourseName()) {
            return null;
        }
        try {
            Course course = new Course();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                course.setId(Integer.parseInt(idField.getText()));
            }
            course.setCourseName(courseNameField.getText().trim());
            course.setCourseType(courseTypeCombobox.getSelectedItem().toString());
            return course;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    private boolean validateCourseName() {
        String name = courseNameField.getText();
        if (name == null || "".equals(name.trim())) {
            courseNameField.requestFocus();
            showMessage("Tên khóa học không được trống.");
            return false;
        }
        return true;
    }
    
    public void filterCourse(List<Course> list) {
        String keySearch = courseSearchField.getText();
        List<Course> filter = new ArrayList<>();
        for(Course c : list) {
            if(c.getCourseName().toLowerCase().contains(keySearch.toLowerCase())) {
                filter.add(c);
            }
        }
        showListCourses(filter);
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void valueChanged(ListSelectionEvent e) {
    }

    public void addAddCourseListener(ActionListener listener) {
        addCourseBtn.addActionListener(listener);
    }

    public void addEditCourseListener(ActionListener listener) {
        editCourseBtn.addActionListener(listener);
    }

    public void addDeleteCourseListener(ActionListener listener) {
        deleteCourseBtn.addActionListener(listener);
    }

    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }

    public void addListCourseSelectionListener(ListSelectionListener listener) {
        courseTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchCourseListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }
}
