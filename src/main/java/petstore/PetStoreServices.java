package petstore;

import models.SimpleResponse;
import models.Pet;
import models.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

import static petstore.PetStoreAPI.*;

public interface PetStoreServices {
    String BASE_URL = PetStoreAPI.BASE_URL;

    @GET(PET_SUFFIX + PET_ID)
    Call<Pet> getPetById(@Path("petId") long petId);

    @POST(PET_SUFFIX)
    Call<Pet> postPet(@Body Pet pet);

    @PUT(PET_SUFFIX)
    Call<Pet> putPet(@Body Pet pet);

    @GET(PET_SUFFIX + PET_STATUS)
    Call<List<Pet>> findByStatus(@Query("status") String petStatus);

    @DELETE(PET_SUFFIX + PET_ID)
    Call<SimpleResponse> deletePet(@Path("petId") long petId);

    @FormUrlEncoded
    @POST(PET_SUFFIX + PET_ID)
    Call<SimpleResponse> updatePet(@Path("petId") long petId, @Field("name") String petName, @Field("status") String petStatus);
    @Multipart
    @POST(PET_SUFFIX + PET_ID + PET_IMG)
    Call<Object> uploadPetPhoto(
            @Path("petId") long petId,
            @Part("additionalMetadata") RequestBody metaData,
            @Part MultipartBody.Part image
    );

    @POST(USER_SUFFIX)
    Call<SimpleResponse> createUser(@Body User user);

    @GET(USER_SUFFIX + USER_LOGIN)
    Call<SimpleResponse> login(@Query("username") String username, @Query("password") String password);
}