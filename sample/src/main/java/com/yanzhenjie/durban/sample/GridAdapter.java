/*
 * Copyright © Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.durban.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yanzhenjie.album.Album;

import java.util.List;

/**
 * <p>Image adapter.</p>
 * Created by Yan Zhenjie on 2016/10/30.
 */
public class GridAdapter extends RecyclerView.Adapter<GridAdapter.ImageViewHolder> {

    private LayoutInflater mInflater;
    private int itemSize;
    private List<String> mImagePathList;

    public GridAdapter(Context context, int itemSize) {
        this.mInflater = LayoutInflater.from(context);
        this.itemSize = itemSize;
    }

    public void notifyDataSetChanged(List<String> imagePathList) {
        this.mImagePathList = imagePathList;
        super.notifyDataSetChanged();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(itemSize, mInflater.inflate(R.layout.item_main_image, parent, false));
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        holder.loadImage(mImagePathList.get(holder.getAdapterPosition()));
    }

    @Override
    public int getItemCount() {
        return mImagePathList == null ? 0 : mImagePathList.size();
    }

    static class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private int itemSize;
        private ImageView mIvIcon;

        ImageViewHolder(int itemSize, View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.itemSize = itemSize;
            itemView.getLayoutParams().height = itemSize;
            itemView.requestLayout();
            mIvIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
        }

        public void loadImage(String imagePath) {
            Album.getAlbumConfig().getImageLoader().loadImage(mIvIcon, imagePath, itemSize, itemSize);
        }

        @Override
        public void onClick(View v) {
        }
    }

}
