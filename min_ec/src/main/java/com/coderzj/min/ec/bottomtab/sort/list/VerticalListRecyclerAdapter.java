package com.coderzj.min.ec.bottomtab.sort.list;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.coderzj.min.delegates.MinDelegate;
import com.coderzj.min.ec.R;
import com.coderzj.min.ec.bottomtab.sort.SortDelegate;
import com.coderzj.min.ec.bottomtab.sort.content.VerticalContentDelegate;
import com.coderzj.min.ui.recycle.ItemTypes;
import com.coderzj.min.ui.recycle.MultipleHolder;
import com.coderzj.min.ui.recycle.MultipleItemEntity;
import com.coderzj.min.ui.recycle.MultipleItemEntityTypes;
import com.coderzj.min.ui.recycle.MultipleRecyclerAdapter;

import java.util.List;

/**
 * Created by 邹俊 on 2018/4/1.
 */
public class VerticalListRecyclerAdapter extends MultipleRecyclerAdapter {
    private SortDelegate mDelegate = null;
    private int mPrePosition = 0;


    protected VerticalListRecyclerAdapter(List<MultipleItemEntity> data, SortDelegate delegate) {
        super(data);
        mDelegate = delegate;
        addItemType(ItemTypes.VERTICAL_LIST, R.layout.item_vertical_list);
    }

    @Override
    protected void convert(final MultipleHolder holder, final MultipleItemEntity entity) {
        super.convert(holder, entity);
        switch (holder.getItemViewType()) {
            case ItemTypes.VERTICAL_LIST:
                final String name = entity.getField(MultipleItemEntityTypes.NAME);
                final boolean isClick = entity.getField(MultipleItemEntityTypes.TAG);
                final AppCompatTextView tv = holder.getView(R.id.tv_vertical_list_item);
                final View line = holder.getView(R.id.right_line);
                final View itemView = holder.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = holder.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            getData().get(mPrePosition).setField(MultipleItemEntityTypes.TAG, false);
                            notifyItemChanged(mPrePosition);

                            entity.setField(MultipleItemEntityTypes.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                            final int contentId = getData().get(currentPosition).getField(MultipleItemEntityTypes.ID);
                            showContent(contentId);
                        }


                    }
                });
                holder.setText(R.id.tv_vertical_list_item, name);
                if (!isClick) {
                    line.setVisibility(View.INVISIBLE);
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.wet_chat));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.blue_like));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                break;
            default:
                break;

        }
    }

    private void showContent(int contentId) {
        final VerticalContentDelegate contentDelegate
                = VerticalContentDelegate.newInstance(contentId);

    }

    private void switchContent(VerticalContentDelegate delegate) {
        final MinDelegate contentDelegate = mDelegate.findChildFragment(VerticalContentDelegate.class);
        if (contentDelegate != null) {
            contentDelegate.replaceFragment(delegate, false);
        }
    }
}
