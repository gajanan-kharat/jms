## Spring JMS with External Active MQ Instance 

This demo uses Apache ActiveMQ running in container, please use following command to launch active-mq instance

```shell
$ docker run -p 61616:61616 -p 8161:8161 rmohr/activemq:5.14.3
```

Access Apache ActiveMQ Dashboard using following URL (Use any web-browser)
http://localhost:8161/

NOTE: Default user credential : `admin/admin`

## Please run `jms-consumer` application before running this one.