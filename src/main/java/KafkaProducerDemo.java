import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {

    public static void main(String[] args){
        Properties properties = new Properties();


        // Kafka bootstrap server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());
        // producer acks
        properties.setProperty("acks","1");
        properties.setProperty("retries","3");
        properties.setProperty("linger.ms", "1");

        //topic name

        Producer<String,String> producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(properties);

        for(int i=0; i<10; i++){
            ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("first_topic",Integer.toString(i), "Message with key" +
                    Integer.toString(i));
            producer.send(producerRecord);
        }



        //producer.flush();
        producer.close();

    }
}
