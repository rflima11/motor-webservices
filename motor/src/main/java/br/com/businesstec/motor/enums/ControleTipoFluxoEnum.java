package br.com.businesstec.motor.enums;

public enum ControleTipoFluxoEnum {

    BUSCA_ORIGEM(0),
    ENTREGA_DESTINO(1);

    ControleTipoFluxoEnum(int value) {
        this.value = value;
    }

    Integer value;

    public Integer getValue() {
        return value;
    }
}
