package kr.dev_mook.user_manager.model;

import java.time.LocalDate;

/**
 * User Model
 * @author Inmook, Jeong
 * @since 2022. 04. 03
 *
 */
public class User {
	
	private Long id;					// 아이디(고유 시퀀스)
	private String userId;				// 사용자 아이디
	private String password;			// 비밀번호
	private boolean isTempPw;			// 임시 비밀번호 여부
	private String name;				// 이름
	private String phoneNumber;			// 전화번호
	private String email;				// 이메일 주소
	private String birth;				// 생년월일
	private String gender;				// 성별
	private String address;				// 주소
	private String nation;				// 국가
	private String description;			// 설명
	private String status;				// 상태
	private Long profileImageId;		// 프로필 이미지 아이디
	private LocalDate createDate;		// 생성일자
	private LocalDate modifiedDate;		// 수정일자
	private LocalDate lastLoginDate;	// 마지막 로그인 날짜
	private String lastLoginIp;			// 마지막 로그인 IP
	private Long failedLoginAttempts;	// 실패한 로그인 횟수
	private boolean lockout;			// 잠금 상태
	private LocalDate lockoutDate;		// 잠긴 날짜
	private boolean emailVerified;		// 이메일 검증 여부
	private boolean siteTermsOfUse;		// 사이트 이용약관 동의 여부
	private boolean userInfoTermsOfUse;	// 개인정보 제공 약관 동의 여부
	private boolean eventTermsOfUse;	// 이벤트 약관 동의 여부
	
	private User() {}
	
	public User(Long id) {
		this.id = id;
	}
	
	public User(Long id, String userId, String password, boolean isTempPw, String name, String phoneNumber,
			String email, String birth, String gender, String address, String nation, String description, String status,
			Long profileImageId, LocalDate createDate, LocalDate modifiedDate, LocalDate lastLoginDate,
			String lastLoginIp, Long failedLoginAttempts, boolean lockout, LocalDate lockoutDate, boolean emailVerified,
			boolean siteTermsOfUse, boolean userInfoTermsOfUse, boolean eventTermsOfUse) {
		super();
		this.id = id;
		this.userId = userId;
		this.password = password;
		this.isTempPw = isTempPw;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.birth = birth;
		this.gender = gender;
		this.address = address;
		this.nation = nation;
		this.description = description;
		this.status = status;
		this.profileImageId = profileImageId;
		this.createDate = createDate;
		this.modifiedDate = modifiedDate;
		this.lastLoginDate = lastLoginDate;
		this.lastLoginIp = lastLoginIp;
		this.failedLoginAttempts = failedLoginAttempts;
		this.lockout = lockout;
		this.lockoutDate = lockoutDate;
		this.emailVerified = emailVerified;
		this.siteTermsOfUse = siteTermsOfUse;
		this.userInfoTermsOfUse = userInfoTermsOfUse;
		this.eventTermsOfUse = eventTermsOfUse;
	}
	
	

	// Setter.
	
	public Long getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public boolean isTempPw() {
		return isTempPw;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public String getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public String getAddress() {
		return address;
	}

	public String getNation() {
		return nation;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public Long getProfileImageId() {
		return profileImageId;
	}

	public LocalDate getCreateDate() {
		return createDate;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public LocalDate getLastLoginDate() {
		return lastLoginDate;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public Long getFailedLoginAttempts() {
		return failedLoginAttempts;
	}

	public boolean isLockout() {
		return lockout;
	}

	public LocalDate getLockoutDate() {
		return lockoutDate;
	}

	public boolean isEmailVerified() {
		return emailVerified;
	}

	public boolean isSiteTermsOfUse() {
		return siteTermsOfUse;
	}

	public boolean isUserInfoTermsOfUse() {
		return userInfoTermsOfUse;
	}

	public boolean isEventTermsOfUse() {
		return eventTermsOfUse;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setIsTempPw(boolean isTempPw) {
		this.isTempPw = isTempPw;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setBirth(String birth) {
		this.birth = birth;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setNation(String nation) {
		this.nation = nation;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setProfileImageId(Long profileImageId) {
		this.profileImageId = profileImageId;
	}
	
	public void setCreateDate(LocalDate createDate) {
		this.createDate = createDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setLastLoginDate(LocalDate lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public void setFailedLoginAttempts(Long failedLoginAttempts) {
		this.failedLoginAttempts = failedLoginAttempts;
	}

	public void setLockout(boolean lockout) {
		this.lockout = lockout;
	}

	public void setLockoutDate(LocalDate lockoutDate) {
		this.lockoutDate = lockoutDate;
	}

	public void setEmailVerified(boolean emailVerified) {
		this.emailVerified = emailVerified;
	}

	public void setSiteTermsOfUse(boolean siteTermsOfUse) {
		this.siteTermsOfUse = siteTermsOfUse;
	}

	public void setUserInfoTermsOfUse(boolean userInfoTermsOfUse) {
		this.userInfoTermsOfUse = userInfoTermsOfUse;
	}

	public void setEventTermsOfUse(boolean eventTermsOfUse) {
		this.eventTermsOfUse = eventTermsOfUse;
	}
	
}
