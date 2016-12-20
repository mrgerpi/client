package client;

import java.sql.SQLException;
import java.util.Enumeration;

import java.io.*;


public abstract class User {
	protected String name;
	protected String password;
	protected String role;
	
	User(String name,String password,String role){
		this.name=name;
		this.password=password;
		this.role=role;				
	}
	
	public boolean changeUserInfo(String password) throws SQLException{
		//д�û���Ϣ���洢
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
//			System.out.println("�޸ĳɹ�");
			return true;
		}else
			return false; 
	}
	
	public static boolean downloadFile(String filename) throws IOException{
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new IOException( "Error in accessing file" );
		
		File serveFile = new File(DataProcessing.servePath + filename);
		File hostFile = new File(DataProcessing.hostPath + filename);
		//��ԭ��û��filename����ᴴ��һ���ļ�
		
		BufferedReader br = new BufferedReader(new FileReader(serveFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(hostFile));
		//Ϊʲô��BufferedReader ������FileReader��
		//BufferedReader��һ���Դ��������ж�ȡ8k(Ĭ����ֵ,��������)�ֽ����ݵ��ڴ�
		
		int ch = 0;
		while((ch = br.read()) > 0){
			bw.write(ch);
		}
		br.close();
		bw.close();
		return true;
	}
	
	public void showFileList() throws SQLException{
//		double ranValue=Math.random();
//		if (ranValue>0.5)
//			throw new SQLException( "Error in accessing file DB" );
//		System.out.println("�б�... ...");
		Enumeration<Doc> docs = DataProcessing.getAllDocs();
		while(docs.hasMoreElements()){
			Doc doc = docs.nextElement();
			System.out.print("ID: ");
			System.out.print(doc.getID() + "\n");
			System.out.print("FileName: ");
			System.out.print(doc.getFilename() + "\n");
			System.out.print("Description: ");
			System.out.print(doc.getDescription() + "\n");
			System.out.print("Creator: ");
			System.out.print(doc.getCreator() + "\n");
			System.out.print("TimeStamp: ");
			System.out.print(doc.getTimestamp() + "\n");
		}
		
		
		
		
		
	}
	
	public abstract void showMenu();
	
	public void exitSystem(){
//		System.out.println("ϵͳ�˳�, ллʹ�� ! ");
		System.exit(0);
	}

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
