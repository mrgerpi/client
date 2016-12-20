package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DocFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DocFrame frame = new DocFrame(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DocFrame(String cmd, String username) throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("\u4E0A\u4F20", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u6863\u6848\u53F7");
		lblNewLabel.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		lblNewLabel.setBounds(61, 24, 50, 23);
		panel_1.add(lblNewLabel);
		
		JLabel label = new JLabel("\u6863\u6848\u63CF\u8FF0");
		label.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label.setBounds(46, 57, 63, 23);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("\u6863\u6848\u540D");
		label_1.setFont(new Font("仿宋_GB2312", Font.PLAIN, 14));
		label_1.setBounds(61, 137, 50, 23);
		panel_1.add(label_1);
		
		textField = new JTextField();
		textField.setBounds(135, 24, 182, 23);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(135, 57, 182, 64);
		panel_1.add(textArea);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 137, 182, 23);
		panel_1.add(textField_1);
		
		JButton btnNewButton = new JButton("\u4E0A\u4F20");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename = textField_1.getText();
				//文件名需要通过文件选择对话框获取
				
				String ID = textField.getText();
				String description = textArea.getText();
				try {
					Operator.uploadFile(username, ID, description, filename);
					JOptionPane.showMessageDialog(btnNewButton, "上传成功！");
					textArea.setText("");
					textField.setText("");
					textField_1.setText("");
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setToolTipText("");
		btnNewButton.setBounds(135, 189, 70, 23);
		panel_1.add(btnNewButton);
		
		JButton button = new JButton("\u53D6\u6D88");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.setText("");
				textField.setText("");
				textField_1.setText("");
			}
		});
		button.setToolTipText("");
		button.setBounds(247, 189, 70, 23);
		panel_1.add(button);
		
		JButton button_1 = new JButton("\u6253\u5F00");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc=new JFileChooser();  
		        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES );  
		        jfc.showDialog(new JLabel(), "选择");
		        textField_1.setText(jfc.getSelectedFile().getPath());
			}
		});
		button_1.setToolTipText("");
		button_1.setBounds(327, 137, 70, 23);
		panel_1.add(button_1);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("\u4E0B\u8F7D", null, panel, null);
		panel.setLayout(null);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			getObjects(),
			new String[] {
				"\u6863\u6848\u53F7", "\u521B\u5EFA\u8005", "\u65F6\u95F4", "\u6587\u4EF6\u540D", "\u63CF\u8FF0"
			}
		));
		table.setBounds(421, 162, -421, -158);
		panel.add(table);
				
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 419, 165);
		panel.add(scrollPane);
		JButton button_2 = new JButton("\u4E0B\u8F7D");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String fileName = (String) table.getModel().getValueAt(table.getSelectedRow(), 3);
				try {
					if(User.downloadFile(fileName)){
						JOptionPane.showMessageDialog(button_2, "下载成功！");
					}
					else{
						JOptionPane.showMessageDialog(button_2, "下载失败！");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		button_2.setBounds(169, 189, 93, 23);
		panel.add(button_2);
		
		if(cmd == "\u4E0A\u4F20")		 tabbedPane.setSelectedIndex(1);
		else 		tabbedPane.setSelectedIndex(0);
	}
	
	Object[][] getObjects() throws SQLException
	{
		ArrayList<Doc> arrayList = new ArrayList<>();
		Enumeration<Doc> docs = DataProcessing.getAllDocs();
		while(docs.hasMoreElements()){
			arrayList.add(docs.nextElement());
		}
		Object[][] result = new Object[arrayList.size() + 1][5];
		result[0][0] = "档案号";
		result[0][1] = "创建者";
		result[0][2] = "时间";
		result[0][3] = "文件名";
		result[0][4] = "描述";
		for(int i = 0;i < arrayList.size();i++){
			result[i + 1][0] = arrayList.get(i).getID();
			result[i + 1][1] = arrayList.get(i).getCreator();
			result[i + 1][2] = arrayList.get(i).getTimestamp().toString();
			result[i + 1][3] = arrayList.get(i).getFilename();
			result[i + 1][4] = arrayList.get(i).getDescription();
		}
		return result;
	}
}
