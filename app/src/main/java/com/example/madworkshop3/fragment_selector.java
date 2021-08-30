package com.example.madworkshop3;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class fragment_selector extends Fragment {
    private Structure currStructure;
    public fragment_selector() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        StructureData data = StructureData.get();
        View view = inflater.inflate(R.layout.fragment_selector, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.selectorRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(
                getActivity(),
                1,
                GridLayoutManager.HORIZONTAL,
                false
        ));
        fragment_selector.SelectorAdapter adapter = new fragment_selector.SelectorAdapter(data);
        recyclerView.setAdapter(adapter);
        return view;
    }

    public Structure getCurrStructure()
    {
        return currStructure;
    }

    public void resetClicker()
    {
        currStructure = null;
    }

    private class SelectorAdapter extends RecyclerView.Adapter<fragment_selector.DataViewHolder>
    {
        private StructureData data;
        public SelectorAdapter(StructureData data)
        {
            this.data = data;
        }

        @NonNull
        @Override
        public fragment_selector.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new fragment_selector.DataViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull fragment_selector.DataViewHolder holder, int position)
        {
            holder.image.setOnClickListener(view -> {
                currStructure = data.get(position);
            });

            holder.bind(data.get(position));
        }

        @Override
        public int getItemCount()
        {
            return data.size();
        }
    }

    private class DataViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView image;
        private TextView text;
        public DataViewHolder(LayoutInflater layoutInflater, ViewGroup parent)
        {
            super(layoutInflater.inflate(R.layout.list_section, parent, false));
            image = (ImageView) itemView.findViewById(R.id.imageView);
            text = (TextView) itemView.findViewById(R.id.textView);
        }

        public void bind(Structure data)
        {
            image.setImageResource(data.getDrawableId());
            text.setText(data.getLabel());
        }
    }
}