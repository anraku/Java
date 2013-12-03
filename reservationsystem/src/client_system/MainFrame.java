package client_system;

import java.awt.*;
import java.awt.event.*;


public class MainFrame extends Frame implements ActionListener,WindowListener,KeyListener{

	ReservationControl reservationControl;
	boolean flagLogin = false;
	String userID = "";
	
	Panel panelNorth;		//�㕔�p�l��
	Panel panelNorthSub1;		//�㕔�p�l���̏�
	Panel panelNorthSub2;		//�㕔�p�l���̒���
	Panel panelNorthSub3;		//�㕔�p�l���̉�
	Panel panelMid;		//�����p�l��
	Panel panelSouth;		//�����p�l��

	Button buttonLog;			// ���O�C���@�E�@���O�A�E�g�@�{�^��
	Button buttonExplanation;		// �{�݊T�v�@�����{�^��
	Button buttonVacancy;			//  �󂫏󋵊m�F
	Button buttonReservation;		// �V�K�\��{�^��
	Button buttonConfirm;			// �\��̊m�F
	Button buttonCancel;			// �\��̃L�����Z���{�^��

	ChoiceFacility choiceFacility;		// �{�ݑI��p�I���{�b�N�X
	TextField tfYear,tfMonth,tfDay;		// �N�����̃e�L�X�g�t�B�[���h
	TextArea textMessage;			// ���ʕ\���p���b�Z�[�W��

	public MainFrame( ReservationControl rc){

		reservationControl = rc;

		// �{�^���̐���
		buttonLog = new Button(" ���O�C�� ");
		buttonExplanation = new Button("�{�݊T�v");
		buttonVacancy = new Button("�󂫏󋵊m�F");
		buttonReservation = new Button("�V�K�\��");
		buttonConfirm = new Button("�\��̊m�F");
		buttonCancel = new Button("�\��̃L�����Z��");

		// �ݔ��`���C�X�{�b�N�X�̐���
		choiceFacility = new ChoiceFacility();
		tfYear = new TextField("",4);
		tfMonth = new TextField("",2);
		tfDay = new TextField("",2);

		// �㒆���̃p�l�����g�����߁C���C�A�E�g�}�l�[�W���[��BorderLayout��ݒ�
		setLayout( new BorderLayout());


		// �㕔�p�l���̏�p�l���Ɂ@�@�\��V�X�e���@�Ƃ������x���Ɓ@[���O�C��]�{�^����ǉ�
		panelNorthSub1 = new Panel();
		panelNorthSub1.add(new Label("�{�ݗ\��V�X�e���@�@�@�@�@�@�@�@�@�@�@�@�@�@�@�@"));
		panelNorthSub1.add(buttonLog);

		// �㕔�p�l���̒����p�l���Ɂ@�@�@�{�݁@�@[�{�ݖ��I��]�`���C�X   [�T�v����]�{�^����ǉ�
		panelNorthSub2 = new Panel();
		panelNorthSub2.add(new Label("�{�݁@�@"));
		panelNorthSub2.add( choiceFacility);
		panelNorthSub2.add(new Label("�@�@�@"));
		panelNorthSub2.add( buttonExplanation);

		// �㕔�p�l���̂����p�l���ɔN�������͗��Ɓ@�󂫏󋵊m�F�{�^����ǉ�
		panelNorthSub3 = new Panel();
		panelNorthSub3.add(new Label("�@�@"));
		panelNorthSub3.add(tfYear);
		panelNorthSub3.add(new Label("�N"));
		panelNorthSub3.add(tfMonth);
		panelNorthSub3.add(new Label("��"));
		panelNorthSub3.add(tfDay);
		panelNorthSub3.add(new Label("���@"));
		panelNorthSub3.add( buttonVacancy);


		// �㕔�p�l���ɂR�̃p�l����ǉ�
		panelNorth = new Panel(new BorderLayout());
		panelNorth.add(panelNorthSub1, BorderLayout.NORTH);
		panelNorth.add(panelNorthSub2, BorderLayout.CENTER);
		panelNorth.add(panelNorthSub3, BorderLayout.SOUTH);
		// ���C�����(MainFrame)�ɏ㕔�p�l����ǉ�
		add(panelNorth,BorderLayout.NORTH);


		// �����p�l���Ƀe�L�X�g���b�Z�[�W����ݒ�
		panelMid = new Panel();
		textMessage = new TextArea( 20, 80);
		textMessage.setEditable(false);
		panelMid.add(textMessage);
		// ���C�����(MainFrame)�ɒ����p�l����ǉ�
		add( panelMid,BorderLayout.CENTER);


		// �����p�l���Ƀ{�^����ݒ�
		panelSouth = new Panel();
		panelSouth.add(buttonReservation);
		panelSouth.add(new Label("�@�@�@"));
		panelSouth.add(buttonConfirm);
		panelSouth.add(new Label("�@�@�@"));
		panelSouth.add(buttonCancel);
		// ���C�����(MainFrame)�ɉ����p�l����ǉ�
		add( panelSouth,BorderLayout.SOUTH);

		//�{�^���̃A�N�V�������X�i�̒ǉ�
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
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		
		String result = new String();
		textMessage.setText("");
		
		if(arg0.getSource() == buttonLog){//���O�C���{�^��
			if(flagLogin){//���O�C����Ԃ̏ꍇ���O�I�t��Ԃɂ���
				Loginini();
			}else{
				//���O�C���_�C�A���O�̐����ƕ\��
				LoginDialog ld = new LoginDialog(this);
				ld.setVisible(true);
				
				if(!ld.canceled){
					String userid = ld.tfUserID.getText();
					String password = ld.tfPassword.getText();
					
					result = reservationControl.login(userid,password);
					
					if(result.isEmpty()){
						//���������ꍇ
						Loginini(userid);
					}
				}
				
			}
		}
		
		if(arg0.getSource() == buttonReservation){
			if(!flagLogin){
				textMessage.setText("���O�C�����Ă�������");
			}else{
				//���O�C���_�C�A���O�̐����ƕ\��
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
						//���������ꍇ
						
					}
				}
			}
		}
		
		
	}
	
	public void Loginini(){
		flagLogin = false;
		userID = "";
		buttonLog.setLabel("���O�C��");
	}
	
	public void Loginini(String id){
		flagLogin = true;
		userID = id;
		buttonLog.setLabel("���O�A�E�g");
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
		System.exit(0);

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

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO �����������ꂽ���\�b�h�E�X�^�u

	}

}