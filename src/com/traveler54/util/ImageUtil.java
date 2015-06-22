package com.traveler54.util;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;


public class ImageUtil extends FileUtil{

	public int getImageWidth(BufferedImage bi) {
		return bi.getWidth();
	}

	public int getImageHeight(BufferedImage bi) {
		return bi.getHeight();
	}
	
	public BufferedImage isImage(InputStream is) {
		try {
			BufferedImage bi = ImageIO.read(is);
			if (this.getImageWidth(bi) == 0 || this.getImageHeight(bi) == 0) {
				return null;
			} else {
				return bi;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
