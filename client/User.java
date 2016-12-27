package client;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.*;


public class User {
	protected String name;
	protected String password;
	protected String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException{
		//写用户信息到存储
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			return true;
		}else
			return false; 
	}
	
	public static boolean downloadFile(String filename) throws IOException{
		File serveFile = new File(DataProcessing.servePath + filename);
		File hostFile = new File(DataProcessing.hostPath + filename);
		//若原来没有filename，则会创建一个文件
		
		BufferedReader br = new BufferedReader(new FileReader(serveFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(hostFile));
		//为什么用BufferedReader 而不用FileReader？
		//BufferedReader会一次性从物理流中读取8k(默认数值,可以设置)字节内容到内存
		
		int ch = 0;
		while((ch = br.read()) > 0){
			bw.write(ch);
		}
		br.close();
		bw.close();
		return true;
	}
	
	public static void uploadFile(String name, String ID,
			String description, String filename) throws SQLException, IOException{	
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		//此时的filename是完全文件名，带有路径
		File hostFile = new File(filename);
		
		//此时的filename已经没有
		for(int i = filename.length() - 1;i > 0;i--){
			if(filename.charAt(i) == '/'){
				filename = filename.substring(i, filename.length() - 1);
			}		
		}
		System.out.println(filename);
		File serveFile = new File(DataProcessing.servePath + filename);
		DataProcessing.insertDoc(ID, name, timeStamp, description, filename);
		//将文件内容添加数据库
		
		BufferedReader br = new BufferedReader(new FileReader(hostFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(serveFile));
		
		int ch = 0;
		while((ch = br.read()) > 0){
			bw.write(ch);
		}
		br.close();
		bw.close();
		//子函数中in.close()使System.in也close了
		//若再返回主函数中无法再用Scanner从标准输入流中输入了
		//问题没有解决
	};
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
