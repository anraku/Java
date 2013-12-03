package client_system;

import java.awt.*;
import java.awt.event.*;

public class ReservationDialog extends Dialog implements ActionListener,WindowListener{
	
	boolean canceled;
	
	ChoiceFacility choiceFacility;	//施設選択用ボックス
	TextField tfYear,tfMonth,tfDay;	//年月日のテキストフィールド
	ChoiceHour startHour;			//予約開始時刻　時の選択用ボックス
	ChoiceMinute startMinute;		//予約開始時刻　分の選択用ボックス
	ChoiceHour endHour;				//予約終了時刻　時の選択用ボックス
	ChoiceMinute endMinute;			//予約終了時刻　分の選択用ボックス
	
	Button buttonOK;
	Button buttonCancel;
	
	Panel panelNorth;
	Panel panelMid;
	Panel panelSouth;
	
	public ReservationDialog(Frame arg0){
		
		super(arg0,"Reservation",true);
		
		canceled = true;
		
		tfDate = new TextField("",10);
		tfStart_time = new TextField("",10);
		tfEnd_time = new TextField("",10);
		tfFacility_name = new TextField("",10);
		
		buttonOK = new Button("OK");
		buttonCancel = new Button("キャンセル");
		
		panelDate = new Panel();
		panelStart_time = new Panel();
		panelEnd_time = new Panel();
		panelFacility_name = new Panel();
		panelButton = new Panel();
		
		//Dateテキストフィールドをパネルに追加
		panelDate.add(new Label("日付"));
		panelDate.add(tfDate);
		
		//start_timeテキストフィールドをパネルに追加
		
		
		//ボタンパネルに2つのボタンを追加
		panelButton.add(buttonCancel);
		panelButton.add(buttonOK);
		
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
