package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CelsiusService {
    public static void main(String[] args){
        var celsiusService = new CelsiusService();
        System.out.println("Olá, serviço de meteorologia está em produção");
        boolean serviceStatus = true;
        Scanner scan = new Scanner(System.in);
        int option, msgQuantity;
        while(serviceStatus){
            System.out.println("O que deseja?");
            System.out.println("1 - Enviar temperaturas randômicas para o tópico TEMPERATURE_CURRENT");
            System.out.println("2 - Ouvir o tópico TEMPERATURE_CONVERT");
            option = scan.nextInt();
            switch(option){
                case 1:
                    System.out.println("Quantos valores deseja enviar?");
                    msgQuantity = scan.nextInt();
                    celsiusService.newProducer(msgQuantity);
                    break;
                case 2:
                    System.out.println("Iniciando o serviço");
                    celsiusService.newConsumer(celsiusService);
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            System.out.println("Continuar serviço (true/false)?");
            serviceStatus = scan.nextBoolean();
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

    private void newProducer(Integer msgQuantity){
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
            for (int i = 0; i < msgQuantity; i++) {
                var key = UUID.randomUUID().toString();
                var actualTemp = Math.random() * 100 + 1;
                var temp = new Temperature(actualTemp, "Celsius", key);
                dispatcher.send("TEMPERATURE_CURRENT", key, temp);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void newConsumer(CelsiusService celsiusService){
        try(var service = new KafkaService<>(CelsiusService.class.getSimpleName(),
                "TEMPERATURE_CONVERT",
                celsiusService::parse,
                Temperature.class,
                Map.of())){
            service.run();
        }
    }
}
