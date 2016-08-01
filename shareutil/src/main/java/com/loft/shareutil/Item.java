package com.loft.shareutil;

public class Item {
    private String name;
    private int resid;
    /**
     * 分享目标类型
     *
     * @see com.loft.shareutil.Constants.Target
     */
    private int target;

    public Item(String name, int resid, int target) {
        this.name = name;
        this.resid = resid;
        this.target = target;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getResid() {
        return resid;
    }

    public void setResid(int resid) {
        this.resid = resid;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }
}
