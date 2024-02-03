package com.qfy.modules.smartform.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 解析excel
 *
 * @author lanls
 * @version 1.0
 * @date 2022/5/8 15:44
 */
public class ExcelUtil {

    /**
     *功能描述
     * @Description 解析Excel文件方法
     **/
    public static List<String[]> parseExcel(InputStream in, String suffix, int startRow) throws IOException {
        Workbook workbook;
        Cell cell;
        List<String[]> cells = new ArrayList<>();
        if("xls".equals(suffix)) {
            workbook = new HSSFWorkbook(in);
        } else if("xlsx".equals(suffix)){
            workbook = new XSSFWorkbook(in);
        } else {
            throw new RuntimeException("未知文件类型");
        }
        Sheet sheet = workbook.getSheetAt(0);
        if(sheet == null) {
            throw new RuntimeException("文件内容为空");
        }
        int lastRowNum = sheet.getLastRowNum();
        if(lastRowNum > startRow) {
            for(int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
                Row row = sheet.getRow(rowNum);
                int firstCellNum = row.getFirstCellNum();
                int lastCellNum = row.getLastCellNum();
                String[] rowValueStr = new String[lastCellNum];
                if(lastCellNum > firstCellNum) {
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        cell = row.getCell(cellNum);
                        if(cell == null) {
                            rowValueStr[cellNum] = "";
                        } else {
                            rowValueStr[cellNum] = parseCell(cell);
                        }
                    }
                }
                cells.add(rowValueStr);
            }
        }
        return cells;
    }

    /**
     *功能描述
     * 判断每一个cell的数据类型，并做简单转换成字符串
     */
    private static String parseCell(Cell cell) {
        String cellValue;
        switch (cell.getCellType()) {
            case NUMERIC:
                cellValue = parseNumeric(cell);
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BLANK:
                cellValue = "";
                break;
            case BOOLEAN:
                cellValue = "" + cell.getBooleanCellValue();
                break;
            default:
                cellValue = "";
                break;
        }
        return cellValue;
    }

    /**
     *功能描述
     * 数学类型包含日期，时间和一般数字类型，按照具体情况做具体的转换
     * */
    private static String parseNumeric(Cell cell) {
        if(DateUtil.isCellDateFormatted(cell)) {   //判断是否是日期类型，是就做转换
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/M/dd");
            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
            return simpleDateFormat.format(date);
        } else {                                        //不是按照一般数值类型处理
            double v = cell.getNumericCellValue();      //默认返回都是double类型
            DecimalFormat format = new DecimalFormat();
            String formatString = cell.getCellStyle().getDataFormatString(); //判断cell的具体数据类型
            if("General".equals(formatString)) {
                format.applyPattern("0.0");    //利用小数进行转换，0.0:表示不显示精确位数，0.00:表示保留两位小数，依次往下
            }
            return format.format(v);
        }
    }

}
