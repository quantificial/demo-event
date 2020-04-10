package demo.demoevent;

import org.springframework.context.ApplicationEvent;

import lombok.Data;

@Data
public class CustomEvent extends ApplicationEvent{

	private String data;
	
	public CustomEvent(Object source, String data) {
		super(source);
		this.data = data;
	}
	

}
