package BlookUtil;

import java.awt.*;

public class ball {
	
	private Point Position;
	private int Size;
	private int MoveX;
	private int MoveY;
	
	public ball(int x,int y,int s){	//コンストラクタ１
		Position = new Point(x,y);
		Size = s;
		MoveX = 2;
		MoveY = -2;
	}
	
	public ball(int x,int y,int s,int movex,int movey){	//コンストラクタ２
		Position = new Point(x,y);
		Size = s;
		MoveX = 2;
		MoveY = -2;
		MoveX = movex;
		MoveY = movey;
	}
	
	public void setPosition(int x,int y){
		Position = new Point(x,y);
	}
	
	public void setMoveX(int x){
		MoveX = x;
	}
	
	public void setMoveY(int y){
		MoveY = y;
	}
	
	public Point getPosition(){
		return Position;
	}
	
	public int getSize(){
		return Size;
	}
	
	public int getMoveX(){
		return MoveX;
	}
	
	public int getMoveY(){
		return MoveY;
	}
	
	public void changeMoveX(){
		MoveX *= -1;
	}
	
	public void changeMoveY(){
		MoveY *= -1;
	}
}
