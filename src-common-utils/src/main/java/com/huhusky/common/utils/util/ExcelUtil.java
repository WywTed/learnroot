package com.huhusky.common.utils.util;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huhusky.common.utils.util.dto.ExcelData;
import com.huhusky.common.utils.util.dto.SheetData;

public class ExcelUtil {

    private Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * excel导出到输出流，谁调用谁负责关闭输出流
     *
     * @param os           输出流
     * @param excelExtName excel文件的扩展名，支持xls和xlsx，不带点号
     * @param data         Map[sheetName,rowList[cellList[cellValue]]]
     * @throws IOException
     */
    public static void writeExcel(OutputStream os, String excelExtName, Map<String, List<List<String>>> data)
            throws IOException {
        Workbook wb = null;
        try {
            if ("xls".equals(excelExtName)) {
                wb = new HSSFWorkbook();
            } else if ("xlsx".equals(excelExtName)) {
                wb = new XSSFWorkbook();
            } else {
                throw new Exception("当前文件不是excel文件");
            }

            for (String sheetName : data.keySet()) {
                Sheet sheet = wb.createSheet(sheetName);
                List<List<String>> rowList = data.get(sheetName);
                for (int i = 0; i < rowList.size(); i++) {
                    List<String> cellList = rowList.get(i);
                    Row row = sheet.createRow(i);
                    for (int j = 0; j < cellList.size(); j++) {
                        Cell cell = row.createCell(j);
                        cell.setCellValue(cellList.get(j));
                    }
                }
            }
            wb.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }

    public static void writeExcel(ExcelData data) throws IOException {

        Workbook wb = null;
        try {
            if ("xls".equals(data.getExtName())) {
                wb = new HSSFWorkbook();

            } else if ("xlsx".equals(data.getExtName())) {
                wb = new XSSFWorkbook();
            } else {
                throw new RuntimeException("当前文件不是excel文件");
            }
            if (data.getSheets() == null || data.getSheets().isEmpty()) {
                throw new RuntimeException("没有可写入内容");
            }
            CellStyle style = buildCellStyle(wb);
            for (SheetData sd : data.getSheets()) {
                Sheet sheet = wb.createSheet(sd.getSheetName());
                int rowStart = 0;
                if (!StringUtils.isBlank(sd.getHead())) {
                    writeHead(sd.getHead(), sd.getColumnCount(), sheet, style);
                    rowStart = 1;
                }
                List<List<String>> rowList = sd.getContent();
                writeRows(rowStart, rowList, sheet, style);
            }
            wb.write(data.getOs());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
    }

    private static CellStyle buildCellStyle(Workbook wb) {
        CellStyle cs = wb.createCellStyle();
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setAlignment(HorizontalAlignment.CENTER);
        cs.setBorderBottom(BorderStyle.THIN);
        cs.setBorderTop(BorderStyle.THIN);
        cs.setBorderRight(BorderStyle.THIN);
        cs.setBorderLeft(BorderStyle.THIN);
        return cs;
    }

    private static void writeHead(String head, int colspan, Sheet sheet, CellStyle style) {
        CellRangeAddress cra = new CellRangeAddress(0, 0, 0, colspan - 1);
        sheet.addMergedRegion(cra);
        Row headerRow = sheet.createRow(0);
        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellStyle(style);
        headerCell.setCellValue(head);
    }

    private static void writeRows(int startRow, List<List<String>> rowList, Sheet sheet, CellStyle style) {
        for (int i = 0; i < rowList.size(); i++) {
            List<String> cellList = rowList.get(i);
            Row row = sheet.createRow(i + startRow);
            for (int j = 0; j < cellList.size(); j++) {
                Cell cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellValue(cellList.get(j));
            }
        }
    }


    public List<List<String>> readExcel(String filePath, String excelExt) {
        File f = new File(filePath);
        if (f == null) {
            log.warn("不存在文件： " + filePath);
            return null;
        }
        return null;
    }

    public static void writeResponseHeader(HttpServletResponse response, String fileName) {
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        response.setHeader("charset", "utf-8");
    }

    public static String buildExcelFileName(String fileName) throws UnsupportedEncodingException {
        return buildExcelFileName(fileName, "YYYYMMddHHmmss", ExcelData.ms_xlsx);
    }

    public static String buildExcelFileName(String fileName, String datePattern, String extName) throws UnsupportedEncodingException {
        Long t = System.currentTimeMillis();
        String d = new SimpleDateFormat(datePattern).format(t);
        return fileName + "_" + d + "." + extName;
    }

}
