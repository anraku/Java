package client_system;

import java.sql.*;

public class ReservationControl {
	String userid = "reservation_user";
	String password = "pass0004";
	Connection sqlCon;
	Statement sqlStmt;
	
	//���̗\��V�X�e���̃��[�UID�ƃ��O�C�����
	String reservation_userid;
	
	public ReservationControl(){

	}
	
	public String login( String strUserID, String strPassword){

		//���O�C���������ʂ����镶����
		//���炩���ߎ��s�������b�Z�[�W�����Ă����A����������󕶎��ɂ���B
		String res = "���O�C���Ɏ��s���܂���";
		
		reservation_userid = strUserID;
		String password = strPassword;
		
		connectDB();
		
		//MySQL�̑���iSELECT���̎��s�j
		try{
			String sql = "SELECT * from db_reservation.user " +
					"where user_id = '" + reservation_userid + "' AND password = '" + password + "';";
			
			//�N�G���[�����s���Č��ʃZ�b�g���擾
			ResultSet rs =sqlStmt.executeQuery(sql);
			
			//�������ʁA���R�[�h�����݂��A�p�X���[�h����v���Ă���ΔF�ؐ����Ȃ̂ŁAres��""����
			if(rs.next()){
				String password_from_db = rs.getString("password");
				if(password_from_db.equals(password)){//�F�ؐ����FID�ƃp�X���[�h����v
					res = "";
				}
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		closeDB();
		return res;
	}
	
	public String reservation(String date,String start_time,
			String end_time,String userid,String facility_name){
		return "a";
	}
	/*public boolean IDauthentication(String id,String pass){
		connectDB();

		try{
			String sql = "SELECT * from reservationsystem.manager " +
					"where manager_id = '" + id + "',manager_password = '" + pass + "';";

			if(sqlStmt.executeUpdate(sql) >= 1){
				System.out.println("login success");
				closeDB();
				return true;
			}

		}catch(Exception e){
			System.out.println("login error");
			return false;
		}

		closeDB();
		return false;
	}

	public String insertReservation(String student_id, String facility_name, String y, String m, String d){
		String res ="";

		try{
			int ryear = Integer.parseInt(y);
			int rmonth = Integer.parseInt(m);
			int rday = Integer.parseInt(d);

		}catch(NumberFormatException e){
			res = "�N�����ɂ͐������w�肵�Ă�������";
			return res;
		}

		if(m.length()==1){
			m = "0" + m;
		}

		if(d.length()==1){
			d = "0" + d;
		}

		String date = y + "-" + m + "-" + d;


		connectDB();

		try{
			String sql = "INSERT INTO practice.reservation(student_id,facility_name,date) VALUES('";
			sql += student_id + "','" + facility_name + "','" + date + "');";

			int rs_int = sqlStmt.executeUpdate(sql);
			res = rs_int + "�s�o�^���܂���";
		}catch(Exception e){
			res = "�G���[�����������߁A�o�^�ł��܂���ł���";
		}

		closeDB();
		return res;
	}

	public String selectReservation(){
		String res = "";
		connectDB();

		try{
			String sql = "SELECT * FROM practice.reservation;";
			ResultSet rs =sqlStmt.executeQuery(sql);
			while(rs.next()){
				String student = rs.getString("student_id");
				String facility_name = rs.getString("facility_name");
				String date = rs.getString("date");
				res += date + " " + student + " " + facility_name +"\n";
			}

		}catch(Exception e){
			res = "�f�[�^�����ɂ����ăG���[�������܂���";
		}
		closeDB();
		return res;
	}*/

	private void connectDB(){
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			String url ="jdbc:mysql://localhost?useUnicode=true&characterEncoding=SJIS";
			sqlCon = DriverManager.getConnection(url,userid,password);
			sqlStmt = sqlCon.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

private void closeDB(){
	try{
		sqlStmt.close();
		sqlCon.close();
	}catch(Exception e){
		e.printStackTrace();
		}
	}
}
