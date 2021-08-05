## Spring JMS (SpringBoot) with Embedded Apache ActiveMQ

1. Create new Spring Starter project
    Projectname:    jms-demo
    Package:        com.mahendra

    Spring boot dependencies:
        Spring for Apache ActiveMQ Starter

2.  Open the ONLY JAVA file created by Spring boot `JmsDemoApplication.java`

    2.1 Add one annotation `@EnableJms`

    2.2 Add a ListenerConnectionFactory bean for Receiving the messages

    ```
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

    2.3 Create receiver
    ```java
    @Component
    public class Receiver {

        @JmsListener(destination = "sample.queue", containerFactory = "myFactory")
        public void receiveMessage(String message) {
            System.out.println("Received <" + message + ">");
        }
    }
    ```

3.  Modify the `JmsDemoApplication.java`

    3.1 Implement an Interface `CommandLineRunner` and Autowire JmsTemplate
    
    ```java
    @Autowired private JmsTemplate template;
    ```

    3.2 Override a method `run` write this code

    ```java
    @Override
	public void run(String... args) throws Exception {
		System.out.println("Preparing to send a message ");
		template.convertAndSend("sample.queue", "Hello WOrld !");
	}
    ```

4.  Stop the previous spring boot application.

5.  Launch an external apache activemq server

    ```cmd
    $ cd \apache-activemq-5.15.10\bin\win64
    $ activemq.bat
    ```

6.  Go back to spring application, add TWO properties in `application.properties` file

    ```ini
    ## Connect to external activemq
    spring.activemq.broker-url=tcp://localhost:61616
    ## Disable in-memory activemq
    spring.activemq.in-memory=false
    ```

7.  Save and Run your spring boot application to send and receive sample message
8.  Open browser and visit: http://localhost:8161
    Login with `admin/admin` and click on `Queues`