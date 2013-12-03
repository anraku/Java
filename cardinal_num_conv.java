/*
 * 作成日: 2012/05/10
 *
 * TODO この生成されたファイルのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */

/**
 * @author 116107
 *
 * TODO この生成された型コメントのテンプレートを変更するには次へジャンプ:
 * ウィンドウ - 設定 - Java - コード・スタイル - コード・テンプレート
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class cardinal_num_conv extends Applet implements ActionListener{
	TextField ten, two, sixteen;//テキストフィールドを宣言
	Label lten, ltwo, lsixteen;//ラベルを宣言
	Button conv;//ボタンを宣言
	
	String num = "0123456789ABCDEF";//String型numを宣言
	char[] data = num.toCharArray();//配列char型dataを宣言
	
	String two_num, sixteen_num;
	int temp;
	
	public void init(){
		
		Panel A =new Panel();
		lten = new Label("10進数");
		A.add(lten);
		
		ten = new TextField("",20);
		A.add(ten);
		
		Panel B =new Panel();
		ltwo = new Label("2進数");
		B.add(ltwo);
		
		two = new TextField("",20);
		B.add(two);
		
		Panel C =new Panel();
		lsixteen = new Label("16進数");
		C.add(lsixteen);
		
		sixteen = new TextField("",20);
		C.add(sixteen);
		
		conv = new Button("変換");
		conv.addActionListener(this);
		add(conv);
		
		Box vBox = Box.createVerticalBox();
		vBox.add(A);
		vBox.add(B);
		vBox.add(C);
		setLayout(new BorderLayout());
		add(vBox, BorderLayout.CENTER);
		add(conv, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == conv){
			temp = Integer.parseInt(ten.getText());
			
			//2進数変換
			two_num = "";
			while(temp > 0){
				two_num += String.valueOf(temp%2);
				temp /= 2;
			}
			
			two_num = Reverse(two_num);
			
			temp = Integer.parseInt(ten.getText());
			//16進数変換
			sixteen_num = "";
			while(temp > 0){
				sixteen_num += (data[temp%16]);
				temp /= 16;
			}
			sixteen_num = Reverse(sixteen_num);
		}
		
		two.setText(two_num);
		sixteen.setText(sixteen_num);
	}
	
	public String Reverse(String str) {
		//反転した文字列（返り値）
		String ret = "";
		
		//文字列を反転する
		int length = str.length();
		for(int i = length - 1; i >= 0; i--) {
			ret += str.charAt(i);
		}
		
		//反転した文字列を返す
		return ret;
	}
}
