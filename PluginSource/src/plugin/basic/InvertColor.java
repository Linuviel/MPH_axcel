package plugin.basic;

import java.awt.image.BufferedImage;

import plugin.IPlugin;

import java.awt.Color;

public class InvertColor implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		for (int i = 0; i < img.getWidth(); i++)
			for (int j = 0; j < img.getHeight(); j++)
			{
				int rgba = img.getRGB(i, j);
                Color col = new Color(rgba, true);
                
                col = new Color(Math.abs(col.getRed() - 255),
                                Math.abs(col.getGreen() - 255),
                                Math.abs(col.getBlue() - 255));
                res.setRGB(i, j, col.getRGB());
			}
		return res;
	}

	@Override
	public String getName()
	{
		return "Invert Color";
	}

}
