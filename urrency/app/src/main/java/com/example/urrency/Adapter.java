package com.example.urrency;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.urrency.data.CurrencyList;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public List<CurrencyList> getCurrency() {
        return (List<CurrencyList>) currencyList;
    }

    public void setCurrency(List<CurrencyList> currencyList) {
        this.currencyList = currencyList;
    }

    private List<CurrencyList> currencyList;

    private AdapterOnClickedItem adapterOnClickedItem;

    public Adapter(List<CurrencyList> currencyList, AdapterOnClickedItem adapterOnClickedItem) {
        this.adapterOnClickedItem = adapterOnClickedItem;

    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency_list, parent, false);
        return new ViewHolder(view, adapterOnClickedItem);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Adapter.ViewHolder holder, int position) {
        holder.setData(currencyList.get(position));
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameCurrency;
        TextView codeCurrency;
        TextView valueCurrency;
        private AdapterOnClickedItem adapterOnClickedItem;

        public ViewHolder(@NonNull @NotNull View itemView, AdapterOnClickedItem adapterOnClickedItem) {
            super(itemView);
            nameCurrency = itemView.findViewById(R.id.tvNameCurrency);
            codeCurrency = itemView.findViewById(R.id.tvCode);
            valueCurrency = itemView.findViewById(R.id.tvValue);
            this.adapterOnClickedItem = adapterOnClickedItem;
            itemView.setOnClickListener(this);
        }

        public void setData(CurrencyList currencyList) {
            nameCurrency.setText(currencyList.getName());
            codeCurrency.setText(currencyList.getCode());
            valueCurrency.setText(currencyList.getValue());
        }

        @Override
        public void onClick(View view) {
            adapterOnClickedItem.onAdapterClickedItem(getAdapterPosition());
        }
    }

    public interface AdapterOnClickedItem {
        void onAdapterClickedItem(int position);
    }
}