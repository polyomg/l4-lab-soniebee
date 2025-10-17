package poly.edu.dao;

import org.springframework.stereotype.Repository;
import poly.edu.model.Account;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountDAO {
    private static final Map<String, Account> accounts = new HashMap<>();

    static {
        accounts.put("poly", new Account("poly", "123", "poly.jpg"));
        accounts.put("user1", new Account("user1", "456", "user1.jpg"));
    }

    public Account findByUsername(String username) {
        return accounts.get(username);
    }

    public void save(Account account) {
        accounts.put(account.getUsername(), account);
    }
}