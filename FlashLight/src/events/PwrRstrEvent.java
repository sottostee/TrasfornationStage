package events;

import rec.eventdef.EventDef;

public class PwrRstrEvent extends EventDef {

	private String name = "PwrRstr";
	
	public PwrRstrEvent(String name){
		super.setName(name);
	}
	
	public PwrRstrEvent(){
		super.setName(name);
	}
	
}
