package circus.imaging;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class Tess {

    private Tesseract tesseract = new Tesseract();
    // This is entirely system dependant. This is Tesseract's default installation path on Windows x64
    private static final String TESS_DATA = "C:\\Program Files\\Tesseract-OCR\\tessdata";

    public Tess() {
        this.tesseract.setDatapath(this.TESS_DATA);
        this.tesseract.setTessVariable("user_defined_dpi", "70");
    }

    public String readBufferedImage(BufferedImage input) throws TesseractException {
        return tesseract.doOCR(input);
    }
}
