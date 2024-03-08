package com.project.login.util;

import com.project.login.entity.UserLogin;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelUtil {
    public static String HEADER[] = {"id","name","password","email","attempts","status"};
    public static String SHEET_NAME = "userData";

    public static ByteArrayInputStream dataToExcel(List<UserLogin> userList) throws IOException {

        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        Row row = sheet.createRow(0);
        for (int i =0; i < HEADER.length ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(HEADER[i]);
        }
        int rowIndex = 1;
        for(UserLogin user : userList) {
            Row row1 = sheet.createRow(rowIndex);
            rowIndex++;
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getName());
            row1.createCell(2).setCellValue(user.getPassword());
            row1.createCell(3).setCellValue(user.getEmail());
            row1.createCell(4).setCellValue(user.getNo_of_attempts());
            row1.createCell(5).setCellValue(user.isActive());
        }
       try {
           workbook.write(byteArrayOutputStream);
           return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
       }catch (IOException ex) {
           throw new RuntimeException();
       }
       finally {
           workbook.close();
           byteArrayOutputStream.close();
       }

    }
}
