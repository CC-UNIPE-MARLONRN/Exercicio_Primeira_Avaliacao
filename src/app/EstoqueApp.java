package app;

import business.Estoque;
import exception.EstoqueException;
import exception.ProdutoIndisponivelException;
import exception.QuantidadeInvalidaException;
import model.Product;
import model.ProdutoComum;
import model.ProdutoPerecivel;

public class EstoqueApp {
    public static void main(String[] args) {
        Estoque estoque = new Estoque();

        try {
            executarFluxo(estoque);
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Erro de quantidade: " + e.getMessage());
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Produto indisponível: " + e.getMessage());
        } catch (EstoqueException e) {
            System.out.println("Erro no estoque: " + e.getMessage());
        }
    }

    private static void executarFluxo(Estoque estoque) throws EstoqueException {
        cadastrarProdutos(estoque);
        mostrarProdutos(estoque);
        realizarVendaValida(estoque);
        tentarVendaIndisponivel(estoque);
        tentarCadastrarProdutoInvalido(estoque);
        System.out.println("\nValor total do estoque: R$ "
                + String.format("%.2f", estoque.calcularValorTotalEstoque()));
    }

    private static void cadastrarProdutos(Estoque estoque) throws QuantidadeInvalidaException {
        estoque.adicionarProduto(new ProdutoComum("Arroz", 12.50, 10));
        estoque.adicionarProduto(new ProdutoComum("Feijão", 8.90, 8));
        estoque.adicionarProduto(new ProdutoPerecivel("Leite", 5.50, 12, 2));
        estoque.adicionarProduto(new ProdutoPerecivel("Iogurte", 6.20, 15, 7));
    }

    private static void mostrarProdutos(Estoque estoque) {
        System.out.println("Produtos cadastrados:");
        for (Product produto : estoque.getProdutos()) {
            System.out.println(produto.getDescricao());
        }
    }

    private static void realizarVendaValida(Estoque estoque) throws ProdutoIndisponivelException {
        System.out.println("\nVenda válida de 3 unidades de arroz:");
        estoque.venderProduto(0, 3);
        System.out.println("Arroz após venda: " + estoque.getProdutos().get(0).getDescricao());
    }

    private static void tentarVendaIndisponivel(Estoque estoque) {
        try {
            estoque.venderProduto(0, 100);
        } catch (ProdutoIndisponivelException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }

    private static void tentarCadastrarProdutoInvalido(Estoque estoque) {
        try {
            estoque.adicionarProduto(new ProdutoComum("Produto Inválido", -10, -3));
        } catch (QuantidadeInvalidaException e) {
            System.out.println("Exceção capturada: " + e.getMessage());
        }
    }
}
