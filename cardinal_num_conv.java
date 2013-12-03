/*
 * �쐬��: 2012/05/10
 *
 * TODO ���̐������ꂽ�t�@�C���̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */

/**
 * @author 116107
 *
 * TODO ���̐������ꂽ�^�R�����g�̃e���v���[�g��ύX����ɂ͎��փW�����v:
 * �E�B���h�E - �ݒ� - Java - �R�[�h�E�X�^�C�� - �R�[�h�E�e���v���[�g
 */

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class cardinal_num_conv extends Applet implements ActionListener{
	TextField ten, two, sixteen;//�e�L�X�g�t�B�[���h��錾
	Label lten, ltwo, lsixteen;//���x����錾
	Button conv;//�{�^����錾
	
	String num = "0123456789ABCDEF";//String�^num��錾
	char[] data = num.toCharArray();//�z��char�^data��錾
	
	String two_num, sixteen_num;
	int temp;
	
	public void init(){
		
		Panel A =new Panel();
		lten = new Label("10�i��");
		A.add(lten);
		
		ten = new TextField("",20);
		A.add(ten);
		
		Panel B =new Panel();
		ltwo = new Label("2�i��");
		B.add(ltwo);
		
		two = new TextField("",20);
		B.add(two);
		
		Panel C =new Panel();
		lsixteen = new Label("16�i��");
		C.add(lsixteen);
		
		sixteen = new TextField("",20);
		C.add(sixteen);
		
		conv = new Button("�ϊ�");
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
			
			//2�i���ϊ�
			two_num = "";
			while(temp > 0){
				two_num += String.valueOf(temp%2);
				temp /= 2;
			}
			
			two_num = Reverse(two_num);
			
			temp = Integer.parseInt(ten.getText());
			//16�i���ϊ�
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
		//���]����������i�Ԃ�l�j
		String ret = "";
		
		//������𔽓]����
		int length = str.length();
		for(int i = length - 1; i >= 0; i--) {
			ret += str.charAt(i);
		}
		
		//���]�����������Ԃ�
		return ret;
	}
}
