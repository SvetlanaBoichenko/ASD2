import java.util.Arrays;

public class AlgorithmsDataStructures2 {

    public static int[] GenerateBBSTArray(int[] a) {
        int len = a.length;
        //Sort
        Arrays.sort(a);

        int b[] = new int[len];

        return makeBSTArray(0, len - 1, 0, a, b);

    }

    public static  int [] makeBSTArray (int left, int right, int pos, int[] A, int[] B )
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


    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

    }
}

