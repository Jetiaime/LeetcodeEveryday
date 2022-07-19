class MyCalendarTwo {

    private ODT odt;

    public MyCalendarTwo() {
        odt = new ODT();
    }

    public boolean book(int start, int end) {
        return odt.add(start, end - 1, 1);
    }

    private class ODT {

        private Node _odt;

        private ODT() {
            _odt = new Node(0, (int) 1e9 + 1, 0);
        }

        private class Node {
            int l, r, val;
            Node nxt;

            private Node(int l, int r, int val) {
                this(l, r, val, null);
            }

            private Node(int l, int r, int val, Node nxt) {
                this.l = l;
                this.r = r;
                this.val = val;
                this.nxt = nxt;
            }
        }

        public Node split(int x) {
            Node p;
            for (p = _odt; p != null; p = p.nxt) {
                if (p.l == x + 1) {
                    return p;
                }
                if (p.l <= x && x + 1 <= p.r) {
                    p.nxt = new Node(x + 1, p.r, p.val, p.nxt);
                    p.r = x;
                    return p.nxt;
                }
            }
            return p;
        }

        public boolean add(int l, int r, int v) {
            for (Node itl = split(l - 1), itr = split(r); itl != itr; itl = itl.nxt) {
                if (itl.val == 2) {
                    return false;
                }
            }
            for (Node itl = split(l - 1), itr = split(r); itl != itr; itl = itl.nxt) {
                itl.val += v;
            }
            return true;
        }
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */