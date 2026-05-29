package lists;

public class DList<T> {
    private class DListNode {
        DListNode prev;
        DListNode next;
        T item;
        private DListNode(T value){
            this.item = value;
            this.prev = null;
            this.next = null;
        }

        private DListNode(T value, DListNode prev, DListNode next){
            this.item = value;
            this.prev = prev;
            this.next = next;
        }
    }

    private DListNode sentFront;
    private DListNode sentBack;
    private int listSize;

    public DList(T value){
        sentFront = new DListNode(null);
        sentBack = new DListNode(null);

        DListNode headNode = new DListNode(value, sentFront, sentBack);

        sentFront.next = headNode;
        sentBack.prev = headNode;
        listSize = 1;
    }

    public DList(){
        sentFront = new DListNode(null);
        sentBack = new DListNode(null, sentFront, null);
        sentFront.next = sentBack;
        listSize = 0;
    }

    public void insertElement(int index, T value){
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index out of bounds for list of size " + size());
        } else {
            DListNode runner = sentFront.next;
            int runnerCount = 0;
            while(runnerCount < index) {
                runner = runner.next;
                runnerCount += 1;
            }

            DListNode prevNode = runner.prev;
            DListNode nodeToInsert = new DListNode(value, prevNode, runner);
            prevNode.next = nodeToInsert;
            runner.prev = nodeToInsert;
            listSize += 1;
        }
    }

    /*
        Question 7
     */
    public T get(int index) {
        if (index >= size() || index < -size()) {
            throw new IndexOutOfBoundsException("IndexOutOfBoundsException");
        }
        if (index < 0) {
            index = size() + index;
        }
        DListNode runner; //initialize runner similar to insertElement()
        if (index < size() / 2) {
            runner = sentFront.next;
            for (int i = 0; i < index; i++) {
                runner = runner.next;
            }
        } else {
            runner = sentBack.prev;
            for (int i = size() - 1; i > index; i--) {
                runner = runner.prev;
            }
        }
        return runner.item;
    }

    public int size(){
        return listSize;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        DListNode runner = sentFront.next;
        while(runner != sentBack){
            result.append(runner.item);
            runner = runner.next;
            if(runner != sentBack){
                result.append(",");
            }
        }
        return result.toString();
    }
}
