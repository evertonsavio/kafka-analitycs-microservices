package dev.evertonsavio.appconfigdata;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "twitter-to-kafka-service")
public class TwitterToKafkaServiceConfigData {

    private List<String> twitterKeywords;
    private String welcomeMessage;
    private Boolean enableMockTweets;
    private Integer mockMinTweetLenght;
    private Integer mockMaxTweetLenght;
    private Long mockSleepMs;

    public TwitterToKafkaServiceConfigData() {
    }

    public TwitterToKafkaServiceConfigData(List<String> twitterKeywords, String welcomeMessage, Boolean enableMockTweets, Integer mockMinTweetLenght, Integer mockMaxTweetLenght, Long mockSleepMs) {
        this.twitterKeywords = twitterKeywords;
        this.welcomeMessage = welcomeMessage;
        this.enableMockTweets = enableMockTweets;
        this.mockMinTweetLenght = mockMinTweetLenght;
        this.mockMaxTweetLenght = mockMaxTweetLenght;
        this.mockSleepMs = mockSleepMs;
    }

    public List<String> getTwitterKeywords() {
        return twitterKeywords;
    }

    public void setTwitterKeywords(List<String> twitterKeywords) {
        this.twitterKeywords = twitterKeywords;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public Boolean getEnableMockTweets() {
        return enableMockTweets;
    }

    public void setEnableMockTweets(Boolean enableMockTweets) {
        this.enableMockTweets = enableMockTweets;
    }

    public Integer getMockMinTweetLenght() {
        return mockMinTweetLenght;
    }

    public void setMockMinTweetLenght(Integer mockMinTweetLenght) {
        this.mockMinTweetLenght = mockMinTweetLenght;
    }

    public Integer getMockMaxTweetLenght() {
        return mockMaxTweetLenght;
    }

    public void setMockMaxTweetLenght(Integer mockMaxTweetLenght) {
        this.mockMaxTweetLenght = mockMaxTweetLenght;
    }

    public Long getMockSleepMs() {
        return mockSleepMs;
    }

    public void setMockSleepMs(Long mockSleepMs) {
        this.mockSleepMs = mockSleepMs;
    }
}
