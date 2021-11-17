package com.tanvircodder.taskclander.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.vipulasri.timelineview.TimelineView;
import com.tanvircodder.taskclander.R;
import com.tanvircodder.taskclander.model.Event;
import com.tanvircodder.taskclander.model.User;

import org.w3c.dom.Text;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewModel>{
    private Context context;
    private List<Event> mData;

    public EventAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_list_item,parent,false);
        return new ViewModel(view,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewModel holder, int position) {
        Event event = mData.get(position);

        holder.mEventView.setText(event.getmName());
        holder.mPlaceView.setText(event.getmLocation());
    }

    @Override
    public int getItemCount() {
        if (mData == null)return 0;
        return mData.size();
    }
    public  void swapData(List<Event> data){
        this.mData = data;
        if (data != null){
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    class ViewModel extends RecyclerView.ViewHolder{
        private TextView mEventView;
        private TextView mPlaceView;
        private TimelineView mTimelineview;
        public ViewModel(@NonNull View itemView,int viewType) {
            super(itemView);
            mEventView = (TextView) itemView.findViewById(R.id.event_list_view);
            mPlaceView = (TextView) itemView.findViewById(R.id.place_list_view);
            mTimelineview = (TimelineView) itemView.findViewById(R.id.timeline);
            mTimelineview.initLine(viewType);
        }
    }
}
