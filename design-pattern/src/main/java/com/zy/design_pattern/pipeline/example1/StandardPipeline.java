package com.zy.design_pattern.pipeline.example1;

public class StandardPipeline implements Pipeline {
    protected Valve head;
    protected Valve tail;

    public Valve getHead() {
        return head;
    }

    public Valve getTail() {
        return tail;
    }

    public void setTail(Valve v) {
        tail = v;
    }

    /**
     * 往链表依次添加节点
     */
    public void addValve(Valve v) {
        if (head == null) {
            // 如果链表为空则当前节点为头结点
            head = v;
            v.setNext(tail);
        } else {
            // 将当前节点添加依次添加到队列
            Valve current = head;
            while (current != null) {
                // 当前节点放入队列尾部，但是需要在tail尾结点前面
                if (current.getNext() == tail) {
                    current.setNext(v);
                    v.setNext(tail);
                    break;
                }
                current = current.getNext();
            }
        }
    }
}