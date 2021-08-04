package com.lisir.web.util;

import com.alibaba.fastjson.util.TypeUtils;
import com.lisir.web.annotation.EnableExport;
import com.lisir.web.annotation.EnableExportField;
import com.lisir.web.annotation.ImportIndex;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author lxp
 * @Date 2021/8/4 12:01
 * @Version 1.0
 */
public class ExcelUtils {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtils.class);

    public static List<Object> parseExcelToEntityList(InputStream inputStream, Class clazz) {
        try (
                // 创建输入流，读取Excel
                Workbook workbook = WorkbookFactory.create(inputStream)) {
            return parse(workbook, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    public static List<Object> parseExcelToEntityList(File file, Class clazz) {
        try (
                // 创建输入流，读取Excel
                Workbook workbook = WorkbookFactory.create(file);) {
            return parse(workbook, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return new ArrayList<>();
    }

    private static List<Object> parse(Workbook workbook, Class clazz) {
        List<Object> list = new ArrayList<>();
        try {
            Sheet sheet = workbook.getSheetAt(0);
            if (sheet == null) {
                return list;
            }
            int i = 1;
            String[] values;
            Row row = sheet.getRow(i);
            while (row != null) {
                int cellNum = sheet.getPhysicalNumberOfRows();
                values = new String[cellNum];
                for (int j = 0; j < cellNum; j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        // 设置单元格格式
                        cell.setCellType(CellType.STRING);
                        // 获取单元格的值
                        String value = cell.getStringCellValue();
                        values[j] = value;
                    }
                }
                Field[] fields = clazz.getDeclaredFields();
                Object o = clazz.newInstance();
                for (Field field : fields) {
                    if (field.isAnnotationPresent(ImportIndex.class)) {
                        ImportIndex annotation = field.getDeclaredAnnotation(ImportIndex.class);
                        int index = annotation.index();
                        field.setAccessible(true);
                        Object castValue = TypeUtils.cast(values[index], field.getType(), null);
                        field.set(o, castValue);
                    }
                }
                list.add(o);
                i++;
                row = sheet.getRow(i);
            }
        } catch (IllegalAccessException | InstantiationException e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    public static void export(List<Object> data, Class clazz,HttpServletResponse response) {
        // 创建一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建一个sheet
        HSSFSheet sheet = workbook.createSheet();
        sheet.setDefaultRowHeight((short) 400);
        if (clazz.isAnnotationPresent(EnableExport.class)) {
            EnableExport export = (EnableExport) clazz.getDeclaredAnnotation(EnableExport.class);
            List<String> colNames = new ArrayList<>();
            List<Field> fields = new ArrayList<>();
            // 查询所有需要导出的字段
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(EnableExportField.class)) {
                    EnableExportField exportField = field.getDeclaredAnnotation(EnableExportField.class);
                    colNames.add(exportField.colName());
                    fields.add(field);
                }
            }
            // 设置列宽
            for (int i = 0; i < fields.size(); i++) {
                Field field = fields.get(i);
                sheet.setColumnWidth(i, field.getDeclaredAnnotation(EnableExportField.class).colWidth() * 20);
            }
            // 创建标题行
            createHeadRow(workbook, sheet, colNames);
            // 填充数据
            HSSFCellStyle basicCellStyle = getBasicCellStyle(workbook);
            for (int i = 0; i < data.size(); i++) {
                HSSFRow row = sheet.createRow(i + 1);
                Object o = data.get(i);
                for (int j = 0; j < fields.size(); j++) {
                    Field field = fields.get(j);
                    field.setAccessible(true);
                    Object value = null;
                    try {
                        value = field.get(o);
                    } catch (IllegalAccessException e) {
                        logger.error(e.getMessage(), e);
                    }
                    HSSFCell cell = row.createCell(j);
                    cell.setCellStyle(basicCellStyle);
                    cell.setCellValue(String.valueOf(value));
                }
            }
            response.setHeader("Content-disposition", "attachment; filename=" + export.fileName());
            response.setContentType("application/vnd.ms-excel");
            try(ServletOutputStream outputStream = response.getOutputStream()) {

                workbook.write(outputStream);
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }finally {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    private static void createHeadRow(HSSFWorkbook workbook, HSSFSheet hssfSheet, List<String> colNames) {
        //插入标题行
        HSSFRow hssfRow = hssfSheet.createRow(0);
        for (int i = 0; i < colNames.size(); i++) {
            HSSFCell hssfcell = hssfRow.createCell(i);
            hssfcell.setCellStyle(getTitleCellStyle(workbook));
            hssfcell.setCellType(CellType.STRING);
            hssfcell.setCellValue(colNames.get(i));
        }
    }

    private static HSSFCellStyle getTitleCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = getBasicCellStyle(workbook);
        hssfcellstyle.setFillForegroundColor((short) HSSFColor.CORNFLOWER_BLUE.index); // 设置背景色
        hssfcellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND.getCode());
        return hssfcellstyle;
    }

    private static HSSFCellStyle getBasicCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle hssfcellstyle = workbook.createCellStyle();
        hssfcellstyle.setBorderLeft(BorderStyle.THIN);
        hssfcellstyle.setBorderBottom(BorderStyle.THIN);
        hssfcellstyle.setBorderRight(BorderStyle.THIN);
        hssfcellstyle.setBorderTop(BorderStyle.THIN);
        hssfcellstyle.setAlignment(HorizontalAlignment.CENTER.getCode());
        hssfcellstyle.setVerticalAlignment(VerticalAlignment.CENTER.getCode());
        hssfcellstyle.setWrapText(true);
        return hssfcellstyle;
    }
}
