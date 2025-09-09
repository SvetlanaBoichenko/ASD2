import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleGraphTest {
    SimpleGraph sg = new SimpleGraph( 6);

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
        assertTrue (sg.vertex.length == 6);
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
        sg.AddEdge(0,1);
        sg.AddEdge(3,6);
        assertTrue (sg.IsEdge(0,1) == true);
        assertTrue (sg.IsEdge(3,6) == false);
        sg.RemoveEdge (0, 1);
        assertTrue (sg.IsEdge(0,1) == false);

    }

}
