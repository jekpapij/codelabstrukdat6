import java.util.*;

public class LogistikGraph {
    private int vertices;
    private LinkedList<Integer> adj[];
    private int[][] adjacencyMatrix;

    // Konstruktor untuk membuat graph dengan 5 gudang
    LogistikGraph(int v) {
        vertices = v;
        adj = new LinkedList[v];
        adjacencyMatrix = new int[v][v];

        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    // Menambahkan jalur pengiriman (edge)
    void addEdge(int src, int dest) {
        adj[src].add(dest);
        adjacencyMatrix[src][dest] = 1;
    }

    // Menampilkan adjacency matrix
    void displayAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        System.out.print("   ");
        for (int i = 0; i < vertices; i++) {
            System.out.print("G" + i + " ");
        }
        System.out.println();

        for (int i = 0; i < vertices; i++) {
            System.out.print("G" + i + " ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(" " + adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Implementasi BFS
    void BFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[startVertex] = true;
        queue.add(startVertex);

        System.out.print("BFS traversal dari Gudang " + startVertex + ": ");

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            System.out.print("G" + vertex + " ");

            for (int neighbor : adj[vertex]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    // Implementasi DFS
    void DFS(int startVertex) {
        boolean[] visited = new boolean[vertices];
        System.out.print("DFS traversal dari Gudang " + startVertex + ": ");
        DFSUtil(startVertex, visited);
        System.out.println();
    }

    // Helper method untuk DFS rekursif
    void DFSUtil(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print("G" + vertex + " ");

        for (int neighbor : adj[vertex]) {
            if (!visited[neighbor]) {
                DFSUtil(neighbor, visited);
            }
        }
    }

    public static void main(String[] args) {
        // Membuat graph dengan 5 gudang (G0, G1, G2, G3, G4)
        LogistikGraph graph = new LogistikGraph(5);

        // Menambahkan 7 jalur pengiriman
        graph.addEdge(0, 1); // G0 -> G1
        graph.addEdge(0, 2); // G0 -> G2
        graph.addEdge(1, 3); // G1 -> G3
        graph.addEdge(2, 3); // G2 -> G3
        graph.addEdge(2, 4); // G2 -> G4
        graph.addEdge(3, 4); // G3 -> G4
        graph.addEdge(4, 0); // G4 -> G0

        System.out.println("=== SISTEM LOGISTIK PERUSAHAAN ===");
        System.out.println("Gudang: G0, G1, G2, G3, G4");
        System.out.println("Total jalur pengiriman: 7");
        System.out.println();

        // Menampilkan adjacency matrix
        graph.displayAdjacencyMatrix();
        System.out.println();

        // Menjalankan BFS dari gudang A (G0)
        graph.BFS(0);

        // Menjalankan DFS dari gudang A (G0)
        graph.DFS(0);

        System.out.println();
        System.out.println("=== ANALISIS JALUR ===");
        System.out.println("BFS: Mencari jalur terpendek secara level-by-level");
        System.out.println("DFS: Mengeksplorasi jalur sedalam mungkin sebelum backtrack");
    }
}
