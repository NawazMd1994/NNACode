package com.nissanusa.helios.ownerservice.vo;

/**
 * 
 * @author x178099
 * @use this class will hold the saveUser wrapper for update account- Save User
 *      endpoint
 *
 */
public class SaveUserWrapper {

    public SavePersonVO saveUser;

    /**
     * @return the saveUser
     */
    public SavePersonVO getSaveUser() {
        return saveUser;
    }

    /**
     * @param saveUser
     *            the saveUser to set
     */
    public void setSaveUser(SavePersonVO saveUser) {
        this.saveUser = saveUser;
    }

}
