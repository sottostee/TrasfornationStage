package events;

import rec.eventdef.EventDef;

public class TurnOnEvent extends EventDef{

	private String name = "TurnOn";
	
	public TurnOnEvent(String name){
		super.setName(name);
	}

	public TurnOnEvent(){
		super.setName(name);
	}
	
}
