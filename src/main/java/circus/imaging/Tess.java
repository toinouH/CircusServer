package circus.imaging;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.awt.image.BufferedImage;

public class Tess
{
    private Tesseract tesseract = new Tesseract();
    // This is entirely system dependant. This is Tesseract's default installation path on Windows x64
    // TODO: Add configuration file to intake user's Tesseract path.
    private static final String TESS_DATA = "C:\\Program Files\\Tesseract-OCR\\tessdata";

    public Tess()
    {
        this.tesseract.setDatapath(this.TESS_DATA);
        // There's potential that this value is not optimal for all situations.
        // So far, it's proved to be a non-issue, though.
        this.tesseract.setTessVariable("user_defined_dpi", "70");
        // Setting Tesseract to treat each ocr pass as single-word, improves accuracy heaps in our case.
        this.tesseract.setTessVariable("psm", "8");
    }

    public String readBufferedImage(BufferedImage input) throws TesseractException {
        return tesseract.doOCR(input);
    }
}
