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
		//д�û���Ϣ���洢
		if (DataProcessing.updateUser(name, password, role)){
			this.password=password;
			return true;
		}else
			return false; 
	}
	
	public static boolean downloadFile(String filename) throws IOException{
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
	
	public static void uploadFile(String name, String ID,
			String description, String filename) throws SQLException, IOException{	
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		//��ʱ��filename����ȫ�ļ���������·��
		File hostFile = new File(filename);
		
		//��ʱ��filename�Ѿ�û��
		for(int i = filename.length() - 1;i > 0;i--){
			if(filename.charAt(i) == '/'){
				filename = filename.substring(i, filename.length() - 1);
			}		
		}
		System.out.println(filename);
		File serveFile = new File(DataProcessing.servePath + filename);
		DataProcessing.insertDoc(ID, name, timeStamp, description, filename);
		//���ļ�����������ݿ�
		
		BufferedReader br = new BufferedReader(new FileReader(hostFile));
		BufferedWriter bw = new BufferedWriter(new FileWriter(serveFile));
		
		int ch = 0;
		while((ch = br.read()) > 0){
			bw.write(ch);
		}
		br.close();
		bw.close();
		//�Ӻ�����in.close()ʹSystem.inҲclose��
		//���ٷ������������޷�����Scanner�ӱ�׼��������������
		//����û�н��
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
