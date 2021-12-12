package com.rankedcircus.imaging;

import com.rankedcircus.Config;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;

// Singleton
public class Tess
{
    private static Tess tess;
    private static final Tesseract tesseract;

    static
    {
        tess = new Tess();
        tesseract = new Tesseract();
        tesseract.setDatapath(Config.getConfig().read("circus.tesseract_path"));
        tesseract.setTessVariable("user_defined_dpi", "70");
        tesseract.setTessVariable("user_defined_dpi", "70");
    }

    public static Tess getInstance()
    {
        return tess;
    }

    public void setTesseractVar(String key, String value)
    {
        tesseract.setTessVariable(key, value);
    }

    public String readSingleLine(BufferedImage input)
    {
        Tess.getInstance().setTesseractVar("psm", "3");
        String ret = this.readBufferedImage(input);
        Tess.getInstance().setTesseractVar("psm", "8");
        return ret;
    }

    public String readBufferedImage(BufferedImage input)
    {
        try {
            return tesseract.doOCR(input);
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}
