package memento;

import java.io.Serializable;
import java.util.ArrayList;

public class CareTaker implements Serializable {
	
	private static final long 		serialVersionUID = -314171089120047242L;
	protected ArrayList<Memento> 	mementosource_;
	protected ArrayList<String> 	listaction_;
	protected ArrayList<int[]>		pixellist_;
	protected int 					currentmod = 0;
	
	public ArrayList<int[]> getPixellist_()
	{
		return pixellist_;
	}
	
	public void setPixellist_(ArrayList<int[]> pixellist_)
	{
		this.pixellist_ = pixellist_;
	}
	
	public CareTaker() 
	{
		mementosource_ = new ArrayList<Memento>();
		listaction_ = new ArrayList<String>();
		pixellist_ = new ArrayList<int[]>();
	}
	
	public void add(Memento m)
	{
		mementosource_.add(m);
		listaction_.add(m.getStateName_());
	}
	
	public void add(int[] pix)
	{
		pixellist_.add(pix);
	}
	
	public Memento get(int index)
	{
		return mementosource_.get(index);
	}
	
	public void undo()
	{
		currentmod -= 1;
	}
	
	public void redo()
	{
		currentmod += 1;
	}
	
	public int getIndex()
	{
		return currentmod;
	}
	
	public ArrayList<String> getListaction()
	{
		return listaction_;
	}
	
	public ArrayList<int[]> getAllPixel()
	{
		return pixellist_;
	}
	
	public int maxEl()
	{
		return mementosource_.size();
	}

}
