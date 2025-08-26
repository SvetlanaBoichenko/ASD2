
import java.util.*;

class BSTNode
{
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int     Level; // глубина узла

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
        // создаём дерево с нуля из неотсортированного массива a
        // 1. сортировка
        int len = a.length;
        //Sort
        Arrays.sort(a);

        BSTNode b[] = new BSTNode[len];
        //  BSTNode c = makeBSTArray (0, len-1, 0, a, b, null, 0);

        // for (int i = 0; i < len; i++) {
        // 2. формирование связей с записью уровня
        makeBSTArray (0, len-1, 0, a,  null, 0); // b,
        //}


    }


    public  BSTNode makeBSTArray (int left, int right, int pos, int[] A,  BSTNode parent, int level )// BSTNode[] B,
    {
        if (left > right)
            return null;
        //  if (pos > B.length-1)
        //  return parent;

        int mid = (int)((left+right) / 2 );
        int val = A [mid];
        // Создать node
        BSTNode new_nod = new BSTNode (val, parent);
        new_nod.Level = level;  //(int)(Math.log(node_count) / Math.log(2));;
        //  B[pos] = new_nod;

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
     // return different +1 -(-1) = 2
        int ret = Math.abs(left_level - right_level);

        return ret;
    }



public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
    }
}

/*
if (currentNode == null)
        {
        return 0;
        }

// checking left subtree
int leftSubtreeHeight = balanceHeight (currentNode.left);
        if (leftSubtreeHeight == -1) return -1;
// if left subtree is not balanced then the entire tree is also not balanced

//checking right subtree
int rightSubtreeHeight = balanceHeight (currentNode.right);
        if (rightSubtreeHeight == -1) return -1;
        // if right subtree is not balanced then the entire          tree is also not balanced

        //checking the difference of left and right subtree for current node
        if (Math.abs(leftSubtreeHeight - rightSubtreeHeight) > 1)
        {
        return -1;
        }
        //if it is balanced then return the height
        return (Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1);
        } */