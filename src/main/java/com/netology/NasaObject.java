package com.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaObject {
    public final String COPY_RIGHT;
    public final String DATE;
    public final String EXPLANATION;
    public final String HD_URL;

    public final String MEDIA_TYPE;
    public final String SERVICE_VERSION;
    public final String TITLE;
    public final String URL;

    public NasaObject(
            @JsonProperty("COPY_RIGHT") String copyRight1,
            @JsonProperty("DATE") String date,
            @JsonProperty("EXPLANATION") String explanation,
            @JsonProperty("HD_URL") String hdUrl,
            @JsonProperty("MEDIA_TYPE") String mediaType,
            @JsonProperty("SERVICE_VERSION") String serviceVersion,
            @JsonProperty("TITLE") String title,
            @JsonProperty("URL") String url) {
        COPY_RIGHT = copyRight1;
        DATE = date;
        EXPLANATION = explanation;
        HD_URL = hdUrl;
        MEDIA_TYPE = mediaType;
        SERVICE_VERSION = serviceVersion;
        TITLE = title;
        URL = url;

    }
}
