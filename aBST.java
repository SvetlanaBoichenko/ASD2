
public class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        int tree_size = (int)Math.pow(2,depth) -1;
        if (tree_size >= 0) {
            Tree = new Integer[tree_size];

            for (int i = 0; i < tree_size; i++)
                Tree[i] = null;
        }
    }

    public Integer FindKeyIndex(int key) {
      /*  Если всё дерево пройдено до его максимальной глубины и все узлы существуют,
        а совпадения не найдено, поиск возвращает null. Если узел (ключ) найден,
        поиск возвращает его индекс в массиве.
             Если найден незаполненный слот, подходящий для размещения указанного значения
        (другими словами, если очередной "узел", выбранный в процессе поиска, хранит null),
        поиск возвращает его индекс в виде отрицательного значения (например, -12).
        // ищем в массиве индекс ключа */

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
        return null; // не найден
    }

        public int AddKey(int key) {
        // добавляем ключ в массив
           Integer index = this.FindKeyIndex(key);
            if (index == null) {
                return -1;
            }

            if (index > 0)
                return index;

            if (index < 0 || index == 0 && this.Tree[0] == null)
                this.Tree[-index] = key;

            return -index;

        // индекс добавленного/существующего ключа или -1 если не удалось
        }

    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}