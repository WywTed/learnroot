package com.huhusky.wechat.cons;

import java.util.ArrayList;
import java.util.List;

public class KeywordArr {
	
	public static List<String> Key_PriceArr = new ArrayList<>();

	public static String Key_Tutor = "教程";
	
	public static List<String> Key_FeedbakArr = new ArrayList<>();
	
	public static String Key_Download = "下载";
	
	public static List<String> Key_WorthArr = new ArrayList<>();
	
	static {
		Key_PriceArr.add("TF");
		Key_PriceArr.add("TFCOIN");
		Key_PriceArr.add("TFCHAIN");
		Key_PriceArr.add("TFC");
		
		Key_FeedbakArr.add("交流");
		Key_FeedbakArr.add("反馈");
		
		Key_WorthArr.add("使命");
		Key_WorthArr.add("愿景");
		Key_WorthArr.add("价值观");
	}

}
