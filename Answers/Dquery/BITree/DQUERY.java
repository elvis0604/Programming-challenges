import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class DQUERY 
{
    final static int size = 1000001;
    static int[] last_visit_arr = new int[size];
    static int[] bitree;

    static class Query
    {
        int left;
        int right;
        int index;
        Query(int l, int r, int i)
        {
            left = l;
            right = r;
            index = i;
        }
    }

    static class SortByRight implements Comparator<Query>
    {
        public int compare(Query x, Query y)    //Sort queries base on right
        {
            return x.right - y.right;
        }
    }

    static void update(int max_index, int index, int val, int[] bitree)
    {
        while(index <= max_index)   //Traverse through parent
        {
            bitree[index] += val;
            index += index & -index;    //Get parent index
        }
    }

    static int query(int index, int[] bitree)
    {
        int sum = 0;
        while(index > 0)
        {
            sum += bitree[index];
            index -= index & -index; //Move to parent index
        }
        return sum;
    }

    static int[] solve(int arr[], Query queries[])
    {
        bitree = new int[arr.length + 1];
        Arrays.fill(bitree, 0);
        Arrays.fill(last_visit_arr, -1);    //Initialize everything inside the array to -1
                                            //to keep track of what has been visit
        int[] answer = new int[queries.length];    //To hold answer of each query
        int query_counter = 0;
        for(int i = 0; i < arr.length; i++)
        {
            /*Construct a BITree in order to store the frequencies of each number*/

            //If the element in the visit array is -1, we haven't visited it
            //otherwise update the BITree
            if(last_visit_arr[arr[i]] != -1)
                update(arr.length, last_visit_arr[arr[i]] + 1, -1, bitree);

            //Update the frequencies
            last_visit_arr[arr[i]] = i; 
            update(arr.length, i + 1, 1, bitree); 

            //Getting the answer depending on the queries and put it in answer array
            //by query through the BITree and collect the frequencies
            while(query_counter < queries.length && queries[query_counter].right == i)
            {
                answer[queries[query_counter].index] = 
                        query(queries[query_counter].right + 1, bitree) -
                        query(queries[query_counter].left, bitree);
                query_counter++;
            }
        }
        return answer;
    }

    static int[] convert(String[] arr)
    {
        int[] result = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            result[i] = Integer.parseInt(arr[i]);
        return result;
    }

    public static void main(String[] args) throws IOException
    {       
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);*/

        String number_of_value = reader.readLine();
        
        // Reading and spliting input 
        String sequence = reader.readLine();
        String[] arr_sequence = sequence.split(" ");
        int[] arr_sequence_convert = convert(arr_sequence);

        int number_of_d_queries = Integer.parseInt(reader.readLine());
        Query[] queries = new Query[number_of_d_queries];

        for(int i = 0; i < number_of_d_queries; i++)
        {
            String range = reader.readLine();
            String[] split_range = range.split(" ");

            int left = Integer.parseInt(split_range[0]) - 1;
            int right = Integer.parseInt(split_range[1]) - 1;

            Query query = new Query(left, right, i);

            queries[i] = query;
        }
        //Sort all the queries
        Arrays.sort(queries, new SortByRight());

        int[] result = solve(arr_sequence_convert, queries);
        for(int i = 0; i < result.length; i++)
        {
            out.println(result[i]);
        }
        out.flush();
        reader.close();
        out.close();
    }
}