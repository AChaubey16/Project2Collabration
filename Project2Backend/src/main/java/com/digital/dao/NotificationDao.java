package com.digital.dao;

import java.util.List;

import com.digital.models.Notification;

public interface NotificationDao {
void addNotification(Notification notification);
	
	List<Notification> getAllNotificationsNotViewed(String email);
	
	Notification getNotification(int notificationId);
	
	void updateNotificactionViewedStatus(int notificationId);
}
