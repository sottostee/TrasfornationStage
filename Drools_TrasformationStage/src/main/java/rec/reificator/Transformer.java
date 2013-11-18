package rec.reificator;

import java.io.FileNotFoundException;

import rec.affect.Affect;
import rec.eventdef.EventDef;
import rec.eventdef.InitiallyDef;
import rec.fluentdef.FluentDef;
import rec.reificator.Reificator;

public class Transformer {

	Reificator ref = new Reificator();

	
	public void insertInitially(){
		EventDef e = new InitiallyDef();
		ref.notify(e.getType(), e.getName(), e);
	}
	
	public void insertEvent(EventDef e){
		ref.notify(e.getType(), e.getName(), e);
	}
	
	public void insertFluent(FluentDef f){
		ref.notify(f.getType(), f.getName(), f);
	}
	
	public void insertSample(Affect s){
		ref.notify(s.getType(), s.getName(), s);
	}
	
	public void init(){
		ref.init();
	}
	
	public void stamp() throws InstantiationException, IllegalAccessException{
		ref.stamp();
	}
	
	public void generate() throws FileNotFoundException{
		ref.generateDRL();
	}
}
