
import java.util.*;

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

class BalancedBST
{
    public BSTNode Root; // корень дерева

    public BalancedBST()
    {
        Root = null;
    }

    public void GenerateTree(int[] a)
    {
    
        // 1. Sort
        int len = a.length;
        //Sort
        Arrays.sort(a);

        BSTNode b[] = new BSTNode[len];   
        // 2. nodes
        makeBSTArray (0, len-1, 0, a,  null, 0); // 
    }


    public  BSTNode makeBSTArray (int left, int right, int pos, int[] A,  BSTNode parent, int level )
    {
        if (left > right)
            return null;
      
        int mid = (int)((left+right) / 2 );
        int val = A [mid];
        //  node
        BSTNode new_nod = new BSTNode (val, parent);
        new_nod.Level = level;  //(int)(Math.log(node_count) / Math.log(2));;
      
        new_nod.LeftChild = makeBSTArray (left, mid -1, pos*2+1, A, new_nod,level+1); //B,
        new_nod.RightChild = makeBSTArray (mid +1, right, pos*2+2, A, new_nod, level+1);

        return new_nod;
    }


    public boolean IsBalanced(BSTNode root_node)
    {
      int r = difLevels(root_node);
       if ( r < 2)
            return true; //

        return false;
    }

    public  int difLevels(BSTNode parent_node)
    {
        if (parent_node == null) {
            return -1;
        }
// necesita extacto left and right
        int left_level = difLevels(parent_node.LeftChild);
        int right_level = difLevels(parent_node.RightChild);
 
        int ret = Math.abs(left_level - right_level);

        return ret;
    }

    

}



