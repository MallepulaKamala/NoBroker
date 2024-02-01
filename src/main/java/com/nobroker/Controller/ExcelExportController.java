package com.nobroker.Controller;

import com.nobroker.Service.ExcelExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/Excel")
public class ExcelExportController {

    @Autowired
    private ExcelExportService excelExportService;

    @GetMapping("/Export")
    public ResponseEntity<byte[]> downloadUsersExcel(HttpServletResponse response) throws IOException {
        byte[] excelBytes = excelExportService.generateExcel();

        // Prepare HTTP response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", "users.xlsx");
        headers.setContentLength(excelBytes.length);

        // Return the response with Excel file and success message in the body
        return ResponseEntity.ok()
                .headers(headers)
                .body(excelBytes);
    }
}
