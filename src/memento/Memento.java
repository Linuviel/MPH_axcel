package memento;

import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Memento implements Serializable {
	
	private static final long 	serialVersionUID = -314171089120047242L;
	protected BufferedImage 		state_;
	protected String 				statename_;
	
	public Memento(Object state)
	{
		state_ = (BufferedImage) state;
	}
	
	public BufferedImage getState()
	{
		return state_;
	}
	
	public void setState(BufferedImage state)
	{
		state_ = state;
	}
	
	public String getStateName_()
	{
		return statename_;
	}
	
	public void setStateName_(String statename_)
	{
		this.statename_ = statename_;
	}
}
