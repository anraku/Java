package Blook;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Timer.*;

import javax.swing.JOptionPane;

import BlookUtil.*;

public class BlookLevel extends Frame implements MouseMotionListener{
	
	static int WindowSizeX = 500;				//ウィンドウの横幅
	static int WindowSizeY = 800;				//ウィンドウの縦幅
	Timer t;									//タイマークラスインスタンス
						
	base Base = new base(200,700);				//バークラスインスタンス
	
	ball Ball = new ball(215,680,20,5,-5);		//ボールクラスインスタンス
	
	int BlookNumX = 6;							//生成する横のブロック数
	int BlookNumY = 10;							//生成する縦のブロックの数
	blook Blook[][]= new blook [BlookNumY][BlookNumX];	//縦に10個、横に6個のブロックのデータ
	
	int BlookSizeX = (WindowSizeX-20)/BlookNumX - 2;	//ブロックの横のサイズ
	int BlookSizeY = (WindowSizeY/2-50)/BlookNumY - 2;	//ブロックの縦のサイズ
	
	public BlookLevel(){
		//終了ボタンを有効にする
		addWindowListener(
			new WindowAdapter() {    
				public void windowClosing(WindowEvent evt) {System.exit(0);}
			}
		);
		
		addMouseMotionListener(this);
		setTitle("ブロック崩し");
		
		//ブロックインスタンスの配列の初期化
		for(int i = 0;i < Blook.length;i++){
			for(int j = 0;j < Blook[0].length;j++){
				Blook[i][j] = new blook(10 + 2*(j+1) + BlookSizeX*j,50 + 2*(i+1) + BlookSizeY*i,
						BlookSizeX,BlookSizeY);
			}
		}
		
		//タイマースタート
		t = new Timer();
		t.schedule(new MyTimer(),0,35);
	}// end constructor
	
	class MyTimer extends TimerTask{
		
		public void run(){
			//ボールの位置を移動距離だけ足す
			int moveX = Ball.getPosition().x + Ball.getMoveX();
			int moveY = Ball.getPosition().y + Ball.getMoveY();
			
			Ball.setPosition(moveX, moveY);	//ボールの位置を更新
			
			//ボールが落ちたらゲームオーバー
			if(Ball.getPosition().y+Ball.getSize() > WindowSizeY){
				t.cancel();
				JOptionPane.showMessageDialog(null, "ゲームオーバーです", "GAMEOVER", JOptionPane.INFORMATION_MESSAGE);
			}
			
			//ブロックをすべて消したらクリア
			boolean clear = true;
			for(int i = 0;i < Blook.length;i++)
				for(int j = 0;j < Blook[0].length;j++)
					if(Blook[i][j].getVisible())
						clear = false;
			if(clear){
				t.cancel();
				JOptionPane.showMessageDialog(null, "ゲームクリアです", "GAMECLEAR!", JOptionPane.INFORMATION_MESSAGE);
			}
				
			//ウインドウとボールの判定///////////////////////////////////
			if(Ball.getPosition().x + Ball.getSize()> WindowSizeX || 
					Ball.getPosition().x < 0){	//もしボールが横のウィンドウを超えたら
				Ball.changeMoveX();				//移動する水平方向に向きを反転する
			}
			
			if(Ball.getPosition().y + Ball.getSize() > WindowSizeY || 
					Ball.getPosition().y < 0){	//もしボールが縦のウィンドウを超えたら
				Ball.changeMoveY();				//移動する垂直方向に向きを反転する
			}
			//ウインドウとボールの判定///////////////////////////////////
			
			
			//ブロックとボールの判定/////////////////////////////////////
			for(int i = 0;i < Blook.length;i++){
				for(int j = 0;j < Blook[0].length;j++){
					//１．ボールの上側 < ブロックの下側
					//２．ボールの下側 > ブロックの上側
					//３．ボールの右側 > ブロックの左側
					//４．ボールの左側 < ブロックの右側
					//↓は１～４を満たしたときの条件
					if(Ball.getPosition().y < Blook[i][j].getPosition().y+Blook[i][j].getYsize() && 
							Ball.getPosition().y + Ball.getSize() > Blook[i][j].getPosition().y && 
							Ball.getPosition().x + Ball.getSize() > Blook[i][j].getPosition().x &&
							Ball.getPosition().x < Blook[i][j].getPosition().x + Blook[i][j].getXsize()){
						
						//↓ボールが上から、または下からブロックに当たった時の条件
						if(Ball.getPosition().y+10 > Blook[i][j].getPosition().y + Blook[i][j].getYsize() ||
								Ball.getPosition().y + Ball.getSize()-10 < Blook[i][j].getPosition().y){
							//↓消えていないブロックであった時の条件
							if(Blook[i][j].getVisible()){
								Ball.changeMoveY();						//ブロックを垂直方向に反転させ
								Blook[i][j].changeVisible(false);		//ブロックを消す
							}		
						}
						
						//↓ボールが左から、または右からブロックに当たった時の条件
						if(Ball.getPosition().x + Ball.getSize()-10 < Blook[i][j].getPosition().x ||
							Ball.getPosition().x+10 > Blook[i][j].getPosition().x + Blook[i][j].getXsize()){
							//↓消えていないブロックであった時の条件
							if(Blook[i][j].getVisible()){
								Ball.changeMoveX();						//ブロックを水平方向に反転させ
								Blook[i][j].changeVisible(false);		//ブロックを消す
							}		
						}
					}
				}
			}
			//ブロックとボールの判定/////////////////////////////////////
			
			
			//バーとボールの判定///////////////////////////////////////
			//↓ボールがバーに当たった時の条件
			if(Base.getPosition().y < Ball.getPosition().y+Ball.getSize() &&
					Base.getPosition().y+Base.getYsize() > Ball.getPosition().y){
				if(Base.getPosition().x < Ball.getPosition().x+Ball.getSize() &&
						Base.getPosition().x+Base.getXsize() > Ball.getPosition().x){
					
					//ボールが右から、または左から当たったときは水平方向に反転させる
					if(Ball.getPosition().x + Ball.getSize()-5 < Base.getPosition().x ||
						Ball.getPosition().x+5 > Base.getPosition().x + Base.getXsize()){
						Ball.changeMoveX();	//水平方向に反転
					}
					
					Ball.changeMoveY();		//・垂直方向に反転
				}
			}
			//バーとボールの判定///////////////////////////////////////
				
			repaint();						//再描画
		}
		
	}// end MyTimer
	
	public void paint(Graphics g){
		
		//バーの表示
		g.fillRect(Base.getPosition().x, Base.getPosition().y,
				Base.getXsize(), Base.getYsize());
		
		//ボールの表示
		g.fillOval(Ball.getPosition().x, Ball.getPosition().y, 
				Ball.getSize(), Ball.getSize());
		
		//ブロックの表示
		for(int i = 0;i < Blook.length;i++){
			for(int j = 0;j < Blook[0].length;j++){
				//消されていないブロックのみを表示する
				if(Blook[i][j].getVisible()){
					g.fillRect(Blook[i][j].getPosition().x,Blook[i][j].getPosition().y
							,Blook[i][j].getXsize() ,Blook[i][j].getYsize());
				}
			}
		}
		
	}//end paint
	
	//バーの位置をマウスの位置に合わせるメソッド
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
