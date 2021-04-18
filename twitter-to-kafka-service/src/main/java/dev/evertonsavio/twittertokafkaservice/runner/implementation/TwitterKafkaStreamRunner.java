package dev.evertonsavio.twittertokafkaservice.runner.implementation;

import dev.evertonsavio.twittertokafkaservice.configuration.TwitterToKafkaServiceConfigData;
import dev.evertonsavio.twittertokafkaservice.listener.TwitterToKafkaListener;
import dev.evertonsavio.twittertokafkaservice.runner.StreamRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PreDestroy;
import java.util.Arrays;

public class TwitterKafkaStreamRunner implements StreamRunner {

    public static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaStreamRunner.class);

    private final TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData;
    private final TwitterToKafkaListener twitterToKafkaListener;

    public TwitterKafkaStreamRunner(TwitterToKafkaServiceConfigData twitterToKafkaServiceConfigData,
                                    TwitterToKafkaListener twitterToKafkaListener) {
        this.twitterToKafkaServiceConfigData = twitterToKafkaServiceConfigData;
        this.twitterToKafkaListener = twitterToKafkaListener;
    }

    private TwitterStream twitterStream;

    @Override
    public void start() throws TwitterException {
        twitterStream = new TwitterStreamFactory().getInstance();
        twitterStream.addListener(twitterToKafkaListener);
        addFilter();
    }

    @PreDestroy
    public void shutdown(){
        if(twitterStream != null){
            LOG.info("Closing Twitter Stream");
            twitterStream.shutdown();
        }
    }

    private void addFilter() {
        String[] keywords = twitterToKafkaServiceConfigData.getTwitterKeywords().toArray(new String[0]);
        FilterQuery filterQuery = new FilterQuery(keywords);
        twitterStream.filter(filterQuery);
        LOG.info("Started twitter stream for keywords {}", Arrays.toString(keywords));
    }
}
