import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerDemo {

    public static void main(String[] args){
        Properties properties = new Properties();


        // Kafka bootstrap server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer",StringDeserializer.class.getName());
        // consumer acks

        properties.setProperty("group.id", "test");
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "1000");
        properties.setProperty("auto.offset.reset", "earliest");

        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(properties);

        kafkaConsumer.subscribe(Arrays.asList("first_topic"));

        while(true){
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(1000);
            for(ConsumerRecord<String,String> consumerRecord : consumerRecords){

                System.out.println("Partition: "+ consumerRecord.partition()+
                                    ", Offset: " + consumerRecord.offset()+
                                    ", Key: " + consumerRecord.key()+
                                    ", Value: "+ consumerRecord.value());

            }
        }


    }
}
