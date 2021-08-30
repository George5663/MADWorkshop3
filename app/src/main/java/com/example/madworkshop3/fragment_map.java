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

public class fragment_map extends Fragment {
    private View view;
    public fragment_map() {
    }
    private class MapAdapter extends RecyclerView.Adapter<DataViewHolder>
    {
        private List<MapElement> data;
        public MapAdapter(List<MapElement> data)
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
            holder.bind(MapData.get().get(row, col));
        }
        @Override
        public int getItemCount()
        {
            return data.size();
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
            topLeft = (ImageView) itemView.findViewById(R.id.topLeft);
            topRight = (ImageView) itemView.findViewById(R.id.topRight);
            bottomLeft = (ImageView) itemView.findViewById(R.id.bottomLeft);
            bottomRight = (ImageView) itemView.findViewById(R.id.bottomRight);
            structure = (ImageView) itemView.findViewById((R.id.structure));
        }

        public void bind(MapElement data)
        {
            topLeft.setBackgroundColor(data.getNorthWest());
            topRight.setBackgroundColor(data.getNorthEast());
            bottomLeft.setBackgroundColor(data.getSouthWest());
            bottomRight.setBackgroundColor(data.getSouthEast());
            if(data.getStructure() != null) {
                structure.setImageResource(data.getStructure().getDrawableId());
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_map, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mapRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                MapData.HEIGHT,
                GridLayoutManager.HORIZONTAL,
                false
        ));
        List<MapElement> mapData = new LinkedList<>();
        MapAdapter adapter = new MapAdapter(mapData);
        recyclerView.setAdapter(adapter);
        return view;
    }
}