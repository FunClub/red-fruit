package com.taomei.dao.entities;


import java.math.BigInteger;
public class Users {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.account
     *
     * @mbggenerated
     */
    private String account;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.passwords
     *
     * @mbggenerated
     */
    private String passwords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.nickName
     *
     * @mbggenerated
     */
    private String nickname;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.born
     *
     * @mbggenerated
     */
    private String born;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.sex
     *
     * @mbggenerated
     */
    private Integer sex;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.profile_img
     *
     * @mbggenerated
     */
    private String profileImg;
    private String originalProfileImg;

    public String getOriginalProfileImg() {
        return originalProfileImg;
    }

    public void setOriginalProfileImg(String originalProfileImg) {
        this.originalProfileImg = originalProfileImg;
    }
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.city
     *
     * @mbggenerated
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column users.half-id
     *
     * @mbggenerated
     */

    private String profession;
    private String hobby;
    private String character;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.user_id
     *
     * @return the value of users.user_id
     *
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.account
     *
     * @return the value of users.account
     *
     * @mbggenerated
     */
    public String getAccount() {
        return account;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.account
     *
     * @param account the value for users.account
     *
     * @mbggenerated
     */
    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.passwords
     *
     * @return the value of users.passwords
     *
     * @mbggenerated
     */
    public String getPasswords() {
        return passwords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.passwords
     *
     * @param passwords the value for users.passwords
     *
     * @mbggenerated
     */
    public void setPasswords(String passwords) {
        this.passwords = passwords == null ? null : passwords.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.nickName
     *
     * @return the value of users.nickName
     *
     * @mbggenerated
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.nickName
     *
     * @param nickname the value for users.nickName
     *
     * @mbggenerated
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.born
     *
     * @return the value of users.born
     *
     * @mbggenerated
     */
    public String getBorn() {
        return born;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.born
     *
     * @param born the value for users.born
     *
     * @mbggenerated
     */
    public void setBorn(String born) {
        this.born = born == null ? null : born.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.sex
     *
     * @return the value of users.sex
     *
     * @mbggenerated
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.sex
     *
     * @param sex the value for users.sex
     *
     * @mbggenerated
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.profile_img
     *
     * @return the value of users.profile_img
     *
     * @mbggenerated
     */
    public String getProfileImg() {
        return profileImg;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.profile_img
     *
     * @param profileImg the value for users.profile_img
     *
     * @mbggenerated
     */
    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg == null ? null : profileImg.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column users.city
     *
     * @return the value of users.city
     *
     * @mbggenerated
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column users.city
     *
     * @param city the value for users.city
     *
     * @mbggenerated
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

}