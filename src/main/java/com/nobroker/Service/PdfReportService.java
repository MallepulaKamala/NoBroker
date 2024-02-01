package com.nobroker.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.nobroker.Entity.User;
import com.nobroker.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PdfReportService {

    @Autowired
    private UserRepository userRepository;

    public byte[] generatePdfReport() throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                document.add(new Paragraph("No users found."));
            } else {
                for (User user : users) {
                    document.add(new Paragraph("User ID: " + user.getId()));
                    document.add(new Paragraph("Name: " + user.getName()));
                    document.add(new Paragraph("Email: " + user.getEmail()));
                    document.add(new Paragraph("Password: " + user.getPassword()));
                    document.add(new Paragraph("Mobile: " + user.getMobile()));
                    document.add(new Paragraph("Email Verified: " + user.isEmailVerified()));
                    document.add(new Paragraph("------------------------------------------"));
                }
            }
        } finally {
            if (document != null) {
                document.close();
            }
        }

        return outputStream.toByteArray();
    }
}
