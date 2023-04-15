package common;

import api_assured.ApiUtilities;
import gpt.api.GPT;
import gpt.chat.Chat;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import utils.PropertiesReader;
import utils.PropertyUtility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class ApiHelper extends ApiUtilities {

    //chat sample
    public static void main(String[] args) {
        PropertyUtility.loadProperties("src/test/resources/test.properties");
        GPT gpt = new GPT(PropertyUtility.properties.getProperty("gpt-token"));
        Chat chat = new Chat(gpt);
        chat.startChat();
    }

    public RequestBody createPartFromString(String text) {
        return RequestBody.create(MultipartBody.FORM, text);
    }

    public MultipartBody.Part getMultipartBody(File file) {
        String mediaType;

        try {mediaType = Files.probeContentType(file.toPath());}
        catch (IOException e) {throw new RuntimeException(e);}

        RequestBody fileBody = RequestBody.create(MediaType.parse(mediaType), file);
        return MultipartBody.Part.createFormData("file", file.getName(), fileBody);
    }

}
