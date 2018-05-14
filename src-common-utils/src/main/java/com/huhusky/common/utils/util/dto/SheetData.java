package com.huhusky.common.utils.util.dto;

import java.util.List;

/**
 * @author wuhuhu
 * @create 2017/7/21 18:11
 */
public class SheetData {

    private String head; //表头
    private int columnCount; //表头所占列数
    private String sheetName; //表名
    private List<List<String>> content; //表内容

    public SheetData(){}

    public SheetData(String sheetName, String head, List<List<String>> data){
        this.sheetName = sheetName;
        this.head = head;
        this.content = data;
        if(data != null && !data.isEmpty()){
            this.columnCount = data.get(0).size();
        }
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<List<String>> getContent() {
        return content;
    }

    public void setContent(List<List<String>> content) {
        this.content = content;
    }
}
