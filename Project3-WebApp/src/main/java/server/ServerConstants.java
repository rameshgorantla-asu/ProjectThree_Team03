package server;

import java.awt.Font;
import utility.Constants;

/**
* ServerConstants holds systemwide values that are to be used
* by the server program.
*/
public class ServerConstants extends Constants {

    public static final Font BLINKINGTEXTFONT = new Font("Courier New", Font.BOLD, 20);
    public static volatile int DEFAULT_MIN = 0;
    public static volatile int DEFAULT_MAX = 100;
    public static volatile int DEFAULT_FREQ = 2;

}
