package rec.affect;

public class Affect {
	
	private String name, event, fluent, type = "Affect", condition;
	
	public Affect(String name, String event, String fluent, String condition){
		this.name = name;
		this.event = event;
		this.fluent = fluent;
		this.condition = condition;
	}
	
	public Affect(String event, String fluent, String condition){
		this.event = event;
		this.fluent = fluent;
		this.condition = condition;
	}
	
	public Affect(String event, String fluent){
		this.name = this.type;
		this.event = event;
		this.fluent = fluent;
	}
	
	public String getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getEvent(){
		return event;
	}
	
	public void setEvent(String event){
		this.event = event;
	}
	
	public String getFluent(){
		return fluent;
	}
	
	public void setFluent(String fluent){
		this.fluent = fluent;
	}
	
	public void setCondition(String condition){
		this.condition = condition;
	}
	
	public String getCondition(){
		return condition;
	}
}
