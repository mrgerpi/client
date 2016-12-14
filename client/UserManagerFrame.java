package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class UserManagerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	//底面板
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox<String> comboBox;
	private ActionListener actionListener = null;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserManagerFrame frame = new UserManagerFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public UserManagerFrame(String cmd) throws SQLException {
		setTitle("UserManager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 10, 414, 241);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u65B0\u589E\u7528\u6237", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label.setBounds(91, 32, 57, 22);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801");
		label_1.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_1.setBounds(101, 74, 57, 22);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u8EAB\u4EFD");
		label_2.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_2.setBounds(104, 113, 44, 22);
		panel.add(label_2);
		
		textField = new JTextField();
		textField.setBounds(145, 33, 145, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(145, 75, 145, 21);
		panel.add(textField_1);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(145, 114, 145, 21);
		panel.add(comboBox);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(getActionListener());
		button.setBounds(111, 163, 67, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.setBounds(223, 163, 67, 23);
		button_1.addActionListener(getActionListener());
		panel.add(button_1);
		comboBox.addItem("browser");
		comboBox.addItem("operator");
		comboBox.addItem("administrator");
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u4FEE\u6539\u7528\u6237", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label_3 = new JLabel("\u7528\u6237\u540D");
		label_3.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_3.setBounds(106, 27, 68, 26);
		panel_1.add(label_3);
		
		JLabel lblMima = new JLabel("\u5BC6\u7801");
		lblMima.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		lblMima.setBounds(106, 78, 68, 26);
		panel_1.add(lblMima);
		
		JLabel label_5 = new JLabel("\u8EAB\u4EFD");
		label_5.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_5.setBounds(106, 126, 68, 26);
		panel_1.add(label_5);
		
		textField_2 = new JTextField();
		textField_2.setBounds(162, 25, 157, 26);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(162, 78, 157, 26);
		panel_1.add(textField_3);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(162, 127, 157, 24);
		comboBox_1.addItem("browser");
		comboBox_1.addItem("operator");
		comboBox_1.addItem("administrator");
		panel_1.add(comboBox_1);
		
		JButton button_2 = new JButton("\u4FEE\u6539");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = textField_2.getText();
				String password = textField_3.getText();
				String role = comboBox_1.getItemAt(comboBox_1.getSelectedIndex());
				try {
					if(DataProcessing.updateUser(userName, password, role)){
						JOptionPane.showMessageDialog(button_2, "修改成功！");
						textField_2.setText("");
						textField_3.setText("");
					}
					else{
						JOptionPane.showMessageDialog(button_2, "修改失败，找不到该用户！");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		button_2.setBounds(107, 166, 67, 23);
		panel_1.add(button_2);
		
		JButton button_3 = new JButton("\u53D6\u6D88");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		button_3.setBounds(224, 166, 67, 23);
		panel_1.add(button_3);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("\u5220\u9664\u7528\u6237", null, panel_2, null);
		
//		//努力得到一个数组，用于构造JList
//		Enumeration<User> e = DataProcessing.getAllUser();
//		ArrayList<User> arrayList = null;
//		while(e.hasMoreElements()){
//			arrayList.add(e.nextElement());
//		}
//		
		JList list = new JList();
		panel_2.add(list);
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);
		
		if(cmd == "\u65B0\u589E\u7528\u6237")	tabbedPane.setSelectedIndex(0);
		else if(cmd == "\u4FEE\u6539\u7528\u6237") 	tabbedPane.setSelectedIndex(1);
		else if(cmd == "\u5220\u9664\u7528\u6237")	tabbedPane.setSelectedIndex(2);
	}

	ActionListener getActionListener(){
		if(actionListener == null){
			actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					if(cmd == "取消"){
						textField.setText("");
						textField_1.setText("");
					}
					else{
						String userName = textField.getText();
						String password = textField_1.getText();
						String role = comboBox.getItemAt(comboBox.getSelectedIndex());
						try {
							if(DataProcessing.insertUser(userName, password, role)){
								JOptionPane.showMessageDialog(comboBox, "添加成功！");
								textField.setText("");
								textField_1.setText("");
							}
							else{
								JOptionPane.showMessageDialog(comboBox, "添加失败！");
								textField.setText("");
								textField_1.setText("");
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			};
		}
		return actionListener;
	}
}
	