import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Dipper
 * @project pdf_imagen
 * @created 16/11/2020 - 12:55
 */
public class ImagenFromPDF {
    private void generateImageFromPDF(String filename, String extension) throws IOException {
        PDDocument pdf = PDDocument.load(new File(filename));
        PDFRenderer pdfRenderer = new PDFRenderer(pdf);
        for (int page = 0; page < pdf.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, String.format("src/output/pokemon-%d.%s", page + 1, extension), 300);
        }
        pdf.close();
    }

    private void generateImageFromPDFArray(String filename, String extension, Integer pags[]) throws IOException {
        PDDocument pdf2 = PDDocument.load(new File(filename));
        PDFRenderer pdfRenderer = new PDFRenderer(pdf2);
        for (int page = 0; page < pags.length; ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(pags[page], 300, ImageType.RGB);
            ImageIOUtil.writeImage(bim, String.format("src/output/img-%d.%s", page + 1, extension), 300);
        }
        pdf2.close();
    }

    public static void main(String[] args) throws IOException {
        Integer pags[] = {0, 2};
        ImagenFromPDF imagenFromPDF = new ImagenFromPDF();
//        imagenFromPDF.generateImageFromPDF("src\\main\\resources\\pdf_entrada\\pokemones.pdf", "jpg");
        imagenFromPDF.generateImageFromPDFArray("src\\main\\resources\\pdf_entrada\\pokemones.pdf", "jpg", pags);
    }
}
