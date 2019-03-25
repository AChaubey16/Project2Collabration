package com.digital.dao;

import com.digital.models.ProfilePicture;

public interface ProfilePictureDao {
	void saveOrUpdateProfilePicture(ProfilePicture profilePicture);
	ProfilePicture getProfilePicture(String email);
}
