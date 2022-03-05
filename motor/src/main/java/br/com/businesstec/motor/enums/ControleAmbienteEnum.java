package br.com.businesstec.motor.enums;

public enum ControleAmbienteEnum {

    TOTVS(1),
    JET(2);

    ControleAmbienteEnum(int value) {
        this.value = value;
    }

    Integer value;

    public Integer getValue() {
        return value;
    }
}
