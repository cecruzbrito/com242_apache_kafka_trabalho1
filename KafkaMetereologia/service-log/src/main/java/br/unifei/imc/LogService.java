package br.unifei.imc;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.*;

public class LogService {
    public static void main(String[] args) {
        var logService = new LogService();
        try (KafkaService<String> service = new KafkaService<>(LogService.class.getSimpleName(),
                Pattern.compile("TEMPERATURE.*"), //Log needs listen all topics
                logService::parse,
                String.class,
                Map.of(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()))) {
            service.run();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------------------------");
        System.out.println("LOG:");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        //Fluxo de saida de um arquivo
        try(OutputStream os = new FileOutputStream("syslog.txt", true)) { // Name file
            Writer wr = new OutputStreamWriter(os); // writer
            BufferedWriter br = new BufferedWriter(wr);
            br.write("Sys-log: " +  now +" - " + record.key());
            br.newLine();
            br.write("Msg - " + record.value());
            br.newLine();
            br.write("Partition - " + record.partition());
            br.newLine();
            br.write("Offset - " + record.offset());
            br.newLine();
            br.newLine();
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
