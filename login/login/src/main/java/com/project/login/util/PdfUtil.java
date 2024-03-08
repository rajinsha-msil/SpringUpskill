package com.project.login.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class PdfUtil {
    public void ExportPdf(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document,response.getOutputStream());
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(18);

        Paragraph paragraph = new Paragraph("Example pdf generation" ,font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontparagraph = FontFactory.getFont(FontFactory.COURIER);
        fontparagraph.setSize(14);

        Paragraph paragraph2 = new Paragraph("Example of pdf generator",fontparagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);

        document.add(paragraph);
        document.add(paragraph2);
        document.close();
    }
}
