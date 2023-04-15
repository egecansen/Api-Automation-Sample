package common;

import api_assured.ApiUtilities;
import gpt.api.GPT;
import gpt.utilities.DataGenerator;
import models.Pet;
import models.User;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import petstore.PetStoreServices;
import retrofit2.Call;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class PetUtilities extends ApiUtilities {

    GPT gpt = new GPT("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");

    public Pet getRandomPet() {
        DataGenerator generator = new DataGenerator(gpt);
        return generator.instantiate(Pet.class, "id");
    }
    public User getRandomUser() {
        DataGenerator generator = new DataGenerator(gpt);
        return generator.instantiate(User.class);
    }

    public List<String> categories = List.of(
            "Donkey",
            "Monkey",
            "Ray",
            "Gorilla",
            "Fish",
            "Dog",
            "Bull",
            "Cat"
    );

    public List<String> statuses = List.of(
            "pending",
            "sold",
            "available"
    );

    public String emailGenerator(String name, String lastname) {
        return name + lastname + "@gmail.com";
    }

    public String getRandomCategory() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, categories.size());
        return categories.get(randomNum);
    }

    public String getRandomStatus() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, statuses.size());
        return statuses.get(randomNum);
    }

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
