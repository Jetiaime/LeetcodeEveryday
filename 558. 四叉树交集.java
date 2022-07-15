/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1.isLeaf && quadTree2.isLeaf) {
            return new Node(quadTree1.val || quadTree2.val, true, null, null, null, null);
        } else if (quadTree1.isLeaf && quadTree1.val || quadTree2.isLeaf && quadTree2.val) {
            return new Node(true, true, null, null, null, null);
        } else if (quadTree1.isLeaf && !quadTree1.val) {
            return quadTree2;
        } else if (quadTree2.isLeaf && !quadTree2.val) {
            return quadTree1;
        } else {
            Node res = new Node(false, false, 
            intersect(quadTree1.topLeft, quadTree2.topLeft),
            intersect(quadTree1.topRight, quadTree2.topRight),
            intersect(quadTree1.bottomLeft, quadTree2.bottomLeft),
            intersect(quadTree1.bottomRight, quadTree2.bottomRight));
            if (res.topLeft.isLeaf && res.topLeft.val &&
            res.topRight.isLeaf && res.topRight.val &&
            res.bottomLeft.isLeaf && res.bottomLeft.val &&
            res.bottomRight.isLeaf && res.bottomRight.val) {
                res = new Node(true, true, null, null, null, null);
            }
            return res;
        }
    }
}