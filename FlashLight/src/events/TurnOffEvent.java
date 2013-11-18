package events;

import rec.eventdef.EventDef;

public class TurnOffEvent extends EventDef{

	private String name = "TurnOff";
	
	public TurnOffEvent(String name){
		super.setName(name);
	}
	
	public TurnOffEvent(){
		super.setName(name);
	}
}
