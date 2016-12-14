package client;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.io.*;

public class Operator extends User{
	public Operator(String name, String password, String role){
		super(name, password, role);
	}
	
	public static void uploadFile(String name, String ID, String description, String filename) throws SQLException, IOException{
//		System.out.print("FileName: ");
//		DataProcessing.scanner.nextLine();	//这一行用于清楚缓存区
		
//		String filename = DataProcessing.scanner.nextLine();
//		System.out.print("ID");
//		String ID = DataProcessing.scanner.nextLine();
//		System.out.print("Description: ");
//		String description = DataProcessing.scanner.nextLine();
		
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		DataProcessing.insertDoc(ID, name, timeStamp, description, filename);
		//将文件内容添加数据库
		
		File serveFile = new File(DataProcessing.servePath + filename);
		File hostFile = new File(DataProcessing.hostPath + filename);
		
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
	
//	public void downloadFile(){};

	public void showMenu(){
		System.out.println("~~~~~~~~录入员菜单~~~~~~~~");
		System.out.println("\t1.上传文件！");
		System.out.println("\t2.下载文件！");
		System.out.println("\t3.文件列表！");
		System.out.println("\t4.修改密码！");
		System.out.println("\t5.退出");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
	};
}
