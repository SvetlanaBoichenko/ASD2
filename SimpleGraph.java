import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
    }
}

public class SimpleGraph 
{
    Vertex[] vertex;       // 
    int[][] m_adjacency;   // 
    int max_vertex;         //

    public SimpleGraph(int size) 
    {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) 
    {
        for (int i = 0; i < this.max_vertex; i++) {
            if (this.vertex[i] == null) {
                Vertex v = new Vertex(value); //new Vertex weth value = v
                this.vertex[i] = v;
                break;
            }
        }
    }

    //  v -- index of vertex
    // in the  vertex list
    public void RemoveVertex(int v) 
    {
        if (v >= this.vertex.length || v < 0)
            return;

        this.vertex[v] = null; // delete vertex
        for (int i = 0; i < this.vertex.length; i++) {
            this.m_adjacency[i][v] = 0; // delete ribs
            this.m_adjacency[v][i] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) 
    {
        if (v1 < this.vertex.length && v2 < this.vertex.length)
            return (this.m_adjacency[v1][v2] == 1 && this.m_adjacency[v2][v1] == 1);

        return false;
    }

    public void AddEdge(int v1, int v2) 
    {
        if (v1 < this.vertex.length && v2 < this.vertex.length) {
            this.m_adjacency[v1][v2] = 1;
            this.m_adjacency[v2][v1] = 1;
        }

    }

    public void RemoveEdge(int v1, int v2) 
    {
        if (v1 < this.max_vertex && v2 < this.max_vertex) {
            this.m_adjacency[v1][v2] = 0;
            this.m_adjacency[v2][v1] = 0;
        }
    }


    void deleteHits () {
        for (int i = 0; i < this.vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].Hit = false;
            }
        }
   }

// 
   public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo)
    {
       ArrayList<Vertex> retList = new ArrayList<>();
       if (VFrom >= this.max_vertex || VFrom < 0 || VTo >= this.max_vertex || VTo < 0)
       return retList;

       if (VFrom == VTo) {
           retList.add(vertex[VFrom]);
           return retList;
       }
       
        Vertex v1 = vertex[VFrom];//
        Vertex v2 = vertex[VTo];
        deleteHits();

        v1.Hit = true; // Visit First vertex
        int X = VFrom; //

        Stack<Integer> v_stack = new Stack<>(); //
        v_stack.push(VFrom);//

        while (X != -1) {
           if (this.m_adjacency[X][VTo] == 1) {
               v_stack.push(VTo);
               break;
           }

           X = this.look_adj_vetrex (X); //fing agi Х vertex
           if (X == -1) {
               v_stack.pop();         //no aji vertex
               X = v_stack.peek();    // send to revise
           } 
           else {
               v_stack.push(X);     // new to stack
               this.vertex[X].Hit = true;// it is in stack
           }
       }
           
       List<Integer> L = v_stack.subList(0, v_stack.size());
           
       for (int i = 0; i < L.size(); i++) {    //for recive vertex of their index
            Vertex vtmp = vertex[L.get(i)];
            retList.add(vtmp);
        }
        
        return retList;
    }

       
    int look_adj_vetrex(int v) 
    {
        for (int i = 0; i < this.max_vertex; i++) {
               if (this.IsEdge(v, i) == true && this.vertex[i].Hit == false)
                   return i;
           }
           return -1;
    }

    
}



