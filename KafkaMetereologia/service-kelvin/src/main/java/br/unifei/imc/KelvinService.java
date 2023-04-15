package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class KelvinService {

    private final ArrayList<Temperature> buffer;

    public KelvinService(ArrayList<Temperature> buffer) {
        this.buffer = buffer;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var toConvertRecords = new ArrayList<Temperature>();
        var kelvinService = new KelvinService(toConvertRecords);
        try(var service = new KafkaService<>(KelvinService.class.getSimpleName(),
                "TEMPERATURE_CURRENT",
                kelvinService::parse,
                Temperature.class,
                Map.of())){
        service.run();
        }
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
            for(var temp : toConvertRecords){
                var key = UUID.randomUUID().toString();
                var convertTmp = new Temperature(toKelvin(temp.getActualTemp()),"Kelvin", temp.getCatchId());
                dispatcher.send("TEMPERATURE_CONVERT", key, convertTmp);
            }
        }
    }

    private void parse(ConsumerRecord<String, Temperature> record) {
        System.out.println("------------------------------------------");
        System.out.println("Mensagem em processamento pelo serviço de Kelvin:");
        System.out.println("PackageUUID - " + record.key());
        System.out.println("Temperature provided - " + record.value().getActualTemp());
        System.out.println("Consumed partition - " + record.partition());
        System.out.println("Message partition offeset - " + record.offset());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Temperaturas processadas!");
        buffer.add(record.value());
    }

    private static Double toKelvin(Double temp) {
        return temp+273;
    }
}
