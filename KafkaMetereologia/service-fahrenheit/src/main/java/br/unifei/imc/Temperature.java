package br.unifei.imc;

import lombok.Getter;

@Getter
public class Temperature {
    private final Double actualTemp;
    private final String scale, catchId, oldId;

    public Temperature(Double actualTemp, String scale, String catchId, String oldId) {
        this.actualTemp = actualTemp;
        this.scale = scale;
        this.catchId = catchId;
        this.oldId = oldId;
    }
}
