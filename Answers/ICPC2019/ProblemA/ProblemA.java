import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class ProblemA 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        char letter;
        char[] c_array;
        int score;
        int A_score_total = 0;
        int B_score_total = 0;

        File file = new File("test.txt");
        Scanner scanner = new Scanner(file);
        while(scanner.hasNextLine())
        {
            c_array = scanner.nextLine().toCharArray();
            for(int i = 0; i < c_array.length; i += 2)
            {
                letter = c_array[i];
                score = Character.getNumericValue(c_array[i + 1]);
                if (letter == 'A')
                    A_score_total += score;
                else
                    B_score_total += score;
            }
            if (A_score_total > B_score_total)
                System.out.println("Winner is A");
            else
                System.out.println("Winner is B");
        }
        scanner.close();
    }
}