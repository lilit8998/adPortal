package adPortal.storage;

import adPortal.model.User;

public interface UserStorage {

    void add(User user);

    User getByPhone(String phone);

}
