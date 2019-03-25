package com.digital.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.digital.models.ProfilePicture;

@Repository
@Transactional
public class ProfilePictureDaoImpl implements ProfilePictureDao {
	 @Autowired
		private SessionFactory sessionFactory;
		
		public void saveOrUpdateProfilePicture(ProfilePicture profilePicture) {
			Session s=sessionFactory.getCurrentSession();
			s.saveOrUpdate(profilePicture);
		}

		public ProfilePicture getProfilePicture(String email) {
			Session s=sessionFactory.getCurrentSession();
			ProfilePicture profilePicture=(ProfilePicture)s.get(ProfilePicture.class, email);
			return profilePicture;
		}

}
