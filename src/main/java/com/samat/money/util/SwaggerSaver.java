package com.samat.money.util;

import com.samat.money.exceprion.CustomError;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
@Slf4j
public class SwaggerSaver implements ApplicationListener<ApplicationReadyEvent> {
    private final String apiUrl = "http://localhost:8080/v3/api-docs.yaml";

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        } catch (IOException e) {
            log.error(CustomError.GATEWAY_NOT_ASK.getMessage(), e);
            return;
        }

        String name = "src/main/resources/swagger.yaml";
        try {
            @Cleanup InputStream in = new BufferedInputStream(connection.getInputStream());
            @Cleanup FileOutputStream out = new FileOutputStream(name);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            log.error(CustomError.IO_EXCEPTION.getMessage(), e);
        }
    }
}

