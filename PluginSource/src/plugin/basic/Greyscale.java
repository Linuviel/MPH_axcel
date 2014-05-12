package plugin.basic;

import java.awt.image.BufferedImage;

import plugin.IPlugin;

import java.awt.Color;

public class Greyscale implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		int[] pixel = new int[3];
		int alpha, red, green, blue;
		int newPixel;
		
		for (int i = 0; i < img.getWidth(); i++)
			for (int j = 0; j < img.getHeight(); j++)
			{
				int p = img.getRGB(i, j);
		        int a = (((p >> 16) & 0xff) + ((p >> 8) & 0xff) + (p & 0xff)) / 3;
		        res.setRGB(i, j, (0xff << 24) | (a << 16) | (a << 8) | a);
				/**
				int rgb = img.getRGB(i, j);
		        int r = (rgb >> 16) & 0xFF;
		        int g = (rgb >> 8) & 0xFF;
		        int b = (rgb & 0xFF);

		        int gray = (r + g + b) / 3;
		        res.setRGB(i, j, gray);**/
			}
		return res;
	}

	@Override
	public String getName()
	{
		return "Greyscale";
	}

}
