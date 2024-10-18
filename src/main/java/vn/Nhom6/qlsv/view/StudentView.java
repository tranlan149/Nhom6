package vn.Nhom6.qlsv.view;

import java.awt.Dimension;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.Nhom6.qlsv.entity.Course;
import vn.Nhom6.qlsv.entity.Student;

public class StudentView extends JFrame implements ActionListener, ListSelectionListener {
    private static final long serialVersionUID = 1L;
    private JButton addStudentBtn;
    private JButton editStudentBtn;
    private JButton deleteStudentBtn;
    private JButton clearBtn;
    private JButton sortStudentGPABtn;
    private JButton sortStudentNameBtn;
    private JScrollPane jScrollPaneStudentTable;
    private JScrollPane jScrollPaneAddress;
    private JTable studentTable;
    private JButton searchBtn;
    
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel addressLabel;
    private JLabel gpaLabel;
    private JLabel studentSearchLabel;
    
    private JTextField idField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea addressTA;
    private JTextField gpaField;
    private JTextField studentSearchField;
    
    // định nghĩa các cột của bảng student
    private String [] columnNames = new String [] {
            "ID", "Họ tên", "Tuổi", "Địa chỉ", "GPA"};
    // định nghĩa dữ liệu mặc định của bẳng student là rỗng
    private Object data = new Object [][] {};
    
    public StudentView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // khởi tạo các phím chức năng
        addStudentBtn = new JButton("Thêm");
        editStudentBtn = new JButton("Sửa");
        deleteStudentBtn = new JButton("Xóa");
        clearBtn = new JButton("Làm mới");
        sortStudentGPABtn = new JButton("Sort By GPA");
        sortStudentNameBtn = new JButton("Sort By Name");
        // khởi tạo bảng student
        jScrollPaneStudentTable = new JScrollPane();
        studentTable = new JTable();
        searchBtn = new JButton("Tìm");
        
        // khởi tạo các label
        idLabel = new JLabel("Id");
        nameLabel = new JLabel("Họ tên");
        ageLabel = new JLabel("Tuổi");
        addressLabel = new JLabel("Địa chỉ");
        gpaLabel = new JLabel("GPA");
        studentSearchLabel = new JLabel("Tìm kiếm");
        
        // khởi tạo các trường nhập dữ liệu cho student
        idField = new JTextField(6);
        idField.setEditable(false);
        nameField = new JTextField(15);
        ageField = new JTextField(6);
        addressTA = new JTextArea();
        addressTA.setColumns(15);
        addressTA.setRows(5);
        jScrollPaneAddress = new JScrollPane();
        jScrollPaneAddress.setViewportView(addressTA);
        gpaField = new JTextField(6);
        studentSearchField = new JTextField(15);
        
        // cài đặt các cột và data cho bảng student
        studentTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollPaneStudentTable.setViewportView(studentTable);
        jScrollPaneStudentTable.setPreferredSize(new Dimension (480, 300));
        
         // tạo spring layout
        SpringLayout layout = new SpringLayout();
        // tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
        JPanel panel = new JPanel();
        panel.setSize(800, 420);
        panel.setLayout(layout);
        panel.add(jScrollPaneStudentTable);
        
        panel.add(addStudentBtn);
        panel.add(editStudentBtn);
        panel.add(deleteStudentBtn);
        panel.add(clearBtn);
        panel.add(sortStudentGPABtn);
        panel.add(sortStudentNameBtn);
        panel.add(searchBtn);
        
        panel.add(idLabel);
        panel.add(nameLabel);
        panel.add(ageLabel);
        panel.add(addressLabel);
        panel.add(gpaLabel);
        panel.add(studentSearchLabel);
        
        panel.add(idField);
        panel.add(nameField);
        panel.add(ageField);
        panel.add(jScrollPaneAddress);
        panel.add(gpaField);
        panel.add(studentSearchField);
        
        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addressLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addressLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gpaLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gpaLabel, 200, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, idField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, ageField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, ageField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, jScrollPaneAddress, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneAddress, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, gpaField, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, gpaField, 200, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, studentSearchLabel, 325, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentSearchLabel, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, studentSearchField, 390, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, studentSearchField, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchBtn, 570, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchBtn, 10, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, jScrollPaneStudentTable, 320, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollPaneStudentTable, 40, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addStudentBtn, 20, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editStudentBtn, 60, SpringLayout.WEST, addStudentBtn);
        layout.putConstraint(SpringLayout.NORTH, editStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteStudentBtn, 60, SpringLayout.WEST, editStudentBtn);
        
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 80, SpringLayout.WEST, deleteStudentBtn);
        
        layout.putConstraint(SpringLayout.NORTH, deleteStudentBtn, 240, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortStudentGPABtn, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortStudentGPABtn, 360, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortStudentNameBtn, 115, SpringLayout.WEST, sortStudentGPABtn);
        layout.putConstraint(SpringLayout.NORTH, sortStudentNameBtn, 360, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Student Information");
        this.setSize(850, 440);
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    /**
     * hiển thị list student vào bảng studentTable
     * 
     * @param list
     */
    public void showListStudents(List<Student> list) {
        int size = list.size();
        // với bảng studentTable có 5 cột, 
        // khởi tạo mảng 2 chiều students, trong đó:
        // số hàng: là kích thước của list student 
        // số cột: là 5
        Object [][] students = new Object[size][5];
        for (int i = 0; i < size; i++) {
            students[i][0] = list.get(i).getId();
            students[i][1] = list.get(i).getName();
            students[i][2] = list.get(i).getAge();
            students[i][3] = list.get(i).getAddress();
            students[i][4] = list.get(i).getGpa();
        }
        studentTable.setModel(new DefaultTableModel(students, columnNames));
    }
    
    /**
     * điền thông tin của hàng được chọn từ bảng student 
     * vào các trường tương ứng của student.
     */
    public void fillStudentFromSelectedRow() {
        // lấy chỉ số của hàng được chọn 
        int row = studentTable.getSelectedRow();
        if (row >= 0) {
            idField.setText(studentTable.getModel().getValueAt(row, 0).toString());
            nameField.setText(studentTable.getModel().getValueAt(row, 1).toString());
            ageField.setText(studentTable.getModel().getValueAt(row, 2).toString());
            addressTA.setText(studentTable.getModel().getValueAt(row, 3).toString());
            gpaField.setText(studentTable.getModel().getValueAt(row, 4).toString());
            // enable Edit and Delete buttons
            editStudentBtn.setEnabled(true);
            deleteStudentBtn.setEnabled(true);
            // disable Add button
            addStudentBtn.setEnabled(false);
        }
    }

    /**
     * xóa thông tin student
     */
    public void clearStudentInfo() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        addressTA.setText("");
        gpaField.setText("");
        // disable Edit and Delete buttons
        editStudentBtn.setEnabled(false);
        deleteStudentBtn.setEnabled(false);
        // enable Add button
        addStudentBtn.setEnabled(true);
    }
    
    /**
     * hiện thị thông tin student
     * 
     * @param student
     */
    public void showStudent(Student student) {
        idField.setText("" + student.getId());
        nameField.setText(student.getName());
        ageField.setText("" + student.getAge());
        addressTA.setText(student.getAddress());
        gpaField.setText("" + student.getGpa());
        // enable Edit and Delete buttons
        editStudentBtn.setEnabled(true);
        deleteStudentBtn.setEnabled(true);
        // disable Add button
        addStudentBtn.setEnabled(false);
    }
    
    /**
     * lấy thông tin student
     * 
     * @return
     */
    public Student getStudentInfo() {
        // validate student
        if (!validateName() || !validateAge() || !validateAddress() || !validateGPA()) {
            return null;
        }
        try {
            Student student = new Student();
            if (idField.getText() != null && !"".equals(idField.getText())) {
                student.setId(Integer.parseInt(idField.getText()));
            }
            student.setName(nameField.getText().trim());
            student.setAge(Byte.parseByte(ageField.getText().trim()));
            student.setAddress(addressTA.getText().trim());
            student.setGpa(Float.parseFloat(gpaField.getText().trim()));
            return student;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateName() {
        String name = nameField.getText();
        if (name == null || "".equals(name.trim())) {
            nameField.requestFocus();
            showMessage("Họ tên không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = addressTA.getText();
        if (address == null || "".equals(address.trim())) {
            addressTA.requestFocus();
            showMessage("Địa chỉ không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateAge() {
        try {
            Byte age = Byte.parseByte(ageField.getText().trim());
            if (age < 0 || age > 100) {
                ageField.requestFocus();
                showMessage("Tuổi không hợp lệ, tuổi nên trong khoảng 0 đến 100.");
                return false;
            }
        } catch (Exception e) {
            ageField.requestFocus();
            showMessage("Tuổi không hợp lệ!");
            return false;
        }
        return true;
    }
    
    private boolean validateGPA() {
        try {
            Float gpa = Float.parseFloat(gpaField.getText().trim());
            if (gpa < 0 || gpa > 10) {
                gpaField.requestFocus();
                showMessage("GPA không hợp lệ, gpa nên trong khoảng 0 đến 10.");
                return false;
            }
        } catch (Exception e) {
            gpaField.requestFocus();
            showMessage("GPA không hợp lệ!");
            return false;
        }
        return true;
    }
    
    public void filterStudent(List<Student> list) {
        String keySearch = studentSearchField.getText();
        List<Student> filter = new ArrayList<>();
        for(Student s : list) {
            if(s.getName().toLowerCase().contains(keySearch.toLowerCase())) {
                filter.add(s);
            }
        }
        showListStudents(filter);
    }
    
    public void actionPerformed(ActionEvent e) {
    }
    
    public void valueChanged(ListSelectionEvent e) {
    }
    
    public void addAddStudentListener(ActionListener listener) {
        addStudentBtn.addActionListener(listener);
    }
    
    public void addEdiStudentListener(ActionListener listener) {
        editStudentBtn.addActionListener(listener);
    }
    
    public void addDeleteStudentListener(ActionListener listener) {
        deleteStudentBtn.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        clearBtn.addActionListener(listener);
    }
    
    public void addSortStudentGPAListener(ActionListener listener) {
        sortStudentGPABtn.addActionListener(listener);
    }
    
    public void addSortStudentNameListener(ActionListener listener) {
        sortStudentNameBtn.addActionListener(listener);
    }
    
    public void addListStudentSelectionListener(ListSelectionListener listener) {
        studentTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchStudentListener(ActionListener listener) {
        searchBtn.addActionListener(listener);
    }
}
