
public class aBST {
    public Integer Tree[]; // 

    public aBST(int depth) {
        int tree_size = (int)Math.pow(2,depth+1) -1;
        if (tree_size >= 0) {
            Tree = new Integer[tree_size];

            for (int i = 0; i < tree_size; i++)
                Tree[i] = null;
        }
    }

    public Integer FindKeyIndex(int key) {
        
        int index = 0;
        while (index <= this.Tree.length - 1) {
            if (Tree[index] == null) {
                return -index;
            }

            if (Tree[index] == key) {
                return index;
            }
                if (key < Tree[index])
                    index = index * 2 + 1;
                else
                    index = index * 2 + 2;
        }
        return null; // 
    }

        public int AddKey(int key) {
      
           Integer index = this.FindKeyIndex(key);
            if (index == null) {
                return -1;
            }

            if (index > 0)
                return index;

            if (index < 0 || index == 0 && this.Tree[0] == null)
                this.Tree[-index] = key;

            return -index;
        }

   
}



