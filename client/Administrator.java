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
		System.out.print("新增用户用户名：");
		String newName = scanner.next();
		System.out.print("新增用户密码：");
		String newPassword = scanner.next();
		System.out.print("新增用户角色：");
		String newRole = scanner.next();
		if(DataProcessing.insertUser(newName, newPassword, newRole))
			System.out.println("新增成功！");
		else
			System.out.println("该用户名已被添加，新增失败！");
		
		scanner.close();
	}
	/*
	 * 此处也有子函数中scanner不得不close()造成主函数中标准流输入被关闭的问题
	 */
	
	public void showMenu(){
		System.out.println("~~~~~~~~管理员菜单~~~~~~~~");
		System.out.println("\t1.修改用户！");
		System.out.println("\t2.删除用户！");
		System.out.println("\t3.新增用户！");
		System.out.println("\t4.列出用户！");
		System.out.println("\t5.下载文件！");
		System.out.println("\t6.文件列表！");
		System.out.println("\t7.修改（本人）密码！");
		System.out.println("\t8.退出！");
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
	/*  Enumeration<ElementType>枚举类
	 *  boolean hasMoreElements()   测试此枚举是否包含更多的元素。 
 	 *	ElementType nextElement()  如果此枚举对象至少还有一个可提供的元素，则返回此枚举的下一个元素(pop)。 
	 */
	
	
	
//	public void  changUserInfo(){};
	
//	public void delUser(){};

}
