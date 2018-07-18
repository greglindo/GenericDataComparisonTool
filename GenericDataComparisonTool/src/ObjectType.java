import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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
	
	/* Will not use as this process is an expensive operation instead use saveToXmlFile */
	public void saveToFile(String xmlObjectTypeData)
	{	
		try {			
			String filePath = CreateDataFilePath();
			File dataFile = new File(filePath);
			FileWriter writer;
			
			if (dataFile.createNewFile())
			{
				writer = new FileWriter(dataFile);
				writer.write(xmlObjectTypeData);
				writer.close();
			}
			else {
				writer = new FileWriter(dataFile, false);
				writer.write(xmlObjectTypeData);
				writer.close();
			}
			
		} catch (IOException ex) {
			Logger.getLogger(ObjectType.class.getName()).log(Level.SEVERE, "Unable to save data!", ex);
		}
	}
	
	public void saveToXmlFile(String xmlObjectTypeData)
			throws SAXException, ParserConfigurationException, IOException 
	{
		String filePath = CreateDataFilePath();
		
		try {
			//	Parse the data inputted
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(xmlObjectTypeData)));
			
			//	Write the parsed document to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			
			try {
				
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(document);
				 
				StreamResult result = new StreamResult(new File(filePath));
				
				try {
					transformer.transform(source, result);
				} catch (TransformerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			} catch (TransformerConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception ex) {
			Logger.getLogger(ObjectType.class.getName()).log(Level.SEVERE, "Unable to save data!", ex);
		}
		
		
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
}
