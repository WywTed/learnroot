package com.huhusky.common.utils.util.dto;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wuhuhu
 * @create 2017/7/21 18:06
 */
public class ExcelData {

    // 导出文件格式
    public static final String ms_xls = "xls";
    public static final String ms_xlsx = "xlsx";

    private String extName;
    private List<SheetData> sheets;
    private OutputStream os;

    public ExcelData(){
        this.extName = ms_xlsx;
    }

    public ExcelData(OutputStream os){
        this.extName = ms_xlsx;
        this.os = os;
    }

    public ExcelData buildFromMap(Map<String, List<List<String>>> data, String commonHead){
        if(data != null && !data.isEmpty()){
            if(this.sheets == null){
                this.sheets = new ArrayList<>();
            }
            for(Map.Entry<String, List<List<String>>> ey : data.entrySet()){
                SheetData sd = new SheetData(ey.getKey(), commonHead, ey.getValue());
                sheets.add(sd);
            }
        }
        return this;
    }



    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public List<SheetData> getSheets() {
        return sheets;
    }

    public void setSheets(List<SheetData> sheets) {
        this.sheets = sheets;
    }

    public OutputStream getOs() {
        return os;
    }

    public void setOs(OutputStream os) {
        this.os = os;
    }
}
