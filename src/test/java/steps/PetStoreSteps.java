package steps;

import common.PetUtilities;
import context.ContextStore;
import gpt.api.GPT;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import models.*;
import models.SimpleResponse;
import org.junit.Assert;
import petstore.PetStore;
import retrofit2.Response;
import utils.ReflectionUtilities;
import utils.StringUtilities;

import java.io.File;
import java.util.List;
import java.util.Map;

public class PetStoreSteps {
    //Printer logger = new Printer(PetStoreSteps.class);
    PetStore petStore = new PetStore();
    PetUtilities petUtilities = new PetUtilities();

    @Given("Get pet by id: {}")
    public void getPetById(long id) {
        Pet pet = petStore.getPet(id).body();
        petStore.log.new Info("This pet is named " + petStore.strUtils.highlighted(StringUtilities.Color.BLUE, pet.getName()));
    }

    @Given("Post a new random pet named {}")
    public void postPet(String petName) {
        Pet newPet = new Pet(petName);

        String categoryName = petUtilities.getRandomCategory();
        Pet.DataModel category = new Pet.DataModel();
        category.setId(petUtilities.categories.indexOf(categoryName));
        category.setName(categoryName);
        newPet.setCategory(category);

        newPet.setStatus(petUtilities.getRandomStatus());

        Pet.DataModel tag = new Pet.DataModel();
        tag.setName("Pet");
        tag.setId(petUtilities.categories.indexOf(categoryName) + 1);
        newPet.setTags(List.of(tag));

        newPet.setPhotoUrls(List.of("myBestPic.com"));

        Response<Pet> petResponse = petStore.postPet(newPet);
        Assert.assertNotNull("date does not exit in headers", petResponse.headers().get("date"));
        petStore.log.new Success("Date is: " + petResponse.headers().get("date"));
        Assert.assertEquals("Content type do not match", "application/json", petResponse.headers().get("Content-Type"));
        petStore.log.new Success("Verified that the content type is application/json");
        Pet pet = petResponse.body();

        assert pet != null;
        ContextStore.put("contextPetId", pet.getId());

        Assert.assertEquals("Categories do not match!", newPet.getCategory().getName(), pet.getCategory().getName());
        petStore.log.new Success("Categories match!");
        Assert.assertEquals("Names do not match!", newPet.getName(), pet.getName());
        petStore.log.new Success("Names match!");
        Assert.assertNotNull("The Id is null!", pet.getId());
        petStore.log.new Success("New petResponse id is " + pet.getId());
    }

 @Given("Generate a random GPT pet")
    public void postGPTPet() {
        Pet pet = petUtilities.getRandomPet();
        Response<Pet> petResponse = petStore.postPet(pet);
        ReflectionUtilities reflectionUtilities = new ReflectionUtilities();
        reflectionUtilities.objectsMatch(pet, petResponse.body(), "id");
    }

    @Given("Randomly update the pet named: {}")
    public void putPet(String petName) {
        Pet putPet = new Pet(petName);

        String categoryName = petUtilities.getRandomCategory();
        Pet.DataModel category = new Pet.DataModel();
        category.setId(petUtilities.categories.indexOf(categoryName) + 1);
        category.setName(categoryName);
        putPet.setCategory(category);

        putPet.setStatus(petUtilities.getRandomStatus());

        Pet.DataModel tag = new Pet.DataModel();
        tag.setName("Petish");
        putPet.setTags(List.of(tag));

        putPet.setPhotoUrls(List.of("lalalale.co"));

        Pet updatedPet = petStore.putPet(putPet);

        Assert.assertEquals("Categories do not match!", putPet.getCategory().getName(), updatedPet.getCategory().getName());
        petStore.log.new Success("New pet's id is " + updatedPet.getId());

    }

    @Given("Find pet by status: {}")
    public void getPetByStatus(String petStatus) {
        List<Pet> pets = petStore.findPetByStatus(petStatus);
        for (Pet pet : pets)
            Assert.assertEquals("Unexpected status on: " + pet.getName(), pet.getStatus(), petStatus);
        petStore.log.new Info("Number of " + petStatus + " pets: " + pets.size());
    }

    @Given("Delete pet from id: {}")
    public void deletePetFromId(long petId) {
        petStore.deletePetById(petId);
    }

    @Given("Delete pet from context")
    public void deletePetFromContext() {
        long petId = Long.parseLong(ContextStore.get("contextPetId").toString());
        SimpleResponse response = petStore.deletePetById(petId).body();
        Assert.assertEquals("Invalid Id", petId, Long.parseLong(response.getMessage().toString()));
        petStore.log.new Success("Pet successfully deleted");
    }

    @Given("Update pet from id: {}, new name: {}, new status: {}")
    public void updatePet(long petId, String petName, String petStatus) {
        petStore.updatePetById(petId, petName, petStatus);
        petStore.log.new Success("Pet successfully updated");
    }

    @Given("Upload a photo for id numbered {} pet, metadata: {}, file path: {}")
    public void uploadPhoto(long petId, String metaData, String filePath) {
        petStore.uploadPetPhoto(petId, petUtilities.createPartFromString(metaData), petUtilities.getMultipartBody(new File(filePath)));
        petStore.log.new Success("Photo successfully uploaded");
    }

    @Given("Create the following users:")
    public void createUsers(DataTable table) {
        List<Map<String, String>> userList = table.asMaps();

        for (Map<String, String> userMap: userList) {
            User user = new User();
            user.setFirstname(userMap.get("First Name"));
            user.setLastname(userMap.get("Last Name"));
            user.setUsername(userMap.get("User Name"));
            user.setPassword(userMap.get("Password"));
            user.setEmail(userMap.get("Email"));
            user.setPhone(userMap.get("Phone Number"));
            user.setUserStatus(Integer.parseInt(userMap.get("userStatus")));
            petStore.createUser(user);
            petStore.log.new Success(user.getUsername() + " successfully created");
        }
    }
    @Given("Create the following user:")
    public void createUser(DataTable table) {
        User user = new User();
        Map<String, String> userMap = table.asMap();
        user.setFirstname(userMap.get("First Name"));
        user.setLastname(userMap.get("Last Name"));
        user.setUsername(userMap.get("User Name"));
        user.setPassword(userMap.get("Password"));
        user.setEmail(userMap.get("Email"));
        user.setPhone(userMap.get("Phone Number"));
        user.setUserStatus(Integer.parseInt(userMap.get("userStatus")));
        petStore.createUser(user);

        ContextStore.put("contextUser", user);
        petStore.log.new Success("User successfully created");
    }

    @Given("Generate random GPT user")
    public void createGPTUser() {
        User user = petUtilities.getRandomUser();

        ContextStore.put("contextUser", user);
        petStore.log.new Success("User successfully created");
    }

    @Given("Login with the context user")
    public void login() {
        User user = (User) ContextStore.get("contextUser");
        petStore.login(user.getUsername(), user.getPassword());
    }
}
