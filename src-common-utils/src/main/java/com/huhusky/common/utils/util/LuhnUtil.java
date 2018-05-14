package com.huhusky.common.utils.util;
import java.util.Arrays;

/**
 * Created by shawn on 2017/3/21.
 */
public class LuhnUtil {

    private int[] no;

    private Boolean isValidate = null;

    public LuhnUtil(String strno) {
        this(convertStrToInArr(strno));
    }

    public LuhnUtil(int[] no) {
        if (null != no && no.length > 0) {
            this.no = Arrays.copyOf(no, no.length);
            for (int i = 0; i < no.length; i++) {
                if (no[i] < 0) {
                    throw new IllegalArgumentException("No can not contain negtive value");
                }
            }
        } else {
            throw new IllegalArgumentException("No is null or Empty");
        }
    }

    public boolean check() {
        if (null == isValidate) {
            isValidate = luhnCheck(getCardNoArr());
        }
        return isValidate;
    }

    public int[] getCardNoArr() {
        return Arrays.copyOf(no, no.length);
    }

    public int getCheckSum() {
        if (check()) {
            return no[0];
        }
        int[] cardNoArr = getCardNoArr();
        for (int i = 0; i < cardNoArr.length; i += 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
            //System.out.print(cardNoArr[i]);
        }
        return sum * 9 % 10;
    }

    private static int[] convertStrToInArr(String cardNo) {
        if (null == cardNo) throw new IllegalArgumentException();
        int index = cardNo.length();
        int[] cardNoArr = new int[cardNo.length()];
        for (char c : cardNo.toCharArray()) {
            cardNoArr[--index] = c - '0';
        }
        return cardNoArr;
    }

    private static boolean luhnCheck(int[] cardNoArr) {
        for (int i = 1; i < cardNoArr.length; i += 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
            //System.out.print(cardNoArr[i]);
        }

        return sum % 10 == 0;
    }

    public static void main(String[] args) {
        LuhnUtil lu = new LuhnUtil("80802288888888818");
        System.out.println(lu.getCheckSum());
        System.out.println(lu.check());
    }

}
