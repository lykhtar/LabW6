
public class MyPojo {

    private Schedule schedule;

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Pojo [schedule = " + schedule + "]";
    }
}

public class Schedule {

    private Teachers teachers;
    private Lessons lessons;

    public Teachers getTeachers() {
        return teachers;
    }

    public void setTeachers(Teachers teachers) {
        this.teachers = teachers;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public String toString() {
        return "Schedule [teachers = " + teachers + ", lessons = " + lessons + "]";
    }
}

public class Lessons {

    private String name;
    private String classroom;
    private String day;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Lessons [name = " + name + ", classroom = " + classroom + ", day = " + day + "]";
    }
}

public class Teachers {

    private String name;
    private Lesson lesson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    @Override
    public String toString() {
        return "Teachers[name = " + name + ", lesson = " + lesson + "]";
    }
}

public class Lesson {

    private String namstud;
    private String name;
    private String numles;

    public String getNamstud() {
        return namstud;
    }

    public void setNamstud(String namstud) {
        this.namstud = namstud;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumles() {
        return numles;
    }

    public void setNumles(String numles) {
        this.numles = numles;
    }

    @Override
    public String toString() {
        return "Lesson [namstud = " + namstud + ", name = " + name + ", numles = " + numles + "]";
    }
}

public class UnMarshalMain {

    public static void main(String[] args) {
        try {
            JAXBContext jc = JAXBContext.newInstance(MyPojo.class);
            Unmarshaller u = jc.createUnmarshaller();
            FileReader reader = new FileReader("E:\КПО\LabW6\marsh.xml");
            MyPojo students = (MyPojo) u.unmarshal(reader);
            System.out.println(students);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class MarshalMain {

    public static void main(String[] args) {
        try {
            JAXBContext context = JAXBContext.newInstance(MyPojo.class);
            Marshaller m = context.createMarshaller();
            MyPojo st = new MyPojo() { // анонимный класс
                {
                    MyPojo p = new MyPojo();
                    MyPojo s = new MyPojo();
                    this.add(p);
                    addr = new MyPojo();
                    s = new MyPojo();
                    this.add(s);
                }
            };

            m.marshal(st, new FileOutputStream("E:\КПО\LabW6\marsh.xml"));
            m.marshal(st, System.out); // копия на консоль
            System.out.println("XML-файл создан");
        } catch (FileNotFoundException e) {
            System.out.println("XML-файл не может быть создан: " + e);
        } catch (JAXBException e) {
            System.out.println("JAXB-контекст ошибочен " + e);
        }
    }
}
