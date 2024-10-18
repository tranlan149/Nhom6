
package vn.Nhom6.qlsv.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "sectionclasses")
@XmlAccessorType(XmlAccessType.FIELD)
public class SectionClassXML {
    private List<SectionClass> classes;

    public List<SectionClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SectionClass> classes) {
        this.classes = classes;
    }
}
