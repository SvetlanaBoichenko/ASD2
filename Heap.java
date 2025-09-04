
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
        int i = 0;
        int ind = 0;
        while (i < this.HeapArray.length / 2) {
            ind =  2 * i + 1;    // index of parent
            if (this.HeapArray[2*i +2] > this.HeapArray[ind])
                ind = 2 * i + 2;

            if (this.HeapArray[i] >= this.HeapArray[ind])
                break;

            int tmp = this.HeapArray[i];
            this.HeapArray[i] = this.HeapArray[ind];
            this.HeapArray[ind] = tmp;
            i = ind;
        }
    }

    void goUp () {
        int i = this.node_count - 1;
        int ind = 0;
        while (i > 0) {
            ind = (i - 1) / 2;
            if (this.HeapArray[i] < this.HeapArray[ind])
                break;
            int tmp =  this.HeapArray[i];
            this.HeapArray[i] = this.HeapArray[ind];
            this.HeapArray[ind] = tmp;
            i = ind;
        }
    }

    public boolean Add(int key)
    {
        // heap ia fill all
        if (this.node_count == this.HeapArray.length) {
            return false;
        }
        // Add key and rebuild
        this.HeapArray[this.node_count] = key;
        this.node_count = this.node_count +1;
        this.goUp();
        return true;
    }

 
 } 



