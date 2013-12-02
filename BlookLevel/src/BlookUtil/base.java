package BlookUtil;

import java.awt.*;

public class base {
	private Point Position;
	private int Xsize;
	private int Ysize;
	
	public base(int x,int y){
		Position = new Point(x,y);
		Xsize = 50;
		Ysize = 20;
	}
	
	public void setPositionX(int x){
		Position.x = x;
	}
	
	public void moveX(int x){
		int tempX = Position.x;
		Position = new Point(tempX+x,Position.y);
	}
	
	public Point getPosition(){
		return Position;
	}
	
	public int getXsize(){
		return Xsize;
	}
	
	public int getYsize(){
		return Ysize;
	}
}
