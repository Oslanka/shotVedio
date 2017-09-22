package com.oslanka.vediorecorderapplication;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author XLC
 * @version [1.0, 2016/2/29]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyAdapter extends BaseAdapter {

    private List<String> fileDate;
    private Context context;

    public MyAdapter(List<String> fileDate, Context context) {
        this.fileDate = fileDate;
        this.context = context;
    }

    @Override
    public int getCount() {
        return fileDate.size();
    }

    @Override
    public Object getItem(int position) {
        return fileDate.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.my_list_item, null);
            holder.VideoImage = (ImageView) convertView.findViewById(R.id.media_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(fileDate.get(position));
        holder.VideoImage.setImageBitmap(media.getFrameAtTime());
        return convertView;
    }

    class ViewHolder {
        ImageView VideoImage;
    }
}
