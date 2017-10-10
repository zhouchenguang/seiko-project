/*
 * Copyright (C),2016-2016. ��ס�Ƶ�������޹�˾
 * FileName: RandomUtil.java
 * Author:   admin
 * Date:     2016-03-08 15:33:15
 * Description: //ģ��Ŀ�ġ���������
 * History: //�޸ļ�¼ �޸������� �޸�ʱ�� �汾�� ���� ������Դ
 * <admin><2016-03-08 15:33:15><version><desc><source>
 *
 */

package com.seiko.utils;

public class RandomUtil {
    public static String getRandomCode() {
        String code = "";
        String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A",
                "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};
        for (int i = 0; i < 10; i++) {
            int j = 1 + (int) (Math.random() * 61);
            code += chars[j];
        }
        return code;
    }

}
