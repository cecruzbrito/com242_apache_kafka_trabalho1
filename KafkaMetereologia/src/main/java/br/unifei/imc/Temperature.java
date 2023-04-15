package br.unifei.imc;

import lombok.Getter;

@Getter
public class Temperature {
    private final Double actualTemp;
    private final String scale, catchId;

    public Temperature(Double actualTemp, String scale, String catchId) {
        this.actualTemp = actualTemp;
        this.scale = scale;
        this.catchId = catchId;
    }
}
