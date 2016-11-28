package client;

import java.io.IOException;
import java.sql.SQLException;

public class Systems {

	public void welcome(){
		System.out.println("********欢迎进入管理系统********");
		System.out.println("\t1.登录");
		System.out.println("\t2.退出");
		System.out.println("***************************");
	}

	public static void main(String[] args) {
		Systems system = new Systems();
		
		while(true){
			system.welcome();
			System.out.print("菜单号：");
			int n = DataProcessing.scanner.nextInt();
			if(n != 1){
				DataProcessing.scanner.close();
				break;
			}
			else{
				DataProcessing.Init();
				System.out.print("用户名:");
				String name = DataProcessing.scanner.next();
				System.out.print("密码：");
				String password = DataProcessing.scanner.next();
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
					System.out.println("查无此人！");
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
	}
	
	/*
	 *user = new operator();允许；
	 *将一个子类引用赋值给一个父类变量是可以的，反之不行
	 * 此时，user(子类引用)不能使用operator(父类变量)特有的方法，属性
	 * 如果想要使用，必须进行类型强制转换
	 */
	
	public void administrator(User user) throws SQLException, IOException{
		Administrator administrator = (Administrator)user;
		administrator.showMenu();
		switch(DataProcessing.scanner.nextInt()){
		case 1:
			System.out.print("待修改用户用户名：");
			String name;
			name = DataProcessing.scanner.next();
			System.out.print("修改后的用户密码：");
			String password;
			password = DataProcessing.scanner.next();
			System.out.print("修改后的用户角色：");
			String role;
			role = DataProcessing.scanner.next();
			if(DataProcessing.updateUser(name, password, role))
				System.out.println("修改成功！");
			else
				System.out.println("修改失败！");
			break;
		case 2:
			System.out.print("待删除用户名：");
			String name_;
			name_ = DataProcessing.scanner.next();
			if(DataProcessing.deleteUser(name_))
				System.out.println("删除成功！");
			else
				System.out.println("删除失败！");
			break;
		case 3:
			administrator.addUser();
			break;
		case 4:
			administrator.listUser();
			break;
		case 5:
			System.out.print("文件名：");
			String filename = DataProcessing.scanner.next();
			administrator.downloadFile(filename);
			break;
		case 6:
			administrator.showFileList();
			break;
		case 7:
			System.out.print("新密码：");
			String newPassword_ = DataProcessing.scanner.next();
			administrator.changeUserInfo(newPassword_);
			break;
		default:
			DataProcessing.scanner.close();
			break;
		}
	}
	//当用户为administrator时，调用该函数
	
	public void operator(User user) throws IOException, SQLException{
		Operator operator = (Operator)user;
		//类型强制转换;将父类引用转换为子类引用
		operator.showMenu();
		switch(DataProcessing.scanner.nextInt()){
		case 1:
			operator.uploadFile();
			break;
		case 2:
			System.out.print("文件名：");
			String filename = DataProcessing.scanner.next();
			operator.downloadFile(filename);
			break;
		case 3:
			operator.showFileList();
			break;
		case 4:
			System.out.print("新密码：");
			String password = DataProcessing.scanner.next();
			operator.changeUserInfo(password);
			break;
		default:
			DataProcessing.scanner.close();
			break;
		}
	}
	//当用户为operator时，调用该函数
	
	public void browser(User user) throws IOException, SQLException{
		Browser browser = (Browser)user;
		browser.showMenu();
		switch(DataProcessing.scanner.nextInt()){
		case 1:
			System.out.print("文件名：");
			String filename = DataProcessing.scanner.next();
			browser.downloadFile(filename);
			break;
		case 2:
			browser.showFileList();
			break;
		case 3:
			System.out.print("新密码：");
			String password = DataProcessing.scanner.next();
			browser.changeUserInfo(password);
			break;
		default:
			DataProcessing.scanner.close();
			break;
		}

	}
	//当用户为browser时，调用该函数

}


