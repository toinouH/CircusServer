package circus;

public class CFunctions {

    static {
        System.loadLibrary("ScreenLockBoner");
    }

    private native void injectMouseMovement(int x, int y);
    private native void injectKeyboardInput(String input);

    public static void clickPos(int x, int y) {
        new CFunctions().injectMouseMovement(x, y);
    }

    public static void keyboardInput(String input) {
        new CFunctions().injectKeyboardInput(input);
    }

}
