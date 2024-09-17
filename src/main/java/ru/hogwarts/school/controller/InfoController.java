package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
public class InfoController {
    @Value("${server.port}")
    private String serverPort;


    @GetMapping("port")
    public String getServerPort() {
        return "Порт, на котором запущено приложение: " + serverPort;
    }

    @GetMapping("parallel-stream")
    public Integer getInteger() {
        return Stream.iterate(1, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, Integer::sum);
    }
}
