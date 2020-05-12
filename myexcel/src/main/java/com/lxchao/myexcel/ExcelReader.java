package com.lxchao.myexcel;

import com.lxchao.myexcel.digit.DanXuan;
import com.lxchao.myexcel.digit.DuoXuan;
import com.lxchao.myexcel.digit.PanDuan;
import com.lxchao.myexcel.digit.TianKong;
import com.lxchao.myexcel.digit.WenDa;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/18
 */
public class ExcelReader {

    public static List<TianKong> readBlank(String fileName) {
        List<TianKong> data = new ArrayList<>();
        TianKong bean;

        if (null == fileName) {
            return data;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            return data;
        }
        try {
            Workbook workbook = WorkbookFactory.create(file);
            int sheetNum = workbook.getNumberOfSheets();
            if (sheetNum <= 0) {
                return data;
            }
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int row = 1;row < rowNum;row++) {
                bean = new TianKong();
                Row r = sheet.getRow(row);
                bean.set难度(r.getCell(0).toString());
                bean.set建议题目分值(r.getCell(1).toString());
                bean.set建议作答时间(r.getCell(2).toString());
                bean.set认知分类(r.getCell(3).toString());
                bean.set题目用途(r.getCell(4).toString());
                bean.set题目名称(r.getCell(5).toString());
                bean.set题目内容(r.getCell(6).toString());
                bean.set题目提示(r.getCell(7).toString());
                bean.set题目要求(r.getCell(8).toString());
                bean.set答案(r.getCell(9).toString());
                if (!bean.get答案().equals("答案") && bean.get答案().length() > 0) {
                    data.add(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        return data;
    }


    public static List<DanXuan> readSingle(String fileName) {
        List<DanXuan> data = new ArrayList<>();
        DanXuan bean;

        if (null == fileName) {
            return data;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            return data;
        }
        try {
            Workbook workbook = WorkbookFactory.create(file);
            int sheetNum = workbook.getNumberOfSheets();
            if (sheetNum <= 0) {
                return data;
            }
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int row = 1;row < rowNum;row++) {
                bean = new DanXuan();
                Row r = sheet.getRow(row);
                bean.set难度(r.getCell(0).toString());
                bean.set建议题目分值(r.getCell(1).toString());
                bean.set建议作答时间(r.getCell(2).toString());
                bean.set认知分类(r.getCell(3).toString());
                bean.set题目用途(r.getCell(4).toString());
                bean.set题目名称(r.getCell(5).toString());
                bean.set题目内容(r.getCell(6).toString());
                bean.set题目提示(r.getCell(7).toString());
                bean.set题目要求(r.getCell(8).toString());
                bean.set选项A(r.getCell(9).toString());
                bean.set选项B(r.getCell(10).toString());
                bean.set选项C(r.getCell(11).toString());
                bean.set选项D(r.getCell(12).toString());
                bean.set选项E(r.getCell(13).toString());
                bean.set答案(r.getCell(14).toString());
                if (!bean.get答案().equals("答案") && bean.get答案().length() > 0) {
                    data.add(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        return data;
    }


    public static List<DuoXuan> readChecked(String fileName) {
        List<DuoXuan> data = new ArrayList<>();
        DuoXuan bean;

        if (null == fileName) {
            return data;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            return data;
        }
        try {
            Workbook workbook = WorkbookFactory.create(file);
            int sheetNum = workbook.getNumberOfSheets();
            if (sheetNum <= 0) {
                return data;
            }
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int row = 1;row < rowNum;row++) {
                bean = new DuoXuan();
                Row r = sheet.getRow(row);
                bean.set难度(r.getCell(0).toString());
                bean.set建议题目分值(r.getCell(1).toString());
                bean.set建议作答时间(r.getCell(2).toString());
                bean.set认知分类(r.getCell(3).toString());
                bean.set题目用途(r.getCell(4).toString());
                bean.set题目名称(r.getCell(5).toString());
                bean.set题目内容(r.getCell(6).toString());
                bean.set题目提示(r.getCell(7).toString());
                bean.set题目要求(r.getCell(8).toString());
                bean.set选项Ａ(r.getCell(9).toString());
                bean.set选项B(r.getCell(10).toString());
                bean.set选项C(r.getCell(11).toString());
                bean.set选项D(r.getCell(12).toString());
                bean.set选项E(r.getCell(13).toString());
                bean.set正确答案(r.getCell(14).toString());
                if (!bean.get正确答案().equals("正确答案") && bean.get正确答案().length() > 0) {
                    data.add(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        return data;
    }


    public static List<PanDuan> readOpinion(String fileName) {
        List<PanDuan> data = new ArrayList<>();
        PanDuan bean;

        if (null == fileName) {
            return data;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            return data;
        }
        try {
            Workbook workbook = WorkbookFactory.create(file);
            int sheetNum = workbook.getNumberOfSheets();
            if (sheetNum <= 0) {
                return data;
            }
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int row = 1;row < rowNum;row++) {
                bean = new PanDuan();
                Row r = sheet.getRow(row);
                bean.set难度(r.getCell(0).toString());
                bean.set建议题目分值(r.getCell(1).toString());
                bean.set建议作答时间(r.getCell(2).toString());
                bean.set认知分类(r.getCell(3).toString());
                bean.set题目用途(r.getCell(4).toString());
                bean.set题目名称(r.getCell(5).toString());
                bean.set题目内容(r.getCell(6).toString());
                bean.set题目提示(r.getCell(7).toString());
                bean.set题目要求(r.getCell(8).toString());
                bean.set答案(r.getCell(9).toString());
                if (!bean.get答案().equals("答案") && bean.get答案().length() > 0) {
                    data.add(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        return data;
    }

    public static List<WenDa> readQa(String fileName) {
        List<WenDa> data = new ArrayList<>();
        WenDa bean;

        if (null == fileName) {
            return data;
        }

        File file = new File(fileName);
        if (!file.exists()) {
            return data;
        }
        try {
            Workbook workbook = WorkbookFactory.create(file);
            int sheetNum = workbook.getNumberOfSheets();
            if (sheetNum <= 0) {
                return data;
            }
            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = sheet.getLastRowNum() + 1;
            for (int row = 1;row < rowNum;row++) {
                bean = new WenDa();
                Row r = sheet.getRow(row);
                bean.set难度(r.getCell(0).toString());
                bean.set建议题目分值(r.getCell(1).toString());
                bean.set建议作答时间(r.getCell(2).toString());
                bean.set认知分类(r.getCell(3).toString());
                bean.set题目用途(r.getCell(4).toString());
                bean.set题目名称(r.getCell(5).toString());
                bean.set题目内容(r.getCell(6).toString());
                bean.set题目提示(r.getCell(7).toString());
                bean.set题目要求(r.getCell(8).toString());
                bean.set答案(r.getCell(9).toString());
                if (!bean.get答案().equals("答案") && bean.get答案().length() > 0) {
                    data.add(bean);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            data = new ArrayList<>();
        }
        return data;
    }
}
