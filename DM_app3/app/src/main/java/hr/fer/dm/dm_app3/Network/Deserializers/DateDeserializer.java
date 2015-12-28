package hr.fer.dm.dm_app3.Network.Deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import android.text.TextUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Kajkara on 18.12.2015..
 */
public class DateDeserializer implements JsonDeserializer<Date>, JsonSerializer<Date> {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.GERMAN);

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        String jsonDate = json.getAsString();
        if (!TextUtils.isEmpty(jsonDate)) {
            try {
                return formatter.parse(jsonDate);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (src != null) {
            return context.serialize(formatter.format(src));
        }
        return null;
    }
}
