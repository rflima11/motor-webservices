package br.com.businesstec.motor.enums;

import java.util.Objects;

public enum IntegraoEnum {

    VARIACAO_STRATEGY(4L),
    CATEGORIA_STRATEGY(3L),
    MARCA_STRATEGY(2L),
    PRODUTO_STRATEGY(1L),
    PRODUTO_SKU_STRATEGY(5L),
    CLIENTES_STRATEGY(9L);

    private Long value;

    IntegraoEnum(long value) {
        this.value = value;
    }

    IntegraoEnum getStrategyById(int id) {
        return null;
    }

    public static IntegraoEnum getStrategyByIdEntidade(Long idEntidade) {
        for (IntegraoEnum e : values()) {
            if (Objects.equals(e.value, idEntidade)) {
                return e;
            }
        }
        return null;
    }


}
