package client;

public class Browser extends User{
	public Browser(String name, String password, String role){
		super(name, password, role);
	}
	
//	public void downloadFile(){};
	
//	public void showFileList(){};
	
	public void showMenu(){
		System.out.println("~~~~~~~~·�˲˵�~~~~~~~~");
		System.out.println("\t1.�����ļ���");
		System.out.println("\t2.�ļ��б�");
		System.out.println("\t3.�޸����룡");
		System.out.println("\t4.�˳���");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		
	};
}
