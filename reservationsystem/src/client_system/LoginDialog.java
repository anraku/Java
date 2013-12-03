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
		buttonCancel = new Button("�L�����Z��");
		
		panelNorth = new Panel();
		panelMid = new Panel();
		panelSouth = new Panel();
		
		//�㕔�p�l���ɁA���[�UID�e�L�X�g�t�B�[���h��ǉ�
		panelNorth.add(new Label("���[�UID"));
		panelNorth.add(tfUserID);
		
		//�����p�l���ɁA�p�X���[�h�e�L�X�g�t�B�[���h��ǉ�
		panelMid.add(new Label("�p�X���[�h"));
		panelMid.add(tfPassword);
		
		//�����p�l����2�̃{�^����ǉ�
		panelSouth.add(buttonCancel);
		panelSouth.add(buttonOK);
		
		//LogiinDialog��BorderLayout�ɐݒ肵�A3�̃p�l����ǉ�
		setLayout(new BorderLayout());
		add(panelNorth,BorderLayout.NORTH);
		add(panelMid,BorderLayout.CENTER);
		add(panelSouth,BorderLayout.SOUTH);
		
		//�E�B���h�E���X�i�[��ǉ�
		addWindowListener(this);
		//�{�^���ɃA�N�V�������X�i�[��ǉ�
		buttonOK.addActionListener(this);
		buttonCancel.addActionListener(this);
		
		//�傫���̐ݒ�A�E�B���h�E�̃T�C�Y�ύX�s�̐ݒ�
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		setVisible(false);
		canceled = true;
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
	}
	
}
