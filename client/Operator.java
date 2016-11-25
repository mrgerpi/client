package client;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;
import java.io.*;

public class Operator extends User{
	public Operator(String name, String password, String role){
		super(name, password, role);
	}
	
	public void uploadFile() throws SQLException, IOException{
		System.out.print("FileName: ");
		Scanner in = new Scanner(System.in);
		String filename = in.nextLine();
		System.out.print("ID");
		String ID = in.nextLine();
		System.out.print("Description: ");
		String description = in.nextLine();
		Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
		DataProcessing.insertDoc(ID, name, timeStamp, description, filename);
		//���ļ������������ݿ�
		
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
		//�Ӻ�����in.close()ʹSystem.inҲclose��
		//���ٷ������������޷�����Scanner�ӱ�׼��������������
		//����û�н��
	};
	
//	public void downloadFile(){};

	public void showMenu(){
		System.out.println("~~~~~~~~¼��Ա�˵�~~~~~~~~");
		System.out.println("\t1.�ϴ��ļ���");
		System.out.println("\t2.�����ļ���");
		System.out.println("\t3.�ļ��б���");
		System.out.println("\t4.�޸����룡");
		System.out.println("\t5.�˳�");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
	};
}