import java.io.BufferedReader;
import java.io.*;

class DQUERY {
    final static int size = 20000;

    static int Solve(String[] arr, int low, int high)
    {
        int count = 0;
        int[] frequencies = new int[size];  //Frequencies array to hold the frequent of each element
        for(int i = 0; low <= high; i++)
        {
            if(frequencies[Integer.parseInt(arr[low])] == 0)
            {
                count++;    //Only count when frequency of element is 0
                frequencies[Integer.parseInt(arr[low])]++;  //Mark the frequencies of the element
            }
            low++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException
    {
        try {
            File file = new File("test.txt");
            /*BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter out = new PrintWriter("output.txt");*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(System.out);

            String number_of_value = reader.readLine();
            
            // Reading and spliting input for both candy boxes and students
            String sequence = reader.readLine();
            String[] arr_sequence = sequence.split(" ");
            //Process(arr_sequence);

            int number_of_d_queries = Integer.parseInt(reader.readLine());

            for(int i = 0; i < number_of_d_queries; i++)
            {
                String range = reader.readLine();
                String[] split_range = range.split(" ");

                int low = Integer.parseInt(split_range[0]);
                int high = Integer.parseInt(split_range[1]);
                int result = Solve(arr_sequence, low - 1, high - 1);
                
                out.println(result);
            }
            out.flush();
            reader.close();
            out.close();
        } catch(Exception e) {
            return;
        }
    }
}