import org.xml.sax.InputSource;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import  java.io.StringReader;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SaxParserDataStore extends DefaultHandler {
    SmartDoorlocks sdl;
    SmartLightings sl;
    SmartDoorbells wt;
    SmartSpeakers va;
	SmartThermostats smttherm;

    static HashMap<String,SmartDoorlocks> sdls;
    static HashMap<String,SmartLightings> sls;
    static HashMap<String,SmartDoorbells> wts;
	static HashMap<String,SmartSpeakers> vas;
	static HashMap<String,SmartThermostats> smttherms;
    
    String consoleXmlFileName;
	HashMap<String,String> accessoryHashMap;
    String elementValueRead;
	String currentElement="";
    public SaxParserDataStore()
	{
	}
	public SaxParserDataStore(String consoleXmlFileName) {
    this.consoleXmlFileName = consoleXmlFileName;
    
    sdls = new HashMap<String, SmartDoorlocks>();
    sls=new HashMap<String, SmartLightings>();
    wts=new HashMap<String, SmartDoorbells>();
    vas=new HashMap<String, SmartSpeakers>();
	smttherms=new HashMap<String, SmartThermostats>(); 
	parseDocument();
    }

   //parse the xml using sax parser to get the data
    private void parseDocument() 
	{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try 
		{
	    SAXParser parser = factory.newSAXParser();
	    parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
	}

	@Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("sdl")) 
		{
			currentElement="sdl";
			sdl = new SmartDoorlocks();
            sdl.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("sl")) 
		{
			currentElement="sl";
			sl = new SmartLightings();
            sl.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("wt")) 
		{
			currentElement="wt";
			wt = new SmartDoorbells();
            wt.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("va")) 
		{
			currentElement="va";
			va = new SmartSpeakers();
            va.setId(attributes.getValue("id"));
		}
		if (elementName.equalsIgnoreCase("smttherm")) 
		{
			currentElement="smttherm";
			smttherm = new SmartThermostats();
            smttherm.setId(attributes.getValue("id"));
		}
        
	}

	@Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equals("sdl")) {
			sdls.put(sdl.getId(),sdl);
			return;
        }
        if (element.equals("sl")) {
			sls.put(sl.getId(),sl);
			return;
        }
        if (element.equals("wt")) {
			wts.put(wt.getId(),wt);
			return;
        }
         if (element.equals("va")) {
			vas.put(va.getId(),va);
			return;
        }
		if (element.equals("smttherm")) {
			smttherms.put(smttherm.getId(),smttherm);
			return;
        }
 


	    if (element.equalsIgnoreCase("image")) {
		    if(currentElement.equals("sdl"))
				sdl.setImage(elementValueRead);
			if(currentElement.equals("sl"))
				sl.setImage(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setImage(elementValueRead);
			if(currentElement.equals("va"))
				va.setImage(elementValueRead);
			if(currentElement.equals("smttherm"))
				smttherm.setImage(elementValueRead);  
			return;
        }
        

		if (element.equalsIgnoreCase("discount")) {
            if(currentElement.equals("sdl"))
				sdl.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("sl"))
				sl.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wt"))
				wt.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("va"))
				va.setDiscount(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smttherm"))
				smttherm.setDiscount(Double.parseDouble(elementValueRead));
      
			return;
	    }


		if (element.equalsIgnoreCase("condition")) {
            if(currentElement.equals("sdl"))
				sdl.setCondition(elementValueRead);
			if(currentElement.equals("sl"))
				sl.setCondition(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setCondition(elementValueRead);
			if(currentElement.equals("va"))
				va.setCondition(elementValueRead);
			if(currentElement.equals("smttherm"))
				smttherm.setCondition(elementValueRead);
               
			return;  
		}

		if (element.equalsIgnoreCase("manufacturer")) {
            if(currentElement.equals("sdl"))
				sdl.setRetailer(elementValueRead);
			if(currentElement.equals("sl"))
				sl.setRetailer(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setRetailer(elementValueRead);
			if(currentElement.equals("va"))
				va.setRetailer(elementValueRead);
			if(currentElement.equals("smttherm"))
				smttherm.setRetailer(elementValueRead);         
			return;
		}

        if (element.equalsIgnoreCase("name")) {
            if(currentElement.equals("sdl"))
				sdl.setName(elementValueRead);
			if(currentElement.equals("sl"))
				sl.setName(elementValueRead);
			if(currentElement.equals("wt"))
				wt.setName(elementValueRead);
			if(currentElement.equals("va"))
				va.setName(elementValueRead);		
			if(currentElement.equals("smttherm"))
				smttherm.setName(elementValueRead);          
			return;
	    }
	
        if(element.equalsIgnoreCase("price")){
			if(currentElement.equals("sdl"))
				sdl.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("sl"))
				sl.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("wt"))
				wt.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("va"))
				va.setPrice(Double.parseDouble(elementValueRead));
			if(currentElement.equals("smttherm"))
				smttherm.setPrice(Double.parseDouble(elementValueRead));         
			return;
        }
    }



	@Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }



 public static void addHashmap() {
		String TOMCAT_HOME = System.getProperty("catalina.home");	
		new SaxParserDataStore(TOMCAT_HOME+"//webapps//Assignment_1//ProductCatalog.xml");
    } 
}