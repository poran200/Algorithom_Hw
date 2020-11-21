package hw3.datastructures;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Ritwik Banerjee
 */
public class BinaryTreeNode<E> {
    
    private E element;
    
    private BinaryTreeNode<E> left   = null;
    private BinaryTreeNode<E> right  = null;
    private BinaryTreeNode<E> parent = null;
    
    public BinaryTreeNode(E element) {
        this.element = element;
    }
    
    public E element() { return element; }
    
    public BinaryTreeNode<E> left() { return left; }
    
    public BinaryTreeNode<E> right() { return right; }
    
    public BinaryTreeNode<E> parent() { return parent; }
    
    public void setElement(E element) { this.element = element; }
    
    public void setLeft(BinaryTreeNode<E> node) { this.left = node; }
    
    public void setRight(BinaryTreeNode<E> node) { this.right = node; }
    
    public void setParent(BinaryTreeNode<E> node) { this.parent = node; }

    /**
      *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
     return isEqual(this, (BinaryTreeNode<E>) o);
    }
    boolean isEqual(BinaryTreeNode<E> treeNode ,BinaryTreeNode<E> treeNode1){
        if (treeNode == null && treeNode1 == null){
            return true;
        }
        if (treeNode != null && treeNode1 != null)
            return (treeNode.element.equals(treeNode1.element)
                    && isEqual(treeNode.left,treeNode1.left)
                    && isEqual(treeNode.right,treeNode1.right)
            );
        return false;
    }


        /**
     * !!DO NOT MODIFY THIS CODE!!
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = element.hashCode();
        result = 31 * result + (left != null ? left.hashCode() : 0);
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }

    /** !!DO NOT MODIFY THIS CODE!! */
    private void print(String prefix, boolean isTail) {
        System.out.println(prefix + (isTail ? "'-- " : "|-- ") + element.toString());
        List<BinaryTreeNode<E>> children = Arrays.asList(left, right);
        for (int i = 0; i < children.size() - 1; i++) {
            if (children.get(i) != null)
                children.get(i).print(prefix + (isTail ? "    " : "|   "), false);
        }
        if (children.size() > 0 && children.get(children.size() - 1) != null) {
            children.get(children.size() - 1).print(prefix + (isTail ? "    " : "|    "), true);
        }
    }
    
    /** !!DO NOT MODIFY THIS CODE!! */
    public void print() {
        print("", true);
    }
}
