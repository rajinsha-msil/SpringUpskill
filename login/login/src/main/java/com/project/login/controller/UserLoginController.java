package com.project.login.controller;

import com.project.login.entity.UserLogin;
import com.project.login.exception.UserNotFoundException;
import com.project.login.requests.LoginRequest;
import com.project.login.service.UserLoginServiceImpl;
import com.project.login.util.PdfUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CommonsLog
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private UserLoginServiceImpl service;

    @Autowired
    private  PdfUtil pdfUtil;


    @PostMapping("/register")
    public String RegisterUser(@RequestBody LoginRequest request) {
        log.info("Registration request received for User:" + request.getName());
        return service.RegisterUser(request);
    }

    @PostMapping("/login")
    public String LoginUser(@RequestBody LoginRequest request) throws UserNotFoundException {
        log.info("Login request received for User:" + request.getName());
        return service.LoginUser(request);
    }

    @PostMapping("/forgotPassword")
    public String ForgotPassword(@RequestBody LoginRequest request) {
        log.info("ForgotPassword request received for User:" + request.getName());
        return service.ForgotPassword(request);
    }

    @GetMapping("/unlock/{name}")
    public String Unlock(@PathVariable String name) throws UserNotFoundException {
        log.info("Unlock request received for User:" + name);
        return service.UnlockAccount(name);
    }

    @GetMapping("/getByName/{name}")
    public UserLogin GetDetails(@PathVariable String name) throws UserNotFoundException {
        log.info("GetInfo Request Received:" + name);
        return service.GetUserDetails(name);
    }


    @GetMapping("/list/download")
    public ResponseEntity<InputStreamResource> Download() throws IOException {
        log.info("Download Request Received" );
        String file = "users.xlsx";
        ByteArrayInputStream inputStream = service.DownloadUserData();
        InputStreamResource response = new InputStreamResource(inputStream);

        ResponseEntity<InputStreamResource> responseEnitity  = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename"+file)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(response);
        return responseEnitity;
    }

    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDate = dateFormat.format(new Date());

        String headerkey = HttpHeaders.CONTENT_DISPOSITION;
        String headervalue = "attachment;filename=pdf_" + currentDate + ".pdf";
        httpServletResponse.setHeader(headerkey,headervalue);

        this.pdfUtil.ExportPdf(httpServletResponse);

    }

}
