import java.lang.reflect.Array;
import java.util.Arrays;



public class MiListaCircular implements ListInterface {

    private Node head;
    private Node tail;
    private int size;


    public MiListaCircular(){
        head = null;
        tail = null;
        size = 0;

    }    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;

    }

    @Override
    public Object getHead() {
        return head == null ? null : head.dato;
    }

    @Override
    public Object getTail() {
        return tail == null ? null : tail.dato;
    }

    public Object get(int node) {
        return node == null ? null : node.dato;
    }

    @Override
    public Node search(Object object) {
        Node actual = head;
        for (int i = 0; i < size; i++) {
            if (iguales(actual.dato, object)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;

    }

    @Override
    public boolean add(Object object) {
        return insertTail(object);
    }

    @Override
    public boolean insert(Node node, Object object) {
        if (node == null) {
            return false;
        }
        if (node == tail) {
            return insertTail(object);
        }
        Node nuevo = new Node(object);
        nuevo.siguiente = node.siguiente;
        node.siguiente = nuevo;
        size++;
        return true;

    }

    @Override
    public boolean insert(Object objectRef, Object object) {
        return insert(search(objectRef), object);
    }

    @Override
    public boolean insertHead(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            nuevo.siguiente = nuevo; // se apunta a sí mismo
            head = nuevo;
            tail = nuevo;
        } else {
            nuevo.siguiente = head;
            head = nuevo;
            tail.siguiente = head; // cerrar el círculo
        }
        size++;
        return true;

    }

    @Override
    public boolean insertTail(Object object) {
        Node nuevo = new Node(object);
        if (isEmpty()) {
            nuevo.siguiente = nuevo;
            head = nuevo;
            tail = nuevo;
        } else {
            nuevo.siguiente = head; // el nuevo último apunta al primero
            tail.siguiente = nuevo;
            tail = nuevo;
        }
        size++;
        return true;

    }

    @Override
    public boolean set(Node node, Object object) {
        if (node == null) {
            return false;
        }
        node.dato = object;
        return true;

    }

    @Override
    public boolean remove(Node node) {
        if (node == null || isEmpty()) {
            return false;
        }

        // Caso: un solo nodo
        if (size == 1) {
            if (node != head) {
                return false;
            }
            clear();
            return true;
        }

        // Buscar el nodo y su anterior (el anterior de head es tail)
        Node previo = tail;
        Node actual = head;
        for (int i = 0; i < size; i++) {
            if (actual == node) {
                break;
            }
            previo = actual;
            actual = actual.siguiente;
        }
        if (actual != node) {
            return false; // ese nodo no está en esta lista
        }

        previo.siguiente = node.siguiente; // me salto el nodo
        if (node == head) {
            head = node.siguiente;
        }
        if (node == tail) {
            tail = previo;
        }
        node.siguiente = null;
        size--;
        return true;

        }

    @Override
    public boolean contains(Object object) {
        return search(object) != null;
    }

    @Override
    public Object[] toArray() {
        Object[] arreglo = new Object[size];
        Node actual = head;
        for (int i = 0; i < size; i++) {
            arreglo[i] = actual.dato;
            actual = actual.siguiente;
        }
        return arreglo;

    }

    @Override
    public Object[] toArray(Object[] object) {
        if (object.length < size) {
            object = (Object[]) Array.newInstance(object.getClass().getComponentType(), size);
        }
        Node actual = head;
        for (int i = 0; i < size; i++) {
            object[i] = actual.dato;
            actual = actual.siguiente;
        }
        if (object.length > size) {
            object[size] = null;
        }
        return object;

    }

    @Override
    public MiListaCircular subList(Node from, Node to) {
        if (from == null || to == null || isEmpty()) {
            return null;
        }
        MiListaCircular sub = new MiListaCircular();
        Node actual = from;
        for (int i = 0; i < size; i++) {
            sub.add(actual.dato);
            if (actual == to) {
                return sub;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public MiListaCircular sortList() {
        Object[] arreglo = toArray();
        Arrays.sort(arreglo);
        MiListaCircular ordenada = new MiListaCircular();
        for (Object o : arreglo) {
            ordenada.add(o);
        }
        return ordenada;

    } private boolean iguales(Object a, Object b) {
        return a == null ? b == null : a.equals(b);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node actual = head;
        for (int i = 0; i < size; i++) {
            sb.append(actual.dato);
            if (i < size - 1) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        return sb.append("]").toString();
    }

}
