import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ServicioBFS {

    private Grafo<Integer> grafo;
    private Queue<Integer> fila;
    private HashMap<Integer, Boolean> visitados;

    public ServicioBFS(Grafo<Integer> grafo) {
        this.grafo = grafo;
        this.fila = new LinkedList<>();
        this.visitados = new HashMap<>();
    }

    public List<Integer> bfsForest() {
        this.fila.clear(); // Limpiar la cola antes de iniciar
        Iterator<Integer> it1 = grafo.obtenerVertices(); // Obtener iterador de vértices
        while(it1.hasNext()) { // Recorrer vértices
            int v = it1.next(); // Obtener vértice actual
            visitados.put(v, false); // Marcar como no visitado
        }

       List<Integer> ordenVisitados = new ArrayList<>(); // Lista para orden de visita
        Iterator<Integer> it2 = grafo.obtenerVertices(); // Nuevo iterador de vértices
        while(it2.hasNext()) { // Recorrer vértices
            int v = it2.next(); // Obtener vértice actual
            if(!visitados.get(v)) { // Si no visitado
                bfsForest(v, ordenVisitados); // Explorar desde este vértice
            }
        }

        return ordenVisitados; // Devolver orden de visita
    }

    private void bfsForest(int v, List<Integer> ordenVisitados) {
        visitados.put(v, true); // Marcar como visitado
        this.fila.add(v); // Agregar a la cola
        ordenVisitados.add(v); // Agregar al orden de visita

        while (!this.fila.isEmpty()) { // Mientras la cola no esté vacía
            int w = this.fila.poll(); // Extraer primer vértice de la cola
            Iterator<Integer> it3 = grafo.obtenerAdyacentes(w); // Obtener adyacentes
            if(it3!=null) { // Si hay adyacentes
                while (it3.hasNext()) { // Recorrer adyacentes
                    int ady = it3.next(); // Obtener adyacente actual
                    if(!visitados.get(ady)) { // Si no visitado
                        visitados.put(ady, true); // Marcar como visitado
                        this.fila.add(ady); // Agregar a la cola
                        ordenVisitados.add(ady); // Agregar al orden de visita
                    }
                }
            }
        }
    }

}
