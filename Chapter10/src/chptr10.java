import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class chptr10 {

	private JFrame frame;
	private JTextField ff;
	private JTextField nn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					chptr10 window = new chptr10();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public chptr10() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 250);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		ff = new JTextField();
		ff.setForeground(new Color(128, 128, 192));
		ff.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		ff.setText("firstname");
		ff.setBounds(128, 5, 86, 20);
		panel.add(ff);
		ff.setColumns(10);
		
		nn = new JTextField();
		nn.setForeground(new Color(0, 128, 192));
		nn.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
		nn.setText("lastname");
		nn.setBounds(219, 5, 86, 20);
		panel.add(nn);
		nn.setColumns(10);
		
		JComboBox<Object> comboBox = new JComboBox<Object>();
		comboBox.setForeground(new Color(0, 0, 128));
		comboBox.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] {"Select grade", "12", "11"}));
		comboBox.setBounds(22, 44, 112, 72);
		panel.add(comboBox);
		
		JComboBox<Object> comboBox_1 = new JComboBox<Object>();
		comboBox_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			
		});
		comboBox_1.setModel(new DefaultComboBoxModel<Object>(new String[] {"Select school", "CHHS", "Western"}));
		comboBox_1.setForeground(new Color(0, 64, 128));
		comboBox_1.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 25));
		comboBox_1.setBounds(161, 44, 132, 93);
		panel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(128, 0, 64));
		btnNewButton.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 20));
		btnNewButton.setBounds(22, 209, 96, 30);
		panel.add(btnNewButton);
		
		JTextArea txtrDisk = new JTextArea();
		txtrDisk.setFont(new Font("Old English Text MT", Font.BOLD, 21));
		txtrDisk.setText("Disk");
		txtrDisk.setBounds(145, 209, 107, 30);
		panel.add(txtrDisk);
		
		JLabel lblNewLabel = new JLabel("label");
		lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 24));
		lblNewLabel.setBounds(304, 142, 95, 55);
		panel.add(lblNewLabel);
	}
}
