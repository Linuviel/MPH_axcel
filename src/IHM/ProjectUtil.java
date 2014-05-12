package IHM;

import java.io.Serializable;
import java.util.ArrayList;

import memento.CareTaker;
import memento.Memento;
import memento.Originator;

public class ProjectUtil implements Serializable {
	
	private static final long 		serialVersionUID = -314171089120047242L;
	private transient ImagePanel	imgP;
	private transient CareTaker		caretaker;
	private transient Originator	originator;
	private transient Memento		memento;
	private ArrayList<int[]>		pixels;
	
	public ProjectUtil()
	{
		
	}
	
	public void storePixel()
	{
		for (int[] pix : caretaker.getAllPixel())
		{
			pixels.add(pix);
		}
	}
	
	public void setImgP(ImagePanel imagePanel)
	{
		this.imgP = imagePanel;
	}
	
	public CareTaker getCareTaker()
	{
		return caretaker;
	}
	
	public void setCareTaker(CareTaker caretaker)
	{
		this.caretaker = caretaker;
	}
	
	public Originator getOriginator()
	{
		return originator;
	}
	
	public void setOriginator(Originator originator)
	{
		this.originator = originator;
	}
	
	public Memento getMemento()
	{
		return memento;
	}
	
	public void setMemento(Memento memento)
	{
		this.memento = memento;
	}
	
	

}
