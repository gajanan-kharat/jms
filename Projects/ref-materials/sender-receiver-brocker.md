## Using Three components:
1. ActiveMQ Standalone server

    ```cmd
    $ activemq.bat
    ```

2.  Message sender application

    2.1 Create New spring boot application with ActiveMQ dependency
    2.2 Add TWO properties in `application.properties`

        ```ini
        spring.activemq.broker-url=tcp://localhost:61616
        spring.activemq.in-memory=false
        ```
    2.3 Modify the Application class with extra annotation `@EnableJms`
    2.4 Add CommandLineRunner interface and write code to send message in "run"

        ```java
        @Autowired private JmsTemplate template;
        @Override
        public void run(String... args) throws Exception {
            for(int i = 0;i<100;i++) {
                template.convertAndSend("sample.queue","Message "+(i+1));
                System.out.println("Message is sent !");
                Thread.sleep(100);
            }
        }
        ```
3.  The message receiver application

    3.1 Create New spring boot application with ActiveMQ dependency
    3.2 Add TWO properties in `application.properties`

        ```ini
        spring.activemq.broker-url=tcp://localhost:61616
        spring.activemq.in-memory=false
        ```
    3.3 Modify the Application class with extra annotation `@EnableJms`
    3.4 Create a new Bean inside Application.java (for JmsListener)

        ```java
        @Bean
        public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                        DefaultJmsListenerContainerFactoryConfigurer configurer) {
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            // This provides all boot's default to this factory, including the message converter
            configurer.configure(factory, connectionFactory);
            // You could still override some of Boot's default if necessary.
            return factory;
        }
        ```
    
    3.5 Create a listener bean (With @JmsListener Annotation)

        ```java
        @Component
        public class MessageReceiver {

            @JmsListener(destination="sample.queue")
            public void receive(String message) {
                System.out.print("DateTime: "+LocalDate.now());
                System.out.println(" Got new message: "+message);
            }
        }
        ```