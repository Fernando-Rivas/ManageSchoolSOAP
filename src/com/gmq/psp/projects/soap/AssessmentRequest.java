
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
 *         &lt;element name="AssessedSubject" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="AssessedStudent" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="FinalGrade" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "assessedSubject",
    "assessedStudent",
    "finalGrade"
})
@XmlRootElement(name = "AssessmentRequest")
public class AssessmentRequest {

    @XmlElement(name = "AssessedSubject", required = true)
    protected String assessedSubject;
    @XmlElement(name = "AssessedStudent", required = true)
    protected String assessedStudent;
    @XmlElement(name = "FinalGrade")
    protected int finalGrade;

    /**
     * Gets the value of the assessedSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessedSubject() {
        return assessedSubject;
    }

    /**
     * Sets the value of the assessedSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessedSubject(String value) {
        this.assessedSubject = value;
    }

    /**
     * Gets the value of the assessedStudent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessedStudent() {
        return assessedStudent;
    }

    /**
     * Sets the value of the assessedStudent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessedStudent(String value) {
        this.assessedStudent = value;
    }

    /**
     * Gets the value of the finalGrade property.
     * 
     */
    public int getFinalGrade() {
        return finalGrade;
    }

    /**
     * Sets the value of the finalGrade property.
     * 
     */
    public void setFinalGrade(int value) {
        this.finalGrade = value;
    }

}
