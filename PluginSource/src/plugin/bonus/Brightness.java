package plugin.bonus;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;

import java.awt.image.Kernel;

import plugin.IPlugin;

import java.awt.Color;

public class Brightness implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
		float ninth = 1.0f / 7.0f;

		float[] blurKernel = { ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth, ninth };

		BufferedImageOp blurOp = new ConvolveOp(new Kernel(3, 3, blurKernel));

		res = blurOp.filter(img, null);
		return res;
	}
	@Override
	public String getName()
	{
		return "Brightness";
	}
}



 