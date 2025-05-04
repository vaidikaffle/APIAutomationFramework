package com.learning.utils;

import io.restassured.response.Response;
import lombok.SneakyThrows;

import javax.imageio.stream.ImageInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class ApiUtils {

    private ApiUtils(){}

    @SneakyThrows
    public static String readJsonAndGetAsString(String filepath){
        return new String(Files.readAllBytes(Paths.get(filepath)));
    }

    @SneakyThrows
    public static void storeStringResponseAsJsonFile(String filepath, Response response){
        Files.write(Paths.get(filepath), response.asByteArray());
    }
}
