package client_system;

import java.sql.*;

public class ReservationControl {
	String userid = "reservation_user";
	String password = "pass0004";
	Connection sqlCon;
	Statement sqlStmt;
	
	//この予約システムのユーザIDとログイン状態
	String reservation_userid;
	
	public ReservationControl(){

	}
	
	public String login( String strUserID, String strPassword){

		//ログイン処理結果を入れる文字列
		//あらかじめ失敗したメッセージを入れておき、成功したら空文字にする。
		String res = "ログインに失敗しました";
		
		reservation_userid = strUserID;
		String password = strPassword;
		
		connectDB();
		
		//MySQLの操作（SELECT文の実行）
		try{
			String sql = "SELECT * from db_reservation.user " +
					"where user_id = '" + reservation_userid + "' AND password = '" + password + "';";
			
			//クエリーを実行して結果セットを取得
			ResultSet rs =sqlStmt.executeQuery(sql);
			
			//検索結果、レコードが存在し、パスワードが一致していれば認証成功なので、resに""を代入
			if(rs.next()){
				String password_from_db = rs.getString("password");
				if(password_from_db.equals(password)){//認証成功：IDとパスワードが一致
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
			res = "年月日には数字を指定してください";
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
			res = rs_int + "行登録しました";
		}catch(Exception e){
			res = "エラーが生じたため、登録できませんでした";
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
			res = "データ検索においてエラーが生じました";
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
