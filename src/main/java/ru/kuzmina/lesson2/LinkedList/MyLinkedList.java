package ru.kuzmina.lesson2.LinkedList;

public class MyLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public MyLinkedList() {
    }

    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(value, null, l);
        last = newNode;
        if (first == null) {
            first = newNode;
        }
        if (l != null) {
            l.setNext(newNode);
        }
        size++;
    }

    public void add(E value, int index) {
        Node<E> currentNode = get(index);
        if (currentNode != null) {
            Node<E> newNode = new Node<>(value, currentNode, currentNode.getPrev());
            currentNode.setPrev(newNode);
            newNode.getPrev().setNext(newNode);
            size++;
        }
    }

    public E delete(E value) {
        return null;
    }

    public E delete(int index) {
        Node<E> node = get(index);
        return delete(node);
    }

    public E delete(Node<E> node) {
        if (node != null) {
            Node<E> prev = node.getPrev();
            Node<E> next = node.getNext();
            E val = node.getValue();

            if (prev == null) {
                first = next;
            } else {
                prev.setNext(next);
                node.setPrev(null);
            }

            if (next == null) {
                last = prev;
            } else {
                next.setPrev(prev);
                node.setNext(null);
            }
            node.setValue(null);
            size--;
            return val;
        }
        return null;
    }

    public E deleteFirst() {
        return delete(first);
    }

    public Node<E> get(int index) {
        Node<E> node = first;
        if (index < size) {
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return node;
                }
                node = node.getNext();
            }
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    private String buildValuesString() {
        StringBuilder str = new StringBuilder();
        for (Node<E> cur = first; cur != null;) {
            str.append(cur.getValue().toString()).append(",");
            cur = cur.getNext();
        }
        if (str.length() > 0){
            str.deleteCharAt(str.length() - 1);
        }
        return str.toString();
    }

    @Override
    public String toString() {
        return "MyLinkedList{ {" +
                buildValuesString() + "} " +
                "first=" + first.getValue() +
                ", last=" + last.getValue() +
                ", size=" + size +
                '}';
    }
}