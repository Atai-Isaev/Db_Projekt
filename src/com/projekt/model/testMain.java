package com.projekt.model;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.math.BigDecimal;

public class testMain {
    public static void main(String[] args){
        try{
            // create an instance of `JAXBContext`
            JAXBContext context = JAXBContext.newInstance(Artikel.class);

            // create an instance of `Marshaller`
            Marshaller marshaller = context.createMarshaller();

            // enable pretty-print XML output
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // write XML to `StringWriter`
            StringWriter sw = new StringWriter();

            // create `Book` object
            Artikel artikel = new Artikel(13, "Hello", 2019, new BigDecimal(12),
                    new Hersteller(10, "Test"), new Kategorie(10, "Hello"));

            // convert book object to XML
            marshaller.marshal(artikel, sw);

            // print the XML
            System.out.println(sw);

        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }
}
