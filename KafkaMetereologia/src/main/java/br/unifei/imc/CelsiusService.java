package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CelsiusService {
    public static void main(String[] args) throws ExecutionException, InterruptedException{
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
            for (int i = 0; i < 10; i++) {
                var key = UUID.randomUUID().toString();
                var actualTemp = Math.random() * 100 + 1;
                var temp = new Temperature(actualTemp, "Celsius", key);
                dispatcher.send("TEMPERATURE_CURRENT", key, temp);
            }
        }
        var celsiusService = new CelsiusService();
        try(var service = new KafkaService<>(CelsiusService.class.getSimpleName(),
                "TEMPERATURE_CONVERT",
                celsiusService::parse,
                Temperature.class,
                Map.of())){
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, Temperature> record) {
        System.out.println("------------------------------------------");
        System.out.println("Dados de temperatura convertidas:");
        System.out.println(record.key());
        System.out.println(record.value().getActualTemp());
        System.out.println(record.value().getScale());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Temperaturas convertidas!");
    }
}
