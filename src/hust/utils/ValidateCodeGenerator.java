package hust.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

/**
 * 验证码生成器
 * 
 * @author liangjian
 *
 */
public class ValidateCodeGenerator {

	final private static char[] letters = "23456789ABDEFGHJKLMNPQRSTUVXYZabdefghijkmnpqrstuvxyz".toCharArray();
	final private static String[] fontNames = new String[]{"Courier", "Arial", "Verdana", "Georgis", "Times", "Tahoma","Times New Roman"};
	final private static int[] fontStyle = new int[]{Font.PLAIN, Font.BOLD, Font.ITALIC, Font.BOLD | Font.ITALIC};
	
	// 输入属性
	private int imgWidth = 160;
	private int imgHeight = 60;
	private int imgCharCnt = 4;
	private int disturbLineNum = 10;
	
	// 图片以输出流的方式传递给前端
	private OutputStream os;
	
	public ValidateCodeGenerator(OutputStream os){
		this.os = os;
	}

	public ValidateCodeGenerator(int width, int height, int charCnt, int disturbLineNum, OutputStream os){
		this.imgWidth = width;
		this.imgHeight = height;
		this.imgCharCnt = charCnt;
		this.disturbLineNum = disturbLineNum;
		this.os = os;
	}
	
	/*
	 * 获取验证码图片
	 */
	public String drawCode() throws IOException {
		BufferedImage codeImg = new BufferedImage(this.imgWidth, this.imgHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = codeImg.createGraphics();
		g.setColor(new Color(245, 245, 245));
		// 背景填充
		g.fillRect(0, 0, this.imgWidth, this.imgHeight);
		
		drawDisturbLine(g);
		
		// 分别画4个背景透明的小正方形图片，这样可以方便字符旋转（长方形整体旋转会发生字符丢失），然后将小正方形图片依次贴到统一的Graphics2D上
		BufferedImage[] bis = new BufferedImage[imgCharCnt];
		char[] codes = generateCode();
		for (int i = 0; i < imgCharCnt; i++) {
			bis[i] = generateBuffImg(codes[i]);
			g.drawImage(bis[i], null, (int) (this.imgHeight * 0.7) * i, 0);
		}
		
		g.dispose();
		
		ImageIO.write(codeImg, "gif", os);
		
		return new String(codes);
	}
	
	/*
	 * 生成包含单个字符的透明背景的正方形小图片
	 */
	private BufferedImage generateBuffImg(char c) {
		String tmp = Character.toString(c);
		Color forecolor = getRandomColor();
		Color backcolor = new Color(255, 255, 255, 0); // 0表示透明背景色
		String fontName = getRandomFontName();
		int fontStyle = getRandomStyle();
		int fontSize = getRandomSize();
		int strX = (this.imgHeight - fontSize) / 2;
		int strY = (this.imgHeight - fontSize) / 2 + fontSize;
		double arch = getRandomArch();

		BufferedImage ret = new BufferedImage(this.imgHeight, this.imgHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = ret.createGraphics();
		g.setColor(backcolor);
		g.fillRect(0, 0, this.imgHeight, this.imgHeight);

		g.setColor(forecolor);
		g.setFont(new Font(fontName, fontStyle, fontSize));
		g.rotate(arch, this.imgHeight / 2, this.imgHeight / 2);
		g.drawString(tmp, strX, strY);

		g.dispose();
		return ret;
	}

	/*
	 * 获取随机字符
	 */
	private char[] generateCode() {
		char[] ret = new char[imgCharCnt];
		for (int i = 0; i < imgCharCnt; i++) {
			int letterPos = (int) (Math.random() * 10000) % (letters.length);
			ret[i] = letters[letterPos];
		}
		return ret;
	}
	
	/*
	 * 获取随机颜色
	 */
	private Color getRandomColor() {
		int r = (int) (Math.random() * 10000) % 200;
		int g = (int) (Math.random() * 10000) % 200;
		int b = (int) (Math.random() * 10000) % 200;
		return new Color(r, g, b);
	}
	
	/*
	 * 获取随机字体
	 */
	private String getRandomFontName() {
		int pos = (int) (Math.random() * 10000) % (fontNames.length);
		return fontNames[pos];
	}
	
	/*
	 * 获取随机字体样式
	 */
	private int getRandomStyle() {
		int pos = (int) (Math.random() * 10000) % (fontStyle.length);
		return fontStyle[pos];
	}
	
	/*
	 * 获取随机大小字符
	 */
	private int getRandomSize() {
		int max = (int) (this.imgHeight * 0.98);
		int min = (int) (this.imgHeight * 0.75);
		return (int) (Math.random() * 10000) % (max - min + 1) + min;
	}
	
	/*
	 * 获取旋转随机角度
	 */
	private double getRandomArch() {
		return ((int) (Math.random() * 1000) % 2 == 0 ? -1 : 1) * Math.random();
	}
	
	/*
	 * 画干扰线
	 */
	private void drawDisturbLine(Graphics2D graphics) {
		for (int i = 0; i < disturbLineNum; i++) {
			graphics.setColor(getRandomColor());
			int x = (int) (Math.random() * 10000) % (this.imgWidth + 1) + 1;
			int x1 = (int) (Math.random() * 10000) % (this.imgWidth + 1) + 1;
			int y = (int) (Math.random() * 10000) % (this.imgHeight + 1) + 1;
			int y1 = (int) (Math.random() * 10000) % (this.imgHeight + 1) + 1;
			graphics.drawLine(x, y, x1, y1);
		}
	}
	
	public static void main(String[] args) throws IOException{
		FileOutputStream os = new FileOutputStream("d:/img.gif");
		ValidateCodeGenerator v = new ValidateCodeGenerator(os);
		v.drawCode();
	}
}
