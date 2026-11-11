import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MiListaCircular lista = new MiListaCircular();

        System.out.println("¿Vacía? " + lista.isEmpty());

        lista.add(1000);
        lista.add(10);
        lista.insertHead(50);
        lista.insertTail(2000);
        System.out.println("Lista " + lista + "  tamaño" + lista.getSize());
        System.out.println("Cabeza" + lista.getHead() + "  Cola" + lista.getTail());

        // Prueba de que es circular: el siguiente del último es el primero
        Node ultimo = lista.search(2000);
        System.out.println("Siguiente del último (2000) = " + ultimo.siguiente.dato);

        Node n1000 = lista.search(1000);
        System.out.println("get(nodo 1000) = " + lista.get(n1000));
        System.out.println("¿Contiene 10? " + lista.contains(10));
        System.out.println("¿Contiene 99? " + lista.contains(99));

        lista.insert(n1000, 35);
        lista.insert((Object) 2000, 25);
        System.out.println("Tras insertar 35 y 25: " + lista);

        lista.set(lista.search(35), 36);
        System.out.println("Tras set 35 - 36: " + lista);

        lista.remove(lista.search(50));   // cabeza
        System.out.println("Tras remover 50 (cabeza): " + lista
                + "  último - siguiente " + lista.search(25).siguiente.dato);
        lista.remove(lista.search(25));   // cola
        System.out.println("Tras remover 25 (cola):" + lista
                + "  último-siguiente " + lista.search(2000).siguiente.dato);

        System.out.println("toArray: " + Arrays.toString(lista.toArray()));
        Integer[] destino = new Integer[0];
        destino = (Integer[]) lista.toArray(destino);
        System.out.println("toArrayInteger: " + Arrays.toString(destino));

        // subList normal y subList que "da la vuelta"
        System.out.println("subList(1000-10): "
                + lista.subList(lista.search(1000), lista.search(10)));
        System.out.println("subList(10-1000) (da la vuelta): "
                + lista.subList(lista.search(10), lista.search(1000)));

        System.out.println("Ordenada" + lista.sortList());
        System.out.println("sigue igual: " + lista);

        lista.clear();
        System.out.println("Tras clear esta vacía? " + lista.isEmpty() + "  tamaño" + lista.getSize());
    }
}
