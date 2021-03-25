package com.example.mngyyu;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class SlideAdapter extends RecyclerView.Adapter {

    private Context mContext;

    public SlideAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        if(viewType == 1){
            View studentView =
                    inflater.inflate(R.layout.item, parent, false);

            ViewHolder viewHolder = new ViewHolder(studentView);
            return viewHolder;

        } else{
            View studentView =
                    inflater.inflate(R.layout.item_count, parent, false);

            ViewHolder viewHolder = new ViewHolder(studentView);
            return viewHolder;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Date ngayYeu = new Date(2019,7,8);
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.YEAR, 2019);
        instance.set(Calendar.MONTH, 6);
        instance.set(Calendar.DAY_OF_MONTH, 8);
        instance.set(Calendar.MINUTE, 0);
        instance.set(Calendar.HOUR_OF_DAY, 0);
        instance.set(Calendar.SECOND, 0);

        ngayYeu = instance.getTime();



        long diff = (new Date().getTime()) - (ngayYeu.getTime());
        Log.e("t", ngayYeu + " " + diff);
        int day = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if(position == 0){

            TextView view = holder.itemView.findViewById(R.id.day);


            view.setText((day+1) + "");
        } else{
            Duration duration = Duration.ofMillis(diff);
            Log.e("tt ", ""+diff);
            int year = (int) (diff/31104000000l);
            int moth = (int) ((int) ((diff%31104000000l))/ 2592000000l);
            TextView view = holder.itemView.findViewById(R.id.day_count);
//            view.setText(String.format("%d Năm, %d Tháng, %d Ngày, %d Giờ, %d Phút, %d Giây",
//                    year, days, duration.toDays(),
//                    duration.toHours(), duration.toMinutes(), duration.toMillis()));
            view.setText(secondsToString(diff/ 1000));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return 1;
        return 2;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
        }
    }

    String secondsToString(long seconds) {
        int numyears = (int) Math.floor(seconds / 31536000);
        int numMonth = (int) Math.floor((seconds % 31536000) / 2628000);
        int numdays = (int) Math.floor(((seconds % 31536000) % 2628000) / 86400);
        int numhours = (int) Math.floor((((seconds % 31536000) % 2628000) % 86400) / 3600);
        int numminutes = (int) Math.floor(((((seconds % 31536000) % 2628000) % 86400) % 3600) / 60);
        int numseconds = (int) (((((seconds % 31536000) % 2628000) % 86400) % 3600) % 60);
        return numyears + " Năm " + numMonth + " Tháng " +  numdays + " Ngày " + numhours + " Giờ " + numminutes + " Phút " + numseconds + " Giây";
    }
}
