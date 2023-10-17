package org.example;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        FileUtils.copyURLToFile(
                new URL("https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"),
                new File("myPdf.pdf")
        );
        PDDocument document = Loader.loadPDF(new File("myPdf.pdf"));
        PDPage page = new PDPage();

        FileUtils.copyURLToFile(
                new URL("https://www.thealexandriazoo.com/images/animals/Capybara02.jpg"),
                new File("myPicture.jpg")
        );

        PDImageXObject image = PDImageXObject.createFromFile(
                "myPicture.jpg",
                document
        );

        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawImage(image, 70, 250, 450, 338);
        contentStream.close();

        document.addPage(page);
        document.save("myPdf.pdf");
        document.close();

    }
}
