package application;

import javafx.scene.shape.Rectangle;

public class MovingObsticle {
	double x1;
	double y1;
	double x;
	double y;
	double xvel;
	double yvel;
	Rectangle object ;
	
	public MovingObsticle(double xsize, double ysize, double x, double y, double xvel, double yvel){
		this.x = x;
		this.y=y;
		x1=x;
		y1=y;
		this.xvel=xvel;
		this.yvel=yvel;
		object = new Rectangle(xsize, ysize);
	}
	public void changeSpeed(double xvel, double yvel){
		this.xvel = xvel;
		this.yvel = yvel;
	}
	public void move(){
		x+=xvel;
		y+=yvel;
		object.setTranslateX(x);
		object.setTranslateY(y);
	}
	public void offScreenX(){
		x=x1;
	}
	public void offScreenY(){
		y=y1;
	}
	

}
