package br.unifei.imc;

import lombok.Getter;

@Getter
public class Temperature {
    private final Double actualTemp;
    private final String scale;

    public Temperature(Double actualTemp, String scale) {
        this.actualTemp = actualTemp;
        this.scale = scale;
    }
}
