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
		calc_scaleFactors(x, y, height, width);
		make_head(g, v.p[1]);
		make_eye(g, v.p[2], v.p[7], v.p[8]);
		make_pupil(g, v.p[3], v.p[7]);
		make_eyebrows(g, v.p[4]);
		make_nose(g, v.p[5]);
		make_mouth(g, v.p[6], v.p[9], v.p[10]);
	}
	
	public void calc_scaleFactors(int x, int y, int height, int width){
		x_factor = width / 100.0;
		y_factor = height / 100.0;
		x_origin = x;
		y_origin = y;
	}
	
	public void make_head(Graphics g, double p){
		createCircle(g, 50, 50, head_radiusval);
	}
	
	public void make_eye(Graphics g, double p2, double p7, double p8){
		int spacing = (int)((p7 - 0.5) * 10);
		int size = (int)(((p8 - 0.5) / 2.0) * 10);
		
		createOval(g, eye_left_xpos - spacing, eye_ypos, eye_radiusval + size, eye_radiusval + size);
		createOval(g, eye_right_xpos + spacing, eye_ypos, eye_radiusval + size, eye_radiusval + size);

	}
	
	public void make_pupil(Graphics g, double p3, double p7){
		int size = (int)(Math.max(1, p3 * 0.2) * 2);
		fillOval(g, eye_left_xpos - (int)((p7 - 0.5) * 10), eye_ypos, size, size);
		fillOval(g, eye_right_xpos + (int)((p7 - 0.5) * 10), eye_ypos, size, size);
	}
	
	public void make_eyebrows(Graphics g, double p4){
		int y1 = eyebrow_y + (int)((p4 - 0.5) * 10);
		int y2 = eyebrow_y - (int)((p4 - 0.5) * 10);
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
