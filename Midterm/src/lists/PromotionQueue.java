package lists;

public class PromotionQueue<T> {
    private class QueueNode {
        QueueNode prev;
        QueueNode next;
        T item;
        private final int nodeId;
        private QueueNode(T value, int id){
            this.item = value;
            this.prev = null;
            this.next = null;
            this.nodeId = id;
        }
    }

    private QueueNode sentFront;
    private QueueNode sentBack;
    private int listSize;

    public PromotionQueue(){
        sentFront = new QueueNode(null, -1);
        sentBack = new QueueNode(null, -1);
        sentFront.next = sentBack;
        sentBack.prev = sentFront;
        listSize = 0;
    }

    public void addFront(T value){
        QueueNode node = new QueueNode(value, listSize + 1);
        QueueNode currentHead = sentFront.next;

        node.next = currentHead;
        currentHead.prev = node;

        node.prev = sentFront;
        sentFront.next = node;
        listSize += 1;
    }

    public void addBack(T value){
        QueueNode node = new QueueNode(value, listSize + 1);
        QueueNode currentTail = sentBack.prev;

        node.next = sentBack;
        currentTail.next = node;

        node.prev = currentTail;
        sentBack.prev = node;
        listSize += 1;
    }

    /*
        Question 5
     */
    public void promote(int indexToPromote) {
        if (indexToPromote <= 0 || indexToPromote >= size()) {
            return; //return if index is greater than the size or less/equal to 0
        }
        QueueNode currNode = sentFront.next; //front node
        for (int i = 0; i < indexToPromote; i++) {//iterates until the indextopromote
            currNode = currNode.next;
        }
        if (currNode == null) { //return if null
            return;
        }
        QueueNode prevNode = currNode.prev;
        QueueNode nextNode = currNode.next;
        prevNode.next = nextNode; //removes the current node
        if (nextNode != null) {
            nextNode.prev = prevNode;
        }
        currNode.prev = prevNode.prev; //update values
        currNode.next = prevNode;
        prevNode.prev.next = currNode;
        prevNode.prev = currNode;
    }

    /*
        Question 5 continued
     */
    public void demote(int indexToDemote) {
        if (indexToDemote < 0 || indexToDemote >= size() - 1) {
            return;
        }
        QueueNode currNode = sentFront.next;
        for (int i = 0; i < indexToDemote; i++) {
            currNode = currNode.next;
        }
        if (currNode == null) {
            return;
        }
        QueueNode prevNode = currNode.prev; //update values
        QueueNode nextNode = currNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;

        currNode.prev = nextNode;
        currNode.next = nextNode.next;
        if (nextNode.next != null) {
            nextNode.next.prev = currNode;
        }
        nextNode.next = currNode;
    }


    public int size() {
        return listSize;
    }

    /*
        Do not modify this method in any way
     */
    public String toString(){
        StringBuilder stringRepr = new StringBuilder();
        QueueNode runner = sentFront.next;
        while(runner != sentBack){
            String nodeRepr = "ID:" + runner.nodeId + "-" + runner.item;
            stringRepr.append(nodeRepr);
            if(runner.next != sentBack){
                stringRepr.append(", ");
            }
            runner = runner.next;
        }
        return stringRepr.toString();
    }
}
