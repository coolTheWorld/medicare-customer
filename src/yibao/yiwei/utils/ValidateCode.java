package yibao.yiwei.utils;

import java.awt.image.BufferedImage;

/**
 * 验证码
 * @author sunshy
 *
 */
public class ValidateCode {
	private String rand;
	private BufferedImage image;

	public String getRand() {
		return rand;
	}

	public void setRand(String rand) {
		this.rand = rand;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
