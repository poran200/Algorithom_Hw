package hw3.datastructures;

/**
 * This class implements the {@link Set} interface. It offers constant time performance (on average) for the basic
 * operations <code>add</code>, <code>remove</code>, <code>containt</code>, and <code>size</code>, under the simple
 * uniform hashing assumption (i.e., the hash function distributes elements uniformly across the slots in the backing
 * table).
 * There are two constructors given to you. You can modify them, or add new constructors. However, the signature of
 * these two constructors must not be changed. That is, the user must be able to create an instance of this class by
 * invoking <code>new ChainedHashSet()</code> and <code>new ChainedHashSet(int k)</code>.
 *
 * @param <E> the type of elements stored in this set
 */
public class ChainedHashSet<E> implements Set<E> {

    /**
     * Once an instance is created, this table size cannot change
     */
    private static  final  double  MAX_LOAD_FACTOR = 0.75;
    private final int tableSize;
    private int size;
    private HashEntry<E>[] elementData;

    // DO NOT MODIFY THIS METHOD
    public final int tablesize() { return this.tableSize; }

    // DO NOT MODIFY THIS METHOD
    public final double loadfactor() {
        return size() / (double) tableSize; }

    public ChainedHashSet(){
        this.tableSize = 10;
        elementData = new HashEntry[tableSize];
        size = 0;
    }

    public ChainedHashSet(int tableSize) {
        this.tableSize = tableSize;
        elementData = new HashEntry[tableSize];
        size = 0;
    }

    @Override public int size() {
        return size;
        // todo
        //done
    }

    @Override public boolean isEmpty() {

        return this.size() == 0;
        // todo
        //done
    }

    @Override public boolean contains(E element) {
        int bucket = hashFunction(element);
        HashEntry<E> current = elementData[bucket];
        while (current != null) {
            if (current.data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override public boolean add(E e) {
        if (!contains(e)) {
            if (loadfactor() >= MAX_LOAD_FACTOR) {
                rehash();
            }

            // insert new value at front of list
            int bucket = hashFunction(e);
            elementData[bucket] = new HashEntry<E>(e, elementData[bucket]);
            size++;
        }
        return false;
        //done
    }

    private void rehash() {
        // replace element data array with a larger empty version
        HashEntry<E>[] oldElementData = elementData;
        elementData = new HashEntry[2 * oldElementData.length];
        size = 0;

        // re-add all of the old data into the new array
        for (int i = 0; i < oldElementData.length; i++) {
            HashEntry<E> current = oldElementData[i];
            while (current != null) {
                add(current.data);
                current = current.next;
            }
        }
    }

    @Override public boolean remove(E e) {
        int bucket = hashFunction(e);
        if (elementData[bucket] != null) {
            // check front of list
            if (elementData[bucket].data.equals(e)) {
                elementData[bucket] = elementData[bucket].next;
                size--;
            } else {
                // check rest of list
                HashEntry<E> current = elementData[bucket];
                while (current.next != null && !current.next.data.equals(e)) {
                    current = current.next;
                }

                // if the element is found, remove it
                if (current.next != null && current.next.data.equals(e)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }
            }
        }
        return false;

    }

    /**
     * This method returns a string showing the entire hash table structure of this set. The format must be as follows:
     * Suppose a table has four slots, with three elements 'a', 'b', 'c', hashed to the first slot and 'z' hashed to the
     * third slot. Printing out the returned string should show the following:
     *
     * 1 || a -> b -> c
     * 2 ||
     * 3 || z
     *
     * Note that the elements 'a', 'b', 'c', and 'z' must also be human-readable.
     *
     * @return a string representation of the entire set, showing the underlying hash table structure
     */
    @Override
    public String toString() {
        String result = "[";
        boolean first = true;
        if (!isEmpty()) {
            for (int i = 0; i < elementData.length; i++) {
                HashEntry<E> current = elementData[i];
                while (current != null) {
                    if (!first) {
                        result += ", ";
                    }
                    result += current.data;
                    first = false;
                    current = current.next;
                }
            }
        }
        return result + "]";
    }
    // Represents a single value in a chain stored in one hash bucket.
    private static class HashEntry<E> {
        public E data;
        public HashEntry<E> next;

        public HashEntry(E data) {
            this(data, null);
        }

        public HashEntry(E data, HashEntry<E> next) {
            this.data = data;
            this.next = next;
        }
    }
    private int hashFunction(E value) {
        return Math.abs(value.hashCode()) % elementData.length;
    }
}
