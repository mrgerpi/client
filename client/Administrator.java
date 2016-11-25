package client;

import java.util.Scanner;
import java.sql.SQLException;
import java.util.Enumeration;

public class Administrator extends User{
	public Administrator(String name, String password, String role){
		super(name, password, role);
	}
	
	public void addUser() throws SQLException{
		Scanner scanner = new Scanner(System.in);
		System.out.print("�����û��û�����");
		String newName = scanner.next();
		System.out.print("�����û����룺");
		String newPassword = scanner.next();
		System.out.print("�����û���ɫ��");
		String newRole = scanner.next();
		if(DataProcessing.insertUser(newName, newPassword, newRole))
			System.out.println("�����ɹ���");
		else
			System.out.println("���û����ѱ���ӣ�����ʧ�ܣ�");
		
		scanner.close();
	}
	/*
	 * �˴�Ҳ���Ӻ�����scanner���ò�close()����������б�׼�����뱻�رյ�����
	 */
	
	public void showMenu(){
		System.out.println("~~~~~~~~����Ա�˵�~~~~~~~~");
		System.out.println("\t1.�޸��û���");
		System.out.println("\t2.ɾ���û���");
		System.out.println("\t3.�����û���");
		System.out.println("\t4.�г��û���");
		System.out.println("\t5.�����ļ���");
		System.out.println("\t6.�ļ��б�");
		System.out.println("\t7.�޸ģ����ˣ����룡");
		System.out.println("\t8.�˳���");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public void listUser() throws SQLException{
		Enumeration<User> e = DataProcessing.getAllUser();
		System.out.println("username\tpassword\trole");
		while(e.hasMoreElements()){
			User user = e.nextElement();
			System.out.println(user.getName() + "\t\t" + user.getPassword() + "\t\t" + user.getRole());
		}
	}
	/*  Enumeration<ElementType>ö����
	 *  boolean hasMoreElements()   ���Դ�ö���Ƿ���������Ԫ�ء� 
 	 *	ElementType nextElement()  �����ö�ٶ������ٻ���һ�����ṩ��Ԫ�أ��򷵻ش�ö�ٵ���һ��Ԫ��(pop)�� 
	 */
	
	
	
//	public void  changUserInfo(){};
	
//	public void delUser(){};

}
