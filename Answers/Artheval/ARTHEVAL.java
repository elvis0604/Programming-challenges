import java.io.BufferedReader;
import java.io.*;
import java.util.Stack;

class ARTHEVAL
{
    boolean isOperand(char c)   //return false if operator, true if operand
    {
        if (c == '+' || c == '-' || c == '*' || c == '(' || c == ')')
            return false;
        else
            return true;
    }

    int checkOperator(char c)   //check for priority of operator, higher means higher priority
    {
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*')
            return 2;
        else 
            return -1;
    }

    int parseOperator(int v0, int v1, char c)
    {
        if (c == '+')
            return v0 + v1;
        else if (c == '-')
            return v0 - v1;
        else if (c == '*')
            return v0 * v1;
        else
            return -1;
    }

    String convertInfixToPostfix(String s) 
    {
        char[] arr = s.toCharArray();

        Stack<Character> stack = new Stack<>();

        String result = "";
        for(int i = 0; i < arr.length; i++)
        {
            char c = arr[i];
            if (isOperand(c))
                result = result + c;
            //If the char is '(', push it to the stack. 
            else if (c == '(') 
                stack.push(c);
            //If the char is ')', pop everything until '('
            else if (c == ')')
            {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result = result + stack.pop();

                if (!stack.isEmpty() && stack.peek() == '(') //Pop the remaining '('
                    stack.pop();                 
            }
            else //Operator
            {
                //Pop operator out regardless of ordering
                while (!stack.isEmpty() && stack.peek() != '(' && stack.peek() != ')')
                    result = result + stack.pop();                    
                stack.push(c);
            }
                
        }
        while (!stack.isEmpty())
        {
            result = result + stack.pop();
        }
        return result;
    }

    int calculatePostfix(String s)
    {
        char[] arr = s.toCharArray();

        Stack<Integer>stack = new Stack<>();
        
        int result = 0;
        for (int i = 0; i < arr.length; i++)
        {
            char c = arr[i];
            if (isOperand(c))
                stack.push(Character.getNumericValue(c));
            else 
            {
                int value_0 = stack.pop();
                int value_1 = stack.pop();
                result = parseOperator(value_1, value_0, c);
                stack.push(result);
            }
        }
        return result;
    }

    public static void main(String[] args) throws Exception
    {
        File file = new File("test.txt");
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);*/

        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        String test = reader.readLine();

        /*for(char i: arr)
        {
            System.out.println(i);
        }*/
        ARTHEVAL ar = new ARTHEVAL();
        String postfix = ar.convertInfixToPostfix(test);
        out.print(ar.calculatePostfix(postfix));
        
        out.flush();
        reader.close();
        out.close();
    }   
}