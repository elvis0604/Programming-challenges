import java.io.BufferedReader;
import java.io.*;
import java.util.PriorityQueue;
import java.util.Iterator;

class MST
{
    static class Edge implements Comparable
    {
        int src;
        int dest;
        int weight;
        Edge(int s, int d, int w)
        {
            src = s;
            dest = d;
            weight = w;
        }

        public int compareTo(Object o) 
        {
            Edge e = (Edge) o;
            return Integer.compare(this.weight, e.weight);
        }
    }

    static class Graph 
    {
        PriorityQueue<Edge>[] adj;
        int V; // Number of vertices in the graph

        Graph(int v) 
        {
            V = v;
            adj = new PriorityQueue[V];
            for (int i = 0; i < V; i++)
                adj[i] = new PriorityQueue<Edge>();
        }

        void addEdge(int src, int dest, int weight) 
        {
            adj[src].add(new Edge(src, dest, weight));
            adj[dest].add(new Edge(dest, src, weight));
        }

        void addEdge(Edge e) 
        {
            adj[e.src].add(e);
        }

        PriorityQueue<Edge> neighbours(int v) 
        {
            return adj[v];
        }
    }

    static int Prim(Graph g, int start) 
    {
        Graph mst = new Graph(g.V);
        boolean[] visited = new boolean[g.V];
        int cost = 0;
    
        // insert neighbours of first vertex
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        Iterator<Edge> it = g.neighbours(start).iterator();
        while (it.hasNext()) 
        {
            edges.add(it.next());
        }
        visited[start] = true;
    
        // loop until no edges remain
        while (!edges.isEmpty()) 
        {
            Edge e = edges.remove();
    
            // if adding makes no cycle
            if (!visited[e.dest]) 
            {
                mst.addEdge(e);
                visited[e.dest] = true;
    
                // add neighbour edges of vertex if not visited
                Iterator<Edge> i = g.neighbours(e.dest).iterator();
                while (i.hasNext())
                    edges.add(i.next());
                visited[e.dest] = true;
                cost += e.weight;
            }
        }
        return cost;
    }
    public static void main(String[] args) throws IOException
    {       
        File file = new File("test.txt");
        /*BufferedReader reader = new BufferedReader(new FileReader(file));
        PrintWriter out = new PrintWriter("output.txt");*/

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String[] number_of_vertex_and_edge = reader.readLine().split(" ");

        int number_of_vertex = Integer.parseInt(number_of_vertex_and_edge[0]);
        int number_of_edge = Integer.parseInt(number_of_vertex_and_edge[1]);

        Graph g = new Graph(number_of_vertex);
        for(int i = 0; i < number_of_edge; i++)
        {
            String[] edges_and_weights = reader.readLine().split(" ");
            g.addEdge(Integer.parseInt(edges_and_weights[0]) - 1, 
            Integer.parseInt(edges_and_weights[1]) - 1, 
            Integer.parseInt(edges_and_weights[2]));
        }
        out.println(Prim(g, 0));

        out.flush();
        reader.close();
        out.close();
    }
}