package BlookUtil;

import java.awt.*;

public class blook {
	
	private Point Position;
	private int sizeY;
	private int sizeX;
	private boolean Visible = true;
	
	public blook(int x,int y,int xsize,int ysize){
		Position = new Point(x,y);
		sizeX = xsize;
		sizeY = ysize;
	}
	
	public void changeVisible(boolean b){
		Visible = b;
	}
	
	public Point getPosition(){
		return Position;
	}
	
	public int getXsize(){
		return sizeX;
	}
	
	public int getYsize(){
		return sizeY;
	}
	
	public boolean getVisible(){
		return Visible;
	}
}
