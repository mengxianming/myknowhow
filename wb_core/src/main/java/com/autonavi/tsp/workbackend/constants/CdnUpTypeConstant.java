package com.autonavi.tsp.workbackend.constants;

/**
 * Created by wei on 2014/6/13.
 */
public enum CdnUpTypeConstant {
    //
    UP("未上传", "0"),
    NO("已上传", "1");

    private String name;
    private String value;

    CdnUpTypeConstant(String name, String value){
        this.name = name;
        this.value = value;
    }

    public static CdnUpTypeConstant get(String name){
        for(CdnUpTypeConstant constant : CdnUpTypeConstant.values()){
            if(constant.getName().equals(name)){
                return constant;
            }
        }
        return null;
    }

    public static CdnUpTypeConstant getByValue(String value){
        for(CdnUpTypeConstant constant : CdnUpTypeConstant.values()){
            if(constant.getValue().equals(value)){
                return constant;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
