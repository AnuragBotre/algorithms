package com.trendcore.problems;

/**
 * Created by Anurag
 */
public class HeightOfBinaryTree {

    public static class Tree extends ConvertTreeToAnotherTree.Tree{

        public int height;

        public void calculateHeight() {
            traverse(root,0);
            System.out.println(height);
        }

        private void traverse(ConvertTreeToAnotherTree.Node root, int i) {
            if(root == null){
                if(height < i){
                    height = i;
                }
                return;
            }

            traverse(root.left,i+1);
            traverse(root.right,i+1);
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();

        tree.addNode(new ConvertTreeToAnotherTree.Node(50));
        tree.addNode(new ConvertTreeToAnotherTree.Node(40));
        tree.addNode(new ConvertTreeToAnotherTree.Node(45));
        tree.addNode(new ConvertTreeToAnotherTree.Node(20));
        //tree.addNode(new ConvertTreeToAnotherTree.Node(30));
        /*tree.addNode(new ConvertTreeToAnotherTree.Node(70));
        tree.addNode(new ConvertTreeToAnotherTree.Node(60));
        tree.addNode(new ConvertTreeToAnotherTree.Node(80));*/

        tree.calculateHeight();
    }

}
