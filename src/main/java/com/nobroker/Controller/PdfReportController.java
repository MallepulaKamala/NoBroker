package com.nobroker.Controller;

import com.itextpdf.text.DocumentException;
import com.nobroker.Service.PdfReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
public class PdfReportController {

    @Autowired
    private PdfReportService pdfReportService;

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generatePdfReport() {
        byte[] pdfBytes;
        HttpHeaders headers = new HttpHeaders();
        try {
            pdfBytes = pdfReportService.generatePdfReport();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("inline", "user_report.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
            ResponseEntity<byte[]> response = new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            return response;
        } catch (DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
