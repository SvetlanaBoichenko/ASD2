
class Vertex
{
    public int Value;
    public Vertex(int val) //Объект- для массив значений
    {
        Value = val;
    }
}

public class SimpleGraph
{
    Vertex [] vertex;       // Массив вершин
    int [][] m_adjacency;   // Матрица с вершинами и без
    int max_vertex;         // Max колво вершин

    public SimpleGraph(int size)
    {
        max_vertex = size;
        m_adjacency = new int [size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value)
    {
        for (int i = 0; i < this.max_vertex; i++) {
            if (this.vertex[i] == null){
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
        if ( v >= this.vertex.length || v < 0)
            return;

        this.vertex[v] = null; // delete vertex
        for (int i = 0; i < this.vertex.length; i++) {
            this.m_adjacency[i][v] = 0; // delete ribs
            this.m_adjacency[v][i] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
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

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        }
}
