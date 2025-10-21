 //==============================================================================
    //      Является ли граф связанным
    //==============================================================================
    public boolean IsGraphConnecting ()
    {
        for (int i = 0; i < vertex.length - 1; i++) {
            ArrayList<Vertex> arrVisitVertex = this.DepthFirstSearch(i, i+1);

           if (arrVisitVertex.size() == 0)
               return false;

        }
         return true;
    }

//=============================================================================
//  В ориентированном графе найдите длину самого длинного простого пути
//  (пути без повторяющихся вершин).
//  Граф может содержать циклы, и вам нужно игнорировать их при поиске.
//=============================================================================


import java.util.*;

class Vertex
{
    public int Value;
    public boolean Hit;
    public int vertPrevVisit;
    int inTriengle;
    public Vertex(int val)
    {
        Value = val;
        Hit = false;
        vertPrevVisit = -1;
        inTriengle = 0;

    }
}

public class SimpleGraph {
    Vertex[] vertex;       // Массив вершин
    int[][] m_adjacency;   // Матрица с вершинами и без
    int max_vertex;         // Max колво вершин

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
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
    public void RemoveVertex(int v) {
        if (v >= this.vertex.length || v < 0)
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

    public void AddEdge(int v1, int v2) {
        if (v1 < this.vertex.length && v2 < this.vertex.length) {
            this.m_adjacency[v1][v2] = 1;
            this.m_adjacency[v2][v1] = 1;
        }
    }

    public void RemoveEdge(int v1, int v2) {
        if (v1 < this.max_vertex && v2 < this.max_vertex) {
            this.m_adjacency[v1][v2] = 0;
            this.m_adjacency[v2][v1] = 0;
        }
    }

    void deleteHits () {
        for (int i = 0; i < this.vertex.length; i++) {
            if (vertex[i] != null) {
                vertex[i].Hit = false;
                vertex[i].vertPrevVisit = -1;
            }
        }
    }

    //--------- Обход в глубину от - до
    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
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

        v1.Hit = true;      // Visit First vertex
        int X = VFrom;      //

        Stack<Integer> v_stack = new Stack<>(); //
        v_stack.push(VFrom);    //

        while (X != -1) {
            if (this.m_adjacency[X][VTo] == 1) {
                v_stack.push(VTo);
                break;
            }

            X = this.look_adj_vetrex (X);   //  fing agi Х vertex
            if (X == -1) { // нет смежных
                if (v_stack.isEmpty())
                    return retList;
                v_stack.pop(); //двигаемся дальше - нет смежных - вытолкнули бессмежного
                // это и есть level?
                if (v_stack.isEmpty())
                    return retList;
                X = v_stack.peek();// send to revise
            }
            else {
               // есть смежные
                v_stack.push(X);            // new to stack
                this.vertex[X].Hit = true;  // it is in stack
            }
        }
        List<Integer> L = v_stack.subList(0, v_stack.size());
        for (int i = 0; i < L.size(); i++) {    //for recive vertex of their index
            Vertex vtmp = vertex[L.get(i)];
            retList.add(vtmp);
        }
        return retList;
    }

    int look_adj_vetrex(int v) {
        for (int i = 0; i < this.max_vertex; i++) {
            if (this.IsEdge(v, i) == true && this.vertex[i].Hit == false)
                return i;
        }
        return -1;
    }

    //-------------------------------------------------------------------------------
    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        ArrayList<Vertex> retList = new ArrayList<>();
        if (VFrom >= this.max_vertex || VFrom < 0 || VTo >= this.max_vertex || VTo < 0)
            return retList;

        if (VFrom == VTo) {
            retList.add(vertex[VFrom]);
            return retList;
        }
        Vertex v1 = this.vertex[VFrom];//
        Vertex v2 = this.vertex[VTo];
        deleteHits();

        v1.Hit = true; // Visit First vertex
        int X = VFrom; //

        LinkedList<Integer> v_queue = new LinkedList<>(); //
        v_queue.add(X);//
        this.vertex[VFrom].vertPrevVisit = VFrom;
        System.out.println("the next= ");

        int child_count = 1, level = 0;

        while (!v_queue.isEmpty()) {
            X = v_queue.poll();  //возвращает и удаляет элемент из головы очереди.

            System.out.println("level = " + level);

            for (int i = 0; i < this.max_vertex; i++) {
                if (this.m_adjacency[X][i] == 1 && this.vertex[i].Hit == false) {
                    this.vertex[i].Hit = true;
                    this.vertex[i].vertPrevVisit = X;//Предыдущ связанный узел

                    if (i == VTo) {
                        retList = this.creatVertexWay(VFrom, VTo);
                    }
                    // добавляем смежных и подсчитываем их
                    v_queue.add(i);// загнать детей в циелк -

                }
            }
        }
        return retList;
    }

    ArrayList<Vertex>  creatVertexWay (int v_from, int v_to)
    {
        ArrayList<Vertex> retList = new ArrayList<>();
         Stack stack = new Stack();
         stack.push(this.vertex[v_to]);

         int vtmp = v_to;

        while (vtmp != v_from) {
            System.out.println("vtmp = " + vtmp);
            vtmp = this.vertex[vtmp].vertPrevVisit;// то что было посещено
             stack.push(this.vertex[vtmp]);
        }

        while(!stack.isEmpty()) {
            Vertex v = (Vertex) stack.peek();
            stack.pop();
            retList.add(v);
        }
        return retList;
    }

//=================================================================================
//    * Используя BFS, найдите два наиболее удалённых друг от друга узла в
//    обычном дереве, которое теперь воспринимаем как граф, и возвращайте это
//    максимальное расстояние.
//===================================================================================
    // если нраф в виде дерева - то наиболее отдаленные элементы  - это корень и  кто то из листьев
    // Однако в графе нет списка "детей" и есть трудность с определением уровня
    // Поэтому если корень - первый элеент массива веошин
    // Надо найти пути до всех последующих вершин и выбрать наибольший

    public int searchLongestWay () {
        int [] a = new int [vertex.length - 1];
        int max_way = 0;

        for (int i = 0; i < vertex.length - 1; i++) {
            ArrayList<Vertex> a_list = this.BreadthFirstSearch(0, i);
            int cur_way = a_list.size();
            if (cur_way > max_way)
                max_way = cur_way;
        }
        return max_way;
    }
