package hu.learnerbot.dynamiclang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(
    exclude = {
        ThymeleafAutoConfiguration.class
    }
)
@ImportResource(value = {"classpath*:applicationContext.xml"})
public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
    }
}
