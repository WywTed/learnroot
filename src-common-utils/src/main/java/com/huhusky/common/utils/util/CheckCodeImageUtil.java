package com.huhusky.common.utils.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CheckCodeImageUtil {

	public static BufferedImage getCodeImage(int width, int height, String codeStr) {
		Random random = new Random();
		// 生成缓冲区image类
		BufferedImage image = new BufferedImage(width, height, 1);
		// 产生image类的Graphics用于绘制操作
		Graphics g = image.getGraphics();
		// Graphics类的样式
		g.setColor(getRandColor(200, 250));
		g.setFont(new Font("Times New Roman", 0, 28));
		g.fillRect(0, 0, width, height);

		// 绘制干扰线
		for (int i = 0; i < 40; i++) {
			g.setColor(getRandColor(130, 200));
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x + x1, y + y1);
		}

		// 绘制字符
		for (int i = 0; i < codeStr.length(); i++) {
			String rand = String.valueOf(codeStr.charAt(i));
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 13 * i + 6, 28);
		}

		g.dispose();
		return image;
	}

	/**
	 * 随机颜色生成
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	private static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
