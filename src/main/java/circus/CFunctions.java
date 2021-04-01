package circus;

public class CFunctions {

    static {
        System.loadLibrary("ScreenLockBoner");
    }

    private native void injectMouseMovement(int x, int y);

    public static void clickPos(int x, int y) {
        new CFunctions().injectMouseMovement(x, y);
    }
}
