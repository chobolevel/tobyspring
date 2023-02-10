package tobyspring.helloboot.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class Producer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ProducerConfig.ACKS_CONFIG, "1");
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        ProducerFactory<String, Object> producerFactory = new DefaultKafkaProducerFactory<>(configs);

        KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<>(producerFactory);

        // 객체 생성 부분
        Person person = new Person();
        person.setId(UUID.randomUUID().toString());
        person.setName("Kang");
        List<String> ids = new ArrayList<>();
        ids.add("id1");
        ids.add("id2");
        ids.add("id3");
        person.setIds(ids);

        ListenableFuture<SendResult<String, Object>> res = kafkaTemplate.send("chobo", person);

        System.out.println("-------------------------------------");
        System.out.println(res.get().getProducerRecord().topic());
        System.out.println(res.get().getProducerRecord().partition());
        System.out.println(res.get().getProducerRecord().value());
        System.out.println(res.get().getProducerRecord().timestamp());
        System.out.println("-------------------------------------");

    }
}
