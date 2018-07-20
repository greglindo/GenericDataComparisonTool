import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ObjectType {
	private String name;
	private ArrayList<Characteristic> characteristics;
	private String dataFile;
		
	public ObjectType()
	{
		
	}
	
	public ObjectType(String name, ArrayList<Characteristic> characteristics)
	{
		this.name = name;
		this.characteristics = characteristics;
	}
	
	public ObjectType(String name)
	{
		GetObjectTypeByName(name);
	}
	
	public String convertToString()
	{
		String characteristicLine = "";
		String newLine = "\n";
		
		for (int x = 0; x < this.characteristics.size(); x++)
		{
			characteristicLine += this.characteristics.get(x).convertToString() + newLine; 
		}
		
		return "<data>" +
			   "<type>" + 
			   "<name>" + this.name + "</name>" +
			   "<characteristics>" + 
			   characteristicLine + 
			   "</characteristics>" +
			   "</type>" +
			   "</data>";
	}
	
	
	public void saveDataFile() throws Exception {
		XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
		XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(this.dataFile));
		
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		
		StartDocument startDocument = eventFactory.createStartDocument();
		eventWriter.add(startDocument);
		
		StartElement startDataElement = eventFactory.createStartElement("", "", "data");
		eventWriter.add(startDataElement);
		eventWriter.add(end);
		
		StartElement startTypeElement = eventFactory.createStartElement("", "", "type");
		eventWriter.add(startTypeElement);
		eventWriter.add(end);
		
		createNode(eventWriter, "name", this.name);
				
		StartElement startCharacteristicsElement = eventFactory.createStartElement("", "", "characteristics");
		eventWriter.add(startCharacteristicsElement);
		eventWriter.add(end);
		
		
		for (int x = 0; x < this.characteristics.size(); x++)
		{
			StartElement startItemCharacteristicElement = eventFactory.createStartElement("", "", "characteristic");
			eventWriter.add(startItemCharacteristicElement);
			eventWriter.add(end);
			
			Characteristic item = this.characteristics.get(x);
			
			createNode(eventWriter, "attribute", item.getAttribute()); 
			//createNode(eventWriter, "value", item.getValue());
			/*createNode(eventWriter, "minimumValue", this.characteristics.get(x).getMinimumValue()+"");			
			createNode(eventWriter, "firstQuartile", this.characteristics.get(x).getFirstQuartile()+"");
			createNode(eventWriter, "medianValue", this.characteristics.get(x).getMedianValue()+"");
			createNode(eventWriter, "thirdQuartile", this.characteristics.get(x).getThirdQuartile()+"");
			createNode(eventWriter, "maximumValue", this.characteristics.get(x).getMaximumValue()+"");
			createNode(eventWriter, "averageValue", this.characteristics.get(x).getAverageValue()+"");
			createNode(eventWriter, "weightValue", this.characteristics.get(x).getScoreWeightValue()+"");*/
			//createNode(eventWriter, "betterValue", item.getBetterValue().toString());
			
			eventWriter.add(eventFactory.createEndElement("", "", "characteristic"));
			eventWriter.add(end);
		}
		
		eventWriter.add(eventFactory.createEndElement("", "", "characteristics"));
		eventWriter.add(end);
		
		eventWriter.add(eventFactory.createEndElement("", "", "type"));
		eventWriter.add(end);
		
		eventWriter.add(eventFactory.createEndElement("", "", "data"));
		eventWriter.add(end);
		
		eventWriter.add(eventFactory.createEndDocument());
		eventWriter.close();
		
	}
	public ObjectType GetObjectTypeByName(String objectTypeName)
	{
		String fileDirectory = "C:\\genericdatacomparison\\";
		String fileName = "gdc_data";
		String fileExtension = ".xml";
		String filePath = fileDirectory + fileName + fileExtension;
		
		String xmlData = GetFromXmlDataFromFile(filePath);
		return MapXmlDataToObjectType(xmlData);
	}
	
	private String GetFromXmlDataFromFile(String filePath)
	{
		File xmlDataFile = new File(filePath);
		
		try {			
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(xmlDataFile);
			document.getDocumentElement().normalize();
			
		} catch (Exception ex) {
			
		}
		
		
		return "";
	}
	
	private ObjectType MapXmlDataToObjectType(String xmlData)
	{
		ObjectType result = new ObjectType();
		this.name = "";
		this.characteristics = new ArrayList<Characteristic>();
		
		return result;
	}
	
	private String CreateDataFilePath()
	{	
		String fileDirectory = "C:\\genericdatacomparison\\";
		String fileName = "gdc_data";
		String fileExtension = ".xml";
		
		File directory = new File(fileDirectory);
		
		if (!directory.exists())
		{
			directory.mkdir();
		}
		
		return fileDirectory + fileName + fileExtension;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public ArrayList<Characteristic> getCharacteristics()
	{
		return this.characteristics;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setCharacteristics(ArrayList<Characteristic> characteristics)
	{
		this.characteristics = characteristics;
	}
	
	public String getDataFile()
	{
		return this.dataFile;
	}
	
	public void setDataFile(String file)
	{
		this.dataFile = file;
	}
	
	private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		
		StartElement startElement = eventFactory.createStartElement("", "", name);
		eventWriter.add(tab);
		eventWriter.add(startElement);
		
		Characters characters = eventFactory.createCharacters(value);
		eventWriter.add(characters);
		
		EndElement endElement = eventFactory.createEndElement("", "", name);
		eventWriter.add(endElement);
		eventWriter.add(end);
	}
}
