package yibao.yiwei.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 生成验证码随机数图片
 * @author sunshy
 *
 */
public class RandomNumber {
	// 先通过随机数生成四位数字，然后把这四位数生成随机图片，响应到客户端，返回这四位数字
		public ValidateCode generateImage() {
			// 1.生成四位随机整数，并转化为字符串
			String sRand = "";
			Random random = new Random();
			for (int i = 0; i < 4; i++) {
				String rand = String.valueOf(random.nextInt(10));// 将生成的随机数转化为字符串
				sRand += rand;// 四个数字拼接为一个字符串
			}

			// 2.创建图像
			int width = 80, height = 30;
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			// 获取图形上下文
			Graphics g = image.getGraphics();
			// 设定背景色
			g.setColor(getRandColor(100, 250));
			// 画一个矩形
			g.fillRect(0, 0, width, height);
			// 设定字体
			g.setFont(new Font("Timess New Roman", Font.PLAIN, 28));
			// 随机生成155条干扰线，使图像中的数字不易被其他程序探测到
			g.setColor(getRandColor(160, 200));
			for (int i = 0; i < 155; i++) {
				int x = random.nextInt(width);
				int y = random.nextInt(height);
				int xl = random.nextInt(12);
				int yl = random.nextInt(12);
				g.drawLine(x, y, x + xl, y + yl);
			}
			// 设置颜色
			g.setColor(new Color(20 + random.nextInt(110),
					20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 将验证码显示到图像
			g.drawString(sRand, 10, 26);
			//释放绘图工具
			g.dispose();
			//组装ValidateCode对象
			ValidateCode vc = new ValidateCode();
			vc.setRand(sRand);
			vc.setImage(image);
			return vc;
		}

		private Color getRandColor(int fc, int bc) {
			// 给定范围内获得随机颜色
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
