::sop::
send System.out.print();
send {Left}{Left}
return
::sopl::
send System.out.println();
Send {Left}{Left}
return

::ml:://Michael Limiero

::sclass::
send ^a
send {Del}
sendraw //Michael Limiero
send {Enter}
;fall-through

::pclass::
send {Home}
send public class
send {Space}{End}{Enter}
sendraw {
send {Enter}
;send {Up}{Up}{End}
return

::psvm::
send public static void main(String[] args)
send {Enter}
sendraw {
send {Enter}
return

::iio::import java.io.*
::isc::import java.util.Scanner
::ial::import java.util.ArrayList
::dsc::Scanner kb = new Scanner(System.in)
::isw::
send import java.awt.*;{Enter}
send import javax.swing.*;{Enter}
send import java.awt.event.*;
return
::aal::addActionListener(this)
+^c::Send .com
+^q::MsgBox All your base are belong to me.