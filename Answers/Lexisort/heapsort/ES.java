import java.io.BufferedReader;
import java.io.*;
import java.util.*;
class ES
{ 
    static class Node   // Attemp to build a heap with linkedlist but got lazy
    {
        String value;
        
        Node(String v)
        {
            value = v;
        }

        String getValue()
        {
            return value;
        }
    }

    static ArrayList<Node> arr = new ArrayList<Node>(); 

    // Making the heap
    void heapify(ArrayList<Node> arr)
    {
        int size = arr.size();

        int number_of_parent = size / 2;
        for (int i = number_of_parent - 1; i >= 0; i--)   // Start from the bottom parent and moving up
            rebuild(arr, i, size);   // Rebuild the heap
    }

    Node[] sort(ArrayList<Node> arr)
    {
        // A temp array to hold the sort result
        Node[] tempArr = new Node[arr.size()];
        int j = 0;
        
        for(int i = arr.size() - 1; i >= 0; i--)    // Since arr.size() will shrink
        {                                           //we cannot use it as conditional
            tempArr[j] = pop(arr);
            j++;
        }
        return tempArr;
    }

    static Node pop(ArrayList<Node> arr)
    {
        // swapping the root node and the last node, then took out the root node
        int last_node_index = arr.size() - 1;
        int root_node_index = 0;
        // swappppppppp
        Node temp = arr.get(root_node_index);
        arr.set(root_node_index, arr.get(last_node_index));
        arr.set(last_node_index, temp);

        // pop the last node
        arr.remove(last_node_index);

        // rebuild the heap after swap and pop
        rebuild(arr, root_node_index, last_node_index);

        return temp;     
    }
    
    // Parent_id is which parent node it passes in, size is to check whether the right or left
    // node is out of bound
    static void rebuild(ArrayList<Node> arr, int parent_id, int size)
    {
        int value;
        // The child of parent i is 2*i+1 and 2*i+2
        int smallest = parent_id;   //assuming parent_id is the smallest value
        int left = 2 * parent_id + 1;
        int right = 2 * parent_id + 2;
        
        String parent_string;
        if(left < size)
        {
            parent_string = arr.get(smallest).getValue();
            String left_string = arr.get(left).getValue();
            value = left_string.compareTo(parent_string);
            // If the left child string is smaller than the parent
            if(value < 0)
            {
                smallest = left;
            }
        }

        if(right < size)
        {
            parent_string = arr.get(smallest).getValue();
            String right_string = arr.get(right).getValue();
            value = right_string.compareTo(parent_string);
            // If the right child string is smaller than the parent
            if(value < 0)
            {
                smallest = right;
            }
        }

        // If smallest did change, recursively call it again with the new parent node
        // (which is the node parent_id just swap to)
        if(smallest != parent_id)
        {
            // swapping 
            Node temp = arr.get(parent_id);
            arr.set(parent_id, arr.get(smallest));
            arr.set(smallest, temp);
            rebuild(arr, smallest, size);
        }
    }

    void printArr(Node[] arr)
    {
        for(int i = 0; i < arr.length; i++)
            System.out.println(arr[i].getValue());
    }

    void printArr(ArrayList<Node> arr)
    {
        for(int i = 0; i < arr.size(); i++)
            System.out.println(arr.get(i).getValue());
    }

    public static void main(String args[]) throws Exception 
    {
        File file = new File("test.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");

        // Self expain reading in input
        int number_of_test = Integer.parseInt(reader.readLine());
        
        for(int i = 0; i < number_of_test; i++)
        {           
            Node node; 
            ES ob = new ES();
            int number_of_line = Integer.parseInt(reader.readLine());

            //arr = new Node[number_of_line]; 
            for(int j = 0; j < number_of_line; j++)
            {
                String characters = reader.readLine();
                node = new Node(characters);
                arr.add(node);
            }
            //System.out.println("Before heap");
            //ob.printArr(arr);

            Node tempArr[] = new Node[arr.size() - 1];
            //System.out.println("After heap, Before sorted");
            ob.heapify(arr);    //min heap
            //ob.printArr(arr);

            //System.out.println("After sorted");
            tempArr = ob.sort(arr);
            //ob.printArr(tempArr);
    
            for (int k = 0; k < tempArr.length; k++) 
                out.println(tempArr[k].getValue());
        }

        out.flush();
        out.close();
        reader.close();
    }

}