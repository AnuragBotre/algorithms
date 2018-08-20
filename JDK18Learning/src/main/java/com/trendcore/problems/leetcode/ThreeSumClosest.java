package com.trendcore.problems.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/3sum-closest/description/
 * 16.
 * 3Sum Closest
 * DescriptionHintsSubmissionsDiscussSolution
 * Given an array nums of n integers and an integer target,
 * find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 * You may assume that each input would have exactly one solution.
 * <p>
 * Example:
 * <p>
 * Given array nums = [-1, 2, 1, -4], and target = 1.
 * <p>
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 */
public class ThreeSumClosest {

    public static void main(String[] args) {
        ThreeSumClosest t = new ThreeSumClosest();
        //printResult(new int[]{-1, 2, 1, -4}, 1);
        /*printResult(new int[]{1, 1, 1, 0}, -100);
        printResult(new int[]{-1000, -900, -1, -2, 1, 4, 5}, -100);
        printResult(new int[]{0, 0, 0}, 1);
        printResult(new int[]{1, 1, -1}, 0);

        printResult(new int[]{0, 2, 1, -3}, 1);

        printResult(new int[]{-100,-99,-98,-95}, -101);
        printResult(new int[]{-100,-99,-98,-95}, 101);*/

        /*BinarySearchTree b = t.new BinarySearchTree();
        b.insert(5);
        b.insert(-5);
        b.insert(10);
        b.insert(-10);
        b.insert(-4);
        b.insert(15);
        b.insert(6);

        System.out.println(b.removeClosestNode(2).val);
        System.out.println(b.removeClosestNode(1).val);
        System.out.println(b.removeClosestNode(0).val);
        System.out.println(b.removeClosestNode(-1).val);
        System.out.println(b.removeClosestNode(3).val);

        System.out.println(b.removeClosestNode(8).val);
        System.out.println(b.removeClosestNode(-2).val);
        System.out.println(b.removeClosestNode(3).val);
        System.out.println(b.removeClosestNode(-3).val);*/

        testForNodeRemoval(t);
    }

    private static void testForNodeRemoval(ThreeSumClosest t) {
        BinarySearchTree b = t.new BinarySearchTree();
        b.insert(100);
        b.insert(50);
        b.insert(25);
        b.insert(20);
        b.insert(30);
        b.insert(75);
        b.insert(70);
        b.insert(80);
        b.insert(150);
        b.insert(125);
        b.insert(120);
        b.insert(130);
        b.insert(175);
        b.insert(170);
        b.insert(180);

        //b.printTree();

        /*b.removeClosestNode(50);
        b.removeClosestNode(150);*/
        b.removeClosestNode(100);
        b.printTree();
        System.out.println("New Root :- " + b.root);
    }

    private static void printResult(int[] nums, int target) {
        ThreeSumClosest t = new ThreeSumClosest();
        System.out.println(t.threeSumClosest(nums, target) + " " + t.bruteForce(nums, target));
    }


    //Approach-3
    //traverse through mid of sorted array
    //2 pointer approach
    //from the centre one will go in left and other will go to right
    //need to decide how pointer should proceed

    //target  = first closet no
    //a = target - first closet no
    //b = first - a

    //Use division approach
    //target / 3
    //then search closest number
    //after substract target - found number
    //substracted no  / 2

    //first create binary Search tree
    class BinarySearchTree {

        public void printTree() {
            preOrderTraversal(root);
        }

        public void preOrderTraversal(Node root) {
            if (root != null) {
                preOrderTraversal(root.left);
                System.out.println(root.val);
                preOrderTraversal(root.right);
            }
        }

        class Node {
            int val;
            int noOfTimes;
            Node left;
            Node right;

            public Node(int val) {
                this.val = val;
            }

            @Override
            public String toString() {
                return "" + this.val;
            }
        }

        Node root;

        public BinarySearchTree() {

        }

        public void insert(int val) {
            Node node = new Node(val);
            if (root == null) {
                root = node;
            } else {
                insertNode(root, node);
            }
        }

        private void insertNode(Node root, Node node) {
            if (root == null) {
                return;
            }

            if (node.val == root.val) {
                root.noOfTimes++;
                return;
            } else if (node.val < root.val) {
                if (root.left != null) {
                    insertNode(root.left, node);
                } else {
                    root.left = node;
                }
            } else {
                if (root.right != null) {
                    insertNode(root.right, node);
                } else {
                    root.right = node;
                }
            }
        }

        public Node removeClosestNode(int val) {
            Node[] traverse = traverse(root, val, root);
            //we need to remove this node

            if (traverse[0].noOfTimes == 0) {
                Node nodeToBeRemoved = traverse[0];
                Node parentNode = traverse[1];

                Node leftTreeNode = nodeToBeRemoved.left;
                Node rightTreeNode = nodeToBeRemoved.right;

                //we will not handle root removal for now

                //need to check for root.
                if(root.val == parentNode.val){
                    //removing left
                    if(leftTreeNode != null){
                        Node rightChildOfLeftTreeNode = leftTreeNode.right;

                        //find node position
                        if (rightChildOfLeftTreeNode != null) {
                            Node rightTraversalRoot = rightTreeNode;
                            leftTreeNode.right = rightTraversalRoot;
                            insertNodeInBetween(rightTraversalRoot, rightChildOfLeftTreeNode);
                            root = leftTreeNode;
                        }
                    }else{
                        root = rightTreeNode;
                    }

                }else if (nodeToBeRemoved.val < parentNode.val) {
                    //left tree
                    //Need to reposition tree
                    //left tree node is going to be next node
                    if (leftTreeNode != null) {
                        parentNode.left = leftTreeNode;
                        //need to reposition tree
                        Node rightChildOfLeftTreeNode = leftTreeNode.right;


                        //find node position
                        if (rightChildOfLeftTreeNode != null) {
                            Node rightTraversalRoot = rightTreeNode;
                            leftTreeNode.right = rightTraversalRoot;
                            insertNodeInBetween(rightTraversalRoot, rightChildOfLeftTreeNode);
                        }


                    } else {
                        parentNode.left = rightTreeNode;
                    }

                } else {
                    //right tree
                    if (rightTreeNode != null) {
                        parentNode.right = rightTreeNode;

                        Node leftChildOfRightTreeNode = rightTreeNode.left;

                        //find node position
                        if (leftChildOfRightTreeNode != null) {
                            Node rightTraversalRoot = leftTreeNode;
                            rightTreeNode.left = rightTraversalRoot;
                            insertNodeInBetween(rightTraversalRoot, leftChildOfRightTreeNode);
                        }
                    } else {
                        parentNode.right = leftTreeNode;
                    }
                }

            } else {
                traverse[0].noOfTimes--;
            }

            return traverse[0];
        }

        private void insertNodeInBetween(Node root, Node nodeToBeAttached) {
            if (root == null) {
                return;
            }
            if (nodeToBeAttached.val < root.val) {
                if (root.left != null) {
                    insertNodeInBetween(root.left, nodeToBeAttached);
                } else {
                    root.left = nodeToBeAttached;
                }
            } else {
                if (root.right != null) {
                    insertNodeInBetween(root.right, nodeToBeAttached);
                } else {
                    root.right = nodeToBeAttached;
                }
            }
        }

        private Node[] traverse(Node node, int val, Node prevNode) {
            if (node == null) {
                return new Node[]{prevNode, null};
            }

            if (node.val == val) {
                return new Node[]{node, prevNode};
            } else if (val < node.val) {
                Node[] traverse = traverse(node.left, val, node);
                traverse[1] = prevNode;
                return traverse;
            } else {
                Node[] traverse = traverse(node.right, val, node);
                traverse[1] = prevNode;
                return traverse;
            }
        }
    }

    //Try with Binary Tree
    //keep track of positions for duplicates
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int a = target - (target / 3);

        Object t[] = getClosestNumber(nums, a);
        int pos1 = (int) t[1];
        int firstNo = (int) t[0];

        int c = (target - firstNo) / 2;

        int[] closestNumber = getClosestNumber(nums, c, new int[]{pos1, -1}, 1);
        int secondNo = closestNumber[0];
        int pos2 = closestNumber[1];

        int thirdNoToFind = target - (firstNo + secondNo);
        closestNumber = getClosestNumber(nums, thirdNoToFind, new int[]{pos1, pos2}, 2);

        int thirdNumber = closestNumber[0];

        return firstNo + secondNo + thirdNumber;
    }

    //Need to correct the binary search algo
    //Target binary search algo problem first then will solve this problem.
    private int[] getClosestNumber(int[] nums, int c, int[] position, int posLen) {
        int low = 0;
        int high = nums.length - posLen;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low >= high) {
                flag = false;
                break;
            }

            if (nums[mid] == c && notInPosition(mid, position)) {
                break;
            } else if (c < nums[mid]) {
                high = mid;
                mid = ((low + high) / 2) + 1;
            } else {
                low = mid;
                mid = ((low + high) / 2) + 1;
            }
        }

        if (mid == nums.length) {
            //find correct mid
            mid = nums.length - 1;

            if (mid == position[0] || mid == position[1]) {
                mid = nums.length - 2;
            }

            if (mid == position[0] || mid == position[1]) {
                mid = nums.length - 3;
            }

        }
        return new int[]{nums[mid], mid};
    }

    private boolean notInPosition(int mid, int[] position) {
        for (int i = 0; i < position.length; i++) {
            if (mid == position[i]) {
                return false;
            }
        }
        return true;
    }

    private Object[] getClosestNumber(int[] nums, int target) {
        int low = 0;
        int high = nums.length;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low >= high) {
                flag = false;
                break;
            }

            if (nums[mid] == target) {
                break;
            } else if (target < nums[mid]) {
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid;
                mid = (low + high) / 2;
            }
        }

        if (mid == nums.length) {
            mid = nums.length - 1;
        }
        return new Object[]{nums[mid], mid};
    }

    //Approach - 2 sort array
    //This approach has a flaw
    //It will not work for this input 0,2,1,-3 , target = 1
    private int approach2(int[] nums, int target) {
        if (nums.length < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int low = 0;
        int high = nums.length;
        int mid = (low + high) / 2;

        //need to find target fits in which region
        boolean flag = true;
        while (flag) {
            if (low == high) {
                flag = false;
                break;
            }

            if (nums[mid] == target) {
                break;
            } else if (target < nums[mid]) {
                low = 0;
                high = mid;
                mid = (low + high) / 2;
            } else {
                low = mid + 1;
                high = high;
                mid = (low + high) / 2;
            }
        }

        if (mid == nums.length) {
            mid = nums.length - 1;
        }

        //try to create window near mid
        if (mid - 1 < 0) {
            //at first
            return nums[mid] + nums[mid + 1] + nums[mid + 2];
        } else if (mid + 1 >= nums.length) {
            //at last
            return nums[mid] + nums[mid - 1] + nums[mid - 2];
        } else {
            //there is chance of sliding
            int r1 = nums[mid - 1] + nums[mid] + nums[mid + 1];
            //can we slide to right
            if (mid + 2 < nums.length) {
                int r2 = nums[mid] + nums[mid + 1] + nums[mid + 2];
                if (closer(r2, r1, target)) {
                    r1 = r2;
                }
            }

            //can we slide to left
            if (mid - 2 > 0) {
                int r3 = nums[mid - 1] + nums[mid - 2] + nums[mid];
                if (closer(r3, r1, target)) {
                    r1 = r3;
                }
            }

            return r1;
        }
    }

    //Approach - 1 brute force
    //Correct Algo
    private int bruteForce(int[] nums, int target) {
        Integer closest = null;

        Map map = new HashMap<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int result = nums[i] + nums[j] + nums[k];

                    if (closest == null) {
                        closest = result;
                    } else {
                        if (closer(result, closest, target)) {
                            closest = result;
                        }
                    }
                }
            }
        }

        return closest;
    }

    private boolean closer(int result, int closest, int target) {
        int a = target - result;
        int b = target - closest;
        return Math.abs(a) < Math.abs(b);
    }

}
