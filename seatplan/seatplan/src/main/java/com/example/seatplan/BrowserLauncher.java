package com.example.seatplan;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        try {

            String url = "http://localhost:8080/";

            if (Desktop.isDesktopSupported()) {

                Desktop.getDesktop().browse(
                        new URI(url)
                );

            }

        } catch (Exception e) {

            System.out.println(
                    "Could not open browser: " + e.getMessage()
            );

        }
    }
}