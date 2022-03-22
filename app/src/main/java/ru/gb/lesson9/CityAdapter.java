package ru.gb.lesson9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityHolder> {

    private PopupMenuItemClicker listener;

    public void setPopupMenuItemClicker(PopupMenuItemClicker listener)
    {
        this.listener = listener;
    }


    private List<City> cities = new ArrayList<>();

    public void  setCities(List<City> cities)
    {
        this.cities = cities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.city_item, parent, false);
        return new CityHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public void delete(List<City> cities, int position) {
        this.cities = cities;
        // notifyDataSetChanged();
        notifyItemRemoved(position);
    }

    public void create(List<City> cities, int pos) {
        this.cities = cities;
        notifyItemInserted(pos);
    }

    public void modify(List<City> all, int position) {
        this.cities = cities;
        notifyItemChanged(position);
    }
}
