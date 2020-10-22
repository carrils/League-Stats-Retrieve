import java.util.Scanner;
import javax.swing.JOptionPane;

public class CoolHackyTricks {
    public static void main(String[] args) {
        System.out.println("Hello Java");

        /****************************************************************/

        float num = (float) 5.5;
        System.out.println(num);

        /****************************************************************/

        String str = "Tre";
        int stringLength = str.length();
        char name[] = new char[stringLength];
        int updatePosition = stringLength;

        for (int i = 0; i < stringLength; i++) {
            name[i] = str.charAt(updatePosition - 1);
            System.out.println(name[i]);

            updatePosition -= 1;
        }

        /****************************************************************/
        //this is how i did the reverse-a-string thingy
        System.out.println();//use a space to separate output
        String s = "Sam";
        char[] s1 = s.toCharArray();

        for (int i = s1.length - 1; i >= 0; i--) {
            //s1.length-1 because s has 3 letters, for loop is 0-based, {0,1,2} instead of {1,2,3}
            System.out.println(s1[i]);
        }
        /****************************************************************/
        int c = 3;
        int j = c++;//int j = ++c;
        //prefix: the variable value is incremented first then used in the expression.
        //postfix: the increment is done after the variable is read
        System.out.println(c+" and "+j);




        /****************************************************************/
        UserInput();
        JOptionPane.showMessageDialog(null, "This is a JPane!", "Pane Header", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @return
     ***************************************************************************************************/

    private static void UserInput() {
        Scanner numEntry = new Scanner(System.in);
        int UserNumber;

        do {
            System.out.print("Please Enter A Number Between 1 and 10: ");
            UserNumber = numEntry.nextInt();
            System.out.println("You entered: " + UserNumber);
        } while (UserNumber > 1 && UserNumber < 10);
        numEntry.close();

        System.out.println("THANK YOU FOR PLAYING!");
        return;
    }
}