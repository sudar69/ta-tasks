package ua.edu.sumdu.ta.alexandersudarenko.lab.tests;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javax.xml.bind.*;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import java.io.IOException;

public class MarshallerToXML {

    public static void marshaller(TestCaseList testCaseList) throws Exception {
            /*JAXBContext jaxbContext = JAXBContext.newInstance(TestCaseList.class, ListMessage.class, TestMessage.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            jaxbMarshaller.marshal(testCaseList, System.out);*/
            
        // Create Transformer
        TransformerFactory tf = TransformerFactory.newInstance();
        StreamSource xslt = new StreamSource("stylesheet.xsl");
        Transformer transformer = tf.newTransformer(xslt);
 
        // Source
        JAXBContext jc = JAXBContext.newInstance(TestCaseList.class, ListMessage.class, TestMessage.class);
        JAXBSource source = new JAXBSource(jc, testCaseList);
 
        // Result
        StreamResult result = new StreamResult("index.html");
         
        // Transform
        transformer.transform(source, result);
        
        jc.generateSchema(new SchemaOutputResolver() {
 
            @Override
            public Result createOutput(String namespaceURI, String suggestedFileName)
                    throws IOException {
                StreamResult result = new StreamResult(System.out);
                result.setSystemId(suggestedFileName);
                return result;
            }
             
        });
        
            
    }
}
