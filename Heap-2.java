
import java.util.*;

public class AlgorithmsDataStructures3 {
    static final int VALUE_NONE = -1;

    public int count_nodes = 10;

    public int[] GenerateBBSTArray(int[] a) {

        //Sort
        Arrays.sort(a);
        int len = a.length;
        int b[] = new int[len];
     //  AlgorithmsDataStructures3 bbst3 = new AlgorithmsDataStructures3();
        this.count_nodes =len;// массив полный

        return makeBSTArray(0, len - 1, 0, a, b);

    }

    public static int [] makeBSTArray (int left, int right, int pos, int[] A, int[] B )
    {
        if (left > right)
            return B;

        int mid = (int)((left+right) / 2 );
        int val = A [mid];

        if (pos > B.length-1)
            return B;
        B[pos] = val;

        makeBSTArray (left, mid -1, pos*2+1, A, B);
        makeBSTArray (mid +1, right, pos*2+2, A, B);
        return B;
    }

    public int IndexMaxofLeftBranch (int b [], int left_index)
    {
        int index_left_max = 0;

        for (int i = left_index; i < b.length; i = i*2 + 2) {
            index_left_max = i; // Правые
        }
        return index_left_max;
    }

//======================================================================================
// Задание 5 задача 3*
// 3.* Метод удаления узла из двоичного дерева, заданного в виде массива. 
// Сложность O(logN)    
// При решении руководствовалась рекомендациями к этой задаче, приведенными в задании 7
// Сложнее всего оказалось тянуть цепочку всех потомков при удалении узла с одним потомком.
// Не совсем понятно, когда точно делать балансировку, сделала когда число пустых узлов = числу
//    листов для заданной глубины массива
//=========================================================================================
  
    public int[] delete_arr_node_val (int []b, int val) {   //1. найти индекс
        int index = -1;
        for (int i = 0; i < b.length; i++) {
            if (b[i] == val) {
                index = i;
                break;
            }
        }

        // лист или нет
        if (index >= b.length/2 && index <b.length) {
            b[index] = VALUE_NONE;
            this.count_nodes--;
            return RebalanceTreeArray (b);
        }
        // Два потомка
        if (b[index * 2 + 1] != VALUE_NONE && b[index * 2 + 2] != VALUE_NONE) {
            int max_left_index = IndexMaxofLeftBranch (b, index * 2 + 1); //самый правый-это макc из левого

            b[index] = b[max_left_index];
            b[max_left_index] = VALUE_NONE;
            this.count_nodes--;
            return RebalanceTreeArray (b);
        }
// только левый потомок
        int next_i = -1;
        if (b[index * 2 + 2] == VALUE_NONE) {
            next_i = index * 2 + 1;
        }
// только правый потомок
        if (b[index * 2 + 1] == VALUE_NONE) {
            next_i = index * 2 + 2;
        }
// меняем массив - поднятие потомков
        b[index] = b[next_i];
        b[next_i] = VALUE_NONE;
        for (int i = next_i; i < b.length; i = i * 2 + 1) {
            if ((i * 2 + 2) < b.length) {
                b[i] = b[i * 2 + 1];
                b[i + 1] = b[i * 2 + 2];
            }
            count_nodes--;
        }
        return RebalanceTreeArray (b);
    }


    int [] RebalanceTreeArray (int[] a ) {
        if (this.count_nodes > (int)a.length/2)
            return a;

        int c[] = new int[this.count_nodes];
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != VALUE_NONE ) { //&& j < c.length
                c[j] = a[i];
                j++;
                if (j >= c.length)
                    break;
            }
        }
        return GenerateBBSTArray(c);
    }

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

    }


}


