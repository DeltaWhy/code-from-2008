public class Diploma
{
    private String name, course;
    public Diploma(String name, String course)
    {
        this.name = name;
        this.course = course;
    }
    public String toString()
    {
        return "This certifies that\n" + name + "\n" +
            "has completed a course in " + course + "\n";
    }
}