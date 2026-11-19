
package vn.mdv.dao;

import vn.mdv.models.User;

public interface UserDAO {

    User get(String userName);

    boolean register(User user);
    
    boolean activate(int userId);
    
    User getById(int userId);
    
    User getByEmail(String email);
    
    boolean updatePassword(int userId, String hashedPassword);

}
