package kaz.post.crmserver.exportData;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import kaz.post.crmserver.dto.ReportOKTDto;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportData {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<ReportOKTDto> listReportKTODto;

    public ExportData(List<ReportOKTDto> listReportKTODto) {
        this.listReportKTODto = listReportKTODto;
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
        createCell(row, 1, "Наименование услуги", style);
        createCell(row, 2, "Январь", style);
        createCell(row, 3, "Февраль", style);
        createCell(row, 4, "Март", style);
        createCell(row, 5, "Апрель", style);
        createCell(row, 6, "Май", style);
        createCell(row, 7, "Июнь", style);
        createCell(row, 8, "Июль", style);
        createCell(row, 9, "Август", style);
        createCell(row, 10, "Сентябрь", style);
        createCell(row, 11, "Октябрь", style);
        createCell(row, 12, "Ноябрь", style);
        createCell(row, 13, "Декабрь", style);

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
        for (ReportOKTDto reportOKTDto : listReportKTODto) {
            rowCount ++;
            Row row = sheet.createRow(rowCount);
            int columnCount = 0;

            createCell(row, columnCount++, rowCount, style);
            createCell(row, columnCount++, reportOKTDto.getName(), style);
            for (int i=0; i<12; i++) {
                createCell(row, i+2, reportOKTDto.getMonthCount().size() > 0 ? reportOKTDto.getMonthCount().get(i) : 0, style);
            }

        }
        rowCount ++;
        Row row = sheet.createRow(rowCount);
        int columnCount = 0;
        createCell(row, columnCount++, null, style);
        createCell(row, columnCount++, "Итог:", style);
        for (int i=0; i<12; i++) {
            int sum = 0;
            for (ReportOKTDto reportOKTDto : listReportKTODto){
                sum+=(reportOKTDto.getMonthCount().size() > 0 ? reportOKTDto.getMonthCount().get(i) : 0);
            }
            createCell(row, columnCount++, sum, style);
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
