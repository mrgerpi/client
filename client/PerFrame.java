package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PerFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PerFrame frame = new PerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 226);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u7528\u6237\u540D");
		label.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label.setBounds(57, 10, 47, 20);
		panel.add(label);
		
		JLabel label_1 = new JLabel("\u65E7\u5BC6\u7801");
		label_1.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_1.setBounds(57, 38, 47, 20);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("\u65B0\u5BC6\u7801");
		label_2.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_2.setBounds(57, 68, 47, 20);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801");
		label_3.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_3.setBounds(43, 98, 61, 20);
		panel.add(label_3);
		
		textField = new JTextField();
		textField.setBounds(114, 10, 114, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(114, 38, 114, 21);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(114, 68, 114, 21);
		panel.add(passwordField_1);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(114, 98, 114, 21);
		panel.add(passwordField_2);
		
		JButton button = new JButton("\u786E\u8BA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = textField.getText();
				String password = String.valueOf(passwordField.getPassword());
				User user = null;
				try {
					if((user = DataProcessing.searchUser(userName, password)) == null){
						//弹出对话框,并数据清零
						JOptionPane.showMessageDialog(button, "用户名与密码不匹配！");
						empty();
					}
					else if(!String.valueOf(passwordField_1.getPassword()).equals(
							String.valueOf(passwordField_2.getPassword()))){
						JOptionPane.showMessageDialog(button, "两次输入新密码不同！");
						empty();
					}
					else{
						String newPassword = String.valueOf(passwordField_1.getPassword());
						user.changeUserInfo(newPassword);
						JOptionPane.showMessageDialog(button, "修改密码成功！");
						dispose();
					}
				} catch (HeadlessException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		button.setBounds(43, 144, 73, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				empty();
			}
		});
		button_1.setBounds(148, 144, 73, 23);
		panel.add(button_1);
	}
	
	private void empty(){
		textField.setText("");
		passwordField.setText("");
		passwordField_1.setText("");
		passwordField_2.setText("");
	}
}
