package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	ActionListener userActionListener = null;
	ActionListener docActionListener = null;
	ActionListener perActionListener = null;
	String username = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame(User user) {
		username = user.getName();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 441, 51);
		panel.add(menuBar);
		menuBar.setMargin(new Insets(0, 10, 0, 10));
		menuBar.setFont(new Font("仿宋", Font.PLAIN, 16));
		
		//用户管理菜单
		JMenu menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("\u65B0\u589E\u7528\u6237");
		menu.add(menuItem);
		menuItem.addActionListener(getUserActionListener());
		
		JMenuItem menuItem_1 = new JMenuItem("\u4FEE\u6539\u7528\u6237");
		menu.add(menuItem_1);
		menuItem_1.addActionListener(getUserActionListener());
		
		JMenuItem menuItem_2 = new JMenuItem("\u5220\u9664\u7528\u6237");
		menu.add(menuItem_2);
		menuItem_2.addActionListener(getUserActionListener());
		
		//档案管理菜单
		JMenu menu_1 = new JMenu("\u6863\u6848\u7BA1\u7406");
		menuBar.add(menu_1);
		
		JMenuItem menuItem_3 = new JMenuItem("\u4E0A\u4F20\u6863\u6848");
		menu_1.add(menuItem_3);
		menuItem_3.addActionListener(getDocActionListener());
		
		JMenuItem menuItem_4 = new JMenuItem("\u4E0B\u8F7D\u6863\u6848");
		menu_1.add(menuItem_4);
		menuItem_4.addActionListener(getDocActionListener());
		
		//个人信息管理菜单
		JMenu menu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		menuBar.add(menu_2);
		
		JMenuItem menuItem_5 = new JMenuItem("\u4FE1\u606F\u4FEE\u6539");
		menu_2.add(menuItem_5);
		menuItem_5.addActionListener(getPerActionListener());
		
		//根据身份对功能做限制
		if(user.getRole() == "operator"){
			menu.setEnabled(false);
			this.setTitle("Operator");
		}
		else if(user.getRole() == "browser"){
			menu.setEnabled(false);
			menuItem_3.setEnabled(false);
			this.setTitle("Browser");
		}
		else if(user.getRole() == "administrator"){
			this.setTitle("Administrator");
		}
	}
	
	ActionListener getUserActionListener()
	{
		if(userActionListener == null){
			userActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					UserManagerFrame frame;
					try {
						frame = new UserManagerFrame(cmd);
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
				}
			};
		}
		return userActionListener;
	}
	
	ActionListener getDocActionListener()
	{
		if(docActionListener == null){
			docActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String cmd = e.getActionCommand();
					DocFrame frame = null;
					try {
						frame = new DocFrame(cmd, username);
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					} catch (Exception e2) {
						e.getSource();
					}
				}
			};
		}
		return docActionListener;
	}

	ActionListener getPerActionListener()
	{
		if(perActionListener == null){
			perActionListener = new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						PerFrame frame = new PerFrame();
						frame.setVisible(true);
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			};
		}
		return perActionListener;
	}

}
