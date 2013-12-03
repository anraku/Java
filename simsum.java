import java.awt.*;
import java.applet.*;

public class simsum extends Applet{
public void paint(Graphics g){

// À•WŽ²‚ð•`‚­
g.drawLine(20, 10, 20, 130);
g.drawLine(20, 70, 230, 70);

// sinƒJ[ƒu‚ð•`‚­(20->220)
g.setColor( Color.blue);
int x0, y0, x1, y1;
x1 = 20; y1 = 70;
for (int i = 1; i <= 20; i++){
x0 = x1; y0 = y1;
x1 = 20+(int)(i/20.0*200);
y1 = 70-(int)(50.0*Math.sin(i/20.0*2*Math.PI));
g.drawLine(x0, y0, x1, y1);
}

// cosƒJ[ƒu‚ð•`‚­(20->220)
g.setColor( Color.red);
x1 = 20; y1 = 20;
for (int i = 1; i <= 20; i++){
x0 = x1; y0 = y1;
x1 = 20+(int)(i/20.0*200);
y1 = 70-(int)(50.0*Math.cos(i/20.0*2*Math.PI));
g.drawLine(x0, y0, x1, y1);
}

//sin + cos‚ð•`‚­
g.setColor(Color.green);
x1 = 20; y1 = 20;
for(int i = 1;i <= 20;i++){
	x0 = x1; y0 = y1;
	x1 = 20+(int)(i/20.0*200);
	y1 = 70-(
	(int)(50.0*Math.cos(i/20.0*2*Math.PI))+
	(int)(50.0*Math.sin(i/20.0*2*Math.PI)));
	g.drawLine(x0, y0, x1, y1);
}
}
}

