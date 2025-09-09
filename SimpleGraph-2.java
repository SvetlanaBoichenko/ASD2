//------------------------------------------------------------
// Задача 6 задание 2*
// строка 66
//
// Задача 6 задание 3*
// "Добавьте метод проверки, действительно ли дерево получилось сбалансированным"
// сделана в рамках основного задания 6
//--------------------------------------------------------------

class BSTNode
{
    public int NodeKey; //
    public BSTNode Parent; //
    public BSTNode LeftChild; //
    public BSTNode RightChild; //
    public int     Level; //

    public BSTNode(int key, BSTNode parent)
    {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

 public class BalancedBST {
    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        // len
        int len = a.length;
        // 1. Sort
        Arrays.sort(a);
        // 2. root
        this.Root = makeBSTArray(0, len - 1, a, null, 0); //
    }

    public BSTNode makeBSTArray(int left, int right, int[] A, BSTNode parent, int level) {

        int mid = (int) ((left + right) / 2);
        int val = A[mid];
        //  node
        BSTNode new_nod = new BSTNode(val, parent);
        new_nod.Level = level;  //(int)(Math.log(node_count) / Math.log(2));;

        if (right > left) {
            new_nod.LeftChild = makeBSTArray(left, mid - 1, A, new_nod, level + 1); //
            new_nod.RightChild = makeBSTArray(mid + 1, right, A, new_nod, level + 1);
        }
        return new_nod;
    }

 //========================================================================
 //  Задаяа 6, задание 2*:
 //  "Добавьте метод проверки, действительно ли дерево получилось правильным."
 //    
 //  Задание делала по рекомендации к решению заданий из № 6, из задачи 8 
 //  Сложность O(N) временная и пространственная    
 //=========================================================================
     
    public boolean   IsBSTTreeCorrect(BSTNode root) {
        if (root == null) {
            return true;
        }

        if (root.LeftChild != null && root.LeftChild.NodeKey >= root.NodeKey) {
            return false;
        }

        if (root.RightChild != null && root.RightChild.NodeKey < root.NodeKey) {
            return false;
        }

       boolean L = IsBSTTreeCorrect(root.LeftChild);
        if (L == false) {
            return false;
        }

       boolean R =  IsBSTTreeCorrect(root.RightChild);
        if (R == false) {
            return false;
        }

        return true;
    }


} 


