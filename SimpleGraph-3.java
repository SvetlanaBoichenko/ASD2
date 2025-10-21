import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    SimpleGraph sg = new SimpleGraph( 7);

    @org.junit.jupiter.api.Test
    void addVertex() {
        sg.AddVertex(1);
        assertTrue (sg.vertex[0].Value == 1 );
        sg.AddVertex(2);
        assertTrue (sg.vertex[1].Value == 2 );
        assertTrue (sg.vertex[0].Value == 1 );
        sg.AddVertex(1);
        assertTrue (sg.vertex[1].Value == 2 );
        assertTrue (sg.vertex[0].Value == 1 );
        assertTrue (sg.vertex.length == 7);
        assertTrue (sg.vertex[2].Value == 1 );
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);
        assertTrue (sg.vertex[5].Value == 5);
        sg.AddVertex(7);
        assertTrue (sg.vertex[5].Value == 5);

        sg.RemoveVertex(0);

        SimpleGraph sg1 = new SimpleGraph( 0);
        assertTrue (sg1.vertex.length == 0);
    }

    @org.junit.jupiter.api.Test
    void removeVertex() {
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);

        sg.RemoveVertex(5);
        assertTrue (sg.vertex[5] == null);
        assertTrue (sg.m_adjacency[0][5] == 0);
        assertTrue (sg.m_adjacency[5][0] == 0);


    }

    @org.junit.jupiter.api.Test
    void isEdge() {
        sg.m_adjacency[1][2] = 1;
        sg.m_adjacency[2][1] = 1;

        assertTrue (sg.IsEdge(1, 2) == true);
        assertTrue (sg.IsEdge(2, 1) == true);

        assertTrue (sg.IsEdge(1, 3) == false);
        sg.m_adjacency[1][3] = 1;
        assertTrue (sg.IsEdge(1, 3) == false);
    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        sg.AddEdge(2,4);
        sg.AddEdge(3,5);
        sg.AddEdge(5,5);

        assertTrue (sg.IsEdge(3,5) == true);
        assertTrue (sg.IsEdge(5,3) == true);

        assertTrue (sg.IsEdge(2,4) == true);
        assertTrue (sg.IsEdge(2,5) == false);

        assertTrue (sg.IsEdge(2,2) == false);
        assertTrue (sg.IsEdge(5,5) == true);

        sg.AddEdge(30,5);
        assertTrue (sg.IsEdge(30,5) == false);
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        sg.AddEdge(0, 1);
        sg.AddEdge(3, 6);
        assertTrue(sg.IsEdge(0, 1) == true);
        //assertTrue(sg.IsEdge(3, 6) == false);
        sg.RemoveEdge(0, 1);
        assertTrue(sg.IsEdge(0, 1) == false);
    }
    @org.junit.jupiter.api.Test
    void look_adj_vetrex_test () {
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);

        int a = sg.look_adj_vetrex(1);
        assertTrue(a == -1);
        sg.AddEdge(0, 1);
        sg.AddEdge(3, 6);
        sg.AddEdge(1, 5);

        a = sg.look_adj_vetrex(1);
        assertTrue(a == 0);

        sg.RemoveEdge(1, 0);
        a = sg.look_adj_vetrex(1);
        assertTrue(a == 5);
    }

    @org.junit.jupiter.api.Test
    void DepthFirstSearch_test () {
        //    DepthFirstSearch() возвращает пустой список для существующего пути
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);
        sg.AddEdge(0, 2);
        sg.AddEdge(1, 2);
        sg.AddEdge(2, 4);
        sg.AddEdge(4, 5);

        ArrayList<Vertex> l2 = sg.DepthFirstSearch(0, 3);
        assertEquals(l2.size(), 0);

        ArrayList<Vertex> l = sg.DepthFirstSearch(5, 1);
        assertEquals(l.get(0).Value, 6);
        assertEquals(l.get(1).Value, 5);
        assertEquals(l.get(2).Value, 3);
        assertEquals(l.get(3).Value, 2);

        ArrayList<Vertex> l1  = sg.DepthFirstSearch(1, 4);
        assertEquals(l1.get(0).Value, 2);
        assertEquals(l1.get(1).Value, 3);
        assertEquals(l1.get(2).Value, 5);

    }


    @org.junit.jupiter.api.Test
    void BreadthFirstSearch () {
        sg.AddVertex(0);
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);
        sg.AddEdge(0, 2);
        sg.AddEdge(1, 2);
        sg.AddEdge(1, 3);
        sg.AddEdge(2, 3);
        sg.AddEdge(2, 4);
        sg.AddEdge(4, 5);

        ArrayList<Vertex> l14 = sg.BreadthFirstSearch (4, 6);
        assertTrue(l14.size() == 0);

        sg.AddEdge(4, 6);
        sg.AddEdge(5, 6);

        ArrayList<Vertex> l4 = sg.BreadthFirstSearch (0, 0);
        assertTrue(l4.size() == 1);

        ArrayList<Vertex> l11 = sg.BreadthFirstSearch (0, 0);
        assertTrue(l11.size() == 1);

        ArrayList<Vertex> l5 = sg.BreadthFirstSearch (0, 2);
        assertEquals(l5.get(0).Value, 0);
        assertEquals(l5.get(1).Value, 2);

        ArrayList<Vertex> l3 = sg.BreadthFirstSearch (0, 6);
        assertEquals(l3.get(0).Value, 0);
        assertEquals(l3.get(1).Value, 2);
        assertEquals(l3.get(2).Value, 4);
        assertEquals(l3.get(3).Value, 6);

        ArrayList<Vertex> l6 = sg.BreadthFirstSearch (0, 3);
        assertEquals(l6.get(0).Value, 0);
        assertEquals(l6.get(1).Value, 2);
        assertEquals(l6.get(2).Value, 3);

    } 

    @org.junit.jupiter.api.Test
    void WeakVertices() {
        sg.AddVertex(0);
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);

        ArrayList<Vertex> b1 = sg.WeakVertices();
        assertTrue (b1.size() == 7);

        sg.AddEdge(0, 1);
        sg.AddEdge(1, 2);
        sg.AddEdge(4, 5);
        ArrayList<Vertex> b = sg.WeakVertices();
        assertTrue (b.size() == 7);

        sg.AddEdge(0, 2);
        sg.searchTriangle(0,1, 6);

        ArrayList<Vertex> a = sg.WeakVertices();
        assertTrue (a.size() == 4);
        assertTrue (a.get(0).Value == 3);
        assertTrue (a.get(1).Value == 4);
        assertTrue (a.get(2).Value == 5);
        assertTrue (a.get(3).Value == 6);

        sg.AddEdge(2, 3);
        sg.AddEdge(3, 5);
        sg.AddEdge(5, 2);
        sg.AddEdge(4, 5);
        sg.AddEdge(0, 4);
        ArrayList<Vertex> a1 = sg.WeakVertices();
        assertTrue (a1.size() == 2);
        assertTrue (a1.get(0).Value == 4);
        assertTrue (a1.get(1).Value == 6);
        sg.AddEdge(0, 1);
        sg.AddEdge(1, 2);
        sg.AddEdge(0, 2);
        sg.AddEdge(4, 5);

    }

    @org.junit.jupiter.api.Test
    void TriangleVertices() {
        sg.AddVertex(0);
        sg.AddVertex(1);
        sg.AddVertex(2);
        sg.AddVertex(3);
        sg.AddVertex(4);
        sg.AddVertex(5);
        sg.AddVertex(6);

        sg.AddEdge(0, 2);
         int a = sg.TriangleVertices();
        assertTrue (a == 0);

        sg.AddEdge(2, 3);
        sg.AddEdge(3, 5);
        sg.AddEdge(5, 2);
        sg.AddEdge(4, 5);
        sg.AddEdge(0, 4);

        a = sg.TriangleVertices();
        assertTrue (a == 1);

        sg.AddEdge(5, 0);
        a = sg.TriangleVertices();
        assertTrue (a == 3);

        sg.AddEdge(3, 0);
        a = sg.TriangleVertices();
        assertTrue (a == 5);


}



