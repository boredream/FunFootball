package com.qysports.funfootball.entity;


import android.text.TextUtils;

import com.boredream.bdcodehelper.entity.Relation;
import com.qysports.funfootball.base.BaseEntity;
import com.qysports.funfootball.constants.CommonConstants;
import com.qysports.funfootball.utils.StringUtils;

import java.util.ArrayList;

public class Act extends BaseEntity {

    /**
     * 所属教练
     */
    private User coach;

    /**
     * 城市
     */
    private String city;

    /**
     * 标题
     */
    private String title;

    /**
     * 类型,多用个符号隔开
     */
    private String type;

    /**
     * 时间
     */
    private String date;

    /**
     * 地址
     */
    private String location;

    /**
     * 人数
     */
    private int personNum;

    /**
     * 年龄段,U4,U5等多个Ux用,隔开
     */
    private String range;

    /**
     * 装备要求
     */
    private String equip;

    /**
     * 课程说明
     */
    private String desc;

    /**
     * 课程图片,多个图片用
     */
    private String imgUrls;

    /**
     * 报名订单
     */
    private Relation signUpOrders;

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getEquip() {
        return equip;
    }

    public void setEquip(String equip) {
        this.equip = equip;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(String imgUrls) {
        this.imgUrls = imgUrls;
    }

    public Relation getSignUpOrders() {
        return signUpOrders;
    }

    public void setSignUpOrders(Relation signUpOrders) {
        this.signUpOrders = signUpOrders;
    }

    public ArrayList<SimpleImageUrl> getImageList() {
        ArrayList<SimpleImageUrl> imageList = new ArrayList<>();

        if(imgUrls != null) {
            for(String url : imgUrls.split(CommonConstants.SEPARATOR.IMAGE_UPLOAD_URLS)) {
                if(TextUtils.isEmpty(url)) {
                    continue;
                }

                SimpleImageUrl siu = new SimpleImageUrl();
                siu.setUrl(url);
                imageList.add(siu);
            }
        }

        return imageList;
    }

    public ArrayList<String> getAgeRanges() {
        return StringUtils.splitString(range, CommonConstants.SEPARATOR.RANGE_AGES);
    }

    public ArrayList<String> getTypes() {
        ArrayList<String> types = new ArrayList<>();

        if(type != null) {
            for(String t : type.split(CommonConstants.SEPARATOR.ACT_TYPES)) {
                if(TextUtils.isEmpty(t)) {
                    continue;
                }

                types.add(t);
            }
        }

        return types;
    }
}

