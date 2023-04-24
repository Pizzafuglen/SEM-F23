
package com.baeldung.soap.ws.client.generated;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PickItemResult" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pickItemResult"
})
@XmlRootElement(name = "PickItemResponse")
public class PickItemResponse {

    @XmlElement(name = "PickItemResult", required = true, nillable = true)
    protected String pickItemResult;

    /**
     * Gets the value of the pickItemResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPickItemResult() {
        return pickItemResult;
    }

    /**
     * Sets the value of the pickItemResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPickItemResult(String value) {
        this.pickItemResult = value;
    }

}
