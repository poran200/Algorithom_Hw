package hw3.datastructures;

import hw3.products.Laptop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ritwik Banerjee
 */
public class PreOrderTraversal<E> extends Traversal<E> {
    List<E> laptops = new ArrayList<>();
    @Override
    public List<E> of(BinaryTree<E> tree) {
        preorder(tree.root());
        return laptops;
    }
    private void preorder(BinaryTreeNode<E> treeNode){
        if (treeNode != null){
           this.laptops.add(treeNode.element());
           preorder(treeNode.left());
           preorder(treeNode.right());
        }
    }
}
