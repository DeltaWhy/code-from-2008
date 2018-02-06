//Michael Limiero
public class Name
{
    private String first, middle, last;
    public Name(String first, String middle, String last)
    {
        this.first = first.trim();
        this.middle = middle.trim();
        this.last = last.trim();
    }
    public String getFirst()
    {
        return first;
    }
    public String getMiddle()
    {
        return middle;
    }
    public String getLast()
    {
        return last;
    }
    public String firstMiddleLast()
    {
        return first + " " + middle + " " + last;
    }
    public String lastFirstMiddle()
    {
        return last + ", " + first + " " + middle;
    }
    public boolean equals(Name otherName)
    {
        return first.equalsIgnoreCase(otherName.first) &&
            middle.equalsIgnoreCase(otherName.middle) &&
            last.equalsIgnoreCase(otherName.last);
    }
    public String initials()
    {
        String temp = "" + first.charAt(0) + middle.charAt(0) + last.charAt(0);
        return temp.toUpperCase();
    }
    public int length()
    {
        return first.length() + middle.length() + last.length();
    }
}