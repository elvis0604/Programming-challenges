import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;


class AGGRCOW
{
    static int[] tempArr;

    static int binarySearch(int arr[], int low, int high, int num_cow) 
    { 
        int mid = 0;
        while(low < high - 1) // in case high = low + 1 led into infine loop
        {
            mid = (low + high) / 2; // Try out mid
            // Solve to see if the mid point is satisfy the demand
            if(solve(tempArr, num_cow, mid)) 
                low = mid;
            else high = mid;    // If there's still cow left to put then lower the ceiling
        }
        return low; 
    } 

    private static boolean solve(int []arr, int num_cow, int mid) 
    {
        // Loop through the array to see if we can put the cow with mid distance, everytime a cow 
        // is placed inside cow_count will increase until all cow are placed.
        int position = 0;
        int cow_count = 1;
        int distance = 0;
        for(int i = 1; i < arr.length; i++) 
        {
            distance = arr[i] - arr[position];

            if(distance >= mid)
            {
                position = i;
                cow_count++;
            }

            if(cow_count == num_cow)    // We found a possible solution
                return true;
        }
        return false;
    }
  
    int partition(int arr[], int low, int high) 
    { 
        int pivot = arr[high];  
        int i = (low - 1); // index of smaller element 
        for(int j = low; j < high; j++) 
        { 
            // If current element is smaller than the pivot 
            if(arr[j] < pivot) 
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
        if(low < high) 
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
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);*/

        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        int number_of_test = Integer.parseInt(reader.readLine());
        for(int i = 0; i < number_of_test; i++)
        {
            // Reading and spliting input for both candy boxes and students
            String stall_and_cow = reader.readLine();
            String[] split_input = stall_and_cow.split(" ");
            
            int number_of_stall = Integer.parseInt(split_input[0]);
            int number_of_cow = Integer.parseInt(split_input[1]);

            // Putting all candies inside tempArr and cast those to integer
            tempArr = new int[number_of_stall];
            for(int j = 0; j < number_of_stall; j++)
                tempArr[j] = Integer.parseInt(reader.readLine());
            
            // Sorting the tempArr
            Arrays.sort(tempArr);

            int low = 1;
            int high = tempArr[tempArr.length - 1];

            int result = binarySearch(tempArr, low, high, number_of_cow);

            out.println(result);
        }

        
        out.flush();
        reader.close();
        out.close();
    }   
}