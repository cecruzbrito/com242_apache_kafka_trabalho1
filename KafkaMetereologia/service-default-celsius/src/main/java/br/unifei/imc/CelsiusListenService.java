package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;

public class CelsiusListenService {
    public static void main(String[] args){
        var celsiusListenService = new CelsiusListenService();
        try(var service = new KafkaService<>(CelsiusListenService.class.getSimpleName(),
                "TEMPERATURE_CONVERT",
                celsiusListenService::parse,
                Temperature.class,
                Map.of())){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Temperature> record) {
        System.out.println("------------------------------------------");
        System.out.println("Dados de temperatura convertidas:");
        System.out.println("PackageUUID - " + record.key());
        System.out.println("Converted temperature - " + record.value().getActualTemp());
        System.out.println("Scale adopted - " + record.value().getScale());
        System.out.println("Consumed partition - " + record.partition());
        System.out.println("Message partition offeset - " + record.offset());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Temperaturas convertidas!");
    }
}
