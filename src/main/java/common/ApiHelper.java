package common;

import api_assured.ApiUtilities;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public abstract class ApiHelper extends ApiUtilities {
    //public Logger logger = LoggerFactory.getLogger(this.getClass());
    public RequestBody createPartFromString(String text) {
        return RequestBody.create(MultipartBody.FORM, text);
    }

    public MultipartBody.Part getMultipartBody(File file) {
        String mediaType;

        try {mediaType = Files.probeContentType(file.toPath());}
        catch (IOException e) {throw new RuntimeException(e);}

        RequestBody fileBody = RequestBody.create(file, MediaType.parse(mediaType));
        return MultipartBody.Part.createFormData("file", file.getName(), fileBody);
    }
}
