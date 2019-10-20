import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "schedule", propOrder = {
    "lessons",
    "teachers"
})

public class Schedule {

    @XmlElement(required = true)
    protected Lessons lessons;
    @XmlElement(required = true)
    protected Teachers teachers;

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

}

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lessons", propOrder = {
    "nameles",
    "day",
    "classroom"
}
)
public class Lessons {

    @XmlElement(required = true)
    protected String nameles;
    @XmlElement(required = true)
    protected String day;
    @XmlElement(required = true)
    protected String classroom;

    public String getNameles() {
        return nameles;
    }

    public void setNameles(String nameles) {
        this.nameles = nameles;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

}

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = " teachers ", propOrder = {"namet",
    "lesson",
    "numles",
    "numst"
}
)
public class Teachers {

    @XmlElement(required = true)
    protected String namet;
    @XmlElement(required = true)
    protected String lesson;
    @XmlElement(required = true)
    protected int numles;
    @XmlElement(required = true)
    protected int numst;

    public String getNamet() {
        return namet;
    }

    public void setNamet(String namet) {
        this.namet = namet;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getNumles() {
        return numles;
    }

    public void setNumles(int numles) {
        this.numles = numles;
    }

    public int getNumst() {
        return numst;
    }

    public void setNumst(int numst) {
        this.numst = numst;
    }
}

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "schedule"
}
)
@XmlRootElement(name = "marsh")
public class Marsh {

    @XmlElementRef(name  = "schedule", namespace = "", type = JAXBElement.class )
    protected List<JAXBElement<? extends Schedule>> schedule;

    public List<JAXBElement<? extends Schedule>> getSchedule() {
        if (schedule == null) {
            schedule = new ArrayList<>();
        }
        return this.schedule;
    }
}

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

@XmlRegistry

public class ObjectFactory {

    private final static QName _Schedule_QNAME = new QName("", "Schedule");
    private final static QName _Marsh_QNAME = new QName("", "marsh");
    private final static QName _Teachers_QNAME = new QName("", "teachers");
    private final static QName _Lessons_QNAME = new QName("", "lessons");

    public ObjectFactory() {
    }

    public Marsh createMarsh() {
        return new Marsh();
    }

    public Schedule createSchedule() {
        return new Schedule();
    }

    public Teachers createTeachers() {
        return new Teachers();
    }

    public Lessons createLessons() {
        return new Lessons();
    }

    @XmlElementDecl(namespace = "", name = "marsh")

    public JAXBElement<Marsh> createMarsh(Marsh value) {
        return new JAXBElement<>(_Marsh_QNAME, Marsh.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "schedule",
            substitutionHeadNamespace = "",
            substitutionHeadName = "marsh"
    )
    public JAXBElement<Schedule> createSchedule(Schedule value) {
        return new JAXBElement<>(_Schedule_QNAME, Schedule.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "teachers",
            substitutionHeadNamespace = "",
            substitutionHeadName = "schedule"
    )
    public JAXBElement<Teachers> createTeachers(Teachers value) {
        return new JAXBElement<>(_Teachers_QNAME, Teachers.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "lessons",
            substitutionHeadNamespace = "",
            substitutionHeadName = "schedule"
    )
    public JAXBElement<Lessons> createLessons(Lessons value) {
        return new JAXBElement<>(_Lessons_QNAME, Lessons.class, null, value);
    }
}

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.validation.*;
import org.xml.sax.SAXException;

public class UnMarshalWithXSD {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller um = jc.createUnmarshaller();
            String schemaName = "E:\\КПО\\Lab6_3\\fx.xsd";
            SchemaFactory factory
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File schemaLocation = new File(schemaName);// создание схемы и перадача ее демарашаллизатору
            Schema schema = factory.newSchema(schemaLocation);
            um.setSchema(schema);
            Marsh st = (Marsh) um.unmarshal(new File("E:\\КПО\\Lab6_3\\fi.xml"));
            System.out.println(st);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}
