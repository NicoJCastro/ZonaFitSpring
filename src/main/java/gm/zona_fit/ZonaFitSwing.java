package gm.zona_fit;

import gm.zona_fit.gui.ZonaFitForma;
import io.github.cdimascio.dotenv.Dotenv;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;

@SpringBootApplication
public class ZonaFitSwing {

    private static final Logger logger = LoggerFactory.getLogger(ZonaFitSwing.class);

    public static void main(String[] args) {
        loadEnvironmentVariables();

        System.setProperty("java.awt.headless", "false");

        logger.info("***Inicio de la aplicación***");
        ConfigurableApplicationContext contextoSpring =
                new SpringApplicationBuilder(ZonaFitSwing.class)
                        .headless(false)
                        .web(WebApplicationType.NONE)
                        .run(args);
        logger.info("***Fin de la aplicación***");

        SwingUtilities.invokeLater(() -> {
            ZonaFitForma zonaFitForma = contextoSpring.getBean(ZonaFitForma.class);
            zonaFitForma.setVisible(true);
        });
    }

    private static void loadEnvironmentVariables() {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DB_NAME", dotenv.get("DB_NAME"));
        System.setProperty("DB_HOST", dotenv.get("DB_HOST"));
        System.setProperty("DB_PORT", dotenv.get("DB_PORT"));
        System.setProperty("DB_USER", dotenv.get("DB_USER"));
        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
    }
}