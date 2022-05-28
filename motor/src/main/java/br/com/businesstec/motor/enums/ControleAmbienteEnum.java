package br.com.businesstec.motor.enums;

import java.util.Objects;

public enum ControleAmbienteEnum {

    TOTVS(1L, "serviceRM"),
    JET(2L, "serviceJET"),
    TOTVS_ENTREGA(3L, "serviceRM.Entrega"),
    JET_ENTREGA(4L, "serviceJET.Entrega");

    ControleAmbienteEnum(Long value, String routingBinding) {
        this.value = value;
        this.routingBinding = routingBinding;
    }

    private Long value;
    private String routingBinding;

    public static String getBindingByValue(Long value) {
        for (ControleAmbienteEnum e : values()) {
            if (Objects.equals(e.value, value)) {
                return e.getBinding();
            }
        }
        return null;
    }

    public String getBinding() { return this.routingBinding; }



    public Long getValue() {
        return value;
    }
}
