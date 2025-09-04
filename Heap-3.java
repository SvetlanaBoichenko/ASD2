
import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @org.junit.jupiter.api.Test
    void makeHeap() {
        int arr1 []  = {1,2,3,4,5,6,7};
        Heap hp1 = new Heap();
        hp1.MakeHeap(arr1,2 );
        int[] wait_arr = { 7,4,6,1,3,2,5};
        assertArrayEquals (hp1.HeapArray, wait_arr);

        int arr2 []  = {1,2,3,4,5,6,7,8,9,11,10,13,12,15,14};
        Heap hp2 = new Heap();
        hp2.MakeHeap(arr2,3 );
        int[] wait_arr2 = { 15, 10, 14, 7, 9, 11, 13, 1, 4, 3, 8, 2, 6, 5, 12};
        assertArrayEquals (hp2.HeapArray, wait_arr2);

        int arr3 []  = {1};
        Heap hp3 = new Heap();
        hp3.MakeHeap(arr3,0 );
        int[] wait_arr3 = {1};
        assertArrayEquals (hp2.HeapArray, wait_arr2);

    }

            @org.junit.jupiter.api.Test
            void getMax() {
                int arr1 []  = {1,2,3,4,5,6,7};
                Heap hp1 = new Heap();
                hp1.MakeHeap(arr1,2 );
                int[] wait_arr = { 7,4,6,1,3,2,5};
                assertArrayEquals (hp1.HeapArray, wait_arr);

                int L = hp1.GetMax();
                assertTrue (L == 7);
                int[] wait_arr2 = {6,4,5,1,3,2,-1};
                assertArrayEquals (hp1.HeapArray, wait_arr2);

                L = hp1.GetMax();
                assertTrue (L == 6);
                int[] wait_arr3 = {5,4,2,1,3,-1, -1};
                assertArrayEquals (hp1.HeapArray, wait_arr3);

                L = hp1.GetMax();
                assertTrue (L == 5);
                int[] wait_arr4 = {4,3,2,1, -1,-1,-1};
                assertArrayEquals (hp1.HeapArray, wait_arr4);

                L = hp1.GetMax();
                assertTrue (L == 4);
                int[] wait_arr5 = {3,1,2,-1, -1,-1,-1};
                assertArrayEquals (hp1.HeapArray, wait_arr5);

                L = hp1.GetMax();
                assertTrue (L == 3);
                int[] wait_arr6 = {2, 1, -1, -1, -1, -1, -1};
                assertArrayEquals (hp1.HeapArray, wait_arr6);


                L = hp1.GetMax();
                assertTrue (L == 2);
                int[] wait_arr7 = {1, -1, -1, -1, -1, -1, -1};
                assertArrayEquals (hp1.HeapArray, wait_arr7);

                L = hp1.GetMax();
                assertTrue (L == 1);
                int[] wait_arr8 = {-1, -1, -1, -1, -1, -1, -1};
                assertArrayEquals (hp1.HeapArray, wait_arr8);

                L = hp1.GetMax();
                assertTrue (L == -1);
                int[] wait_arr9 = {-1, -1, -1, -1, -1, -1, -1};
                assertArrayEquals (hp1.HeapArray, wait_arr9);

            }

        }



