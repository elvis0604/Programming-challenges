import java.io.BufferedReader;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class BUSYMAN
{
    static class Interval
    {
        int start, end, index;
        Interval(int l, int r, int i)
        {
            start = l;
            end = r;
            index = i;
        }
    }

    static class SortByEndTime implements Comparator<Interval>
    {
        public int compare(Interval s, Interval e)    // Sort interval base on end time
        {
            return s.end - e.end;
        }
    }

    static int solve(Interval[] inv, int n) // Greedy
    {
        int count = 0;

        for(int i = 0; i < n;)
        {
            int end = inv[i].end;
            count++;
            int j = i + 1;
            while(j < n && end > inv[j].start)  // Comparing the end of the picked interval to the rest of the interval
            {                                   // until getting one with higher or equal start time
                j++;
            }
            i = j;
        }

        return count;
    }

    public static void main(String[] args) throws IOException
    {       
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);*/

        int number_of_test = Integer.parseInt(reader.readLine());
        int[] result = new int[number_of_test];

        // Reading and spliting input 
        for(int i = 0; i < number_of_test; i++)
        {
            int number_of_activities = Integer.parseInt(reader.readLine());

            Interval[] intervals = new Interval[number_of_activities];
            for(int j = 0; j < number_of_activities; j++)
            {
                String interval = reader.readLine();
                String[] split_interval = interval.split(" ");

                int interval_start = Integer.parseInt(split_interval[0]);
                int interval_end = Integer.parseInt(split_interval[1]);
                
                Interval inv = new Interval(interval_start, interval_end, j);
                intervals[j] = inv;
            }

            Arrays.sort(intervals, new SortByEndTime());
            result[i] = solve(intervals, intervals.length);
        }

        for(int i = 0; i < result.length; i++)
        {
            out.println(result[i]);
        }

        out.flush();
        reader.close();
        out.close();
    }

}