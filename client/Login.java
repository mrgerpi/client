package client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login {

	private JFrame frmBmslogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private ActionListener actionListener = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmBmslogin.setVisible(true);
					window.frmBmslogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						DataProcessing.disconnectFromDB();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	public Login() throws SQLException {
		frmBmslogin = new JFrame();
		frmBmslogin.setSize(500, 300);
		frmBmslogin.setEnabled(true);
		frmBmslogin.setTitle("\u7CFB\u7EDF\u767B\u5F55");
		
		JPanel panel = new JPanel();
		frmBmslogin.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel 用户名 = new JLabel("\u7528\u6237\u540D");
		用户名.setFont(new Font("仿宋_GB2312", Font.PLAIN, 20));
		用户名.setBounds(93, 69, 62, 39);
		panel.add(用户名);
		
		textField = new JTextField();
		textField.setBounds(172, 69, 171, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel(" \u5BC6\u7801");
		lblPassword.setFont(new Font("仿宋_GB2312", Font.PLAIN, 18));
		lblPassword.setBounds(93, 130, 62, 39);
		panel.add(lblPassword);
		
		JButton button = new JButton("\u786E\u5B9A");
		button.addActionListener(getActionListener());
		button.setBounds(93, 206, 93, 23);
		panel.add(button);
		
		JButton button_1 = new JButton("\u53D6\u6D88");
		button_1.addActionListener(getActionListener());
		button_1.setBounds(230, 206, 93, 23); 
		panel.add(button_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(172, 130, 171, 32);
		panel.add(passwordField);
	}
	
	ActionListener getActionListener()throws SQLException
	{
		if(actionListener == null){
			actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					if(cmd == "取消")		System.exit(0);
					else{
						try {
							User user = DataProcessing.searchUser(textField.getText(), String.valueOf(passwordField.getPassword()));
							if(user == null){
								JOptionPane.showMessageDialog(frmBmslogin, "搞错了你！没这个人！！！");
							}
							else{
								MainFrame frame = new MainFrame(user);
								frame.setVisible(true);
								frmBmslogin.dispose();	
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
