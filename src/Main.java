import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by compu on 25/06/2018.
 */
public class Main {

        public static final String SRC = "templates/W1.pdf";
        public static final String DEST = "results/watermarked.pdf";

    public static void main(String[] args) throws IOException, DocumentException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        manipulatePdf(SRC, DEST);
    }

    public static void manipulatePdf(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
        PdfContentByte over = stamper.getOverContent(1);
        over.beginText();

        over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL);
        over.setFontAndSize(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.EMBEDDED), 6);

        for (int i = 25; i < 2000; i += 25){
            for (int k = 25; k < 1500; k += 25) {
                over.showTextAligned(PdfContentByte.ALIGN_LEFT, "_" + k + "_", i, k, 0);
            }
        }
        for (int i = 25; i < 2000; i += 25){
            for (int k = 25; k < 1500; k += 25) {
                over.showTextAligned(PdfContentByte.ALIGN_LEFT, "_" + i + "_", i, k, 90);
            }
        }

        over.endText();
        stamper.close();
        reader.close();

    }
}
