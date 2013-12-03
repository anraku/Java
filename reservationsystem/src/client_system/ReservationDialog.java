package client_system;

import java.awt.*;
import java.awt.event.*;

public class ReservationDialog extends Dialog implements ActionListener,WindowListener{
	
	boolean canceled;
	
	ChoiceFacility choiceFacility;	//�{�ݑI��p�{�b�N�X
	TextField tfYear,tfMonth,tfDay;	//�N�����̃e�L�X�g�t�B�[���h
	ChoiceHour startHour;			//�\��J�n�����@���̑I��p�{�b�N�X
	ChoiceMinute startMinute;		//�\��J�n�����@���̑I��p�{�b�N�X
	ChoiceHour endHour;				//�\��I�������@���̑I��p�{�b�N�X
	ChoiceMinute endMinute;			//�\��I�������@���̑I��p�{�b�N�X
	
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
		buttonCancel = new Button("�L�����Z��");
		
		panelDate = new Panel();
		panelStart_time = new Panel();
		panelEnd_time = new Panel();
		panelFacility_name = new Panel();
		panelButton = new Panel();
		
		//Date�e�L�X�g�t�B�[���h���p�l���ɒǉ�
		panelDate.add(new Label("���t"));
		panelDate.add(tfDate);
		
		//start_time�e�L�X�g�t�B�[���h���p�l���ɒǉ�
		
		
		//�{�^���p�l����2�̃{�^����ǉ�
		panelButton.add(buttonCancel);
		panelButton.add(buttonOK);
		
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
