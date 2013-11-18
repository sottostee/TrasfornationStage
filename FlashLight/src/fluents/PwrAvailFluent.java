package fluents;

import rec.fluentdef.FluentDef;

public class PwrAvailFluent extends FluentDef{

	private String name = "PwrAvail";
	
	public PwrAvailFluent(String name){
		super.setName(name);
	}

	public PwrAvailFluent(){
		super.setName(name);
	}
}
