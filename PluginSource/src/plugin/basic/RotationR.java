package plugin.basic;

import java.awt.image.BufferedImage;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import plugin.IPlugin;

import java.awt.Color;

public class RotationR implements IPlugin
{

	@Override
	public BufferedImage perform(BufferedImage img)
	{
		BufferedImage res = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());



		BufferedImage newImage = null;
		AffineTransform tx = new AffineTransform();
		tx.rotate(Math.PI / 2, img.getWidth() / 2, img.getHeight() / 2);

		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
		newImage = op.filter(img, newImage);

		return newImage;


	}
	@Override
	public String getName()
	{
		return "RotationR";
	}

	}