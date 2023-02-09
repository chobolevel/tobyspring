package tobyspring.helloboot.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consumer {
    public static void main(String[] args) {

        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "chobo_group");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        KafkaConsumer<String, Person> consumer = new KafkaConsumer<>(configs);

        consumer.subscribe(List.of("chobo"));

        ConsumerRecords<String, Person> records = consumer.poll(500);
        for(ConsumerRecord<String, Person> record : records) {
            System.out.println("-------------------------------------------------");
            System.out.println(record.topic());
            System.out.println(record.partition());
            System.out.println(record.value());
            System.out.println(record.timestamp());
            System.out.println("-------------------------------------------------");
        }

        consumer.close();

    }
}
