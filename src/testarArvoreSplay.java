public class testarArvoreSplay {
    public static void main(String[] args) {
        ArvoreSplay arvore = new ArvoreSplay();

        System.out.println("\n1. Inserindo elementos...");
        int[] elementos = {50, 30, 70, 20, 40, 60, 80, 10, 25, 35, 45};
        for (int elem : elementos) {
            System.out.println("\n--- Inserindo " + elem + " ---");
            arvore.inserir(elem);
        }
        arvore.percorrerEmOrdem();
    }
}
