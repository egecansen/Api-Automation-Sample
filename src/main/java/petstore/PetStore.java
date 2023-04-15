package petstore;

import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import models.SimpleResponse;
import models.Pet;
import models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

import java.util.List;

public class PetStore extends ApiUtilities {
    PetStoreServices petStore = new ServiceGenerator().generate(PetStoreServices.class);

    public Response<Pet> getPet(long petId) {
        log.new Info("Getting pet by id from: " + petId);
        Call<Pet> petCall = petStore.getPetById(petId);
        return getResponse(petCall, true, true);
    }

    public Response<Pet> postPet(Pet pet) {
        log.new Info("Posting a new pet");
        Call<Pet> newPetCall = petStore.postPet(pet);
        return getResponse(newPetCall, true, true);
    }

    public Pet putPet(Pet pet) {
        log.new Info("Updating a pet named: " + pet.getName());
        Call<Pet> putPetCall = petStore.putPet(pet);
        return perform(putPetCall, true, true);
    }

    public List<Pet> findPetByStatus(String petStatus) {
        log.new Info("Getting pet by status from: " + petStatus);
        Call<List<Pet>> petCall = petStore.findByStatus(petStatus);
        return perform(petCall, true, true);
    }
    public Response<SimpleResponse> deletePetById(long petId) {
        log.new Info("Deleting the pet from id: " + petId);
        Call<SimpleResponse> petCall = petStore.deletePet(petId);
        return getResponse(petCall, true, true);
    }
    public SimpleResponse updatePetById(long petId, String petName, String petStatus) {
        log.new Info("Updating an existing pet");
        Call<SimpleResponse> petCall = petStore.updatePet(petId, petName, petStatus);
        return perform(petCall, true, true);
    }
    public Object uploadPetPhoto(long petId, RequestBody metaData, MultipartBody.Part filePath) {
        log.new Info("Uploading a photo");
        Call<Object> petCall = petStore.uploadPetPhoto(petId, metaData, filePath);
        return perform(petCall, true, true);
    }
    public SimpleResponse createUser(User user) {
        log.new Info("Creating a new user");
        Call<SimpleResponse> userCall = petStore.createUser(user);
        return perform(userCall, true, true);
    }
    public SimpleResponse login(String username, String password) {
        log.new Info("Loging in");
        Call<SimpleResponse> userCall = petStore.login(username, password);
        return perform(userCall, true, true);
    }
}
