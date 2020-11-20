package hw3.datastructures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Ritwik Banerjee
 */
public class BinarySearchTree<T extends Comparable<T>> implements BinaryTree<T> {
    
    private BinaryTreeNode<T> root;
    private int               size;
    List<BinaryTreeNode<T>> scarcePath = new ArrayList<>();
    /**
     * !!DO NOT MODIFY THIS CODE!!
     * Consrtucts a binary search tree consisting of items from the given collection. Duplicates in the collection are
     * ignored, i.e., every item will be considered only once for the tree being constructed.
     *
     * @param collection the given collection
     */
    public BinarySearchTree(Collection<T> collection) {
        this();
        for (T t : collection)
            add(t);
    }
    
    /**
     * !!DO NOT MODIFY THIS CODE!!
     * Constructs an empty binary search tree.
     */
    private BinarySearchTree() {
        root = null;
        size = 0;
    }
    
    @Override
    public void add(T t) {
        if (root == null){
            root = new BinaryTreeNode<>(t);
            ++size;
            return;
        }
        recursiveAdd(root,t);
        // Done
    }
    private void  recursiveAdd(BinaryTreeNode<T> current, T value){
        int comparisonResult = current.element().compareTo(value);
        if (comparisonResult < 0){
            if (current.right() == null){
                current.setRight(new BinaryTreeNode<>(value));
                ++size;
                return;
            }
            recursiveAdd(current.right(),value);
        }
        else if (comparisonResult > 0){
            if (current.left() == null){
                current.setLeft(new BinaryTreeNode<>(value));
                ++size;
            }
            recursiveAdd(current.left(),value);
        }
    }
    @Override
    public void remove(T t) {
         removeRecursive(root,null,t);
         //Done
    }
    private boolean removeRecursive(BinaryTreeNode<T> current, BinaryTreeNode<T> parent , T value){
        if (current == null){
            return false;
        }
        var compareResult = value.compareTo(current.element());
        if (compareResult < 0){
            return removeRecursive(current.left(),current,value);
        }
        if (compareResult > 0){
            return removeRecursive(current.right(),current,value);
        }
        int childCount = 0;
        childCount += (current.left() == null) ? 0: 1;
        childCount += (current.right() == null) ? 0: 1;
        if (childCount == 0){
           if (current == root){
               root = null;
               --size;
               return true;
           }
           if (parent.left() == current){
               parent.setLeft(null);
           }else {
               parent.setRight(null);
           }
           --size;
           return true;
        }else if (childCount == 1){
           if (current == root){
               if (root.left() != null){
                   root = root.left();
               }
               else {
                   root = root.right();
               }
               --size;
               return true;
           }
           BinaryTreeNode<T> child = (current.left() != null) ? current.left() : current.right();
           if (parent.left() == current){
               parent.setLeft(child);
           }else {
               parent.setRight(child);
           }
           --size;
           return true;
        }
        BinaryTreeNode<T> successor =  getLeftMostChild(current.right());
        current.setElement(successor.element());
        BinaryTreeNode<T> successorParent = current.right();
        while (successorParent.left() != null && successorParent.left() != successor){
            successorParent = successorParent.left();
        }
        if (successorParent == successor){
            current.setRight(successor.right());
        }else {
            successorParent.setLeft(successor.right());
        }
        --size;
        return  true;
    }
    private BinaryTreeNode<T> getLeftMostChild(BinaryTreeNode<T> current){
        while (current.left() != null){
            current = current.left();
        }
        return current;
    }

    /**
     * Returns the search path that starts at the root node of the tree, and ends at the node containing the specified
     * item. If such a node exists in the tree, it is the last object in the returned list. Otherwise, this method will
     * still return the path corresponding to the search for this item, but append a <code>null</code> element at the
     * end of the list.
     *
     * @param t the specified item
     * @return the search path, with a node containing the specified item as the last object in the list if the item is
     * found in the tree, and the <code>null</code> node if item is not found in the tree.
     */
    @Override
    public List<BinaryTreeNode<T>> find(T t) {
        findElement(t);
        return scarcePath;
        // TODO: implement the search/find algorithm for binary search trees
    }
    public BinaryTreeNode<T> findElement(T t){
       return findRecursive(root,t);
    }
    public BinaryTreeNode<T> findRecursive(BinaryTreeNode<T> n ,T t){
       if (n.element().equals(t)){
           this.scarcePath.add(n);
          return n;

       }
       if (t.compareTo(n.element()) < 0){
           var treeNode = findRecursive(n.left(), t);
           this.scarcePath.add(treeNode);
           return treeNode;
       }else {
        var treeNode =   findRecursive(n.right(),t);
         this.scarcePath.add(treeNode);
        return treeNode;
       }
    }
    /**
     * !!DO NOT MODIFY THIS CODE!!
     */
    @Override
    public void print() {
        root.print();
    }
    
    /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return the root node of this tree
     */
    @Override
    public BinaryTreeNode<T> root() {
        return root;
    }
    
    /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return the size, i.e., the number of nodes in this tree
     */
    @Override
    public int size() {
        return size;
    }
}