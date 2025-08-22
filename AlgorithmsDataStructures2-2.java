//===========================================================================================
// Урок 3 задание 5*
//      
// Восстановление BST дерева из результаты обхода дерева в префиксном и инфиксном порядке
// Сложность O(N)
// Рефлексия:
// Оченьдолго возилась с индексами при построении исхродного дерева по результатам двух обходов. 
// Помогла подсказка из рекомендаций по задачам* урока 3, приведенным в задании 5
// Реализовать восстановление BST дерева оказалось легче,чем просто бинарного дерева, учитывая особенности его построения.
// Не надо искать узлы из дерева в результатах pre , а потом в in  - обходов, можно использовать результат 
// pre - обхода и структуру BST дерева.
// При решении опдпобной задачи для обычного дерева, неодходимы результаты обоих обходов, для установления корня и узлов. 
//===========================================================================================
    public BST ReсoverTree (int prearr[]) {

        BSTNode nod =  ReсoverBSTTree(prearr, 0, prearr.length-1,null);
        return new BST(nod);
    }

    public BSTNode ReсoverBSTTree (int prearr[],int left, int right, BSTNode parent)
    {
        if (left >= right) {
            return null;
        }
        BSTNode nod = new BSTNode (prearr[left], prearr[left], parent);
        int ind = 0;
        for (int i = left+1; i < right; i++) {
            if (prearr[i] > prearr[left]) {      //
                ind = i;
                break;
            }
            ind = i;
        }
        right = ind; // Изменение right здесь

        if (left+1 >= right) {
            return nod;
        }
        if (right >= prearr.length) {
            return nod;
        }

        nod.LeftChild  = ReсoverBSTTree (prearr, left+1, right, nod);
        nod.RightChild = ReсoverBSTTree (prearr, right, prearr.length, nod);
        return nod;
    }


//=====================================================================================================
// Задаие 4 задача 2*
// Поиск наименьшего общего предка (LCA)
// можно использовать индексы массива, зная формулу поиска индеква предка Index_предка = (Iindex_элемента - 1) / 2
// Сложность O(logN) пространственная и временная
//========================================================================================================

 public Integer LookLCT (Integer bst_array[], Integer node_val1, Integer node_val2)  
    {
       int index_nod1 = -1, index_nod2 = -1;

        for (int i = 0; i < bst_array.length; i++) {     // Поиск текущих индексов
           if (bst_array [i] == node_val1)
               index_nod1 = i;

            if (bst_array [i] == node_val2)
                index_nod2 = i;

            if(index_nod1 >= 0 && index_nod2 >= 0){
                break;
            }
        }
        if (index_nod1 == -1  || index_nod2 == -1)
            return -1;                          // не найден эл.
        if (index_nod1 == index_nod2)
            return index_nod1;                // совпалb из за int,но меньший индекс из них(из за int) = предка

        int res_index = 0;
        for (int i = 0; i < bst_array.length; i++) {
                                                //  индекс родителей
            index_nod1 = (index_nod1 - 1) / 2;
            index_nod2 = (index_nod2 - 1) / 2;  // Уменьш индех-идем вверх
                                         
            if (index_nod1 == index_nod2       // совпалb из за int,но меньший индекс из них(из за int) = предка
                || index_nod1 == 0) {          // или корень уже
                res_index = index_nod1;
                break;
            }

            if (index_nod2 == 0) {       //  корень уже
                res_index = index_nod2;
                break;
            }
        }

        if (res_index >= 0)
            return bst_array[res_index];

        return null;
    } 






