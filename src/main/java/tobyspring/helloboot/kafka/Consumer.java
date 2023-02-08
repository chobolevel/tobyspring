package tobyspring.helloboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args) throws IOException {

        Properties configs = new Properties();
        configs.put("bootstrap.servers", "localhost:9092");
        configs.put("group.id", "dev-topic-group");
        configs.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        configs.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(Arrays.asList("dev-topic"));

        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for(ConsumerRecord record : records) {
                System.out.println("============" + record.value() + "============");
            }
            consumer.close();
        }

    }
}
