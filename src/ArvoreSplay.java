class NoSplay {
    int valor;
    NoSplay esquerda, direita, pai;

    public NoSplay(int valor) {
        this.valor = valor;
        this.esquerda = this.direita = this.pai = null;
    }
}

public class ArvoreSplay {
    private NoSplay raiz;

    public ArvoreSplay() {
        raiz = null;
    }

    // Rotação para direita (zig)
    private void rotacaoDireita(NoSplay x) {
        NoSplay y = x.esquerda;
        if (y != null) {
            x.esquerda = y.direita;
            if (y.direita != null) {
                y.direita.pai = x;
            }
            y.pai = x.pai;
        }

        if (x.pai == null) {
            raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }

        if (y != null) {
            y.direita = x;
        }
        x.pai = y;
    }

    // Rotação para esquerda (zag)
    private void rotacaoEsquerda(NoSplay x) {
        NoSplay y = x.direita;
        if (y != null) {
            x.direita = y.esquerda;
            if (y.esquerda != null) {
                y.esquerda.pai = x;
            }
            y.pai = x.pai;
        }

        if (x.pai == null) {
            raiz = y;
        } else if (x == x.pai.esquerda) {
            x.pai.esquerda = y;
        } else {
            x.pai.direita = y;
        }

        if (y != null) {
            y.esquerda = x;
        }
        x.pai = y;
    }

    // Operação Splay - traz o nó para a raiz
    private void splay(NoSplay no) {
        while (no.pai != null) {
            // nó é filho da raiz
            if (no.pai.pai == null) {
                if (no.pai.esquerda == no) {
                    rotacaoDireita(no.pai); // zig
                } else {
                    rotacaoEsquerda(no.pai); // zag
                }
            }
            // zig-zig ou zag-zag
            else if (no.pai.esquerda == no &&
                    no.pai.pai.esquerda == no.pai) {
                rotacaoDireita(no.pai.pai);
                rotacaoDireita(no.pai);
            } else if (no.pai.direita == no &&
                    no.pai.pai.direita == no.pai) {
                rotacaoEsquerda(no.pai.pai);
                rotacaoEsquerda(no.pai);
            }
            // zig-zag ou zag-zig
            else if (no.pai.esquerda == no &&
                    no.pai.pai.direita == no.pai) {
                rotacaoDireita(no.pai);
                rotacaoEsquerda(no.pai);
            } else {
                rotacaoEsquerda(no.pai);
                rotacaoDireita(no.pai);
            }
        }
    }

    public NoSplay buscar(int valor) {
        NoSplay no = buscarNo(raiz, valor);
        if (no != null) {
            splay(no);
        }
        return no;
    }

    private NoSplay buscarNo(NoSplay no, int valor) {
        if (no == null || no.valor == valor) {
            return no;
        }

        if (valor < no.valor) {
            return no.esquerda == null ? no : buscarNo(no.esquerda, valor);
        } else {
            return no.direita == null ? no : buscarNo(no.direita, valor);
        }
    }

    // Inserção na árvore Splay
    public void inserir(int valor) {
        NoSplay novoNo = new NoSplay(valor);
        NoSplay pai = null;
        NoSplay atual = raiz;

        // Encontra a posição de inserção
        while (atual != null) {
            pai = atual;
            if (novoNo.valor < atual.valor) {
                atual = atual.esquerda;
            } else {
                atual = atual.direita;
            }
        }

        // pai é o pai do novo nó
        novoNo.pai = pai;
        if (pai == null) {
            raiz = novoNo;
        } else if (novoNo.valor < pai.valor) {
            pai.esquerda = novoNo;
        } else {
            pai.direita = novoNo;
        }
        splay(novoNo);
    }

    public NoSplay obterRaiz() {
        return raiz;
    }

    public boolean estaVazia() {
        return raiz == null;
    }

    //APENAS PARA VISUALIZAR OS VAL0RES
    public void percorrerEmOrdem() {
        percorrerEmOrdemRec(raiz);
        System.out.println();
    }

    private void percorrerEmOrdemRec(NoSplay no) {
        if (no != null) {
            percorrerEmOrdemRec(no.esquerda);
            System.out.print(no.valor + " ");
            percorrerEmOrdemRec(no.direita);
        }
    }
    // ACABA AQUI O METODO PARA VISUALIZAR OS VALORES

    public void limpar() {
        raiz = null;
    }
}