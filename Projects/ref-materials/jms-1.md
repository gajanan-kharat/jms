## JMS : Send Message to QUEUE

1. Create new spring-boot application
    with dependency "Spring for ActiveMQ5"
2. Add following properties inside `application.properties` file

    spring.activemq.broker-url=tcp://localhost:61616
    ## DO NOT USE EMBEDDED ACTIVEMQ
    spring.activemq.in-memory=false

3.  Modify the Application class

    3.1 Add class level annotation `@EnableJms`
    3.2 Inject a dependency:
        
    ```java
    @Autowired 
    private JmsTemplate template;
    ```

    3.3 Implement the `CommandLineRunner` interface and add method:

    ```java
    @Override
    public void run(String... args) throws Exception {
        template.convertAndSend("sample.queue","Hello India!");
        System.out.println("Message sent!");
    }
    ```

4.  Run the Application and once it shows `Message Sent!` stop it.
5.  View the messages in QUEUE using Web browser http://localhost:8161

5.  Create Another SpringBoot Application with Dependency "Spring for ActiveMQ5"
6.  Use following properties in `application.properties` file
    ```ini
    spring.activemq.broker-url=tcp://localhost:61616
    ## DO NOT USE EMBEDDED ACTIVEMQ
    spring.activemq.in-memory=false
    ```
7.  Create a new BEAN inside Application class

    ```java
	@Bean
	public JmsListenerContainerFactory<?> myFactory
		(ConnectionFactory conFactory, DefaultJmsListenerContainerFactoryConfigurer config)
	{	
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		config.configure(factory, conFactory);
		return factory;
	}
    ```

8.  Create a new Class with name `MessageListener` and create a listener method
    ```java
    @Component
    public class MessageListener {

        @JmsListener(destination="sample.queue",containerFactory="myFactory")
        public void readMessage(String message) {
            System.out.print("Date "+ LocalDate.now());
            System.out.println(" Message : "+message);
        }
    }
    ```