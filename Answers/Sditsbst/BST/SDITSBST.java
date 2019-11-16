import java.io.BufferedReader;
import java.io.*;

class SDITSBST
{
    Node root = null;
    void constructBST(long value)
    {
        root = insertBST(value, root);
    }

    Node insertBST(long value, Node node)
    {
        if(node == null)
        {
            node = new Node(value);
            return node;
        }
        
        if(node.value < value)
        {
            node.num_right_sub++;
            node.right = insertBST(value, node.right);
        } 
        else if (node.value > value)
        {
            node.num_left_sub++;
            node.left = insertBST(value, node.left);
        }
        return node;
    }

    Node utilSearch(long value)
    {
        long index = 1;
        Node temp = search(value, root, index);
        return temp;
    }

    Node search(long value, Node node, long index)
    { 
        if(node == null)
            return null;

        if(value == node.value)
        {
            node.index = index + node.num_right_sub;
            return node;
        } 
        else if(value <= node.value)
        {
            index += node.num_right_sub;
            return search(value, node.left, ++index);
        }
        else if(value >= node.value)
        {
            return search(value, node.right, index);
        }
        return null;
    }

    public static void main(String[] args) throws IOException
    {
        File file = new File("test.txt");
        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);*/

        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        int number_of_test = Integer.parseInt(reader.readLine());
        Node result;

        SDITSBST tree = new SDITSBST();
        // Reading and spliting input 
        for(int i = 0; i < number_of_test; i++)
        {
            String input = reader.readLine();
            String[] arr_input = input.split(" ");
            long[] arr_input_convert = convert(arr_input);
            if(arr_input_convert[0] == 1)
            {
                tree.constructBST(arr_input_convert[1]);   //Keep track of root then pass it back to construct BST
            }
            else
            {
                result = tree.utilSearch(arr_input_convert[1]);
                if(result == null)
                    out.println(("Data tidak ada"));
                else
                    out.println(result.index);
            }
        }
        out.flush();
        reader.close();
        out.close();
    }

    static class Node
    {
        long value = 0;
        long num_right_sub = 0;
        long num_left_sub = 0;
        long index = 0;
        Node right, left;

        Node(long v)
        {   
            value = v;
            right = left = null;
        }
    }

    static long[] convert(String[] arr)
    {
        long[] result = new long[arr.length];
        for(int i = 0; i < arr.length; i++)
            result[i] = Long.parseLong(arr[i]);
        return result;
    }
}