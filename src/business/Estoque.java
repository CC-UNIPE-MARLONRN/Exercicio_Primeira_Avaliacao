package business;

import exception.ProdutoIndisponivelException;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private final List<Product> produtos;

    public Estoque() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Product p) {
        if (p == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        produtos.add(p);
    }

    public void venderProduto(int indice, int quantidade) throws ProdutoIndisponivelException {
        if (indice < 0 || indice >= produtos.size()) {
            throw new IndexOutOfBoundsException("Índice do produto inválido: " + indice);
        }
        produtos.get(indice).vender(quantidade);
    }

    public double calcularValorTotalEstoque() {
        double total = 0.0;
        for (Product produto : produtos) {
            total += produto.calcularValorTotal();
        }
        return total;
    }

    public List<Product> getProdutos() {
        return produtos;
    }
}
