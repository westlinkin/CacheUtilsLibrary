package com.lifeofcoding.cacheutlislibrary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Wesley Lin on 9/7/15.
 */
class GsonHelper {

    static Gson buildGson() {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapter(Date.class, new TypeAdapter<Date>() {

            @Override
            public void write(com.google.gson.stream.JsonWriter writer, Date value) throws IOException {
                if (value == null) {
                    writer.nullValue();
                    return;
                }

                long num = value.getTime();
                num /= 1000;
                writer.value(num);
            }

            @Override
            public Date read(com.google.gson.stream.JsonReader reader) throws IOException {
                if (reader.peek() == JsonToken.NULL) {
                    reader.nextNull();
                    return null;
                }

                long value = reader.nextLong();
                return new Date(value * 1000);
            }

        });
        return b.create();
    }
}
