package org.abondar.experimental.springboot.kafka.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abondar.experimental.springboot.kafka.util.KafkaUtil;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HealthService {
    public boolean isKafkaHealthy() {

        try (ZooKeeper zk = new ZooKeeper(KafkaUtil.ZOOKEEPER_HOST, 10000, event -> {
        })) {

            return isKafkaUp(zk) && isTopicPresent(zk);
        } catch (KeeperException | IOException ex) {
            StringBuilder sb = new StringBuilder();
            sb.append(ex.getClass().getName());
            sb.append(":");
            sb.append(ex.getMessage());

            System.out.println(sb.toString());
            return false;
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return false;
    }

    private boolean isTopicPresent(ZooKeeper zk) throws KeeperException, InterruptedException {
        List<String> topics = zk.getChildren("/brokers/topics", false);
        return topics.contains(KafkaUtil.KAFKA_TOPIC);
    }

    private boolean isKafkaUp(ZooKeeper zk) throws KeeperException, InterruptedException, IOException {
        List<String> ids = zk.getChildren("/brokers/ids", false);
        List<Map> brokerList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();

        for (String id : ids) {
            Map map = objectMapper.readValue(zk.getData("/brokers/ids/" + id, false, null), Map.class);
            brokerList.add(map);
        }

        return brokerList.stream().anyMatch(bl -> bl.get("port")
                .equals(Integer.valueOf(KafkaUtil.KAFKA_HOST.split(":")[1])));
    }


}
