package plugin.bonus;

import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;

import java.awt.image.Kernel;

import plugin.IPlugin;

import java.awt.Color;

public class Sharpen implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		float[] sharpenMatrix = { 0.0f, -1.0f, 0.0f, -1.0f, 5.0f, -1.0f, 0.0f, -1.0f, 0.0f };
		BufferedImageOp sharpenFilter = new ConvolveOp(new Kernel(3, 3, sharpenMatrix),
				ConvolveOp.EDGE_NO_OP, null);
		return sharpenFilter.filter(img, null);
	}
	@Override
	public String getName()
	{
		return "Sharpen";
	}

}