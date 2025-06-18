/** Array based list.
 *  @author Josh Hug
 */

public class AList <T> {
    /** Creates an empty list. */
    int size;
    T [] items;
    public AList() {
        items = (T[])new Object[100];
        size = 0;
    }

    /** Inserts X into the back of the list. */
    public void addLast(T x) {
        if(size == items.length){
            T[] temp =(T[]) new Object[items.length*10];
            System.arraycopy(items,0,temp,0,size);
            items = temp;
        }
        items[size++] = x;
    }

    /** Returns the item from the back of the list. */
    public T getLast() {
        return items[size-1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public T get(int i) {
        return items[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public T removeLast() {
        T temp = items[size-1];
        items[size-1] = null;
        size--;
        return temp;
    }
} 