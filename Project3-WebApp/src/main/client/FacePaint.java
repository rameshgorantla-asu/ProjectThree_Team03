package client;

import java.applet.Applet;
import java.awt.*;

public class FacePaint extends Applet {
	Graphics offscreenContext;
    Image offscreenImage;
    Color foregroundcolor = Color.black;
    Color backgroundcolor = Color.white;
	
	public int head_radiusval = 30;
	public int eye_radiusval = 5;
	public int eye_left_xpos = 40;
	public int eye_right_xpos = 60;
	public int eye_ypos = 40;
	public int eyebrow_left_left_x = 35;
	public int eyebrow_left_right_x = 45;
	public int eyebrow_right_left_x = 55;
	public int eyebrow_right_right_x = 65;
	public int eyebrow_y = 30;
	public double pupil_radiusval = 0.2;
	public int mouth_ypos = 65;
	public int nose_apex_x = 50;
	public int nose_apex_y = 45;
	public int nose_height = 16;
	public int nose_width = 8;
	private double x_factor, y_factor;
	private int x_origin, y_origin;
	
	getVectors v = new getVectors();
	
	public void drawFace(Graphics g, getVectors v, int x, int y, int height, int width){
		String direction = "Center";
		int blinkl = 0;
		int blinkr = 0;
		if(v.p[11] == 1){ 
			blinkl = 1;
			blinkr = 1;
		}
		else if(v.p[12] == 1){ 
			blinkl = 1;
		}
		else if(v.p[13] == 1){ 
			blinkr = 1;
		}
		else if(v.p[14] == 1){
			direction = "Left";
		}
		else if(v.p[15] == 1){
			direction = "Right";
		}
		calc_scaleFactors(x, y, height, width);
		make_head(g);
		make_eye(g, blinkl, blinkr);
		make_pupil(g, direction, blinkl, blinkr);
		make_eyebrows(g, v.p[1], v.p[2]);
		make_nose(g, v.p[5]);
		make_mouth(g, v.p[6], v.p[9], v.p[10]);
	}
	
	public void calc_scaleFactors(int x, int y, int height, int width){
		x_factor = width / 100.0;
		y_factor = height / 100.0;
		x_origin = x;
		y_origin = y;
	}
	
	public void make_head(Graphics g){
		createCircle(g, 50, 50, head_radiusval);
	}
	
	public void make_eye(Graphics g, int bl, int br){
		if(bl == 1 && br == 1){
			createLine(g, eye_left_xpos + eye_radiusval, eye_ypos, eye_left_xpos - eye_radiusval, eye_ypos);
			createLine(g, eye_right_xpos - eye_radiusval, eye_ypos, eye_right_xpos + eye_radiusval, eye_ypos);
		}
		else if(bl == 1){
			createLine(g, eye_left_xpos + eye_radiusval, eye_ypos, eye_left_xpos - eye_radiusval, eye_ypos);
			createOval(g, eye_right_xpos, eye_ypos, eye_radiusval, eye_radiusval);
		}
		else if(br == 1){
			createOval(g, eye_left_xpos, eye_ypos, eye_radiusval, eye_radiusval);
			createLine(g, eye_right_xpos - eye_radiusval, eye_ypos, eye_right_xpos + eye_radiusval, eye_ypos);
		}
		else{
			createOval(g, eye_left_xpos, eye_ypos, eye_radiusval, eye_radiusval);
			createOval(g, eye_right_xpos, eye_ypos, eye_radiusval, eye_radiusval);
		}
	}
	
	public void make_pupil(Graphics g, String direction, int bl, int br){
		double d = 0.5;
		int size = 2;
		if(direction.equals("Right")){
			d = 0.3;
			fillOval(g, eye_left_xpos + (int)((d - 0.5) * 10), eye_ypos, size, size);
			fillOval(g, eye_right_xpos + (int)((d - 0.5) * 10), eye_ypos, size, size);
		}
		else if(direction.equals("Left")){
			d = 0.3;
			fillOval(g, eye_left_xpos - (int)((d - 0.5) * 10), eye_ypos, size, size);
			fillOval(g, eye_right_xpos - (int)((d - 0.5) * 10), eye_ypos, size, size);
		}
		else{
			if(bl == 1){
				fillOval(g, eye_right_xpos + (int)((d - 0.5) * 10), eye_ypos, size, size);
			}
			else if(br == 1){
				fillOval(g, eye_left_xpos - (int)((d - 0.5) * 10), eye_ypos, size, size);
			}
			else if(bl == 0 && br == 0){
				fillOval(g, eye_left_xpos - (int)((d - 0.5) * 10), eye_ypos, size, size);
				fillOval(g, eye_right_xpos + (int)((d - 0.5) * 10), eye_ypos, size, size);
			}
		}
	}
	
	public void make_eyebrows(Graphics g, double p1, double p2){
		p1 = 0;
		int y1, y2;
		if(p1 != 0 ){
			y1 = eyebrow_y + (int)(p1 * 10);
			y2 = eyebrow_y - (int)(p1 * 10);
		}
		else if(p2 != 0){
			y1 = eyebrow_y - (int)(p2 * 10);
			y2 = eyebrow_y + (int)(p2 * 10);
		}
		else{
			y1 = eyebrow_y;
			y2 = eyebrow_y;
		}
		createLine(g, eyebrow_left_left_x, y1, eyebrow_left_right_x, y2);
		createLine(g, eyebrow_right_left_x, y2, eyebrow_right_right_x, y1);
	}
	
	public void make_nose(Graphics g, double p5){
		int y = 55 + (int)(((p5 - 0.5) / 2.0) * 10);
		createLine(g, nose_apex_x, nose_apex_y, nose_apex_x - (nose_width / 2), y);
		createLine(g, nose_apex_x - (nose_width / 2), y, nose_apex_x + (nose_width / 2), y);
		createLine(g, nose_apex_x + (nose_width / 2), y, nose_apex_x, nose_apex_y);
	}
	
	public void make_mouth(Graphics g, double p6, double p9, double p10){
		double size = ((p9 - 0.5) * 10);
		double x1 = 40 - size;
		double y1 = mouth_ypos;
		double x2 = 60 + size;
		double y2 = mouth_ypos;
		double x3 = ((x2 - x1) / 2) + x1;
		double y3 = ((p6 - 0.5) * 10) + mouth_ypos;

		make_lips(g, x1, y1, x2, y2, x3, y3);
		make_lips(g, x1, y1, x2, y2, x3, y3 + ((p10 / 2.0) * 10));
	}
	
	public void make_lips(Graphics g, double x1, double y1, double x2, double y2, double  x3, double y3){
		int i, new_x, new_y, last_x, last_y;
		double denom = (Math.pow(x1, 2) * (x2 - x3)) + (x1 * (Math.pow(x3, 2) - Math.pow(x2, 2))) 
						+ (Math.pow(x2, 2) * x3) + -(Math.pow(x3, 2) * x2);
					    
		double a = ((y1 * (x2 - x3)) + (x1 * (y3 - y2)) + (y2 * x3) + -(y3 * x2)) / denom;
					   
		double bb = ((Math.pow(x1, 2) * (y2 - y3)) +  (y1 * (Math.pow(x3, 2) - Math.pow(x2, 2))) 
					+ (Math.pow(x2, 2) * y3) + -(Math.pow(x3, 2) * y2)) / denom;
					 
		double c = ((Math.pow(x1, 2) * ((x2 * y3) - (x3 * y2))) +  (x1 * ((Math.pow(x3, 2) * y2) 
					- (Math.pow(x2, 2) * y3))) + (y1 * ((Math.pow(x2, 2) * x3) 
					- (Math.pow(x3, 2) * x2)))) / denom;
		
		for(i = (int)x1, last_x = (int)x1, last_y = (int)y1; i <= x2; i++) {
			new_x = i;
			new_y = (int)((a * Math.pow(i, 2)) + (bb * i) + c);
			createLine(g, last_x, last_y, new_x, new_y);
			last_x = new_x;
			last_y = new_y;
		}
	}
	
	public void createCircle(Graphics g, int x, int y, int radius){
		g.drawOval(scale_x(x - radius) + x_origin, scale_y(y - radius) + y_origin,
		           scale_x(radius * 2), scale_y(radius * 2));
	}
	public void createOval(Graphics g, int x, int y, int height, int width){
		g.drawOval(scale_x(x - width) + x_origin, scale_y(y - height) + y_origin,
				   scale_x(width * 2), scale_y(height * 2));
	}
	
	public void fillOval(Graphics g, int x, int y, int height, int width){
		g.fillOval(scale_x(x - width) + x_origin, scale_y(y - height) + y_origin,
				   scale_x(width * 2), scale_y(height * 2) );
	}
	
	public void createLine(Graphics g, int x1, int y1, int x2, int y2){
		g.drawLine(scale_x(x1) + x_origin, scale_y(y1) + x_origin,
				 scale_x(x2) + x_origin, scale_y(y2) + x_origin);
	}
	public int scale_x(int x) {
		return (int)(x * x_factor);
	}

	public int scale_y(int y) {
		return (int)(y * y_factor);
	}
	
	public void init(){
		offscreenImage = this.createImage(getSize().width, getSize().height);
        offscreenContext = offscreenImage.getGraphics();
        backgroundcolor = getBackground();
	}
	
	public void paint(Graphics g){
		drawFace(g, v, 0, 0, getSize().height, getSize().width);
	}
	
}
