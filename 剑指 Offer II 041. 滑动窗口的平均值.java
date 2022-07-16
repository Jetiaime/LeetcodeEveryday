class MovingAverage {

    private int size = 0;
    private Queue<Integer> _q;
    private double _val = 0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        _q = new LinkedList<>();
        _val = 0;
    }

    public double next(int val) {
        _q.add(val);
        _val += val;
        if (_q.size() > size) {
            _val -= _q.remove();
        }
        return _val / _q.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */