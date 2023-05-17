package com.netology;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    //api_key
    public static final String API_KEY = "1DdGzcwrfTKNfeHMboP8BEglPVTspG0um8kPN4Dn";

    //URI for connection with private api_key
    public static final String URI =
            "https://api.nasa.gov/planetary/apod?api_key=" + API_KEY;

    //Entity that will transform the response into our NASA object
    public static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        //HTTP client settings for sending requests
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                                         .setDefaultRequestConfig(RequestConfig.custom()
                                         .setConnectTimeout(5000)
                                         .setSocketTimeout(30000)
                                         .setRedirectsEnabled(false)
                                         .build())
                                         .build();

        //send request and get response
        CloseableHttpResponse response = httpClient.execute(new HttpGet(URI));

        //transform the response into Java Object
        NasaObject nasaObject = mapper.readValue(response.getEntity().getContent(),NasaObject.class);
        System.out.println(nasaObject);

        //get response with an image
        CloseableHttpResponse pictureResponse = httpClient.execute(new HttpGet(nasaObject.getUrl()));

        //get an image name from the URL
        String[] array = nasaObject.getUrl().split("/");
        String picName = array[array.length - 1];

        HttpEntity httpEntity = pictureResponse.getEntity();

        //save the image into file
//        FileOutputStream fileOutputStream = new FileOutputStream(picName);
//        httpEntity.writeTo(fileOutputStream);

        InputStream in = new URL(nasaObject.getUrl()).openStream();
        Files.copy(in, Paths.get("C:/Users/alex.panaev/YandexDisk/" + picName));
        //fileOutputStream.close();
        in.close();
    }
}
