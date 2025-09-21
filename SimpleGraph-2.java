//==================================================================
// Задание 8 задача 2*
//Реализуйте направленный граф, представленный матрицей смежности,
// и добавьте метод проверки.
//
// Для реализации направленного графа необходимо знать направления ребра между вершинами
// Например (1,2) от вершины с индекосм 1 к вершине с индексом 2 (из матрицы)
// Для добаления ребра реализована ф-ция AddDirectEdgeint v1, int v2)
// Строка 66
//
//Проверьте, будет ли он циклическим.
// Функция выявления цикличности isHaveLoops ()
// Строка 96
//=====================================================================

//КОнстанты для обозначения состояния вершин
   static final int IN_STACK = 1;
    static final int VISITED = 2;
    static final int NO_VISITED = 0;

class Vertex
{
    public int Value;
    int visited;
    public Vertex(int val)
    {
        Value = val;
        visited  = NO_VISITED;
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


    public void RemoveVertex(int v) {
        if (v >= this.vertex.length || v < 0)
            return;

        this.vertex[v] = null; // delete vertex
        for (int i = 0; i < this.vertex.length; i++) {
            this.m_adjacency[i][v] = 0; // delete ribs
            this.m_adjacency[v][i] = 0;
        }
    }
//====================================================================
    
    public void AddDirectEdge(int v1, int v2) {
        if (v1 < this.vertex.length && v2 < this.vertex.length) {
            this.m_adjacency[v1][v2] = 1;

        }
    }
    public boolean IsDirectEdge(int v1, int v2) {
        if (v1 < this.vertex.length && v2 < this.vertex.length)
            return (this.m_adjacency[v1][v2] == 1);

        return false;
    }

    int look_adj_direct_vetrex(int v) {
          for (int i = 0; i < this.max_vertex; i++) {
            if (this.IsDirectEdge(v, i) == true && this.vertex[i].visited == NO_VISITED)
                return i;
            if (this.IsDirectEdge(v, i) == true && this.vertex[i].visited == IN_STACK)
                return -2;
        }
        return -1;// Не найдены вообще смежные или уже с ними сталкивались и они обработаны
    }

//===============================================================
//Функция выявления цикличности. Для реализации воспользовалась советами 
// из рекомендаций. 
//Сложность O(V)пространственная, где V - число вершин
// O(V + E) временная. Е - число ребер
//===============================================================
    
    public boolean isHaveLoops () {
        int VFrom = 0;
        int VTo = max_vertex-1;

        if (VFrom == VTo || max_vertex == 2) { // не надо никуда ходить
            return false;                       // нет петель
        }

        Vertex v1 = vertex[VFrom];          //по номерам позиицй
        Vertex v2 = vertex[VTo];

        int X = VFrom;                      // Временная переменная

        Stack<Integer> v_stack = new Stack<>(); // создаем стек
        v_stack.push(X);                        //Помещаем первую в стек
        this.vertex[X].visited = IN_STACK;      // в стеке сейчас

        while (X != -1) {
            X = this.look_adj_direct_vetrex (X); //поиск смежных с Х вершин

            if (X == -2)                    // Смежная и она уже есть IN STACK
                return true;
            if (X == -1) {                  // нет смежных вершин - удаляем из стека->
                int X1 =  v_stack.pop();    // -> удаляем из стека (нет смежных)
                this.vertex[X1].visited = VISITED; // Мы тут были, но смежных не нашли
                if (v_stack.isEmpty())
                    continue;
                X = v_stack.peek();         // Следующий из хранилища прочитали - отправляем на поиск смежных вершин
        }  else {
                v_stack.push(X);            // затолкали Х в стек(текущих повторяющихся быть не должно)
                this.vertex[X].visited = IN_STACK;//В процессе- в стеке - (можно попасть сюда повторно, но после выталкивания)
            }
        }
        return false;
    }


} 

