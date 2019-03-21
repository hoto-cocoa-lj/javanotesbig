
package cn.itcast.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.itcast.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SayHi_QNAME = new QName("http://service.itcast.cn/", "sayHi");
    private final static QName _FindCarsByUserResponse_QNAME = new QName("http://service.itcast.cn/", "findCarsByUserResponse");
    private final static QName _FindCarsByUser_QNAME = new QName("http://service.itcast.cn/", "findCarsByUser");
    private final static QName _SayHiResponse_QNAME = new QName("http://service.itcast.cn/", "sayHiResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.itcast.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SayHiResponse }
     * 
     */
    public SayHiResponse createSayHiResponse() {
        return new SayHiResponse();
    }

    /**
     * Create an instance of {@link FindCarsByUser }
     * 
     */
    public FindCarsByUser createFindCarsByUser() {
        return new FindCarsByUser();
    }

    /**
     * Create an instance of {@link FindCarsByUserResponse }
     * 
     */
    public FindCarsByUserResponse createFindCarsByUserResponse() {
        return new FindCarsByUserResponse();
    }

    /**
     * Create an instance of {@link SayHi }
     * 
     */
    public SayHi createSayHi() {
        return new SayHi();
    }

    /**
     * Create an instance of {@link Car }
     * 
     */
    public Car createCar() {
        return new Car();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.itcast.cn/", name = "sayHi")
    public JAXBElement<SayHi> createSayHi(SayHi value) {
        return new JAXBElement<SayHi>(_SayHi_QNAME, SayHi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCarsByUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.itcast.cn/", name = "findCarsByUserResponse")
    public JAXBElement<FindCarsByUserResponse> createFindCarsByUserResponse(FindCarsByUserResponse value) {
        return new JAXBElement<FindCarsByUserResponse>(_FindCarsByUserResponse_QNAME, FindCarsByUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindCarsByUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.itcast.cn/", name = "findCarsByUser")
    public JAXBElement<FindCarsByUser> createFindCarsByUser(FindCarsByUser value) {
        return new JAXBElement<FindCarsByUser>(_FindCarsByUser_QNAME, FindCarsByUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.itcast.cn/", name = "sayHiResponse")
    public JAXBElement<SayHiResponse> createSayHiResponse(SayHiResponse value) {
        return new JAXBElement<SayHiResponse>(_SayHiResponse_QNAME, SayHiResponse.class, null, value);
    }

}
