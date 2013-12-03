package client_system;

import java.awt.*;
import java.awt.event.*;

public class LoginDialog extends Dialog implements ActionListener,WindowListener{
	
	boolean canceled;
	
	TextField tfUserID;
	TextField tfPassword;
	
	Button buttonOK;
	Button buttonCancel;
	
	Panel panelNorth;
	Panel panelMid;
	Panel panelSouth;
	
	public LoginDialog(Frame arg0){
		
		super(arg0,"Login",true);
		
		canceled = true;
		
		tfUserID = new TextField("",10);
		tfPassword = new TextField("",10);
		
		tfPassword.setEchoChar('*');
		
		buttonOK = new Button("OK");
		buttonCancel = new Button("キャンセル");
		
		panelNorth = new Panel();
		panelMid = new Panel();
		panelSouth = new Panel();
		
		//上部パネルに、ユーザIDテキストフィールドを追加
		panelNorth.add(new Label("ユーザID"));
		panelNorth.add(tfUserID);
		
		//中央パネルに、パスワードテキストフィールドを追加
		panelMid.add(new Label("パスワード"));
		panelMid.add(tfPassword);
		
		//下部パネルに2つのボタンを追加
		panelSouth.add(buttonCancel);
		panelSouth.add(buttonOK);
		
		//LogiinDialogをBorderLayoutに設定し、3つのパネルを追加
		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		add(panelMid,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
		
		//ウィンドウリスナーを追加
		addWindowListener(this);
		//ボタンにアクションリスナーを追加
		buttonOK.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		//大きさの設定、ウィンドウのサイズ変更不可の設定
		this.setBounds(100,100,350,150);
		setResizable(false);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == buttonCancel){
			canceled = true;
		}else if(arg0.getSource() == buttonOK){
			canceled = false;
		}
		setVisible(false);
		dispose();
		
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
		setVisible(false);
		canceled = true;
		dispose();
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
	
}
