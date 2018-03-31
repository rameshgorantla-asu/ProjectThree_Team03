package client;


import java.util.Random;

public class getVectors {
    public double p[] = new double[16];
	
	public getVectors(double p1, double p2, double p3, double p4, double p5, double p6, double p7, double p8, double p9, double p10) {
        p[1] = p1; p[2] = p2; p[3] = p3; p[4] = p4; p[5] = p5;
        p[6] = p6; p[7] = p7; p[8] = p8; p[9] = p9; p[10] = p10;
    }
    
    public getVectors() {
        int i;
        Random r = new Random();	
        for (i = 1; i < 16; i++) {
            p[i] = r.nextDouble();
            System.out.println(p[i]);
        }
        p[12] = 1;
    }
    
//    public double distance(getVectors v) {
//        int i;
//        double sum = 0.0;
//        double diff;
//	
//        for (i = 1; i < 11; i++) {
//            diff = p[i] - v.p[i];
//            sum = sum + diff * diff;
//        }
//        return Math.sqrt(sum);
//    }
}
