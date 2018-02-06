public class Exercises
{
    public static boolean twoA(String s)
    {
        if (s == null || s.equals("")) return false;
        if (s.charAt(s.length()-1) == '*') return true; //char is primitive
        return false;
    }
    public static boolean twoB(String s)
    {
        if (s == null || s.length() < 2) return false;
        if (s.substring(s.length()-2, s.length()).equals("**")) return true;
        return false;
    }
    public static String removeDashes(String s)
    {
        return s.replace("-","");
    } //also works on telephone numbers!

public static String fourA()
{    
String dateStr = "05/31/2009";
String mm = dateStr.substring(0,2);
String dd = dateStr.substring(3,5);
String yyyy = dateStr.substring(6,10);
dateStr = dd + "-" + mm + "-" + yyyy;
return dateStr;
}

public static String five()
{
String ccNumber = "4111 1111 1111 1111";
String last4 = ccNumber.substring(ccNumber.length()-4,ccNumber.length());
String last5 = ccNumber.charAt(ccNumber.length()-6) + last4;
return last4 + " " + last5;
}

    public static String scroll(String s)
    {
        return s.substring(1) + s.charAt(0);
    }
    public static String convertName(String s)
    {
        int commaPos = s.indexOf(",");
        String lastName = s.substring(0, commaPos);
        String firstName = s.substring(commaPos+2, s.length());
        return firstName + " " + lastName;
    }
    public static String negative(String s)
    {
        s = s.replace('0','X');
        s = s.replace('1','0');
        s = s.replace('X','1');
        return s;
    }
}