package rec.reificator;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.conf.EventProcessingOption;
import org.drools.definition.type.FactType;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.FactHandle;
import org.drools.runtime.rule.impl.NativeQueryResults;

import rec.affect.Affect;

/**
 * This is a sample class to launch a rule.
 */
public class Reificator {

	private StatefulKnowledgeSession ksession;
	
	private StringBuilder strbuild = new StringBuilder();
	
	private static final String PACKAGE = "rec.reificator";
	
    public void init() {
        try {
            // load up the knowledge base
        	 KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
             kbuilder.add(ResourceFactory.newClassPathResource("Descriptive.drl"), ResourceType.DRL);
             KnowledgeBuilderErrors errors = kbuilder.getErrors();
             if (errors.size() > 0) {
                 for (KnowledgeBuilderError error: errors) {
                     System.err.println(error);
                 }
                 throw new IllegalArgumentException("Could not parse knowledge.");
             }
             
             KnowledgeBaseConfiguration config = KnowledgeBaseFactory
     				.newKnowledgeBaseConfiguration();
     		config.setOption(EventProcessingOption.STREAM);
     		KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase(config);
             
//             KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
             
             kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
            ksession = kbase.newStatefulKnowledgeSession();
            KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newFileLogger(ksession, "test");
            
            
            //set the stringbuilder
            ksession.setGlobal("strbuild", strbuild);
            
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    public void stamp() throws InstantiationException, IllegalAccessException{
    	
    	char[] dst = new char[strbuild.length()];
    	strbuild.getChars(0, strbuild.length(), dst, 0);
        
    	System.out.println("descriptive drl:\n\n");
        for(int i=0; i<dst.length; i++)
        	System.out.print(dst[i]);
    }
    
    public void generateDRL() throws FileNotFoundException{
    	 FileOutputStream file = new FileOutputStream("General.drl");
         PrintStream out = new PrintStream(file);
         
         char[] dst = new char[strbuild.length()];
     	 strbuild.getChars(0, strbuild.length(), dst, 0);
     	
         System.out.println("create General.drl.\n\n");
         for(int i=0; i<dst.length; i++)
         	out.print(dst[i]);
    }
   
    public FactHandle notify(String type, String name, Object obj) {
		FactHandle handle = null;
		
		try {
			FactType facttype = ksession.getKnowledgeBase().getFactType(PACKAGE,
					type);

			Object p = facttype.newInstance();
			facttype.set(p, "name", name);
			
			if(type.equals("Affect")){
				facttype.set(p, "event", ((Affect) obj).getEvent());
				facttype.set(p, "fluent", ((Affect) obj).getFluent());
				facttype.set(p, "condition", ((Affect) obj).getCondition());
			}
			
			handle = ksession.insert(p);

			ksession.fireAllRules();

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return handle;
    }
    
}
