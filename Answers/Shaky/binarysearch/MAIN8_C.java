import java.io.BufferedReader;
import java.io.*;


class MAIN8_C
{
    static int[] tempArr;

    static int binarySearch(int arr[], int low, int high, int number_of_students) 
    { 
        while (low < high) 
        {
            int mid = (low + high + 1) / 2; // Calculate the mid (average) amount of candy student can take
            // Solve to see if the mid point is satisfy the demand
            if (solve(tempArr, number_of_students, mid)) 
                low = mid;
            else high = mid - 1;    // If there's still candy to get then lower the ceiling
        }
        return low; 
    } 

    private static boolean solve(int []arr, int left_over_student, int mid) 
    {
        // Loop through the array and divide each element to see which one can be divide
        // by the midpoint to get the number of candies the student can get out of each box
        for (int i = 0; i < arr.length; i++) 
        {
            int num_candy_gotten = arr[i] / mid;   
            left_over_student = left_over_student - num_candy_gotten;
            if (left_over_student <= 0) // Loop through until no student can pick up anymore candy
                return true;
        }
        return false;
    }
  
    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
    
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
    
        // swap arr[i + 1] and arr[high] (or pivot) 
        int temp = arr[i + 1]; 
        arr[i + 1] = arr[high]; 
        arr[high] = temp; 
    
        return i+1; 
    }

    void sort(int arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* i is partitioning index, arr[i] is  
                now at right place */
            int i = partition(arr, low, high); 
    
            // Recursively sort elements before partition and after partition 
            sort(arr, low, i - 1); 
            sort(arr, i + 1, high); 
        } 
    }

    public static void main(String[] args) throws Exception
    {
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int number_of_test = Integer.parseInt(reader.readLine());
        for(int i = 0; i < number_of_test; i++)
        {
            
            // Reading and spliting input for both candy boxes and students
            String candy_boxes_and_students = reader.readLine();
            String[] split_input = candy_boxes_and_students.split(" ");
            
            int number_of_candy_boxes = Integer.parseInt(split_input[0]);
            int number_of_students = 0;

            if(split_input.length > 1)
                number_of_students = Integer.parseInt(split_input[1]);
            else
                number_of_students = 0;
            
            // Spliting the input for no of candy
            String number_of_candy = reader.readLine();
            String[] split_input_candy = number_of_candy.split(" ");

            // Putting all candies inside tempArr and cast those to integer
            tempArr = new int[number_of_candy_boxes];
            for(int j = 0; j < number_of_candy_boxes; j++)
                tempArr[j] = Integer.parseInt(split_input_candy[j]);

            MAIN8_C ob = new MAIN8_C();
            
            // Sorting the tempArr using quicksort
            ob.sort(tempArr, 0, tempArr.length - 1);

            int low = 0;
            int high = tempArr[tempArr.length - 1];

            int result = binarySearch(tempArr, low, high, number_of_students);

            out.println(result);
        }

        
        out.flush();
        reader.close();
        out.close();
    }   
}