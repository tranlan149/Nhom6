package vn.Nhom6.qlsv.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import vn.Nhom6.qlsv.entity.SectionClass;


public class ClassStatsView extends JFrame implements ActionListener {
    private JTextField minStudentsField;
    private JButton filterButton;
    private JTable resultTable;
    private JScrollPane scrollPane;

    private List<SectionClass> sectionClasses;
    
    String[] columnNames = {"ID", "Loại lớp", "Số sinh viên tối đa", "Môn học", "Số sinh viên"};
    
    public ClassStatsView() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Thống kê danh sách lớp");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Số sinh viên ít nhất:"));
        minStudentsField = new JTextField(10);
        inputPanel.add(minStudentsField);
        filterButton = new JButton("Lọc");
        filterButton.addActionListener(this);
        inputPanel.add(filterButton);

        panel.add(inputPanel, BorderLayout.NORTH);

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        resultTable = new JTable(tableModel);
        scrollPane = new JScrollPane(resultTable);

        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
    }

    public void filterClassStats(List<SectionClass> list) {
        int size = list.size();
        String minStudentsText = minStudentsField.getText();
        Object[][] sectionClasses = new Object[size][5];
        try {
            int minStudents = (minStudentsText == "" || minStudentsText.isEmpty()) ? 0 : Integer.parseInt(minStudentsText);
            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            for (SectionClass s : list) {
                if (s.getStudents().size() >= minStudents) {
                    tableModel.addRow(new Object[]{
                            s.getId(),
                            s.getClassType(),
                            s.getMaxStudents(),
                            s.getCourse().getCourseName(),
                            s.getStudents().size()
                    });
                }
            }
            resultTable.setModel(tableModel);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập một số nguyên hợp lệ", "Lỗi đầu vào", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent e) {
    }
    
    public void addFilterSectionClassListener(ActionListener listener) {
        filterButton.addActionListener(listener);
    }
}