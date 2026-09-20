package model;

import exception.ProdutoIndisponivelException;
import exception.QuantidadeInvalidaException;

public abstract class Product implements Vendavel {
    private String nome;
    private double preco;
    private int quantidade;

    public Product(String nome, double preco, int quantidade) throws QuantidadeInvalidaException {
        if (preco < 0 || quantidade < 0) {
            throw new QuantidadeInvalidaException("Preço e quantidade não podem ser negativos.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public abstract double calcularValorTotal();

    public String getDescricao() {
        return String.format("%s | Preço: R$ %.2f | Quantidade: %d",
                nome, preco, quantidade);
    }

    public void aplicarDesconto(double percentual) {
        if (percentual < 0) {
            return;
        }
        preco = preco * (1 - (percentual / 100.0));
    }

    public void aplicarDesconto(double percentual, double descontoMaximo) {
        if (percentual < 0 || descontoMaximo < 0) {
            return;
        }

        double descontoAplicado = Math.min(percentual, descontoMaximo);
        preco = preco * (1 - (descontoAplicado / 100.0));
    }

    @Override
    public void vender(int quantidadeDesejada) throws ProdutoIndisponivelException {
        if (quantidadeDesejada <= 0 || quantidadeDesejada > quantidade) {
            throw new ProdutoIndisponivelException(
                    "Produto '" + nome + "' indisponível: solicitado " + quantidadeDesejada
                            + " unidade(s), mas há apenas " + quantidade + " em estoque.");
        }
        quantidade -= quantidadeDesejada;
    }
}
