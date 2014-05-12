package plugin.basic;

import java.awt.image.BufferedImage;

import plugin.IPlugin;

import java.awt.Color;

public class Binary implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		int threshold = 70;
		int w = img.getWidth(null);
		int h = img.getHeight(null);
		BufferedImage img2 = img;
		for (int i = 0; i < w; i++) 
		{
			for (int j = 0; j < h; j++) 
			{
				if ((img.getRGB(i, j) & 0xff) > threshold) img2.setRGB(i, j, 0xffffffff);
				else img2.setRGB(i, j, 0xff000000);
			}
		}
		return img2;
	}

	@Override
	public String getName()
	{
		return "Binary";
	}

}
