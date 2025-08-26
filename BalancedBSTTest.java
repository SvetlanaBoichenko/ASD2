import static org.junit.jupiter.api.Assertions.*;

    @org.junit.jupiter.api.Test
    void  GenerateTree() {

        int A [] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        BalancedBST AD = new BalancedBST();

        BSTNode res   = AD.makeBSTArray ( 0, A.length-1,   0 ,A, null, 1);
        boolean is_balansed = AD.IsBalanced(res);
        assertTrue (is_balansed == true);
        res.LeftChild.LeftChild.LeftChild.LeftChild = new BSTNode(111, res.LeftChild.LeftChild.LeftChild);
        res.LeftChild.LeftChild.LeftChild.LeftChild.LeftChild = new BSTNode(112, res.LeftChild.LeftChild.LeftChild.LeftChild);
        res.LeftChild.LeftChild.LeftChild.LeftChild.LeftChild.LeftChild = new BSTNode(112, res.LeftChild.LeftChild.LeftChild.LeftChild.LeftChild);

        is_balansed = AD.IsBalanced(res);
        assertTrue (is_balansed == false);
        int A1 [] = {1,2,3,4,5,6,7};

        BSTNode res1   = AD.makeBSTArray ( 0, A1.length-1,   0 ,A1, null, 1);
        is_balansed = AD.IsBalanced(res1);
        assertTrue (is_balansed == true);

        BSTNode root1 = new BSTNode(1,null);
        is_balansed = AD.IsBalanced(root1);
        assertTrue (is_balansed == true);

        root1.LeftChild = new BSTNode(111, root1);
        is_balansed = AD.IsBalanced(root1);
        assertTrue (is_balansed == true);

        root1.LeftChild.LeftChild = new BSTNode(112, root1.LeftChild);
        is_balansed = AD.IsBalanced(root1);
        assertTrue (is_balansed == false);


        root1.RightChild = new BSTNode(114, root1);
        is_balansed = AD.IsBalanced(root1);
        assertTrue (is_balansed == true);

        root1.LeftChild.LeftChild.LeftChild = new BSTNode(113, root1.LeftChild.LeftChild);
        is_balansed = AD.IsBalanced(root1);
        assertTrue (is_balansed == false);

        //  assertArrayEquals(res,waitres);
}

    public void main() {
        GenerateTree();

    }

