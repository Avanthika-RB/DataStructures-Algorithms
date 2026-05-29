package lists;

public class SList {
    private class ListNode{
        int item;
        ListNode next;

        private ListNode(int value){
            this.item = value;
            this.next = null;
        }

        private ListNode(int value, ListNode next){
            this.item = value;
            this.next = next;
        }
    }

    ListNode sentinelNode;
    int listSize;
    public SList(){
        sentinelNode = new ListNode(-1);
        listSize = 0;
    }
    public SList(int value){
        ListNode headNode = new ListNode(value);
        sentinelNode = new ListNode(Integer.MAX_VALUE, headNode);
        listSize = 1;
    }

    public void addFirst(int value){
        if(sentinelNode.next == null){
            sentinelNode.next = new ListNode(value);
        } else {
            ListNode currentHead = sentinelNode.next;
            ListNode newheadNode = new ListNode(value, currentHead);
            sentinelNode.next = newheadNode;
        }
        listSize += 1;
    }

    public int size(){
        return listSize;
    }


    /*
       Question 6.
    */
    public void compressNodes() {
        ListNode current = sentinelNode.next;
        while (current != null && current.next != null) { //continue if not null
            if (current.item == current.next.item) {
                int ans = current.item;
                ListNode value = current.next; //compress
                while (value != null && value.item == current.item) { //not null and equal to curr
                    ans += value.item;
                    value = value.next;
                    listSize--; //decrement count
                }
                current.item = ans;
                current.next = value;
            } else { //value and curr is different
                current = current.next;
            }
        }
    }

    /*
        Do not modify this method in any way
     */
    public boolean equals(Object o) {
        if(o == null){
            return false;
        } else if(!(o instanceof SList)) {
            return false;
        }

        ListNode currentRunner = this.sentinelNode.next;
        ListNode oRunner = ((SList) o).sentinelNode.next;
        while(currentRunner != null && oRunner != null) {
            if(currentRunner.item != oRunner.item) {
                return false;
            }
            currentRunner = currentRunner.next;
            oRunner = oRunner.next;
        }

        if(currentRunner == null && oRunner == null) {
            return true;
        }
        return false;
    }

    /*
        Do not modify this method in any way
     */
    public String toString(){
        StringBuilder stringRepr = new StringBuilder();
        ListNode runner = sentinelNode.next;
        while(runner != null){
            stringRepr.append(runner.item);
            if(runner.next != null){
                stringRepr.append(",");
            }
            runner = runner.next;
        }
        return stringRepr.toString();
    }
}

