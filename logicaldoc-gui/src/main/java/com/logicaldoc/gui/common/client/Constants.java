package com.logicaldoc.gui.common.client;

import java.util.Arrays;
import java.util.List;

public final class Constants {

	private Constants() {
		// Nothing to do
	}

	// The currenly logged user name
	public static final String AUTH_USERNAME = "authUser";

	// The currenly logged user identifier
	public static final String AUTH_USERID = "authUserId";

	// The currenly logged user password
	public static final String AUTH_PASSWORD = "authPassword";

	// The current user session
	public static final String USER_SESSION = "UserSession";

	// The language of the currently logged user
	public static final String LANGUAGE = "language";

	// Sessions the context key of the sessions fieldsMap
	public static final String SESSIONS = "sessions";

	public static final long DOCUMENTS_FOLDERID = 5L;

	public static final String WORKSPACE_DEFAULTNAME = "Default";

	public static final long TENANT_DEFAULTID = 1L;

	public static final String TENANT_DEFAULTNAME = "default";

	public static final String TENANT_DEFAULTDISPLAYNAME = "Default";

	public static final String TIME_MINUTE = "minute";

	public static final String TIME_HOUR = "hour";

	public static final String TIME_BUSINESS_HOUR = "businesshour";

	public static final String TIME_DAY = "day";

	public static final String TIME_BUSINESS_DAY = "businessday";

	public static final String TIME_WEEK = "week";

	public static final String TIME_BUSINESS_WEEK = "businessweek";

	public static final int DOC_UNLOCKED = 0;

	public static final int DOC_CHECKED_OUT = 1;

	public static final int DOC_LOCKED = 2;

	public static final int DOC_ARCHIVED = 3;

	public static final int NATURE_FORM = 2;

	public static final String SMTP_SECURITY_NONE = "0";

	public static final String SMTP_SECURITY_TLS_IF_AVAILABLE = "1";

	public static final String SMTP_SECURITY_TLS = "2";

	public static final String SMTP_SECURITY_SSL = "3";

	public static final String KEY_ENTER = "enter";

	public static final String GROUP_ADMIN = "admin";

	public static final String GROUP_PUBLISHER = "publisher";

	public static final String GROUP_GUEST = "guest";

	public static final String EVENT_LOCKED = "event.locked";

	public static final String EVENT_CHECKEDOUT = "event.checkedout";

	public static final String EVENT_DOWNLOADED = "event.downloaded";

	public static final String EVENT_CHANGED = "event.changed";

	public static final String EVENT_CHECKEDIN = "event.checkedin";

	/**
	 * The document must be indexed, both metadata and content
	 */
	public static final int INDEX_TO_INDEX = 0;

	/**
	 * The document must be indexed, just the metadata
	 */
	public static final int INDEX_TO_INDEX_METADATA = 3;

	/**
	 * The document has already been indexed
	 */
	public static final int INDEX_INDEXED = 1;

	// The document is un-indexable
	public static final int INDEX_SKIP = 2;

	public static final String LOCALE = "locale";

	public static final String TENANT = "tenant";

	public static final String ANONYMOUS = "anonymous";

	public static final String BLANK_PLACEHOLDER = "___";

	public static final String SID = "sid";

	public static final String KEY = "key";

	public static final String SKIN = "skin";

	public static final String FOLDER_PATH = "folderPath";

	public static final String FOLDER_ID = "folderId";

	public static final String DOC_ID = "docId";

	private static final List<String> AUDIT_DEFAULT_EVENTS = Arrays.asList("event.stored", EVENT_CHANGED,
			EVENT_CHECKEDIN);

	public static final String FULLTEXT_FIELD_TITLE = "title";

	public static final String FULLTEXT_FIELD_FILENAME = "fileName";

	public static final String FULLTEXT_FIELD_TAGS = "tags";

	public static final String FULLTEXT_FIELD_CONTENT = "content";

	public static final String FULLTEXT_FIELD_CUSTOMID = "customId";

	public static final String FULLTEXT_FIELD_NOTES = "notes";

	public static final String FULLTEXT_FIELD_COMMENT = "comment";

	public static final String TWOFA_GOOGLE_AUTHENTICATOR = "googleauthenticator";

	public static final String TWOFA_YUBIKEY = "yubikey";

	public static final String TWOFA_EMAIL_AUTHENTICATOR = "emailauthenticator";

	public static final String TWOFA_DUO = "duo";

	private static final List<String> FULLTEXT_DEFAULT_FIELDS = Arrays.asList(FULLTEXT_FIELD_FILENAME,
			FULLTEXT_FIELD_CONTENT, FULLTEXT_FIELD_TAGS);

	public static List<String> getAuditDefaultEvents() {
		return AUDIT_DEFAULT_EVENTS;
	}

	public static List<String> getFulltextDefaultFields() {
		return FULLTEXT_DEFAULT_FIELDS;
	}
}