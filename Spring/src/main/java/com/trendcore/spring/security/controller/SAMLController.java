package com.trendcore.spring.security.controller;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import net.shibboleth.utilities.java.support.xml.BasicParserPool;
import net.shibboleth.utilities.java.support.xml.XMLParserException;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.saml2.core.Response;
import org.opensaml.saml.saml2.core.impl.ResponseImpl;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.servlet.http.HttpServletRequest;
import java.io.StringReader;
import java.util.Base64;
import java.util.Map;
import java.util.function.Function;

@RestController
public class SAMLController {

    {
        try {
            InitializationService.initialize();
        } catch (InitializationException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/faceUrl", method = RequestMethod.GET)
    @ResponseBody
    public Object faceUrl() {
        System.out.println(" -> ");
        return "This is login page.";
    }

    @RequestMapping(value = "/assertionConsumerUrl", method = RequestMethod.POST)
    @ResponseBody
    public void assertionConsumerUrl(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        parameterMap.entrySet().stream().forEach(stringEntry -> System.out.println(stringEntry.getKey() + " " + stringEntry.getValue()[0]));

        //process this response
        String[] samlResponses = parameterMap.get("SAMLResponse");

        if (samlResponses.length == 0) {
            //error
            System.out.println("Error not received saml response");

        } else {
            String samlRespone = samlResponses[0];
            parse(samlRespone);
        }

    }

    private void parse(String samlRespone) {
        String samlResponse = ((Function<String, String>) s -> new String(Base64.getMimeDecoder().decode(s))).apply(samlRespone);
        ResponseImpl response = (ResponseImpl) parsedSAMLContents(samlResponse);
        response.getAssertions().forEach(assertion -> System.out.println(assertion.getSubject().getNameID().getValue()));
    }

    private Response parsedSAMLContents(String samlRespone) {

        StringReader reader = new StringReader(samlRespone);
        BasicParserPool parser = new BasicParserPool();
        try {
            parser.initialize();

            Document inCommonMDDoc = null;

            inCommonMDDoc = parser.parse(reader);

            Element metadataRoot = inCommonMDDoc.getDocumentElement();

            // Get appropriate unmarshaller
            UnmarshallerFactory unmarshallerFactory = XMLObjectProviderRegistrySupport.getUnmarshallerFactory();
            Unmarshaller unmarshaller = unmarshallerFactory.getUnmarshaller(metadataRoot);

            Response unmarshall = (Response) unmarshaller.unmarshall(metadataRoot);
            return unmarshall;
        } catch (ComponentInitializationException e) {
            throw new RuntimeException(e);
        } catch (UnmarshallingException e) {
            throw new RuntimeException(e);
        } catch (XMLParserException e) {
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "/logoutUrl", method = RequestMethod.GET)
    @ResponseBody
    public void logoutUrl(@RequestBody Object body) {
        System.out.println(" -> " + body);
    }

}
