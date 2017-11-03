package config;
import java.io.File; 
import java.io.IOException;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import javax.swing.plaf.basic.BasicSliderUI.ActionScroller;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.Node; 
import org.w3c.dom.NodeList; 
import org.xml.sax.SAXException;

import com.sun.org.apache.bcel.internal.util.Class2HTML;

import interceptor.dispatcher.Dispatcher.MyInterceptorStack;
import interceptor.frame.services.ActionOutside;
import interceptor.interceptors.MyInterceptor; 
 
public class InterceptorConfigXMLParser { 
  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
  //Load and parse XML file into DOM Document
  private Document parse(String filePath) { 
     Document xmlDocument = null; 
     try { 
        //DOM parser instance 
        DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
        //parse an XML file into a DOM tree 
        xmlDocument = builder.parse(new File(filePath)); 
     } catch (ParserConfigurationException e) { 
        e.printStackTrace();  
     } catch (SAXException e) { 
        e.printStackTrace(); 
     } catch (IOException e) { 
        e.printStackTrace(); 
     } 
     return xmlDocument ; 
  } 
    
  public static Map < Class<? extends ActionOutside>, MyInterceptorStack>  getInterceptorMap()  { 
        InterceptorConfigXMLParser parser = new InterceptorConfigXMLParser(); 
        Document document = parser.parse("interceptorConfig.xml"); 
        //get root element : <interceptorConfig>
        Element rootElement = document.getDocumentElement(); 
        
        Map < Class<? extends ActionOutside>, MyInterceptorStack> iConfig = new HashMap<Class<? extends ActionOutside>, MyInterceptorStack>();
 
        //traverse child elements : actions ( build-in service in concreteFramework )
        NodeList nodes = rootElement.getChildNodes(); 
        for (int i=0; i < nodes.getLength(); i++) 
        { 
           Node node = nodes.item(i); // for each action 
           if (node.getNodeType() == Node.ELEMENT_NODE) {  
              Element actionElement   = (Element) node; 
              String  actionClassName = actionElement.getAttribute("name"); 
              //process child element 
              /**
               * TODO:: Here find the action defined in concrete framework.
               * 		Next is using name to bind the interceptors.
               * 		NOTHING TO DO HERE ..... 
               * */
              
              NodeList interceptorNodes = actionElement.getElementsByTagName("interceptor");  // get list of interceptors 
              
              MyInterceptorStack interceptorStack = new MyInterceptorStack();
              for(int j = 0 ; j < interceptorNodes.getLength(); j++){  
            	 if (node.getNodeType() == Node.ELEMENT_NODE) {   
            	     Node interceptorNode = interceptorNodes.item(j);
            	     Element element = (Element)interceptorNode ;	            			  
		             String interceptorClassName = element.getAttribute("name") ;
		               /**
		                * TODO:: Here find each coresponding elements defined in concrete framework.
		                * 		HERE Should using interceptor name to bind the linked action.
		                * 		NOTHING TO DO HERE ..... 
		                * */  
    		        Class<?> concreteInterceptorClass;
					try {
						concreteInterceptorClass = Class.forName(interceptorClassName); 
						interceptorStack.push( (MyInterceptor) concreteInterceptorClass.newInstance() ); 
					} catch (ClassNotFoundException | InstantiationException e) {
						System.err.println("Failed to initial Interceptor stack: Please ensure your xml file format is correct.");
						e.printStackTrace();
						System.exit(-1); 
					}   catch (IllegalAccessException e) {
						System.err.println("Cannot have an instanciation : "+ interceptorClassName +"in Interceptor Config ");
						e.printStackTrace();
						System.exit(-1); 
					}   
            	 }
              } 
              try {
				iConfig.put( (Class<? extends ActionOutside>) Class.forName(actionClassName), interceptorStack);
			} catch (ClassNotFoundException e) { 
				System.err.println("cannot find class: " + actionClassName + "in your project : all action class should be one of your project..");
				e.printStackTrace();
			}
           } 
        }
		return iConfig ;  
  } 
}