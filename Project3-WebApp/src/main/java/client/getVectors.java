package client;
import java.util.Random;

/**
 * Class to transfer the received expressions into an array. 
 * @SER516 Project3_Team03
 * @version 1.0
 */
public class getVectors {
    public double p[] = new double[13];
    
    /**
     * Puts all the received expression inputs into an array
     * @param p1 contains input to raise brow
     * @param p2 contains input to furrow brow
     * @param p3 contains input to smile
     * @param p4 contains input to clench
     * @param p5 contains input to smirk left
     * @param p6 contains input to smirk right
     * @param p7 contains input to laugh
     * @param p8 contains input to blink the eyes
     * @param p9 contains input to wink left eye
     * @param p10 contains input to wink right eye
     * @param p11 contains input to look left
     * @param p12 contains input to look right
     */
    public getVectors(double p1, double p2, double p3, double p4, double p5, double p6, double p7, double p8, double p9, double p10, double p11, double p12) {
        p[1] = p1; p[2] = p2; p[3] = p3; p[4] = p4; p[5] = p5;
        p[6] = p6; p[7] = p7; p[8] = p8; p[9] = p9; p[10] = p10;
        p[11] = p11; p[12] = p12;
    }
    
    public getVectors() {
        int i;
        Random r = new Random();    
        for (i = 1; i < 13; i++) {
            p[i] = r.nextDouble();
        }
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
