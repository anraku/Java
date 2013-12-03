package client_system;

import java.awt.*;
import java.awt.event.*;


public class MainFrame extends Frame implements ActionListener,WindowListener,KeyListener{

	ReservationControl reservationControl;
	boolean flagLogin = false;
	String userID = "";
	
	Panel panelNorth;		//上部パネル
	Panel panelNorthSub1;		//上部パネルの上
	Panel panelNorthSub2;		//上部パネルの中央
	Panel panelNorthSub3;		//上部パネルの下
	Panel panelMid;		//中央パネル
	Panel panelSouth;		//下部パネル

	Button buttonLog;			// ログイン　・　ログアウト　ボタン
	Button buttonExplanation;		// 施設概要　説明ボタン
	Button buttonVacancy;			//  空き状況確認
	Button buttonReservation;		// 新規予約ボタン
	Button buttonConfirm;			// 予約の確認
	Button buttonCancel;			// 予約のキャンセルボタン

	ChoiceFacility choiceFacility;		// 施設選択用選択ボックス
	TextField tfYear,tfMonth,tfDay;		// 年月日のテキストフィールド
	TextArea textMessage;			// 結果表示用メッセージ欄

	public MainFrame( ReservationControl rc){

		reservationControl = rc;

		// ボタンの生成
		buttonLog = new Button(" ログイン ");
		buttonExplanation = new Button("施設概要");
		buttonVacancy = new Button("空き状況確認");
		buttonReservation = new Button("新規予約");
		buttonConfirm = new Button("予約の確認");
		buttonCancel = new Button("予約のキャンセル");

		// 設備チョイスボックスの生成
		choiceFacility = new ChoiceFacility();
		tfYear = new TextField("",4);
		tfMonth = new TextField("",2);
		tfDay = new TextField("",2);

		// 上中下のパネルを使うため，レイアウトマネージャーにBorderLayoutを設定
		setLayout( new BorderLayout());


		// 上部パネルの上パネルに　　予約システム　というラベルと　[ログイン]ボタンを追加
		panelNorthSub1 = new Panel();
		panelNorthSub1.add(new Label("施設予約システム　　　　　　　　　　　　　　　　"));
		panelNorthSub1.add(buttonLog);

		// 上部パネルの中央パネルに　　　施設　　[施設名選択]チョイス   [概要説明]ボタンを追加
		panelNorthSub2 = new Panel();
		panelNorthSub2.add(new Label("施設　　"));
		panelNorthSub2.add( choiceFacility);
		panelNorthSub2.add(new Label("　　　"));
		panelNorthSub2.add( buttonExplanation);

		// 上部パネルのしたパネルに年月日入力欄と　空き状況確認ボタンを追加
		panelNorthSub3 = new Panel();
		panelNorthSub3.add(new Label("　　"));
		panelNorthSub3.add(tfYear);
		panelNorthSub3.add(new Label("年"));
		panelNorthSub3.add(tfMonth);
		panelNorthSub3.add(new Label("月"));
		panelNorthSub3.add(tfDay);
		panelNorthSub3.add(new Label("日　"));
		panelNorthSub3.add( buttonVacancy);


		// 上部パネルに３つのパネルを追加
		panelNorth = new Panel(new BorderLayout());
		panelNorth.add(panelNorthSub1, BorderLayout.NORTH);
		panelNorth.add(panelNorthSub2, BorderLayout.CENTER);
		panelNorth.add(panelNorthSub3, BorderLayout.SOUTH);
		// メイン画面(MainFrame)に上部パネルを追加
		add(panelNorth,BorderLayout.NORTH);


		// 中央パネルにテキストメッセージ欄を設定
		panelMid = new Panel();
		textMessage = new TextArea( 20, 80);
		textMessage.setEditable(false);
		panelMid.add(textMessage);
		// メイン画面(MainFrame)に中央パネルを追加
		add( panelMid,BorderLayout.CENTER);


		// 下部パネルにボタンを設定
		panelSouth = new Panel();
		panelSouth.add(buttonReservation);
		panelSouth.add(new Label("　　　"));
		panelSouth.add(buttonConfirm);
		panelSouth.add(new Label("　　　"));
		panelSouth.add(buttonCancel);
		// メイン画面(MainFrame)に下部パネルを追加
		add( panelSouth,BorderLayout.SOUTH);

		//ボタンのアクションリスナの追加
		buttonLog.addActionListener(this);
		buttonExplanation.addActionListener(this);
		buttonVacancy.addActionListener(this);
		buttonReservation.addActionListener(this);
		buttonConfirm.addActionListener(this);
		buttonCancel.addActionListener(this);

		addWindowListener(this);
		addKeyListener(this);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		
		String result = new String();
		textMessage.setText("");
		
		if(arg0.getSource() == buttonLog){//ログインボタン
			if(flagLogin){//ログイン状態の場合ログオフ状態にする
				Loginini();
			}else{
				//ログインダイアログの生成と表示
				LoginDialog ld = new LoginDialog(this);
				ld.setVisible(true);
				
				if(!ld.canceled){
					String userid = ld.tfUserID.getText();
					String password = ld.tfPassword.getText();
					
					result = reservationControl.login(userid,password);
					
					if(result.isEmpty()){
						//成功した場合
						Loginini(userid);
					}
				}
				
			}
		}
		
		if(arg0.getSource() == buttonReservation){
			if(!flagLogin){
				textMessage.setText("ログインしてください");
			}else{
				//ログインダイアログの生成と表示
				ReservationDialog rd = new ReservationDialog(this);
				rd.setVisible(true);
				
				if(!rd.canceled){
					String date = rd.tfDate.getText();
					String start_time = rd.tfStart_time.getText();
					String end_time = rd.tfEnd_time.getText();
					String userid = userID;
					String facility_name = rd.tfFacility_name.getText();
					
					result = reservationControl.reservation(
							date,start_time,end_time,userid,facility_name);
					
					if(result.isEmpty()){
						//成功した場合
						
					}
				}
			}
		}
		
		
	}
	
	public void Loginini(){
		flagLogin = false;
		userID = "";
		buttonLog.setLabel("ログイン");
	}
	
	public void Loginini(String id){
		flagLogin = true;
		userID = id;
		buttonLog.setLabel("ログアウト");
	}
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ
		System.exit(0);

	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO 自動生成されたメソッド・スタブ

	}

}