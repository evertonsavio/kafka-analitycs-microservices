package dev.evertonsavio.twittertokafkaservice.runner.implementation;

import dev.evertonsavio.twittertokafkaservice.configuration.TwitterToKafkaServiceConfigData;
import dev.evertonsavio.twittertokafkaservice.exception.TwitterToKafkaException;
import dev.evertonsavio.twittertokafkaservice.listener.TwitterToKafkaListener;
import dev.evertonsavio.twittertokafkaservice.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Component
@ConditionalOnProperty(name = "twitter-to-kafka-service.enable-mock-tweets", havingValue = "true")
public class MockKafkaStreamRunner implements StreamRunner {

    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final TwitterToKafkaListener twitterToKafkaListener;

    /*MOCK*/
    public static final Random RANDOM = new Random();
    private static final String[] WORDS = new String[]{
            "Lorem",
            "ipsum",
            "dolor",
            "sit",
            "amet",
            "consectetuer",
            "adipiscing",
            "elit"
    };
    public static final String tweetAsRawJson = "{" +
            "\"created_at\":\"{0}\"," +
            "\"id\":\"{1}\"," +
            "\"text\":\"{2}\"," +
            "\"user\":{\"id\":\"{3}\"}" +
            "}";

    private static final String TWITTER_STATUS_DATE_FORMAT = "EEE MMM dd HH:mm:ss zzz yyyy";

    /*MOCK*/

    public MockKafkaStreamRunner(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData, TwitterToKafkaListener twitterToKafkaListener) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.twitterToKafkaListener = twitterToKafkaListener;
    }

    @Override
    public void start() throws TwitterException {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        Integer mockMinTweetLenght = twitterToKafkaServiceConfigData.getMockMinTweetLenght();
        Integer mockMaxTweetLenght = twitterToKafkaServiceConfigData.getMockMaxTweetLenght();
        Long mockSleepMs = twitterToKafkaServiceConfigData.getMockSleepMs();
        LOG.info("Starting mock filtering twitter streams for keywords {}", Arrays.toString(keywords));
        simulateTwitterStream(keywords, mockMinTweetLenght, mockMaxTweetLenght, mockSleepMs);

    }

    private void simulateTwitterStream(String[] keywords, Integer mockMinTweetLenght,
                                       Integer mockMaxTweetLenght, Long mockSleepMs){

            Executors.newSingleThreadExecutor().submit(() -> {
                try {
                    while (true){
                        String formattedTweetAsRawJson = getFormattedTweet(keywords, mockMinTweetLenght, mockMaxTweetLenght);
                        Status status = TwitterObjectFactory.createStatus(formattedTweetAsRawJson);
                        twitterToKafkaListener.onStatus(status);
                        sleep(mockSleepMs);
                    }
                }catch (TwitterException e){
                    LOG.error("Error creating twitter status!", e);
                }
            });
    }

    private void sleep(long sleepTimeMs){
        try {
            Thread.sleep(sleepTimeMs);
        } catch (InterruptedException e) {
            throw new TwitterToKafkaException("Error whie sleeping for waiting new status create!");
        }
    }
    private String getFormattedTweet(String[] keywords, Integer mockMinTweetLenght, Integer mockMaxTweetLenght) {
        String[] params = new String[] {
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern(TWITTER_STATUS_DATE_FORMAT, Locale.ENGLISH)),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE)),
                getRandomTweetContent(keywords, mockMinTweetLenght, mockMaxTweetLenght),
                String.valueOf(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE))
        };

        return formatTweetAsJsonParam(params);
    }

    private String formatTweetAsJsonParam(String[] params) {
        String tweet = tweetAsRawJson;

        for (int i = 0; i < params.length; i++) {
            tweet = tweet.replace("{" + i + "}", params[i]);
        }
        return tweet;
    }

    private String getRandomTweetContent(String[] keywords, Integer mockMinTweetLenght, Integer mockMaxTweetLenght) {
        StringBuilder tweet = new StringBuilder();
        int tweetLenght = RANDOM.nextInt(mockMaxTweetLenght - mockMinTweetLenght + 1) + mockMinTweetLenght;
        return constructRandomActivity(keywords, tweet, tweetLenght);
    }

    private String constructRandomActivity(String[] keywords, StringBuilder tweet, int tweetLenght) {
        for (int i = 0; i < tweetLenght; i++) {
            tweet.append(WORDS[RANDOM.nextInt(WORDS.length)]).append(" ");
            if(i == tweetLenght /2){
                tweet.append(keywords[RANDOM.nextInt(keywords.length)]).append(" ");
            }
        }
        return tweet.toString().trim();
    }
}
