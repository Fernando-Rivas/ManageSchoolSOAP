
package com.gmq.psp.projects.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="StudentName" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="StudentAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="StudentID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "studentName",
    "studentAddress",
    "studentID"
})
@XmlRootElement(name = "StudentRequest")
public class StudentRequest {

    @XmlElement(name = "StudentName", required = true)
    protected String studentName;
    @XmlElement(name = "StudentAddress", required = true)
    protected String studentAddress;
    @XmlElement(name = "StudentID", required = true)
    protected String studentID;

    /**
     * Gets the value of the studentName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the value of the studentName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentName(String value) {
        this.studentName = value;
    }

    /**
     * Gets the value of the studentAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     * Sets the value of the studentAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentAddress(String value) {
        this.studentAddress = value;
    }

    /**
     * Gets the value of the studentID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the value of the studentID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStudentID(String value) {
        this.studentID = value;
    }

}
