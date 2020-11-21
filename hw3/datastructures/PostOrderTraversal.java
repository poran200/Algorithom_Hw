package hw3.datastructures;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Ritwik Banerjee
 */
public class PostOrderTraversal<E> extends Traversal<E> {
        List<E> list = new LinkedList<>();
    @Override
    public List<E> of(BinaryTree<E> tree) {
        postOrder(tree.root());
        return list;
        // done
    }
    private void postOrder(BinaryTreeNode<E> treeNode){
        if (treeNode != null){
            postOrder(treeNode.left());
            postOrder(treeNode.right());
            this.list.add(treeNode.element());
        }
    }
}
