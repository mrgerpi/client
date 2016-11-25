package client;

public class Browser extends User{
	public Browser(String name, String password, String role){
		super(name, password, role);
	}
	
//	public void downloadFile(){};
	
//	public void showFileList(){};
	
	public void showMenu(){
		System.out.println("~~~~~~~~路人菜单~~~~~~~~");
		System.out.println("\t1.下载文件！");
		System.out.println("\t2.文件列表！");
		System.out.println("\t3.修改密码！");
		System.out.println("\t4.退出！");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~");
		
	};
}
