// Дополнительные задачи  к заданию 7
// Задача 4* строка 90
// Задача 5* строка 132
// Задача 6* - пока не решила(по разным причинам), попытаюсь перед окончанием курса,если можно
//
public class Heap
{
    public int [] HeapArray; //
    int node_count;

    public Heap() {
        HeapArray = null;
        node_count = 0;
    }

    public void MakeHeap(int[] a, int depth)
    {
        int len = (int) (Math.pow(2,(depth + 1) )- 1);
        this.HeapArray = new int [len];
        for (int i = 0; i < a.length; i++){
            this.Add(a[i]);
        }
    }
    public int GetMax() {  //return rais and rebuild hape
        if (this.node_count == 0)
            return -1;

        int ret = this.HeapArray[0];
        this.HeapArray[0] = this.HeapArray[this.node_count - 1];
        this.HeapArray[this.node_count - 1] = -1;// -1 < 0 -> NO_VALUE

        this.node_count = this.node_count - 1;
        this.goDown();

        return ret;
    }

    void goDown() {
        int i = 0;      // начинаем сначала-и до листьев
        int ind = 0;
        while (i < this.HeapArray.length / 2) {         //вторая половина-листья или null
            ind =  2 * i + 1;         // index of left child
            if (this.HeapArray[2 * i +2] > this.HeapArray[ind])
                ind = 2 * i + 2;     // index of right child

            if (this.HeapArray[i] >= this.HeapArray[ind])
                break;

            int tmp = this.HeapArray[i];
            this.HeapArray[i] = this.HeapArray[ind];
            this.HeapArray[ind] = tmp;
            i = ind;
        }
    }

    void goUp () {      // от листьев к корню
        int i = this.node_count - 1;
        int ind = 0;
        while (i > 0) {
            ind = (i - 1) / 2; // индекс родителя
            if (this.HeapArray[i] < this.HeapArray[ind])
                break;
            int tmp =  this.HeapArray[i]; // меняем местами с большим - двигаем вниз
            this.HeapArray[i] = this.HeapArray[ind];
            this.HeapArray[ind] = tmp;
            i = ind;
        }
    }

    public boolean Add(int key)
    {
        // heap fill all
        if (this.node_count == this.HeapArray.length) {
            return false;
        }
        // Add key and rebuild
        this.HeapArray[this.node_count] = key;// добавили в конец
        this.node_count = this.node_count +1;
        this.goUp(); // Двигаем вверх - меняем местами с меньшим
        return true;
    }
//===============================================================================
// Задание 7 задача 4*
// Добавьте метод поиска максимального элемента в заданном диапазоне значений
// Куча представлена в виде массива,
// в куче положительные неповторяющиеся числа
 // Числа расположены в неопределенном порядке в заданном диапазоне, не факт,
 // что первое больше второго или последнего  - если это не корень (нулевой элемент)

 int findMax (int a, int b)
 {
        int x = -1, y = -1;
        for (int i = 0; i < this.HeapArray.length; i ++) {

            if (this.HeapArray[i] == a) {
                x = i;
            }
            if (this.HeapArray[i] == b) {
                y = i;
            }

            if (x >= 0 && y >= 0)
                break;
        }

        int i;
        if (x > y) {
            i = y;
            y = x;
            x = i;
        }

        int max = x;
        for (i = x; i <= y; i++) {
            if (i == x)
                max = this.HeapArray[i];

            if (this.HeapArray[i] > max)
                max = this.HeapArray[i];
        }
        return max;
 }

// Задание 7 задача 5*
//-----------------Найти значение меньще заданного Х--------------------------------
// Точно равное заданное можно помощью хэш таблицы, которую можно слделать на основе массива кучи
// Меньше или больше - придется искать.
// Если не важно на сколько отличается искомое число от заданного Х после его нахождения можно сразу вернуть
// искомое число и прекратить поиск. Если необходимо вернуть наиболее приближенное к искомому
// - алгоритм может иметь сложность O(N) и не быть оптимальным.

    int MenosX (int X)
    {
        if (this.HeapArray[0] < X)
            return this.HeapArray[0];
        int node = this.HeapArray[0];
        return FindMenosX (this.HeapArray, X,0);
    }

    int FindMenosX ( int []heap_arr,int X, int nod_index )
    {
        int l_index = nod_index * 2 + 1;
        int r_index = nod_index * 2 + 2;
        int ret1 = -1, ret2 = -1;

        if (r_index >= heap_arr.length)
            return -1;;

        if (X > heap_arr[l_index] && X > heap_arr[r_index]) {
            int ret = Max(heap_arr[l_index], heap_arr[r_index]);
            return ret;
        }

       if (X <= heap_arr[l_index]) {
           ret1 = FindMenosX(heap_arr, X, l_index);
       }
        if (X <= heap_arr[r_index] ) {
            ret2 = FindMenosX(heap_arr, X, r_index);
        }

        if (X > heap_arr[l_index])
            ret1 = (heap_arr[l_index]);

        if (X > heap_arr[r_index])
            ret2 = heap_arr[r_index];

        return Max(ret1, ret2);
    }


    int Max (int a, int b) {

        if (a >= b)return a;
        return b;
    }







    public static void main(String[] args) {
       Heap H = new Heap();
       int [] Arr = {11,9,4,7,8,3,1,2,5,6};
       H.MakeHeap(Arr, 3);
        int m = H.FindMenosX(H.HeapArray, 11,0);

        int m2 = H.findMax(3,6);
        System.out.printf("menos X =  %d   %d \n", m, m2);
        }
}
