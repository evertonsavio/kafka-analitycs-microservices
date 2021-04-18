### Kafkacat
```
sudo docker run -it --network=host confluentinc/cp-kafkacat kafkacat -L -b localhost:19092
kafkacat -L -b localhost:19092

-L = list
-b brokers
```