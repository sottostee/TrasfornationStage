package main;


import java.io.FileNotFoundException;
import java.util.ArrayList;

import rec.affect.Affect;
import rec.eventdef.EventDef;
import rec.eventdef.InitiallyDef;
import rec.fluentdef.FluentDef;
import events.PwrFailEvent;
import events.PwrRstrEvent;
import events.TurnOffEvent;
import events.TurnOnEvent;
import fluents.LightsOnFluent;
import fluents.PwrAvailFluent;
import fluents.SwitchOnFluent;
import rec.reificator.Transformer;

public class Flashlight{

	//flashlight example:

	private static String pwrrstr_condition = "\t$ff: SwitchOn()\n\t" +
			"MVI( fluent == $ff, start<=$s, (start+length)>=$s, value!=0 )";
	private static String turnon_condition = "\t$ff: PwrAvail()\n\t" +
			"MVI( fluent == $ff, start<=$s, (start+length)>=$s, value!=0 )";
	private static String empty_condition = "";
	
	Transformer trs = new Transformer();
	ArrayList<EventDef> eventDefs = new ArrayList<EventDef>();
	ArrayList<FluentDef> fluentDefs = new ArrayList<FluentDef>();;
	ArrayList<Affect> affects = new ArrayList<Affect>();;
	
	public Flashlight(){
		
		//to create objects (events's def, fluents's def and affect)
		createObjects();
		
		//to initialize KB
		trs.init();
		
	}
	
	private void createObjects(){
		//Initially
		InitiallyDef init = new InitiallyDef();
		eventDefs.add(init);
		
		//Event's definitions
		PwrFailEvent pwrfail = new PwrFailEvent();
		PwrRstrEvent pwrrstr = new PwrRstrEvent();
		TurnOffEvent turnoff = new TurnOffEvent();
		TurnOnEvent turnon = new TurnOnEvent();
		eventDefs.add(pwrfail);	eventDefs.add(pwrrstr);
		eventDefs.add(turnoff);	eventDefs.add(turnon);
		
		
		//Fluent's definitions
		LightsOnFluent lightson = new LightsOnFluent();
		PwrAvailFluent pwravail = new PwrAvailFluent();
		SwitchOnFluent switchon = new SwitchOnFluent();
		fluentDefs.add(lightson);		fluentDefs.add(pwravail);		fluentDefs.add(switchon);
		
		//Affects
		Affect s1 = new Affect(pwrfail.getName(), pwravail.getName());
		Affect s2 = new Affect(pwrfail.getName(), lightson.getName());
		Affect s3 = new Affect(pwrrstr.getName(), lightson.getName(), pwrrstr_condition);
		Affect s4 = new Affect(pwrrstr.getName(), pwravail.getName());
		Affect s5 = new Affect(turnoff.getName(), lightson.getName());
		Affect s6 = new Affect(turnoff.getName(), switchon.getName());
		Affect s7 = new Affect(turnon.getName(), lightson.getName(), turnon_condition);
		Affect s8 = new Affect(turnon.getName(), switchon.getName());
		affects.add(s1);	affects.add(s2);	affects.add(s3);	affects.add(s4);
		affects.add(s5);	affects.add(s6);	affects.add(s7);	affects.add(s8);
	}
	
	private void insertObjects(){
		for(int i=0; i<eventDefs.size(); i++)
			trs.insertEvent(eventDefs.get(i));
		
		for(int i=0; i<fluentDefs.size(); i++)
			trs.insertFluent(fluentDefs.get(i));
		
		for(int i=0; i<affects.size(); i++)
			trs.insertSample(affects.get(i));
	}
	
	public void start(){
		//to insert objects and fire all rules of PM
		insertObjects();
	}
	
	public void stampDRL() throws InstantiationException, IllegalAccessException{
		trs.stamp();
	}
	
	public void generateDRL() throws FileNotFoundException{
		trs.generate();
	}
}
