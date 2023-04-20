package br.unifei.imc;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class CelsiusService {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        UserComponent user = new UserComponent();
        var celsiusService = new CelsiusService();
        System.out.println("Olá, serviço de meteorologia está em produção");
        boolean serviceStatus = true;
        int option, msgQuantity;
        while(serviceStatus){
            System.out.println("O que deseja?");
            System.out.println("1 - Enviar temperaturas randômicas para o tópico TEMPERATURE_CURRENT");
            System.out.println("2 - Enviar temperatura para o tópico TEMPERATURE_CURRENT");
            option = user.getOption(scan);
            switch(option){
                case 1:
                    System.out.println("Quantos valores deseja enviar?");
                    msgQuantity = user.getQuantityTemperatures(scan);
                    celsiusService.newProducer(msgQuantity);
                    break;
                case 2:
                    System.out.println("Quantos valores deseja enviar?");
                    celsiusService.newProducer(user.getTemperature(scan));
                    break;
                default:
                    System.out.println("Opção inválida");
            }
            System.out.println("Continuar serviço (true/false)?");
            serviceStatus =  user.getApplicationStatus(scan);
        }
        scan.close();
    }

    private void newProducer(Integer msgQuantity){
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
            for (int i = 0; i < msgQuantity; i++) {
                var key = UUID.randomUUID().toString();
                var actualTemp = Math.random() * 100 + 1;
                var temp = new Temperature(actualTemp, "Celsius", key, "0");
                dispatcher.send("TEMPERATURE_CURRENT", key, temp);
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void newProducer(Double temperature){
        try(var dispatcher = new KafkaDispatcher<Temperature>()){
                var key = UUID.randomUUID().toString();
                var temp = new Temperature(temperature, "Celsius", key, "0");
                dispatcher.send("TEMPERATURE_CURRENT", key, temp);
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
