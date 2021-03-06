
package ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ws package. 
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

    private final static QName _GetProductList_QNAME = new QName("http://model.com/", "getProductList");
    private final static QName _GetProductListResponse_QNAME = new QName("http://model.com/", "getProductListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetProductList }
     * 
     */
    public GetProductList createGetProductList() {
        return new GetProductList();
    }

    /**
     * Create an instance of {@link GetProductListResponse }
     * 
     */
    public GetProductListResponse createGetProductListResponse() {
        return new GetProductListResponse();
    }

    /**
     * Create an instance of {@link Product }
     * 
     */
    public Product createProduct() {
        return new Product();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.com/", name = "getProductList")
    public JAXBElement<GetProductList> createGetProductList(GetProductList value) {
        return new JAXBElement<GetProductList>(_GetProductList_QNAME, GetProductList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProductListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://model.com/", name = "getProductListResponse")
    public JAXBElement<GetProductListResponse> createGetProductListResponse(GetProductListResponse value) {
        return new JAXBElement<GetProductListResponse>(_GetProductListResponse_QNAME, GetProductListResponse.class, null, value);
    }

}
