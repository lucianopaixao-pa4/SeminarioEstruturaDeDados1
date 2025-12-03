public class Node {
    public int valor;
    public Node esquerda, direita;
}

class ArvoreSpay{
    static Node novoValor(int valor){
        Node node = new Node();
        node.valor = valor;
        node.direita = null;
        node.esquerda = null;
        return node;
    }

    static Node irDireita(Node x) {
        Node y = x.esquerda;    // Passo 1: Guarda a subárvore esquerda de x
        x.esquerda = y.direita; // Passo 2: Move a subárvore direita de y para esquerda de x
        y.direita = x;          // Passo 3: Faz x ser filho direito de y
        return y;               // Passo 4: Retorna a nova raiz da subárvore
    }

    static Node irEsquerda(Node x) {
        Node y = x.direita;    // Guarda a subárvore direita de x
        x.direita = y.esquerda; // Move a subárvore esquerda de y para direita de x
        y.esquerda = x;         // Faz x ser filho esquerdo de y
        return y;               // Retorna a nova raiz
    }

    static Node splay(Node raiz, int valor){
        if (raiz == null || raiz.valor == valor)
            return raiz;

        if (raiz.valor > valor){
            if (raiz.esquerda == null)
                return raiz;
            if (raiz.esquerda.valor > valor){
                raiz.esquerda.esquerda = splay (raiz.esquerda.esquerda, valor);
                raiz = irDireita(raiz);
            } else if (raiz.esquerda.valor < valor){
                raiz.esquerda.direita = splay(raiz.esquerda.direita, valor);
                if (raiz.esquerda.direita != null)
                    raiz.esquerda = irEsquerda(raiz.esquerda);
            }
            return (raiz.esquerda == null) ? raiz : irDireita(raiz);
        }
        else {
            if (raiz.direita == null)
                return raiz;
            if (raiz.direita.valor > valor){
                raiz.direita.esquerda = splay(raiz.direita.esquerda, valor);
                if (raiz.direita.esquerda != null)
                    raiz.direita = irDireita(raiz.direita);
            } else if (raiz.direita.valor < valor) {
                raiz.direita.direita = splay(raiz.direita.direita, valor);
                raiz = irEsquerda(raiz);
            }
            return (raiz.direita == null) ? raiz : irDireita(raiz);
        }
    }
    static Node inserir(Node raiz, int valor){
        if (raiz == null)
            return novoValor(valor);

        raiz = splay(raiz, valor);

        if (raiz.valor == valor)
            return raiz;

        Node node = novoValor(valor);
        if (raiz.valor > valor) {
            node.direita = raiz;
            node.esquerda = raiz.esquerda;
            raiz.esquerda = null;
        }
        else {
            node.esquerda = raiz;
            node.direita = raiz.direita;
            raiz.direita = null;
        }
        return node;
    }

    void preOrder(Node node) {
        if (node != null) {
            System.out.println();
            System.out.print(node.valor + " ");
            preOrder(node.esquerda);
            preOrder(node.direita);
        }
    }


    void imprimirArvore(Node raiz) {
        System.out.println("\n=== ESTRUTURA DA ÁRVORE (Vertical) ===");
        imprimirRecursivo(raiz, 0);
    }

    private static void imprimirRecursivo(Node node, int nivel) {
        if (node == null) {
            imprimirIndentacao(nivel);
            System.out.println("null");
            return;
        }

        // Imprime direita primeiro (para visualizar corretamente)
        imprimirRecursivo(node.direita, nivel + 1);

        // Imprime o nó atual
        imprimirIndentacao(nivel);
        System.out.println("[" + node.valor + "]");

        // Imprime esquerda
        imprimirRecursivo(node.esquerda, nivel + 1);
    }

    private static void imprimirIndentacao(int nivel) {
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
    }

    public static void main(String[] args) {
        Node raiz = null;
        raiz = inserir(raiz, 100);
        raiz = inserir(raiz, 50);
        raiz = inserir(raiz, 200);
        raiz = inserir(raiz, 40);
        raiz = inserir(raiz, 60);
        System.out.println("Arvore");

        ArvoreSpay avs = new ArvoreSpay();
        avs.imprimirArvore(raiz);
    }
}
