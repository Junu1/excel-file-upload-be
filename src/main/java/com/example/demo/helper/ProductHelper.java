package com.example.demo.helper;

import com.example.demo.model.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductHelper {
    public static boolean checkExcelFormat(MultipartFile multipartFile) {

        String contentType = multipartFile.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            return true;
        } else {
            return false;
        }

    }

    public static List<Product> convertExcelToListOfProduct(InputStream is) {
        List<Product> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;

                Product product = new Product();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            product.setId((long) cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setBrand(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setPrice(cell.getNumericCellValue());
                            break;
                        case 4:
                            product.setImagePath(cell.getStringCellValue());
                            break;
                        case 5:
                            product.setCategoryName(cell.getStringCellValue());
                            break;
                        default:
                            break;

                    }
                    cid++;
                }
                list.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }
}
