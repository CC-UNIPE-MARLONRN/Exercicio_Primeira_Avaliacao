package model;

import exception.QuantidadeInvalidaException;

public class ProdutoPerecivel extends Product {
    private int diasParaVencer;

    public ProdutoPerecivel(String nome, double preco, int quantidade, int diasParaVencer)
            throws QuantidadeInvalidaException {
        super(nome, preco, quantidade);
        if (diasParaVencer < 0) {
            throw new QuantidadeInvalidaException("Dias para vencer não podem ser negativos.");
        }
        this.diasParaVencer = diasParaVencer;
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    @Override
    public double calcularValorTotal() {
        double valor = getPreco() * getQuantidade();
        if (diasParaVencer <= 3) {
            valor *= 0.80;
        }
        return valor;
    }

    @Override
    public String getDescricao() {
        return super.getDescricao() + " | Validade: " + diasParaVencer + " dias";
    }
}
