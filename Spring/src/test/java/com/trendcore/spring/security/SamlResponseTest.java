package com.trendcore.spring.security;

import net.shibboleth.utilities.java.support.component.ComponentInitializationException;
import net.shibboleth.utilities.java.support.xml.BasicParserPool;
import net.shibboleth.utilities.java.support.xml.XMLParserException;
import org.junit.Test;
import org.opensaml.core.config.InitializationException;
import org.opensaml.core.config.InitializationService;
import org.opensaml.core.xml.XMLObject;
import org.opensaml.core.xml.config.XMLObjectProviderRegistrySupport;
import org.opensaml.core.xml.io.Unmarshaller;
import org.opensaml.core.xml.io.UnmarshallerFactory;
import org.opensaml.core.xml.io.UnmarshallingException;
import org.opensaml.saml.saml2.core.impl.ResponseImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
import java.util.function.Function;


public class SamlResponseTest {

    public static final String RESPONSE = "PHNhbWxwOlJlc3BvbnNlIHhtbG5zOnNhbWxwPSJ1cm46b2FzaXM6bmFtZXM6dGM6\n" +
            "U0FNTDoyLjA6cHJvdG9jb2wiIHhtbG5zOmRzaWc9Imh0dHA6Ly93d3cudzMub3Jn\n" +
            "LzIwMDAvMDkveG1sZHNpZyMiIHhtbG5zOmVuYz0iaHR0cDovL3d3dy53My5vcmcv\n" +
            "MjAwMS8wNC94bWxlbmMjIiB4bWxuczpzYW1sPSJ1cm46b2FzaXM6bmFtZXM6dGM6\n" +
            "U0FNTDoyLjA6YXNzZXJ0aW9uIiB4bWxuczp4NTAwPSJ1cm46b2FzaXM6bmFtZXM6\n" +
            "dGM6U0FNTDoyLjA6cHJvZmlsZXM6YXR0cmlidXRlOlg1MDAiIHhtbG5zOnhzaT0i\n" +
            "aHR0cDovL3d3dy53My5vcmcvMjAwMS9YTUxTY2hlbWEtaW5zdGFuY2UiIERlc3Rp\n" +
            "bmF0aW9uPSJodHRwOi8vZHQtYW51cmFnLnRlY2hub2xvZ2ljLmNvbTo4MDgwL3Nz\n" +
            "by9jYWYvYXNzZXJ0aW9uQ29uc3VtZXJVcmwiIElEPSJpZC1SU2JBTUdYaVVzOVZW\n" +
            "YlJkVW44dVZsNUllV211TWh3bzlVYnNxOVczIiBJc3N1ZUluc3RhbnQ9IjIwMTkt\n" +
            "MDItMTRUMDg6NTE6MDFaIiBWZXJzaW9uPSIyLjAiPjxzYW1sOklzc3VlciBGb3Jt\n" +
            "YXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQtZm9ybWF0OmVu\n" +
            "dGl0eSI+aHR0cDovL0RULUFOVVJBRy50ZWNobm9sb2dpYy5jb206MTQxMDAvb2Ft\n" +
            "L2ZlZDwvc2FtbDpJc3N1ZXI+PHNhbWxwOlN0YXR1cz48c2FtbHA6U3RhdHVzQ29k\n" +
            "ZSBWYWx1ZT0idXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6Mi4wOnN0YXR1czpTdWNj\n" +
            "ZXNzIi8+PC9zYW1scDpTdGF0dXM+PHNhbWw6QXNzZXJ0aW9uIElEPSJpZC13YjlK\n" +
            "b2tMc2tzR29ObGp0VzF6eHNhUG1VVmFjTTgydXk4VjBTLS12IiBJc3N1ZUluc3Rh\n" +
            "bnQ9IjIwMTktMDItMTRUMDg6NTE6MDFaIiBWZXJzaW9uPSIyLjAiPjxzYW1sOklz\n" +
            "c3VlciBGb3JtYXQ9InVybjpvYXNpczpuYW1lczp0YzpTQU1MOjIuMDpuYW1laWQt\n" +
            "Zm9ybWF0OmVudGl0eSI+aHR0cDovL0RULUFOVVJBRy50ZWNobm9sb2dpYy5jb206\n" +
            "MTQxMDAvb2FtL2ZlZDwvc2FtbDpJc3N1ZXI+PGRzaWc6U2lnbmF0dXJlPjxkc2ln\n" +
            "OlNpZ25lZEluZm8+PGRzaWc6Q2Fub25pY2FsaXphdGlvbk1ldGhvZCBBbGdvcml0\n" +
            "aG09Imh0dHA6Ly93d3cudzMub3JnLzIwMDEvMTAveG1sLWV4Yy1jMTRuIyIvPjxk\n" +
            "c2lnOlNpZ25hdHVyZU1ldGhvZCBBbGdvcml0aG09Imh0dHA6Ly93d3cudzMub3Jn\n" +
            "LzIwMDAvMDkveG1sZHNpZyNyc2Etc2hhMSIvPjxkc2lnOlJlZmVyZW5jZSBVUkk9\n" +
            "IiNpZC13YjlKb2tMc2tzR29ObGp0VzF6eHNhUG1VVmFjTTgydXk4VjBTLS12Ij48\n" +
            "ZHNpZzpUcmFuc2Zvcm1zPjxkc2lnOlRyYW5zZm9ybSBBbGdvcml0aG09Imh0dHA6\n" +
            "Ly93d3cudzMub3JnLzIwMDAvMDkveG1sZHNpZyNlbnZlbG9wZWQtc2lnbmF0dXJl\n" +
            "Ii8+PGRzaWc6VHJhbnNmb3JtIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcv\n" +
            "MjAwMS8xMC94bWwtZXhjLWMxNG4jIi8+PC9kc2lnOlRyYW5zZm9ybXM+PGRzaWc6\n" +
            "RGlnZXN0TWV0aG9kIEFsZ29yaXRobT0iaHR0cDovL3d3dy53My5vcmcvMjAwMC8w\n" +
            "OS94bWxkc2lnI3NoYTEiLz48ZHNpZzpEaWdlc3RWYWx1ZT5FQm1TTFptWU1aL1lD\n" +
            "emtqSlVjUXJCRDhucEU9PC9kc2lnOkRpZ2VzdFZhbHVlPjwvZHNpZzpSZWZlcmVu\n" +
            "Y2U+PC9kc2lnOlNpZ25lZEluZm8+PGRzaWc6U2lnbmF0dXJlVmFsdWU+RmlJZ3Rs\n" +
            "SEEwU1dOWDJicTBhNEhLbG1PREk3dHhMQXJpZUtURE5RT3lSR0YxWFRsLy8wSDZU\n" +
            "TS9pcHhxK1dBa1d4RHdrU1RSeEFvWmJpcC9wTXo0UnBqQ2EvcnhsRXJ4emJySmJp\n" +
            "bndnRGZYeUx3SlZEOVVpYUhSaDE3S0h0bTM5M3JMVGlyQkRYMm9VVmlxNFpXUUM4\n" +
            "Z2E5aklvcjFxVkhXUWZubFM4TGtZPTwvZHNpZzpTaWduYXR1cmVWYWx1ZT48L2Rz\n" +
            "aWc6U2lnbmF0dXJlPjxzYW1sOlN1YmplY3Q+PHNhbWw6TmFtZUlEIEZvcm1hdD0i\n" +
            "dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6MS4xOm5hbWVpZC1mb3JtYXQ6ZW1haWxB\n" +
            "ZGRyZXNzIj5pZG11c2VyQDFlcS5jb208L3NhbWw6TmFtZUlEPjxzYW1sOlN1Ympl\n" +
            "Y3RDb25maXJtYXRpb24gTWV0aG9kPSJ1cm46b2FzaXM6bmFtZXM6dGM6U0FNTDoy\n" +
            "LjA6Y206YmVhcmVyIj48c2FtbDpTdWJqZWN0Q29uZmlybWF0aW9uRGF0YSBOb3RP\n" +
            "bk9yQWZ0ZXI9IjIwMTktMDItMTRUMDg6NTY6MDFaIiBSZWNpcGllbnQ9Imh0dHA6\n" +
            "Ly9kdC1hbnVyYWcudGVjaG5vbG9naWMuY29tOjgwODAvc3NvL2NhZi9hc3NlcnRp\n" +
            "b25Db25zdW1lclVybCIvPjwvc2FtbDpTdWJqZWN0Q29uZmlybWF0aW9uPjwvc2Ft\n" +
            "bDpTdWJqZWN0PjxzYW1sOkNvbmRpdGlvbnMgTm90QmVmb3JlPSIyMDE5LTAyLTE0\n" +
            "VDA4OjUxOjAxWiIgTm90T25PckFmdGVyPSIyMDE5LTAyLTE0VDA4OjU2OjAxWiI+\n" +
            "PHNhbWw6QXVkaWVuY2VSZXN0cmljdGlvbj48c2FtbDpBdWRpZW5jZT5odHRwOi8v\n" +
            "ZHQtYW51cmFnLnRlY2hub2xvZ2ljLmNvbTo4MDgwL3Nzby9jYWYvZmFjZVVybDwv\n" +
            "c2FtbDpBdWRpZW5jZT48L3NhbWw6QXVkaWVuY2VSZXN0cmljdGlvbj48L3NhbWw6\n" +
            "Q29uZGl0aW9ucz48c2FtbDpBdXRoblN0YXRlbWVudCBBdXRobkluc3RhbnQ9IjIw\n" +
            "MTktMDItMTRUMDg6NTE6MDFaIiBTZXNzaW9uSW5kZXg9ImlkLTdwb0NNTFBTN1g0\n" +
            "by1neTlVdUZFaElQVlFzY0dqbWdGai1BRmdxcDgiIFNlc3Npb25Ob3RPbk9yQWZ0\n" +
            "ZXI9IjIwMTktMDItMTRUMDk6NTE6MDFaIj48c2FtbDpBdXRobkNvbnRleHQ+PHNh\n" +
            "bWw6QXV0aG5Db250ZXh0Q2xhc3NSZWY+dXJuOm9hc2lzOm5hbWVzOnRjOlNBTUw6\n" +
            "Mi4wOmFjOmNsYXNzZXM6UGFzc3dvcmRQcm90ZWN0ZWRUcmFuc3BvcnQ8L3NhbWw6\n" +
            "QXV0aG5Db250ZXh0Q2xhc3NSZWY+PC9zYW1sOkF1dGhuQ29udGV4dD48L3NhbWw6\n" +
            "QXV0aG5TdGF0ZW1lbnQ+PC9zYW1sOkFzc2VydGlvbj48L3NhbWxwOlJlc3BvbnNl\n" +
            "Pg==";

    @Test
    public void decryptResponse() throws Exception {


        File privateKeyFile = new File("oam.pk8");
        FileInputStream inputStreamPrivateKey = new FileInputStream(privateKeyFile);
        byte[] encodedPrivateKey = new byte[(int) privateKeyFile.length()];
        inputStreamPrivateKey.read(encodedPrivateKey);
        inputStreamPrivateKey.close();

        // Create the private key.
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
        RSAPrivateKey privateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(privateKeySpec);

        // Create the credentials.
        /*BasicX509Credential decryptionCredential = new BasicX509Credential();
        decryptionCredential.setPrivateKey(privateKey);

        // Create a decrypter.
        Decrypter decrypter = new Decrypter(null, new StaticKeyInfoCredentialResolver(decryptionCredential), new InlineEncryptedKeyResolver());*/

    }

    @Test
    public void readKey() throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("oam.cert");

        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(resourceAsStream);

        PublicKey pubKey = cert.getPublicKey();


        System.out.println(pubKey);
        System.out.println(pubKey.getAlgorithm());
        System.out.println(pubKey.getFormat());
        System.out.println();

        Cipher cipher = Cipher.getInstance(pubKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);

        String[] lines = RESPONSE.split("\n");

        Arrays.stream(lines).forEach(s -> {
            String decrypted = null;
            try {
                decrypted = new String(cipher.doFinal(s.getBytes()));
                System.err.println(decrypted);
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            }

        });

    }

    @Test
    public void printResponse() throws Exception {
        System.out.println(RESPONSE);
    }

    @Test
    public void readOAMKeystore() throws Exception {
        File file = new File("D:\\OracleAccessManager\\fmw\\user_projects\\domains\\OIAMDomain\\config\\fmwconfig\\.oamkeystore");
        FileInputStream fis = new FileInputStream(file);
        KeyStore keyStore = KeyStore.getInstance("JCEKS");
        String pass = "qdvlj4gf4h7rub56d534rllrau";
        keyStore.load(fis, pass.toCharArray());
        fis.close();

        Enumeration<String> aliases = keyStore.aliases();

        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            if (alias.equals("stsprivatekeyalias")) {
                System.out.println(alias);
                Key key = keyStore.getKey(alias, pass.toCharArray());
                System.out.println(key);
            }
        }
    }

    @Test
    public void decodeResponse() throws Exception {
        System.out.println(new String(Base64.getMimeDecoder().decode(RESPONSE)));
    }

    @Test
    public void parseResponse() throws Exception {
        String samlResponse = ((Function<String, String>) s -> new String(Base64.getMimeDecoder().decode(RESPONSE))).apply(RESPONSE);
    ResponseImpl response = (ResponseImpl) parsedSAMLContents(samlResponse);
        response.getAssertions().forEach(assertion -> {
        System.out.println(assertion.getSubject().getNameID().getValue());
    });
}

    private XMLObject parsedSAMLContents(String samlRespone) {

        try {
            InitializationService.initialize();
        } catch (InitializationException e) {
            e.printStackTrace();
        }


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

            XMLObject unmarshall = unmarshaller.unmarshall(metadataRoot);
            return unmarshall;
        } catch (ComponentInitializationException e) {
            throw new RuntimeException(e);
        } catch (UnmarshallingException e) {
            throw new RuntimeException(e);
        } catch (XMLParserException e) {
            throw new RuntimeException(e);
        }
    }
}
