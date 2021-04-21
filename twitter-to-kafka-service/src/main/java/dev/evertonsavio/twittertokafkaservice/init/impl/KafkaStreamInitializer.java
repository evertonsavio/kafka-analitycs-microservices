package dev.evertonsavio.twittertokafkaservice.init.impl;

import dev.evertonsavio.app.kafkaadm.client.KafkaAdmClient;
import dev.evertonsavio.appconfigdata.KafkaConfigData;
import dev.evertonsavio.twittertokafkaservice.init.StreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class KafkaStreamInitializer implements StreamInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaStreamInitializer.class);

    private final KafkaConfigData kafkaConfigData;

    private final KafkaAdmClient kafkaAdminClient;

    public KafkaStreamInitializer(KafkaConfigData configData, KafkaAdmClient adminClient) {
        this.kafkaConfigData = configData;
        this.kafkaAdminClient = adminClient;
    }

    @Override
    public void init() {
        kafkaAdminClient.createTopics();
        kafkaAdminClient.checkSchemaRegistry();
        LOG.info("Topics with name {} is ready for operations!", kafkaConfigData.getTopicNamesToCreate().toArray());
    }
}
