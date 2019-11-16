import java.io.BufferedReader;
import java.io.*;
import java.util.*;
class ES
{
    static String arr[];
    static ArrayList<String> tempArr = new ArrayList<String>();
    /* Partitioning the array so that the pivot will be in correct order */
    int partition(String arr[], int low, int high) 
    { 
        String pivot = arr[high];  
        String temp;
        int i = (low - 1); // index of smaller element 
        for (int j = low; j < high; j++) 
        { 
            // If current element is smaller than the pivot swap arr[i] and arr[j] 
            int value = arr[j].compareTo(pivot);
            if (value < 0) 
            { 
                i++; 
                temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
    
        // swap arr[i+1] and arr[high] (or pivot) 
        temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
    
        return i+1; 
    } 
    
    /* sort will be call recursively until all element are in correct order */
    void sort(String arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            /* i is partitioning index, arr[i] is  
                now at right place */
            int i = partition(arr, low, high); 
    
            // Recursively sort 
            sort(arr, low, i-1); 
            sort(arr, i + 1, high); 
        } 
    } 
      
    public static void main(String args[]) throws Exception 
    {
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int number_of_test = Integer.parseInt(reader.readLine());
        for(int i = 0; i < number_of_test; i++)
        {
            int number_of_line = Integer.parseInt(reader.readLine());
            arr = new String[number_of_line]; 
            for(int j = 0; j < number_of_line; j++)
            {
                String characters = reader.readLine();
                arr[j] = characters;
            }
    
            ES ob = new ES(); 
            ob.sort(arr, 0, arr.length - 1); 
    
            for (int k = 0; k < arr.length; ++k) 
                tempArr.add(arr[k]);
        }
        // This is to eliminate the last blankline inside the output
        int max = tempArr.size();
        for(int i = 0; i < tempArr.size(); i++)
        {
            if(i < max - 1)
                out.println(tempArr.get(i));
            else
                out.print(tempArr.get(i));
        }
        out.flush();
        out.close();
        reader.close();
    }

}