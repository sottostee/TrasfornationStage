package rec.eventdef;

public abstract class EventDef {

	private String name;
	private String type = "EventDef";
	
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
