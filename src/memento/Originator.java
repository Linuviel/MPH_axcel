package memento;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Originator implements Serializable {
	
	private static final long 	serialVersionUID = -314171089120047242L;
	private BufferedImage 		state_;
	
	public void setMemento(Memento m)
	{
		state_ = m.getState();
	}
	
	public Memento createMemento()
	{
		return new Memento(state_);
	}
	
	public BufferedImage restore(Memento memento)
	{
		state_ = memento.getState();
		return state_;
	}

}
