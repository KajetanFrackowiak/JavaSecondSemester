package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException {
        PDDocument document = new PDDocument();

        for (int i = 0; i < 10; i++) {
            PDPage page = new PDPage();
            PDPageContentStream contentStream = new PDPageContentStream(
                    document,
                    page,
                    PDPageContentStream.AppendMode.APPEND,
                    true
            );
            if (i == 0) {
                contentStream.beginText();
                contentStream.setFont(
                        new PDType1Font(Standard14Fonts.FontName.COURIER),
                        50);
                contentStream.showText("Hello World");
                contentStream.endText();
            }

            if (i == 9) {
                contentStream.beginText();
                contentStream.setFont(
                        new PDType1Font(Standard14Fonts.FontName.COURIER),
                        50);
                contentStream.showText("End World");
                contentStream.endText();
            }
            document.addPage(page);
            contentStream.close();
        }

        document.save("myPDF.pdf");
        document.close();
    }
}