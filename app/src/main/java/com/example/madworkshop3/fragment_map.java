package com.example.madworkshop3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class fragment_map extends Fragment {
    private fragment_selector selector;
    public fragment_map() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MapData data = MapData.get();
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                MapData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false
        ));
        MapAdapter adapter = new MapAdapter(data);
        recyclerView.setAdapter(adapter);
        return view;
    }
    public void setSelector(fragment_selector selector)
    {
        this.selector = selector;
    }
    private class MapAdapter extends RecyclerView.Adapter<DataViewHolder>
    {
        private MapData data;
        public MapAdapter(MapData data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new DataViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull DataViewHolder holder, int position)
        {
            int row = position % MapData.HEIGHT;
            int col = position / MapData.HEIGHT;
            holder.bind(data.get(row, col));

            holder.structure.setOnClickListener(view -> {
                if(selector.getCurrStructure() != null) {
                    holder.structure.setImageResource(selector.getCurrStructure().getDrawableId());
                    selector.resetClicker();
                }
            });
        }
        @Override
        public int getItemCount()
        {
            return MapData.HEIGHT * MapData.WIDTH;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getItemViewType(int position)
        {
            return position;
        }
    }

    private class DataViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView structure;
        private ImageView topLeft;
        private ImageView topRight;
        private ImageView bottomLeft;
        private ImageView bottomRight;

        public DataViewHolder(LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.grid_cell, parent, false));
            int size = parent.getMeasuredHeight() / MapData.HEIGHT+1;
            ViewGroup.LayoutParams lp = itemView.getLayoutParams();
            lp.width = size;
            lp.height = size;
            topLeft = (ImageView) itemView.findViewById(R.id.topLeft);
            topRight = (ImageView) itemView.findViewById(R.id.topRight);
            bottomLeft = (ImageView) itemView.findViewById(R.id.bottomLeft);
            bottomRight = (ImageView) itemView.findViewById(R.id.bottomRight);
            structure = (ImageView) itemView.findViewById((R.id.structure));
        }

        public void bind(MapElement data)
        {
            topLeft.setImageResource(data.getNorthWest());
            topRight.setImageResource(data.getNorthEast());
            bottomLeft.setImageResource(data.getSouthWest());
            bottomRight.setImageResource(data.getSouthEast());
        }
    }
}