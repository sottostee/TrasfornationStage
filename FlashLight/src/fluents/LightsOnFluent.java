package fluents;

import rec.fluentdef.FluentDef;

public class LightsOnFluent extends FluentDef{

	private String name = "LightsOn";
	
	public LightsOnFluent(String name){
		super.setName(name);
	}

	public LightsOnFluent(){
		super.setName(name);
	}
	
}
