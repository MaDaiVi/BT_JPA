
package vn.mdv.dao;

import vn.mdv.models.User;

public interface UserDAO {

    User get(String userName);

    boolean register(User user);

}
