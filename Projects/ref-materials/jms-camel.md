## Using Apache Camel with Message Brocker (ActiveMQ)
1.  Create new Spring Boot Application:
        Spring Boot Version: 2.1.10.RELEASE
        Dependencies:   apache-camel, activemq5
    
2.  Open POM.XML file verify following dependencies:

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-activemq</artifactId>
    </dependency>

    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-spring-boot-starter</artifactId>
        <version>2.24.0</version>
    </dependency>

    <dependency>
        <groupId>org.apache.camel</groupId>
        <artifactId>camel-jms</artifactId>
        <version>2.24.0</version>
    </dependency>
    ```

    > NOTE: The last dependency is added manually

3.  Open `application.properties` file and add following propertoes

    ```ini
    spring.activemq.broker-url=tcp://localhost:61616
    output.queue=jms:out
    input.queue=jms:in
    ```

4.  Add new beans in Application class (Required for Apache Camel)

    ```java
    @Bean
    public JmsTransactionManager jmsTransactionManager(final ConnectionFactory connectionFactory) {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(connectionFactory);
        return jmsTransactionManager;
    }

    @Bean
    public JmsComponent jmsComponent(final ConnectionFactory connectionFactory, final JmsTransactionManager jmsTransactionManager) {
        JmsComponent jmsComponent = JmsComponent.jmsComponentTransacted(connectionFactory, jmsTransactionManager);
        return jmsComponent;
    }
    ```

5.  Create new class `JmsRouter` 

    ```java
    import org.apache.camel.LoggingLevel;
    import org.apache.camel.builder.RouteBuilder;
    import org.slf4j.Logger;
    import org.slf4j.LoggerFactory;
    import org.springframework.stereotype.Component;

    @Component
    public class JmsRouter extends RouteBuilder{

    static final Logger log = LoggerFactory.getLogger(JmsRouter.class);

    @Override
    public void configure() throws Exception {
        from("{{input.queue}}")
        .log(LoggingLevel.DEBUG, log, "New message received")
        .process(exchange -> {
            System.out.println("Accepted Order : "+exchange.getMessage().getBody());
            String convertedMessage = exchange.getMessage().getBody() + " is Dispatched !";
            exchange.getMessage().setBody(convertedMessage);
        })
        .to("{{output.queue}}")
        .log(LoggingLevel.DEBUG, log, "Message is successfully sent to the output queue")
        .end();

    }
    }
    ```

6.  Launch Apache ActiveMQ5 (If not already running)
    Then visit management console on localhost:8161/admin

    User credentials: admin/admin

7.  Run the project and go back to activeMq admin console
8.  Click on `send` button > enter name of QUEUE as `in` and Message body `DDR4 RAM (8GB)` click `send` button
9.  Click on `Queues` and then `out` Queque, and read the message inside queue
    Message body must be `DDR4 RAM (8GB) is dispatched`
