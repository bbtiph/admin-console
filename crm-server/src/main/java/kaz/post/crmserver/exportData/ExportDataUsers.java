package kaz.post.crmserver.exportData;

import kaz.post.crmserver.dto.ReportOKTDto;
import kaz.post.crmserver.dto.UserDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ExportDataUsers {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<UserDTO> listUserDto;

    public ExportDataUsers(List<UserDTO> listUserDto) {
        this.listUserDto = listUserDto;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("отчёт");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "№", style);
        createCell(row, 1, "Логин", style);
        createCell(row, 2, "Номер", style);
        createCell(row, 3, "ИИН", style);
        createCell(row, 4, "Организация", style);
        createCell(row, 5, "Имя", style);
        createCell(row, 6, "Фамиля", style);
        createCell(row, 7, "Отчества", style);
        createCell(row, 8, "Дата рождение", style);
        createCell(row, 9, "Дата регистрация", style);
        createCell(row, 10, "Язык", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        int rowCount = 0;
        for (UserDTO userDTO : listUserDto) {
            rowCount ++;
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;

            try {
                createCell(row, columnCount++, rowCount, style);
                createCell(row, columnCount++, userDTO.getLogin() != null ? userDTO.getLogin() : "", style);
                createCell(row, columnCount++, userDTO.getMobileNumber() != null ? userDTO.getMobileNumber() : "", style);
                createCell(row, columnCount++, userDTO.getIin() != null ? userDTO.getIin() : "", style);
                createCell(row, columnCount++, userDTO.getOrganizations() != null ? userDTO.getOrganizations() : "", style);
                createCell(row, columnCount++, userDTO.getFirstName() != null ? userDTO.getFirstName() : "", style);
                createCell(row, columnCount++, userDTO.getLastName() != null ? userDTO.getLastName() : "", style);
                createCell(row, columnCount++, userDTO.getMiddleName() != null ? userDTO.getMiddleName() : "", style);
                createCell(row, columnCount++, userDTO.getBirthDate() != null ? userDTO.getBirthDate().toString() : "", style);
                createCell(row, columnCount++, userDTO.getCreatedDate() != null ? userDTO.getCreatedDate().toString() : "", style);
                createCell(row, columnCount++, userDTO.getLangKey() != null ? userDTO.getLangKey() : "", style);
            } catch (Exception e) {
            }


        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        response.flushBuffer();
        outputStream.close();

    }
}
