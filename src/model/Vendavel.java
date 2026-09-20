package model;

import exception.ProdutoIndisponivelException;

public interface Vendavel {
    void vender(int quantidadeDesejada) throws ProdutoIndisponivelException;
}
