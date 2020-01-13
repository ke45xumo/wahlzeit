/*
 * Copyright (c) 2006-2009 by Dirk Riehle, http://dirkriehle.com
 *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

import com.google.api.client.util.ArrayMap;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.images.Image;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Parent;
import org.wahlzeit.patterns.DesignPattern;
import org.wahlzeit.patterns.PatternInstance;
import org.wahlzeit.services.DataObject;
import org.wahlzeit.services.EmailAddress;
import org.wahlzeit.services.Language;
import org.wahlzeit.services.ObjectManager;

import java.util.Map;

/**
 * A photo represents a user-provided (uploaded) photo.
 */
@PatternInstance(
		patternName = DesignPattern.ABSTRACT_FACTORY,
		participants = {"Product","Abstract Product"}

)
@PatternInstance(
		patternName = DesignPattern.PROXY,
		participants = {"Client"}

)
@Entity
public class Photo extends DataObject {

/**
 *
 */
public static final String IMAGE = "image";
public static final String THUMB = "thumb";
public static final String LINK = "link";
public static final String PRAISE = "praise";
public static final String NO_VOTES = "noVotes";
public static final String CAPTION = "caption";
public static final String DESCRIPTION = "description";
public static final String KEYWORDS = "keywords";

public static final String TAGS = "tags";
public static final String OWNER_ID = "ownerId";

public static final String STATUS = "status";
public static final String IS_INVISIBLE = "isInvisible";
public static final String UPLOADED_ON = "uploadedOn";

/**
 *
 */
public static final int MAX_PHOTO_WIDTH = 420;
public static final int MAX_PHOTO_HEIGHT = 600;
public static final int MAX_THUMB_PHOTO_WIDTH = 105;
public static final int MAX_THUMB_PHOTO_HEIGHT = 150;

protected PhotoId id = null;
protected Location location;
/**
 *
 */
protected String ownerId;

/**
 * Each photo can be viewed in different sizes (XS, S, M, L, XL)
 * Images are pre-computed in these sizes to optimize bandwidth when requested.
 */
@Ignore
transient protected Map<PhotoSize, Image> images = new ArrayMap<PhotoSize, Image>();

/**
 *
 */
protected boolean ownerNotifyAboutPraise = false;
protected EmailAddress ownerEmailAddress = EmailAddress.EMPTY;
protected Language ownerLanguage = Language.ENGLISH;

/**
 *
 */
protected int width;
protected int height;
protected PhotoSize maxPhotoSize = PhotoSize.MEDIUM; // derived

/**
 *
 */
protected Tags tags = Tags.EMPTY_TAGS;

/**
 *
 */
protected PhotoStatus status = PhotoStatus.VISIBLE;

/**
 *
 */
protected int praiseSum = 10;
protected int noVotes = 1;
protected int noVotesAtLastNotification = 1;

/**
 *
 */
protected long creationTime = System.currentTimeMillis();

/**
 * The default type is jpg
 */
protected String ending = "jpg";

/**
 *
 */
//TODO: change it to a single long
@Id
	Long idLong;
@Parent
	Key parent = ObjectManager.applicationRootKey;

/**
 *
 */
public Photo() {
		id = PhotoId.getNextId();
		incWriteCount();
		}

/**
 * @methodtype constructor
 */
public Photo(PhotoId myId) {
		if (myId == null){
			throw new IllegalArgumentException("myID may not be null");
		}
		id = myId;

		incWriteCount();
		}

public Photo(PhotoId myId, Location photoLocation){
		if (myId == null){
			throw new IllegalArgumentException("myId may not be null");
		}
		if(photoLocation == null){
			throw new IllegalArgumentException("photoLocation may not be null");
		}

		id = myId;
		location = photoLocation;

		incWriteCount();
		}

/**
 * @methodtype get
 */
public Image getImage(PhotoSize photoSize) {
		if (photoSize == null){
			throw new IllegalArgumentException("photosize may not be null");
		}
		return images.get(photoSize);
		}

/**
 * @methodtype set
 */
public void setImage(PhotoSize photoSize, Image image) {
	if (photoSize == null){
		throw new IllegalArgumentException("photosize may not be null");
	}

	if (image == null){
		throw new IllegalArgumentException("image may not be null");
	}
	this.images.put(photoSize, image);
}

/**
 * @methodtype get
 */
public String getIdAsString() {
		return id.asString();
		}

/**
 * @methodtype get
 */
public PhotoId getId() {
		return id;
		}

/**
 * @methodtype get
 */
public String getOwnerId() {
		return ownerId;
		}

/**
 * @methodtype set
 */
public void setOwnerId(String newName) {
	if (newName == null){
		throw new IllegalArgumentException("newName may not be null");
	}
		ownerId = newName;
		incWriteCount();
		}

/**
 * @methodtype get
 */
public String getSummary(ModelConfig cfg) {
	if (cfg == null){
		throw new IllegalArgumentException("Modelconfig cfg may not be null");
	}
		return cfg.asPhotoSummary(ownerId);
		}

/**
 * @methodtype get
 */
public String getCaption(ModelConfig cfg) {
	if (cfg == null){
		throw new IllegalArgumentException("Modelconfig cfg may not be null");
	}
	String ownerName = UserManager.getInstance().getUserById(ownerId).getNickName();
	return cfg.asPhotoCaption(ownerName);
	}

/**
 * @methodtype get
 */
public boolean getOwnerNotifyAboutPraise() {
		return ownerNotifyAboutPraise;
		}

/**
 * @methodtype set
 */
public void setOwnerNotifyAboutPraise(boolean newNotifyAboutPraise) {
		ownerNotifyAboutPraise = newNotifyAboutPraise;
		incWriteCount();
		}

/**
 *
 */
public Language getOwnerLanguage() {
		return ownerLanguage;
		}

/**
 *
 */
public void setOwnerLanguage(Language newLanguage) {
	if(newLanguage == null){
		throw new IllegalArgumentException("Language language may not be null");
	}
		ownerLanguage = newLanguage;
		incWriteCount();
		}

/**
 * @methodtype boolean-query
 */
public boolean hasSameOwner(Photo photo) {
	if(photo == null){
		throw new IllegalArgumentException("Photo photo may not be null");
	}
		return photo.getOwnerEmailAddress().equals(ownerEmailAddress);
		}

/**
 * @methodtype get
 */
public EmailAddress getOwnerEmailAddress() {
		return ownerEmailAddress;
		}

/**
 * @methodtype set
 */
public void setOwnerEmailAddress(EmailAddress newEmailAddress) {
	if(newEmailAddress == null){
		throw new IllegalArgumentException("EmailAddress newEmailAddress may not be null");
	}
		ownerEmailAddress = newEmailAddress;
		incWriteCount();
		}

/**
 * @methodtype get
 */
public int getWidth() {
		return width;
		}

/**
 * @methodtype get
 */
public int getHeight() {
		return height;
		}

/**
 * @methodtype get
 */
public int getThumbWidth() {
		return isWiderThanHigher() ? MAX_THUMB_PHOTO_WIDTH : (width * MAX_THUMB_PHOTO_HEIGHT / height);
		}

/**
 * @methodtype boolean-query
 */
public boolean isWiderThanHigher() {
		return (height * MAX_PHOTO_WIDTH) < (width * MAX_PHOTO_HEIGHT);
		}

/**
 * @methodtype get
 */
public int getThumbHeight() {
		return isWiderThanHigher() ? (height * MAX_THUMB_PHOTO_WIDTH / width) : MAX_THUMB_PHOTO_HEIGHT;
		}

/**
 * @methodtype set
 */
public void setWidthAndHeight(int newWidth, int newHeight) {
	if (newWidth <= 0){
		throw new IllegalArgumentException("newWidth may not be <= 0.");
	}

	if (newHeight <= 0){
		throw new IllegalArgumentException("newHeight may not be <= 0.");
	}
	width = newWidth;
	height = newHeight;

	maxPhotoSize = PhotoSize.getFromWidthHeight(width, height);

	incWriteCount();
	}

/**
 * Can this photo satisfy provided photo size?
 *
 * @methodtype boolean-query
 */
public boolean hasPhotoSize(PhotoSize size) {
		if (size == null) {
			throw new IllegalArgumentException("Photosize size may not be null");
		}
		return maxPhotoSize.asInt() >= size.asInt();
		}

/**
 * @methodtype get
 */
public PhotoSize getMaxPhotoSize() {
		return maxPhotoSize;
		}

/**
 * @methodtype get
 */
public String getPraiseAsString(ModelConfig cfg) {
	if(cfg == null){
		throw new IllegalArgumentException("Modelconfig cfg may not be null");
	}
		return cfg.asPraiseString(getPraise());
		}

/**
 * @methodtype get
 */
public double getPraise() {
		return (double) praiseSum / noVotes;
		}

/**
 *
 */
public void addToPraise(int value) {
		praiseSum += value;
		noVotes += 1;
		incWriteCount();
		}

/**
 * @methodtype boolean-query
 */
public boolean isVisible() {
		return status.isDisplayable();
		}

/**
 * @methodtype get
 */
public PhotoStatus getStatus() {
		return status;
		}

/**
 * @methodtype set
 */
public void setStatus(PhotoStatus newStatus) {
	if (newStatus == null){
		throw new IllegalArgumentException("Photostatus newStatus may not be null");
	}
		status = newStatus;
		incWriteCount();
		}

	/**
	 * @methodtype boolean-query
	 */
	public boolean hasTag(String tag) {
		if (tag == null){
			throw new IllegalArgumentException("String tag may not be null");
		}
		return tags.hasTag(tag);
	}

	/**
	 * @methodtype get
	 */
	public Tags getTags() {
		return tags;
	}

	/**
	 * @methodtype set
	 */
	public void setTags(Tags newTags) {
		if (newTags == null){
			throw new IllegalArgumentException("Tags newTags may not be null");
		}
		tags = newTags;
		incWriteCount();
	}

	/**
	 * @methodtype get
	 */
	public long getCreationTime() {
		return creationTime;
	}


	public String getEnding() {
		return ending;
	}

	public void setEnding(String ending) {
		if(ending == null){
			throw new IllegalArgumentException("String ending may not be null");
		}
		this.ending = ending;
	}

	/**
	 * @methodtype boolean query
	 */
	public boolean hasNewPraise() {
		return noVotes > noVotesAtLastNotification;
	}

	/**
	 * @methodtype set
	 */
	public void setNoNewPraise() {
		noVotesAtLastNotification = noVotes;
		incWriteCount();
	}
}
