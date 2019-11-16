import java.io.BufferedReader;
import java.io.*;
import java.util.*;
class ES
{ 
    static String arr[];
    static ArrayList<String> tempArr = new ArrayList<String>(); 
    // Merges two subarrays of arr
    void merge(String arr[], int low, int mid, int high) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = mid - low + 1; 
        int n2 = high - mid; 
  
        String tempL[] = new String [n1]; 
        String tempR[] = new String [n2]; 
  
        // Tranfers elements to temp arrays
        for (int i = 0; i < n1; ++i) 
            tempL[i] = arr[low + i]; 
        for (int j = 0; j < n2; ++j) 
            tempR[j] = arr[mid + 1 + j]; 

        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = low; 
        // Compare elements in order to determine which element goes first
        while (i < n1 && j < n2) 
        { 
            int value = tempL[i].compareTo(tempR[j]);
            if (value < 0)
            { 
                arr[k] = tempL[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = tempR[j]; 
                j++; 
            } 
            k++; 
        } 
  
        // Getting all the remaining elements from tempL and tempR
        while (i < n1) 
        { 
            arr[k] = tempL[i]; 
            i++; 
            k++; 
        } 
        while (j < n2) 
        { 
            arr[k] = tempR[j]; 
            j++; 
            k++; 
        } 
    } 
    // sort being call recursively
    void sort(String arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            // Find the middle point 
            int mid = (low + high)/2; 
  
            // Sort first and second halves 
            sort(arr, low, mid); 
            sort(arr , mid+1, high); 
  
            // Merge the sorted halves 
            merge(arr, low, mid, high); 
        } 
    } 

    public static void main(String args[]) throws Exception 
    {
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        // Self expain reading in input
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

            for (int k=0; k < arr.length; ++k) 
            tempArr.add(arr[k]);
        }
        // This is to eliminate the blank line when flush. Turns out you don't need it
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