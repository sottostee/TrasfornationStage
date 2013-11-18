package fluents;

import rec.fluentdef.FluentDef;

public class SwitchOnFluent extends FluentDef{

	private String name = "SwitchOn";
	
	public SwitchOnFluent(String name){
		super.setName(name);
	}

	public SwitchOnFluent(){
		super.setName(name);
	}
}
