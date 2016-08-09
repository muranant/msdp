/*******************************************************************************
 ******************************************************************************/
/**
 * Copyright (c) 2009-2012 Cloupia, Inc. All rights reserved.
 */
package com.msdp.worker.utils;

import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

public class XMLMarshaller
{
    static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";

    public static String marshall(Object item) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(item, sw); // save to StringWriter, you can then call

        return sw.toString();
    }

    public static String marshallWithoutXMLHeader(Object item) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        marshaller.marshal(item, sw); // save to StringWriter, you can then call
    
        return sw.toString();
    }
    
    public static void marshall(Object item, OutputStream os) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = context.createMarshaller();

        marshaller.marshal(item, os);

    }

    public static String marshall(Object item, boolean stripHeader) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(item.getClass());
        Marshaller marshaller = context.createMarshaller();
        StringWriter sw = new StringWriter();
        marshaller.marshal(item, sw); // save to StringWriter, you can then call

        String result = sw.toString();

        if (stripHeader)
        {
            result = result.trim();

            if (result.startsWith(XML_HEADER))
            {
                if (result.length() > XML_HEADER.length())
                {
                    result = result.substring(XML_HEADER.length());
                }
            }
        }

        return result;
    }

    public static String stripHeader(String result)
    {
        String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        result = result.trim();

        if (result.startsWith(xmlHeader))
        {
            if (result.length() >= xmlHeader.length())
            {
                result = result.substring(xmlHeader.length());
            }
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public static Object unmarshall(Class toClass, String str) throws Exception
    {
        ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream(str.getBytes());
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // note: setting schema to null will turn validator off
        unmarshaller.setSchema(null);

        Object xmlObject = toClass.cast(unmarshaller.unmarshal(xmlContentBytes));

        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    public static Object unmarshall(Class toClass, InputStream in) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // note: setting schema to null will turn validator off
        unmarshaller.setSchema(null);

        Object xmlObject = toClass.cast(unmarshaller.unmarshal(in));

        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    public static Object unmarshall(Class toClass, StreamSource ss) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // note: setting schema to null will turn validator off
        unmarshaller.setSchema(null);

        Object xmlObject = toClass.cast(unmarshaller.unmarshal(ss));

        return xmlObject;
    }

    @SuppressWarnings("unchecked")
    public static Object unmarshall(Class toClass, Element element) throws Exception
    {
        JAXBContext context = JAXBContext.newInstance(toClass);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // note: setting schema to null will turn validator off
        unmarshaller.setSchema(null);

        JAXBElement xmlObject = unmarshaller.unmarshal(element, toClass);

        return xmlObject.getValue();
    }
}
