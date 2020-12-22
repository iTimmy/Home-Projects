package com.blog.blog.memory;

import com.blog.blog.models.*;

public interface ProfileDao {
    Profile getProfileByID(int profileID);
    void updateProfile(Profile profile);
}
