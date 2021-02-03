package adPortal.storage;

import adPortal.model.Category;
import adPortal.model.User;
import adPortal.model.Advertisment;


public interface AdStorage {

    void add(Advertisment ad);

    void printMyAds(User user);

    void printAllAds();

    void printByCategory(Category category);

    void deleteMyAds(User user);

    void deleteAdByTitle(String title, User currentUser);
}
