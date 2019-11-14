package com.lxchao.app.utils.excel;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Handler;
import android.util.Log;
import android.util.Xml;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * @author lixiangchao
 * @version 1.0.0
 * @date 2019/11/12
 */
public class ReadExcelPoi implements Runnable {
    private static final String TAG = ReadExcelPoi.class.getSimpleName();

    private static final int BYTE_BUF_SIZE = 2048;
    private String inputFile;
    ExcelInfoListener listener;

    public ReadExcelPoi(String inputFile, ExcelInfoListener listener) {
        this.inputFile = inputFile;
        this.listener = listener;
    }

    @Override
    public void run() {
        File file = new File(inputFile);
        if (!file.exists()) {
            backInfo(false, null);
            return;
        }
        readExcel2(file);
    }

    public static String readXLSX(String path) {
        String str = "";
        String v = null;
        boolean flat = false;
        ArrayList<String> ls = new ArrayList<String>();
        try {
            ZipFile xlsxFile = new ZipFile(new File(path));
            ZipEntry sharedStringXML = xlsxFile.getEntry("xl/sharedStrings.xml");
            InputStream inputStream = xlsxFile.getInputStream(sharedStringXML);
            XmlPullParser xmlParser = Xml.newPullParser();
            xmlParser.setInput(inputStream, "utf-8");
            int evtType = xmlParser.getEventType();
            while (evtType != XmlPullParser.END_DOCUMENT) {
                switch (evtType) {
                    case XmlPullParser.START_TAG:
                        String tag = xmlParser.getName();
                        if (tag.equalsIgnoreCase("t")) {
                            ls.add(xmlParser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                    default:
                        break;
                }
                evtType = xmlParser.next();
            }

            ZipEntry sheetXML = xlsxFile.getEntry("xl/worksheets/sheet1.xml");
            InputStream inputStreamsheet = xlsxFile.getInputStream(sheetXML);
            XmlPullParser xmlParsersheet = Xml.newPullParser();
            xmlParsersheet.setInput(inputStreamsheet, "utf-8");
            int evtTypesheet = xmlParsersheet.getEventType();
            while (evtTypesheet != XmlPullParser.END_DOCUMENT) {
                switch (evtTypesheet) {
                    case XmlPullParser.START_TAG:
                        String tag = xmlParsersheet.getName();
                        Log.e("sss", "tag === " + tag);
                        if (tag.equalsIgnoreCase("row")) {

                            Log.e("sss", "rpowssssssssssssss");
                        } else if (tag.equalsIgnoreCase("c")) {
                            String t = xmlParsersheet.getAttributeValue(null, "t");
                            if (t != null) {
                                flat = true;
                                Log.e("sss", "有");
//                                System.out.println(flat + "有");
                            } else {
                                Log.e("sss", "没有");
//                                System.out.println(flat + "没有");
                                flat = false;
                            }
                        } else if (tag.equalsIgnoreCase("v")) {
                            Log.e("sss", "vvvvvvvvvv");
                            v = xmlParsersheet.nextText();
                            if (v != null) {
                                Log.e("sss", "vvvvvvvvv1111111v");
                                if (flat) {
                                    Log.e("sss", "ififififif");
                                    str += ls.get(Integer.parseInt(v)) + " -1 ";
                                } else {
                                    Log.e("sss", "elseelelelel ==== " + v.length());
                                    str += v + "  ";
                                }
                            } else {
                                Log.e("sss", "elelelelelel e================");
                            }
                        } else {
                            Log.e("sss", "tag === empty");
                            str += " empty ";
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (xmlParsersheet.getName().equalsIgnoreCase("row") && v != null) {
                            str += "\n";
                        }
                        break;
                }
                evtTypesheet = xmlParsersheet.next();
            }
            Log.e("sss", str);
//            System.out.println(str);
        } catch (ZipException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        if (str == null) {
            str = "解析文件出现问题";
        }
        return str;
    }

    public void readExcel2(File excelFile) {
        try {
            Log.e("sss", "file == " + excelFile.exists());
            Log.e("sss", "file == " + excelFile.getAbsolutePath());
            Log.e("sss", "file == " + excelFile.getName());

            Log.e("sss", "file == " + WorkbookFactory.create(excelFile));

            Workbook workbook = WorkbookFactory.create(excelFile);
            //或者根据表名称读取：sheet=workbook.getSheet("表1");
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();
            List<ExcelEntity> excelEntityList = new ArrayList<>();
            while (iterator.hasNext()) {
                //遍历每一行的数据
                Row row = iterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<ExcelItemEntity> excelItemEntityList = new ArrayList<>();
                while (cellIterator.hasNext()) {
                    //遍历某行的所有单元格
                    Cell cell = cellIterator.next();
                    int cellType = cell.getCellType();
                    ExcelItemEntity excelItemEntity = null;
                    switch (cellType) {
                        //String
                        case Cell.CELL_TYPE_STRING:
                            excelItemEntity = new ExcelItemEntity(cell.getStringCellValue());
                            Log.e("sss", "=========刷新得数据 String===" + cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            //int
                            String result = getExcelEntity(cell);
                            excelItemEntity = new ExcelItemEntity(result);
                            Log.e("sss", "=========刷新得数据 int===" + cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_FORMULA:
                            //公式
                            excelItemEntity = new ExcelItemEntity(cell.getCellFormula());
                            Log.e("sss", "=========刷新得数据 公式===" + cell.getCellFormula());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            //空格
                            Log.e("sss", "=========刷新得数据 空格===");
                            excelItemEntity = new ExcelItemEntity("");
                            break;
                        case Cell.CELL_TYPE_BOOLEAN:
                            //boolean
                            excelItemEntity = new ExcelItemEntity(String.valueOf(cell.getBooleanCellValue()));
                            Log.e("sss", "=========刷新得数据 boolean===" + cell.getBooleanCellValue());
                            break;
                        case Cell.CELL_TYPE_ERROR:
                            //错误单元格
                            excelItemEntity = new ExcelItemEntity("");
                            Log.e("sss", "=========刷新得数据 错误单元格===" + cell.getErrorCellValue());
                            break;
                        default:
                            Log.e("sss", "=========刷新得数据 default===" + cell.getErrorCellValue());
                            excelItemEntity = new ExcelItemEntity(cell.getStringCellValue());
                            break;
                    }
                    excelItemEntityList.add(excelItemEntity);
                }
                excelEntityList.add(new ExcelEntity(0, excelItemEntityList));
            }
            backInfo(true, excelEntityList);
        } catch (Exception e) {
            backInfo(false, null);
            Log.e("sss", "=========刷新得数据===" + e.toString());
            e.printStackTrace();
        }
    }


    /**
     * 获取时间格式得
     *
     * @param cell
     */
    private String getExcelEntity(Cell cell) {
        String result = "";
        // 处理日期格式、时间格式
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
            SimpleDateFormat sdf = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat
                    .getBuiltinFormat("h:mm")) {
                sdf = new SimpleDateFormat("HH:mm");
            } else {// 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd");
            }
            Date date = cell.getDateCellValue();
            result = sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil
                    .getJavaDate(value);
            result = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            result = format.format(value);
        }
        Log.e("sss", "=========刷新得数据 data===" + result);
        return result;
    }

    private void backInfo(final boolean isTrue, final List<ExcelEntity> lists) {
        if (listener == null) {
            return;
        }
        handler.post(() -> listener.backExcelInfo(isTrue, lists));
    }

    private Handler handler = new Handler();


    /**
     * Copies a file from assets.
     *
     * @param context application context used to discover assets.
     * @param assetName the relative file name within assets.
     * @param targetName the target file name, always over write the existing file.
     * @throws IOException if operation fails.
     */
    public static void copy(Context context, String assetName, String targetName) throws IOException {

        Log.d(TAG, "creating file " + targetName + " from " + assetName);

        File targetFile = null;
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        try {
            AssetManager assets = context.getAssets();
            targetFile = new File(targetName);
            if(!targetFile.getParentFile().exists()){
                targetFile.getParentFile().mkdirs();
            }
            if(!targetFile.exists()){
                targetFile.createNewFile();
            }
            inputStream = assets.open(assetName);
            // TODO(kanlig): refactor log messages to make them more useful.
            Log.d(TAG, "Creating outputstream");
            outputStream = new FileOutputStream(targetFile, false /* append */);
            copy(inputStream, outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static void copy(InputStream from, OutputStream to) throws IOException {
        byte[] buf = new byte[BYTE_BUF_SIZE];
        while (true) {
            int r = from.read(buf);
            if (r == -1) {
                break;
            }
            to.write(buf, 0, r);
        }
    }

}
