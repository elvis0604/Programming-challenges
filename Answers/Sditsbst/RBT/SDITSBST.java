import java.io.BufferedReader;
import java.io.*;

class SDITSBST
{
    Node root = null;
    static final int RED = 0;
    static final int BLACK = 1;
    
    void constructRBT(long value)
    {
        root = insertRBT(value, root, null);
        root.color = BLACK; //Always set root back to black
    }

    Node insertRBT(long value, Node node, Node parent)
    {
        if(node == null)
        {
            node = new Node(value, parent);
        }
        
        if(node.value < value)
        {
            node.right = insertRBT(value, node.right, node);
        } 
        else if (node.value > value)
        {
            node.left = insertRBT(value, node.left, node);
        }

        if(parent != null && node.color == RED && parent.color == RED)
        {
            Node grandparent = parent.prev;
            Node temp = grandparent;
            parent.right = grandparent;
        }
        
        return node;
    }

    boolean isRed(Node node) 
    {
        if(node == null) 
            return false;
		else
			return node.color == RED;
    }
  
    public Node flipColor(Node node) 
    {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
		return node;
	}
	
    public Node leftRotate(Node node) 
    {
		Node temp = node.right;
		Node temp1 = temp.left;
		
		//Rotation
		temp.left = node;
		node.right = temp1;
		
		return temp;
	}
	
    public Node rightRotate(Node node) 
    {
		Node temp = node.left;
		Node temp1 = temp.right;
		
		//Rotation
		temp.right = node;
		node.left = temp1;
		
		return temp;
	}

    /*Node utilSearch(long value)
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
    }*/

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
                tree.constructRBT(arr_input_convert[1]);   //Keep track of root then pass it back to construct BST
            }
            /*else
            {
                result = tree.utilSearch(arr_input_convert[1]);
                if(result == null)
                    out.println(("Data tidak ada"));
                else
                    out.println(result.index);
            }*/
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
        int color = RED;
        Node right, left, prev;

        Node(long v, Node p)
        {   
            value = v;
            prev = p;
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