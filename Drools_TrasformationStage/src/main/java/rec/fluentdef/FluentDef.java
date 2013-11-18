package rec.fluentdef;

public abstract class FluentDef {
	
	private String name;
	private String type = "FluentDef";
	
	public String getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
}
