package com.common.opthelperserver.utils;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : liutao
 * @Date : 2023/1/3
 * @Description Excel工具类
 **/
@Component
public class ExcelUtil {
    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);
    public  void writeExcel(String outName, List<Map<String,String>> rowList){
        try{
            File fileWrite = new File(outName);
            fileWrite.createNewFile();
            OutputStream os = new FileOutputStream(fileWrite);
            WritableWorkbook writableWorkbook = Workbook.createWorkbook(os);
            WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1",0);

            for(int i=0;i<rowList.size();i++){
                Map<String,String> row = rowList.get(i);
                int j=0;
                for (Map.Entry entry : row.entrySet()) {
                    Label label = new Label(j,i, String.valueOf(entry.getValue()));
                    writableSheet.addCell(label);
                    j++;
                }
            }
            writableWorkbook.write();
            writableWorkbook.close();
            os.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public  List<Map<String,String>> readExcel(MultipartFile file, boolean readHead) throws BiffException, IOException
    {
        //1:创建workbook
        Workbook workbook=Workbook.getWorkbook(file.getInputStream());
        //2:获取第一个工作表sheet
        Sheet sheet=workbook.getSheet(0);
        //3:获取数据
        List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
        int begin = 0;
        if(!readHead) {
            begin = 1;
        }

        for(int i=begin;i<sheet.getRows();i++){
            Map<String,String> row = new HashMap<String,String>();
            for(int j=0;j<sheet.getColumns();j++){
                Cell cell=sheet.getCell(j,i);
                row.put("COLUMN"+j, cell.getContents());
            }
            rows.add(row);
        }

        //最后一步：关闭资源
        workbook.close();
        return rows;
    }

    public  List<Map<String,String>> readExcelXlsx(MultipartFile file, boolean readHead) throws BiffException, IOException
    {
        List<Map<String,String>> rows = new ArrayList<Map<String,String>>();
        InputStream inputStream = file.getInputStream();
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);

        XSSFSheet sheet = workBook.getSheetAt(0);
        int lastRowNum = sheet.getLastRowNum();
        int begin = 0;
        if(!readHead) {
            begin = 1;
        }
        for (int rowNum = begin; rowNum <= lastRowNum; rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            if (row!=null){
                Map<String,String> rowInfo = new HashMap<String,String>();
                row.getCell(0).setCellType(CellType.STRING);
                String dateInfo=""+row.getCell(0).getStringCellValue();
                rowInfo.put("COLUMN0", dateInfo);
                rows.add(rowInfo);
            }
        }
        return rows;
    }
    public  void downloadExcel(String outName, String [] headers, List<String[]> rowList, HttpServletResponse response){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        //headers表示excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        if (rowList.size() != 0) {
            //在表中存放查询到的数据放入对应的列
            for (int i = 0; i < rowList.size(); i++) {
                HSSFRow row1 = sheet.createRow(rowNum);
                String[] rowInfo = rowList.get(i);
                for (int j = 0; j < rowInfo.length; j++) {
                    row1.createCell(j).setCellValue(rowInfo[j]);
                }
                rowNum++;
            }
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + outName);
        response.setContentType("application/x-download");
        response.setHeader("Connection", "keep-alive");
        try {
            response.flushBuffer();
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }finally {
            try {
                workbook.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
    }
}
