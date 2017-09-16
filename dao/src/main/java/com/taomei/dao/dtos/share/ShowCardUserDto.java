package com.taomei.dao.dtos.share;

/**
 * 显示名片上的用户信息
 */
public class ShowCardUserDto {
    private String nickname;
    private String halfNickname;
    private String profileImg;
    private String sex;
    private String character;
    private String city;
    private String profession;

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHalfNickname() {
        return halfNickname;
    }

    public void setHalfNickname(String halfNickname) {
        this.halfNickname = halfNickname;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
