package utils;

import trees.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class ToWebGraphViz<T> {
    private StringBuilder dot;
    private BinaryTreeNode<T> root;

    public ToWebGraphViz(BinaryTreeNode<T> root) {
        this.root = root;
        dot = new StringBuilder();
        dot.append("digraph BinarySearchTree {\n");
    }

    public String toDotString() {
        List<String> lines = new ArrayList<>();
        generateDot(root, lines);

        for (String line : lines) {
            dot.append("  ").append(line).append("\n");
        }

        dot.append("}");

        return dot.toString();
    }

    private void generateDot(BinaryTreeNode<T> node, List<String> lines) {
        if (node != null) {
            if (node.getLeft() != null) {
                lines.add(node.getData() + " -> " + node.getLeft().getData().toString() + ";") ;
                generateDot(node.getLeft(), lines);
            }
            if (node.getRight() != null) {
                lines.add(node.getData() + " -> " + node.getRight().getData().toString() + ";");
                generateDot(node.getRight(), lines);
            }
        }
    }
}
