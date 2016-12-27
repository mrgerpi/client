package client;

import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public  class DataProcessing {
	public static String hostPath = "E:\\document\\�����ļ�\\hostFile\\";
	public static String servePath = "E:\\document\\�����ļ�\\serveFile\\";
	public static Scanner scanner = new Scanner(System.in); 
	
	private static String dbUrl = "jdbc:mysql://localhost:3307/document";
	private static String dbUserName = "root";
	private static String dbPassword = "123456";
	private static String jdbcName = "com.mysql.jdbc.Driver";
	private static Connection connection = null;
	private static Statement statement = null;
	
	static {
		Init();
	}
	
	/**
	 * �ú������ڼ������� ��ȡ���ݿ�����
	 */
	public static  void Init(){
		try {
			Class.forName(jdbcName);
			System.out.println("���������ɹ���");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("��������ʧ�ܣ�");
		}	//��������
		
		try {
			connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
			System.out.println("��ȡ���ݿ����ӳɹ���");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("��ȡ���ݿ�����ʧ�ܣ�");
		}	//��ȡ���ݿ�����
	}
	
	private static Connection getConnection() throws SQLException{
		if(connection == null){
			connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		}
		return connection;
	}
	private static Statement getStatement() throws SQLException{
		if(statement == null){
			statement = getConnection().createStatement();
		}
		return statement;
	}
	
	public static boolean insertDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws SQLException{
		Statement statement = getStatement();
		String sql = "insert into doc_info values('" + ID + "', '" + creator + "', '" + timestamp + "', '" + description + "', '" + filename + "')";
		int result = statement.executeUpdate(sql);
		
		if(result == 1)		return true;
		else 				return false;
	} 

	public static boolean insertUser(String name, String password, String role) throws SQLException{	
		Statement statement = getStatement();
		String sql = "insert into user_info values('" + name + "', '" + password + "', '" + role + "')";
		int result = statement.executeUpdate(sql);
		if(result == 1)			return true;
		else 					return false;
	}

	public static boolean updateUser(String name, String password, String role) throws SQLException{	
		Statement statement = getStatement();
		String sql = "UPDATE user_info SET password = '" + password + "', role = '" + role + "' WHERE username = '" + name +"'";
		int result = statement.executeUpdate(sql);
		if(result == 1)		return true;
		else 				return false;
	}
	
	public static boolean deleteUser(String name) throws SQLException{
		Statement statement = getStatement();
		String sql = "DELETE FROM user_info WHERE username = '" + name + "'";
		int result = statement.executeUpdate(sql);
		if(result == 1)		return true;
		else 		return false;
	}	
            
	public static User searchUser(String name, String password) throws SQLException {
		Statement statement = getStatement();
		String sql = "select * from user_info where username = '" + name + "'";
		ResultSet resultSet = statement.executeQuery(sql);
		System.out.println(resultSet);
		if(resultSet.next() && resultSet.getString(2).equals(password)){
			String sqlUsername = resultSet.getString(1);
			String sqlPassword = resultSet.getString(2);
			String sqlRole = resultSet.getString(3);
			return new User(sqlUsername, sqlPassword, sqlRole);
		}
		else
			return null;
	}
	
	public static Doc searchDoc(String ID) throws SQLException {
		Statement statement = getStatement();
		String sql = "select * from doc_info where Id = " + ID;
		ResultSet resultSet = statement.executeQuery(sql);
		if(resultSet.next()){
			String id = resultSet.getString(1);
			String creator = resultSet.getString(2);
			Timestamp timestamp = (Timestamp) resultSet.getObject(3);
			String description = resultSet.getString(4);
			String filename = resultSet.getString(5);
			return new Doc(id, creator, timestamp, description, filename);
		}
		return null;
	}
	
	public static ArrayList<Doc> getAllDocs() throws SQLException{		
		ArrayList<Doc> docList = new ArrayList<Doc>(); 
		Statement statement = getStatement();
		String sql = "select * from doc_info";
		ResultSet resultSet = statement.executeQuery(sql);
		while(resultSet.next()){
			String id = resultSet.getString(1);
			String creator = resultSet.getString(2);
			Timestamp timestamp = (Timestamp)resultSet.getObject(3);
			String description = resultSet.getString(4);
			String filename = resultSet.getString(5);
			docList.add(new Doc(id, creator, timestamp, description, filename));
		}
		return docList;
	} 
	
	public static ArrayList<User> getAllUser() throws SQLException{
		ArrayList<User> userList = new ArrayList<User>();
		
		//�������Statementִ��sql��䣬�Ի�ȡһ��ResultSet��
		Statement statement = getStatement();
		String sql = "select * from user_info";
		ResultSet resultSet = statement.executeQuery(sql);
		
		while(resultSet.next()){
			String username = resultSet.getString(1);
			String password = resultSet.getString(2);
			String role = resultSet.getString(3);
			User user = new User(username, password, role);
			userList.add(user);
		}
		return userList;
	}
	
	public  static void disconnectFromDB() throws SQLException {
		statement.close();
		connection.close();
   }           

	public static void main(String[] args) throws SQLException {	
		System.out.println(DataProcessing.getAllDocs());
		
		System.out.println(DataProcessing.getAllUser());
		
		User user = DataProcessing.searchUser("jack", "123");
		System.out.println(user.getRole());
		
		Doc doc = DataProcessing.searchDoc("1");
		System.out.println(doc.getCreator());
		
		
		if(DataProcessing.deleteUser("kate"))
			System.out.println("ɾ���ɹ���");
		else
			System.out.println("ɾ��ʧ�ܣ�");
		
		if(DataProcessing.updateUser("kate", "456", "operator"))
			System.out.println("���³ɹ���");
		else
			System.out.println("����ʧ�ܣ�");
		/*if(DataProcessing.insertDoc("0003", "kate", new Timestamp(0), null, null))
			
		else 
			System.out.println("����ʧ�ܣ�");
		if(DataProcessing.insertUser("blue", "456", "operator"))
			System.out.println("����ɹ���");
		else 
			System.out.println("����ʧ�ܣ�");*/
	}
	
}

/**
 * ���ݿ������
 * 1�������������Զ���ȡ������������ DriverManager
 * 2����ȡ�����࣬ Connection connection = DriverManager.getConnection();
 * 3����ȡ����࣬Statement statement = connection.createStatement();
 * 4��дsql���, ������Ӧ�ı�׼
 * 5���������ִ��sql��䣬���ṩ����ֵ��
 * 		��ִ������ ɾ�� �ģ�����statement.excuteUpdate()�� ����int
 * 		��ִ�в�ѯ����,����statement.excuteQuery(), ����ResultSet��
 * 6��������ζ�ȡ��ResultSet���е����ݣ���java API
 */





