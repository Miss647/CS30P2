package packagE;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.io.*;
//
public class StudentGradeApp extends JFrame {
	//

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtGradeLevel;
    private JTextField txtSemester;
    private JTextField txtGrade1;
    private JTextField txtGrade2;
    private JTextField txtGrade3;
    private JTextField txtGrade4;
    private JTextField txtAverage;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                StudentGradeApp frame = new StudentGradeApp();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public StudentGradeApp() {
        setTitle("Student Grade Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 480, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // ===== LABELS =====
        JLabel lblName = new JLabel("Student Name:");
        lblName.setForeground(new Color(128, 0, 0));
        lblName.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblName.setBounds(10, 15, 150, 25);
        contentPane.add(lblName);

        JLabel lblGradeLevel = new JLabel("Grade Level:");
        lblGradeLevel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblGradeLevel.setBounds(10, 50, 105, 25);
        contentPane.add(lblGradeLevel);

        JLabel lblSemester = new JLabel("Semester Number:");
        lblSemester.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblSemester.setBounds(10, 85, 140, 25);
        contentPane.add(lblSemester);

        JLabel lblGrade1 = new JLabel("Grade 1:");
        lblGrade1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblGrade1.setBounds(10, 120, 100, 25);
        contentPane.add(lblGrade1);

        JLabel lblGrade2 = new JLabel("Grade 2:");
        lblGrade2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblGrade2.setBounds(10, 150, 100, 25);
        contentPane.add(lblGrade2);

        JLabel lblGrade3 = new JLabel("Grade 3:");
        lblGrade3.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblGrade3.setBounds(10, 180, 100, 25);
        contentPane.add(lblGrade3);

        JLabel lblGrade4 = new JLabel("Grade 4:");
        lblGrade4.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
        lblGrade4.setBounds(10, 210, 100, 25);
        contentPane.add(lblGrade4);

        JLabel lblAverage = new JLabel("Average:");
        lblAverage.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
        lblAverage.setForeground(new Color(0, 0, 128));
        lblAverage.setBounds(10, 245, 100, 25);
        contentPane.add(lblAverage);

        // ===== TEXT FIELDS =====
        txtName = new JTextField();
        txtName.setBounds(155, 20, 150, 25);
        contentPane.add(txtName);

        txtGradeLevel = new JTextField();
        txtGradeLevel.setBounds(112, 51, 150, 25);
        contentPane.add(txtGradeLevel);

        txtSemester = new JTextField();
        txtSemester.setBounds(145, 88, 150, 25);
        contentPane.add(txtSemester);

        txtGrade1 = new JTextField();
        txtGrade1.setBounds(77, 121, 80, 25);
        contentPane.add(txtGrade1);

        txtGrade2 = new JTextField();
        txtGrade2.setBounds(77, 153, 80, 25);
        contentPane.add(txtGrade2);

        txtGrade3 = new JTextField();
        txtGrade3.setBounds(77, 183, 80, 25);
        contentPane.add(txtGrade3);

        txtGrade4 = new JTextField();
        txtGrade4.setBounds(77, 213, 80, 25);
        contentPane.add(txtGrade4);

        txtAverage = new JTextField();
        txtAverage.setBounds(106, 250, 80, 25);
        txtAverage.setEditable(false);
        contentPane.add(txtAverage);

        // ===== BUTTONS =====
        JButton btnSave = new JButton("Save to File");
        btnSave.setBounds(340, 210, 120, 25);
        contentPane.add(btnSave);

        JButton btnView = new JButton("View File");
        btnView.setBounds(340, 245, 120, 25);
        contentPane.add(btnView);

        // ===== LOGIC =====

        // Calculate and save data
        btnSave.addActionListener(e -> {
            try {
                // Parse grades
                double g1 = Double.parseDouble(txtGrade1.getText());
                double g2 = Double.parseDouble(txtGrade2.getText());
                double g3 = Double.parseDouble(txtGrade3.getText());
                double g4 = Double.parseDouble(txtGrade4.getText());

                double average = (g1 + g2 + g3 + g4) / 4.0;
                txtAverage.setText(String.format("%.2f", average));

                // Save to file
                FileWriter writer = new FileWriter("grades.txt", true);
                writer.write("Name: " + txtName.getText() + ", ");
                writer.write("Grade Level: " + txtGradeLevel.getText() + ", ");
                writer.write("Semester: " + txtSemester.getText() + ", ");
                writer.write(String.format("Grades: %.2f, %.2f, %.2f, %.2f, ", g1, g2, g3, g4));
                writer.write(String.format("Average: %.2f%n", average));
                writer.close();

                JOptionPane.showMessageDialog(this, "Saved successfully!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for grades.", "Input Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // View file contents
        btnView.addActionListener(e -> {
            try (BufferedReader reader = new BufferedReader(new FileReader("grades.txt"))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                JTextArea textArea = new JTextArea(content.toString());
                textArea.setEditable(false);
                JScrollPane scroll = new JScrollPane(textArea);
                scroll.setPreferredSize(new Dimension(400, 200));
                JOptionPane.showMessageDialog(this, scroll, "File Contents", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "No file found yet. Save data first.", "File Not Found", JOptionPane.WARNING_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error reading file.", "File Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
