package dev.evertonsavio.appconfigdata;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryUrlKey;
    private String schemaRegistryUrl;
    private String topicName;
    private List<String> topicNamesToCreate;
    private Integer numOfPartitions;
    private Short replicationFactor;

    public KafkaConfigData() {
    }

    public KafkaConfigData(String bootstrapServers, String schemaRegistryUrlKey, String schemaRegistryUrl, String topicName, List<String> topicNamesToCreate, Integer numOfPartitions, Short replicationFactor) {
        this.bootstrapServers = bootstrapServers;
        this.schemaRegistryUrlKey = schemaRegistryUrlKey;
        this.schemaRegistryUrl = schemaRegistryUrl;
        this.topicName = topicName;
        this.topicNamesToCreate = topicNamesToCreate;
        this.numOfPartitions = numOfPartitions;
        this.replicationFactor = replicationFactor;
    }

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getSchemaRegistryUrlKey() {
        return schemaRegistryUrlKey;
    }

    public void setSchemaRegistryUrlKey(String schemaRegistryUrlKey) {
        this.schemaRegistryUrlKey = schemaRegistryUrlKey;
    }

    public String getSchemaRegistryUrl() {
        return schemaRegistryUrl;
    }

    public void setSchemaRegistryUrl(String schemaRegistryUrl) {
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTopicNamesToCreate() {
        return topicNamesToCreate;
    }

    public void setTopicNamesToCreate(List<String> topicNamesToCreate) {
        this.topicNamesToCreate = topicNamesToCreate;
    }

    public Integer getNumOfPartitions() {
        return numOfPartitions;
    }

    public void setNumOfPartitions(Integer numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
