package model;

/**
 * Singleton for {@link User} in order to keep the logged-in user stored
 */
public final class UserSession {

    private static UserSession instance;

    private User user;

    /**
     * static getInstance()-Method to get the Singleton Object
     * for a thread-safe behaviour, the method is synchronized
     */
    public synchronized static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    /**
     * initialization of the session singleton,
     * sets the user
     */
    public void init(User user) {
        this.user = user;
    }

    /**
     * Clears the Singleton
     */
    public void clear() {
        // sets the singleton and the data of it to null
        // by doing this, the gc will collect the data
        user = null;
        instance = null;
    }

    public String getFirtsname() {
        return user.getFirstName();
    }

    public long getUid() {
        return user.getUid();
    }

    public String getSurname() {
        return user.getSurname();
    }

    public int getPermissionLevel() { return user.getPermissionLevel();}
}
