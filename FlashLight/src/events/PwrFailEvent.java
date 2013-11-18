package events;

import rec.eventdef.EventDef;

public class PwrFailEvent extends EventDef{

	private String name = "PwrFail";
	
	public PwrFailEvent(String name){
		super.setName(name);
		
	}
	
	public PwrFailEvent(){
		super.setName(name);
		
	}
}
