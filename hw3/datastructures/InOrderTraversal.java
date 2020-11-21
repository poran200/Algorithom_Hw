package hw3.datastructures;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ritwik Banerjee
 */
public class InOrderTraversal<E> extends Traversal<E> {
    List<E> list = new LinkedList<>();
    @Override
    public List<E> of(BinaryTree<E> tree) {
        inorder(tree.root());
        return list;
    }
    public void inorder(BinaryTreeNode<E> treeNode){
        if (treeNode != null){
            inorder(treeNode.left());
            this.list.add(treeNode.element());
            inorder(treeNode.right());
        }
    }
}
