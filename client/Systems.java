package client;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Systems {

	public void welcome(){
		System.out.println("********��ӭ�������ϵͳ********");
		System.out.println("\t1.��¼");
		System.out.println("\t2.�˳�");
		System.out.println("***************************");
	}

	public static void main(String[] args) {
		Systems system = new Systems();
		
		Scanner in = new Scanner(System.in);
		
		while(true){
			system.welcome();
			System.out.print("�˵��ţ�");
			int n = in.nextInt();
			if(n != 1)
				break;
			else{
				DataProcessing.Init();
				System.out.print("�û���:");
				String name = in.next();
				System.out.print("���룺");
				String password = in.next();
				User user = null;
				try {
					user = DataProcessing.searchUser(name, password);
				} catch (SQLException e) {
					System.out.println("Not Fount! SQLException!");
					e.printStackTrace();
					e.getMessage();
					e.getCause();
				}
				
				if(user == null){
					System.out.println("���޴��ˣ�");
					continue;
				}
				switch (user.getRole()){
				case "administrator":
					try {
						system.administrator(user);
						
					} catch (SQLException e) {
						System.out.println("Administrator! SQLException!");
						e.getMessage();
						e.printStackTrace();
					} catch (IOException e) {
						System.out.println("Administrator! IOException!");
						e.getMessage();
						e.printStackTrace();
					}
					break;
				case "browser":
					try {
						system.browser(user);
						
					} catch (IOException e) {
						System.out.println("Browser! IOException!");
						e.getMessage();
						e.printStackTrace();
					} catch (SQLException e) {
						System.out.println("Browser! SQLException!");
						e.getMessage();
						e.printStackTrace();
					}
					break;
				case "operator":
					try {
						system.operator(user);
						
					} catch (IOException e) {
						System.out.println("Operator! IOException!");
						e.getMessage();
						e.printStackTrace();
					} catch (SQLException e) {
						System.out.println("Operator! SQLException!");
						e.getMessage();
						e.printStackTrace();
					}
					break;
				}
			}
		}
		in.close();
	}
	
	/*
	 *user = new operator();����
	 *��һ���������ø�ֵ��һ����������ǿ��Եģ���֮����
	 * ��ʱ��user(��������)����ʹ��operator(�������)���еķ���������
	 * �����Ҫʹ�ã������������ǿ��ת��
	 */
	
	public void administrator(User user) throws SQLException, IOException{
		Administrator administrator = (Administrator)user;
		administrator.showMenu();
		Scanner in = new Scanner(System.in);
		switch(in.nextInt()){
		case 1:
			System.out.print("���޸��û��û�����");
			String name;
			name = in.next();
			System.out.print("�޸ĺ���û����룺");
			String password;
			password = in.next();
			System.out.print("�޸ĺ���û���ɫ��");
			String role;
			role = in.next();
			if(DataProcessing.updateUser(name, password, role))
				System.out.println("�޸ĳɹ���");
			else
				System.out.println("�޸�ʧ�ܣ�");
			break;
		case 2:
			System.out.print("��ɾ���û�����");
			String name_;
			name_ = in.next();
			if(DataProcessing.deleteUser(name_))
				System.out.println("ɾ���ɹ���");
			else
				System.out.println("ɾ��ʧ�ܣ�");
			break;
		case 3:
			administrator.addUser();
			break;
		case 4:
			administrator.listUser();
			break;
		case 5:
			System.out.print("�ļ�����");
			String filename = in.next();
			administrator.downloadFile(filename);
			break;
		case 6:
			administrator.showFileList();
			break;
		case 7:
			System.out.print("�����룺");
			String newPassword_ = in.next();
			administrator.changeUserInfo(newPassword_);
			break;
		default:
			in.close();
			break;
		}
	}
	//���û�Ϊadministratorʱ�����øú���
	
	public void operator(User user) throws IOException, SQLException{
		Operator operator = (Operator)user;
		//����ǿ��ת��;����������ת��Ϊ��������
		operator.showMenu();
		Scanner in = new Scanner(System.in);
		switch(in.nextInt()){
		case 1:
			operator.uploadFile();
			break;
		case 2:
			System.out.print("�ļ�����");
			String filename = in.next();
			operator.downloadFile(filename);
			break;
		case 3:
			operator.showFileList();
			break;
		case 4:
			System.out.print("�����룺");
			String password = in.next();
			operator.changeUserInfo(password);
			break;
		default:
			in.close();
			break;
		}
	}
	//���û�Ϊoperatorʱ�����øú���
	
	public void browser(User user) throws IOException, SQLException{
		Browser browser = (Browser)user;
		browser.showMenu();
		Scanner in = new Scanner(System.in);
		switch(in.nextInt()){
		case 1:
			System.out.print("�ļ�����");
			String filename = in.next();
			browser.downloadFile(filename);
			break;
		case 2:
			browser.showFileList();
			break;
		case 3:
			System.out.print("�����룺");
			String password = in.next();
			browser.changeUserInfo(password);
			break;
		default:
			in.close();
			break;
		}

	}
	//���û�Ϊbrowserʱ�����øú���

}


