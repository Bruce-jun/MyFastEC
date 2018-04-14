package com.coderzj.min.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by 邹俊 on 2018/3/26.
 * <p>
 * 将tab与界面关联起来
 */

public class ItemBuilder {
    private final LinkedHashMap<ButtomTabBean, ButtomItemdelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder() {
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(ButtomTabBean bean, ButtomItemdelegate itemdelegate) {
        ITEMS.put(bean, itemdelegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<ButtomTabBean, ButtomItemdelegate> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<ButtomTabBean, ButtomItemdelegate> build() {
        return ITEMS;
    }
}
