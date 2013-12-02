package Blook;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer.*;

import javax.swing.JOptionPane;

import BlookUtil.*;

public class BlookLevel extends Frame implements MouseMotionListener{
	
	static int WindowSizeX = 500;				//�E�B���h�E�̉���
	static int WindowSizeY = 800;				//�E�B���h�E�̏c��
	Timer t;									//�^�C�}�[�N���X�C���X�^���X
						
	base Base = new base(200,700);				//�o�[�N���X�C���X�^���X
	
	ball Ball = new ball(215,680,20,5,-5);		//�{�[���N���X�C���X�^���X
	
	int BlookNumX = 6;							//�������鉡�̃u���b�N��
	int BlookNumY = 10;							//��������c�̃u���b�N�̐�
	blook Blook[][]= new blook [BlookNumY][BlookNumX];	//�c��10�A����6�̃u���b�N�̃f�[�^
	
	int BlookSizeX = (WindowSizeX-20)/BlookNumX - 2;	//�u���b�N�̉��̃T�C�Y
	int BlookSizeY = (WindowSizeY/2-50)/BlookNumY - 2;	//�u���b�N�̏c�̃T�C�Y
	
	public BlookLevel(){
		//�I���{�^����L���ɂ���
		addWindowListener(
			new WindowAdapter() {    
				public void windowClosing(WindowEvent evt) {System.exit(0);}
			}
		);
		
		addMouseMotionListener(this);
		setTitle("�u���b�N����");
		
		//�u���b�N�C���X�^���X�̔z��̏�����
		for(int i = 0;i < Blook.length;i++){
			for(int j = 0;j < Blook[0].length;j++){
				Blook[i][j] = new blook(10 + 2*(j+1) + BlookSizeX*j,50 + 2*(i+1) + BlookSizeY*i,
						BlookSizeX,BlookSizeY);
			}
		}
		
		//�^�C�}�[�X�^�[�g
		t = new Timer();
		t.schedule(new MyTimer(),0,35);
	}// end constructor
	
	class MyTimer extends TimerTask{
		
		public void run(){
			//�{�[���̈ʒu���ړ�������������
			int moveX = Ball.getPosition().x + Ball.getMoveX();
			int moveY = Ball.getPosition().y + Ball.getMoveY();
			
			Ball.setPosition(moveX, moveY);	//�{�[���̈ʒu���X�V
			
			//�{�[������������Q�[���I�[�o�[
			if(Ball.getPosition().y+Ball.getSize() > WindowSizeY){
				t.cancel();
				JOptionPane.showMessageDialog(null, "�Q�[���I�[�o�[�ł�", "GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
			}
			
			//�u���b�N�����ׂď�������N���A
			boolean clear = true;
			for(int i = 0;i < Blook.length;i++)
				for(int j = 0;j < Blook[0].length;j++)
					if(Blook[i][j].getVisible())
						clear = false;
			if(clear){
				t.cancel();
				JOptionPane.showMessageDialog(null, "�Q�[���N���A�ł�", "GAMECLEAR!", JOptionPane.INFORMATION_MESSAGE);
			}
				
			//�E�C���h�E�ƃ{�[���̔���///////////////////////////////////
			if(Ball.getPosition().x + Ball.getSize()> WindowSizeX || 
					Ball.getPosition().x < 0){	//�����{�[�������̃E�B���h�E�𒴂�����
				Ball.changeMoveX();				//�ړ����鐅�������Ɍ����𔽓]����
			}
			
			if(Ball.getPosition().y + Ball.getSize() > WindowSizeY || 
					Ball.getPosition().y < 0){	//�����{�[�����c�̃E�B���h�E�𒴂�����
				Ball.changeMoveY();				//�ړ����鐂�������Ɍ����𔽓]����
			}
			//�E�C���h�E�ƃ{�[���̔���///////////////////////////////////
			
			
			//�u���b�N�ƃ{�[���̔���/////////////////////////////////////
			for(int i = 0;i < Blook.length;i++){
				for(int j = 0;j < Blook[0].length;j++){
					//�P�D�{�[���̏㑤 < �u���b�N�̉���
					//�Q�D�{�[���̉��� > �u���b�N�̏㑤
					//�R�D�{�[���̉E�� > �u���b�N�̍���
					//�S�D�{�[���̍��� < �u���b�N�̉E��
					//���͂P�`�S�𖞂������Ƃ��̏���
					if(Ball.getPosition().y < Blook[i][j].getPosition().y+Blook[i][j].getYsize() && 
							Ball.getPosition().y + Ball.getSize() > Blook[i][j].getPosition().y && 
							Ball.getPosition().x + Ball.getSize() > Blook[i][j].getPosition().x &&
							Ball.getPosition().x < Blook[i][j].getPosition().x + Blook[i][j].getXsize()){
						
						//���{�[�����ォ��A�܂��͉�����u���b�N�ɓ����������̏���
						if(Ball.getPosition().y+10 > Blook[i][j].getPosition().y + Blook[i][j].getYsize() ||
								Ball.getPosition().y + Ball.getSize()-10 < Blook[i][j].getPosition().y){
							//�������Ă��Ȃ��u���b�N�ł��������̏���
							if(Blook[i][j].getVisible()){
								Ball.changeMoveY();						//�u���b�N�𐂒������ɔ��]����
								Blook[i][j].changeVisible(false);		//�u���b�N������
							}		
						}
						
						//���{�[����������A�܂��͉E����u���b�N�ɓ����������̏���
						if(Ball.getPosition().x + Ball.getSize()-10 < Blook[i][j].getPosition().x ||
							Ball.getPosition().x+10 > Blook[i][j].getPosition().x + Blook[i][j].getXsize()){
							//�������Ă��Ȃ��u���b�N�ł��������̏���
							if(Blook[i][j].getVisible()){
								Ball.changeMoveX();						//�u���b�N�𐅕������ɔ��]����
								Blook[i][j].changeVisible(false);		//�u���b�N������
							}		
						}
					}
				}
			}
			//�u���b�N�ƃ{�[���̔���/////////////////////////////////////
			
			
			//�o�[�ƃ{�[���̔���///////////////////////////////////////
			//���{�[�����o�[�ɓ����������̏���
			if(Base.getPosition().y < Ball.getPosition().y+Ball.getSize() &&
					Base.getPosition().y+Base.getYsize() > Ball.getPosition().y){
				if(Base.getPosition().x < Ball.getPosition().x+Ball.getSize() &&
						Base.getPosition().x+Base.getXsize() > Ball.getPosition().x){
					
					//�{�[�����E����A�܂��͍����瓖�������Ƃ��͐��������ɔ��]������
					if(Ball.getPosition().x + Ball.getSize()-5 < Base.getPosition().x ||
						Ball.getPosition().x+5 > Base.getPosition().x + Base.getXsize()){
						Ball.changeMoveX();	//���������ɔ��]
					}
					
					Ball.changeMoveY();		//�E���������ɔ��]
				}
			}
			//�o�[�ƃ{�[���̔���///////////////////////////////////////
				
			repaint();						//�ĕ`��
		}
		
	}// end MyTimer
	
	public void paint(Graphics g){
		
		//�o�[�̕\��
		g.fillRect(Base.getPosition().x, Base.getPosition().y,
				Base.getXsize(), Base.getYsize());
		
		//�{�[���̕\��
		g.fillOval(Ball.getPosition().x, Ball.getPosition().y, 
				Ball.getSize(), Ball.getSize());
		
		//�u���b�N�̕\��
		for(int i = 0;i < Blook.length;i++){
			for(int j = 0;j < Blook[0].length;j++){
				//������Ă��Ȃ��u���b�N�݂̂�\������
				if(Blook[i][j].getVisible()){
					g.fillRect(Blook[i][j].getPosition().x,Blook[i][j].getPosition().y
							,Blook[i][j].getXsize() ,Blook[i][j].getYsize());
				}
			}
		}
		
	}//end paint
	
	//�o�[�̈ʒu���}�E�X�̈ʒu�ɍ��킹�郁�\�b�h
	public void mouseMoved(MouseEvent e) {
		Base.setPositionX(e.getX()-Base.getXsize()/2);
	    repaint();
	}	 
	  
	//main
	public static void main(String args[]){
		BlookLevel fm = new BlookLevel();
		fm.setSize(WindowSizeX,WindowSizeY);
		fm.setVisible(true);
	}

	public void mouseDragged(MouseEvent e) {
		
	}
	
}// end BlookLevel
