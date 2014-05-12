package plugin.basic;

import java.awt.image.BufferedImage;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import plugin.IPlugin;

import java.awt.Color;

public class FlipV implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());

		int newPixel;
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-res.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
				AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		res = op.filter(img, null);
		return res;
	}

	@Override
	public String getName()
	{
		return "FlipV";
	}

}
