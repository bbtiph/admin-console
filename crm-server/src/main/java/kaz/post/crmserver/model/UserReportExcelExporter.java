package kaz.post.crmserver.model;

import kaz.post.crmserver.entity.UserEntity;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserReportExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;


    private List<UserEntity> userEntityList;

    public UserReportExcelExporter(List<UserEntity> userEntityList) {
        this.userEntityList = userEntityList;
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet("UserEntity");
    }

    private void writeHeaderRow() {
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("user ID");
    }

    private void wrietDataRows() {

    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderRow();
        wrietDataRows();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }


}
