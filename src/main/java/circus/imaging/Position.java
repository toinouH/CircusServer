package circus.imaging;

public class Position {

    /**
     * Positions are used to store screen region coordinates for where image capturing.
     * Currently Positions also hold "resizeW" and "resizeH". I don't know think I want this to be the case
     * permanently, but c'est la vie.
     *
     * resizeW and resizeH are used when preprocessing the captured image to get accurate ocr.
     * Since each Position object is declared with the intent of capturing a specific menu element,
     * the width and height values when resizing differ depending on the element.
     */

    int y;
    int x;
    int width;
    int height;
    int resizeW;
    int resizeH;

    public Position(int x, int y, int width, int height, int resizeW, int resizeH) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.resizeW = resizeW;
        this.resizeH = resizeH;
    }

    public int getX() { return this.x; }
    public int getY() { return this.y; }
    public int getWidth() { return this.width; }
    public int getHeight() { return this.height; }
    public int getResizeW() { return this.resizeW; }
    public int getResizeH() { return this.resizeH; }
}
