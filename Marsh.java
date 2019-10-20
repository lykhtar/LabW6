//класс для здания структуры xml
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "schedule", propOrder = {"lessons", "teachers"})
// задание последовательности элементов XML
public class Schedule {

    @XmlElement(required = true)
    private Lessons lessons = new Lessons();

    private Teachers teachers = new Teachers();

    public Schedule() {
    } // необходим для маршаллизации/демаршалиизации XML

    public Schedule(Lessons lessons, Teachers teachers) {
        this.lessons = lessons;
        this.teachers = teachers;

    }

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

    public String toString() {
        return "\nTeachers: " + teachers + "\nLessons: " + lessons;

    }

    @XmlRootElement
    @XmlType(name = "lessons", propOrder = {
        "nameles",
        "day",
        "classroom"
    })
    public static class Lessons { // внутренний класс

        private String nameles;
        private String day;
        private String classroom;

        public Lessons() {// необходим для маршаллизации/демаршалиизации XML
        }

        public Lessons(String nameles, String day, String classroom) {
            this.nameles = nameles;
            this.day = day;
            this.classroom = classroom;
        }

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

        public String toString() {
            return "\nLessons:" + "\n\tName_lesson: " + nameles + "\n\tDay: " + day + "\n\tClassroom: " + classroom + "\n";

        }
    }

    @XmlRootElement
    @XmlType(name = " teachers ", propOrder = {"namet",
        "lesson",
        "numles",
        "numst"
    })
    public static class Teachers { // внутренний класс

        private String namet;
        private String lesson;
        private int numles;
        private int numst;

        public Teachers() {// необходим для маршаллизации/демаршалиизации XML
        }

        public Teachers(String namet, String lesson, int numles, int numst) {
            this.namet = namet;
            this.lesson = lesson;
            this.numles = numles;
            this.numst = numst;
        }

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

        public String toString() {
            return "\nTeachers:" + "\n\tName_teacher: " + namet + "\n\tLesson: " + lesson + "\n\tNumber_lesson: " + numles + "\n\tMumber_student" + numst + "\n";

        }
    }
}
//класс для корневого элемента
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class Marsh {

    @XmlElement(name = "schedule")
    private ArrayList<Schedule> list = new ArrayList<Schedule>();

    public Marsh() {
        super();
    }

    public void setList(ArrayList<Schedule> list) {
        this.list = list;
    }

    public boolean add(Schedule st) {
        return list.add(st);
    }

    @Override

    public String toString() {
        return "Schedule [list=" + list + "]";
    }
}

//класс для маршаллизации
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MarshalMain {

    public void marshalMain() {
        try {
            JAXBContext context = JAXBContext.newInstance(Marsh.class);
            Marshaller m = context.createMarshaller();
            Marsh st = new Marsh() {
                {
                    // добавление первого предмета и учителя
                    Schedule.Lessons les = new Schedule.Lessons("Math", "Saturday", "345Д");
                    Schedule.Teachers teach = new Schedule.Teachers("Ivanov", "Math", 2, 12);
                    Schedule s = new Schedule(les, teach);
                    this.add(s); // добавление второго предмета и учителя

                    les = new Schedule.Lessons("IT", "Monday", "211A");
                    teach = new Schedule.Teachers("Petrov", "IT", 3, 52);
                    s = new Schedule(les, teach);
                    this.add(s);
                }
            };

            m.marshal(st, new FileOutputStream("E:\\КПО\\Lab6_2\\f.xml"));
            m.marshal(st, System.out); // копия на консоль
            System.out.println("XML-файл создан");
        } catch (FileNotFoundException e) {
            System.out.println("XML-файл не может быть создан: " + e);
        } catch (JAXBException ex) {
            Logger.getLogger(MarshalMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

//класс для демаршаллизации
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class UnMarshalMain {

    public void unMarshalMain() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Marsh.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("E:\\КПО\\Lab6_2\\f.xml");
            Marsh students = (Marsh) u.unmarshal(reader);
            System.out.println(students);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

//общий главный класс
public class Main {
       public static void main(String[] args) {
       MarshalMain m = new MarshalMain();
       m.marshalMain();
       UnMarshalMain m1 = new UnMarshalMain();
       m1.unMarshalMain();
       }
    
}
