package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class KelvinService {

    public static void main(String[] args) {
        var kelvinService = new KelvinService();
        try(var service = new KafkaService<>(KelvinService.class.getSimpleName(),
                "TEMPERATURE_CURRENT",
                kelvinService::parse,
                Temperature.class,
                Map.of())){
        service.run();
        }
    }

    private void outputRecord(ConsumerRecord<String, Temperature> record){
        System.out.println("------------------------------------------");
        System.out.println("Mensagem em processamento pelo serviço de Kelvin:");
        System.out.println("PackageUUID - " + record.key());
        System.out.println("Temperature provided - " + record.value().getActualTemp());
        System.out.println("Consumed partition - " + record.partition());
        System.out.println("Message partition offeset - " + record.offset());
    }

    private void parse(ConsumerRecord<String, Temperature> record) {
        outputRecord(record);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Temperaturas processadas!");
        dispatcherConvertedTemperature(record);
    }
    private void dispatcherConvertedTemperature(ConsumerRecord<String, Temperature> record){
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
            var key = UUID.randomUUID().toString();
            var convertTmp = new Temperature(toKelvin(record.value().getActualTemp()),"Kelvin", key, record.value().getCatchId());
            dispatcher.send("TEMPERATURE_CONVERT", key, convertTmp);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private static Double toKelvin(Double temp) {
        return temp+273;
    }
}
