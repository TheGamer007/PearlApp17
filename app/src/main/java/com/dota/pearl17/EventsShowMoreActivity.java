package com.dota.pearl17;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class EventsShowMoreActivity extends AppCompatActivity {

    Typeface fontface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_show_more);

        fontface = Typeface.createFromAsset(getAssets(),"cubano_regular.otf");

        RecyclerView mRecycler = (RecyclerView) findViewById(R.id.recycler_more_categories);
        mRecycler.setLayoutManager(new GridLayoutManager(this,2));
        mRecycler.setAdapter(new MyAdapter());
    }

    public void showLess(View v){
        //ANIM: This needs a slide down animation
        startActivity(new Intent(EventsShowMoreActivity.this,EventsHomeActivity.class));
        finish();
    }

    class EventCategoryItem extends RecyclerView.ViewHolder{
        TextView title;
        ImageView icon;
        public EventCategoryItem(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.title_category);
            icon = (ImageView) v.findViewById(R.id.icon_category);

            title.setTypeface(fontface);
        }



    }

    class MyAdapter extends RecyclerView.Adapter<EventCategoryItem>{

        String titles[] = new String[]{
                "DANCE",
                "MUSIC",
                "DRAMA",
                "PHOTOG",
                "SHADES",
                "MOVIE",
                "DESIGN",
                "JOURNAL",
                "ELAS",
                "QUIZZES",
                "HINDI T",
                "FINANCE",
                "VFX"
        };

        int icons[] = new int[]{
                R.drawable.icon_dance,
                R.drawable.icon_music,
                R.drawable.icon_drama,
                R.drawable.icon_photog,
                R.drawable.icon_shades,
                R.drawable.icon_movie,
                R.drawable.icon_design,
                R.drawable.icon_journal,
                R.drawable.icon_elas,
                R.drawable.icon_quiz,
                R.drawable.icon_hindi,
                R.drawable.icon_finance,
                R.drawable.icon_vfx
        };

        @Override
        public EventCategoryItem onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EventCategoryItem(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_category,parent,false));
        }

        @Override
        public void onBindViewHolder(EventCategoryItem holder, int position) {
            holder.title.setText(titles[position]);
            holder.icon.setImageResource(icons[position]);
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}