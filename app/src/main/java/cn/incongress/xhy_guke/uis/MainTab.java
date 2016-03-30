package cn.incongress.xhy_guke.uis;

import cn.incongress.xhy_guke.R;
import cn.incongress.xhy_guke.fragment.DynamicFragment;
import cn.incongress.xhy_guke.fragment.MeFragment;
import cn.incongress.xhy_guke.fragment.SuggestFragment;
import cn.incongress.xhy_guke.fragment.VVTalkFragment;

public enum MainTab {

    NEWS(0, R.string.fragment_v_v_talk, R.drawable.tab_icon_vvtalk, VVTalkFragment.class),

    TWEET(1, R.string.fragment_dynamic, R.drawable.tab_icon_dynamic, DynamicFragment.class),

    EXPLORE(2, R.string.fragment_suggest, R.drawable.tab_icon_suggest, SuggestFragment.class),

    ME(3, R.string.fragment_me, R.drawable.tab_icon_me, MeFragment.class);

    private int idx;
    private int resName;
    private int resIcon;
    private Class<?> clz;

    MainTab(int idx, int resName, int resIcon, Class<?> clz) {
        this.idx = idx;
        this.resName = resName;
        this.resIcon = resIcon;
        this.clz = clz;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public int getResName() {
        return resName;
    }

    public void setResName(int resName) {
        this.resName = resName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class<?> getClz() {
        return clz;
    }

    public void setClz(Class<?> clz) {
        this.clz = clz;
    }
}
