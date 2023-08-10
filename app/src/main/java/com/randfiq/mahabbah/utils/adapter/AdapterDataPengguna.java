package com.randfiq.mahabbah.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.randfiq.mahabbah.R;
import com.randfiq.mahabbah.data.model.DataPengguna;

import java.util.ArrayList;
import java.util.List;

public class AdapterDataPengguna extends RecyclerView.Adapter<AdapterDataPengguna.ViewHolder> {
	private final List<DataPengguna> itemList;
	private final List<DataPengguna> filteredList;
	private OnItemClickListener onItemClickListener;

	@SuppressLint("NotifyDataSetChanged")
	public AdapterDataPengguna(List<DataPengguna> itemList) {
		this.itemList = itemList;
		this.filteredList = new ArrayList<>(itemList);
	}

	public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	@NonNull
	@Override
	public AdapterDataPengguna.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View itemView = inflater.inflate(R.layout.data_item, parent, false);

		return new AdapterDataPengguna.ViewHolder(itemView);
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		TextView tvNama;
		TextView tvMarga;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvNama = itemView.findViewById(R.id.tv_nama);
			tvMarga = itemView.findViewById(R.id.tv_marga);
		}
	}

	@Override
	public void onBindViewHolder(@NonNull AdapterDataPengguna.ViewHolder holder, int position) {
		DataPengguna item = filteredList.get(position);
		holder.tvNama.setText(item.getNama());
		holder.tvMarga.setText(item.getMarga());
		holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(item));
	}

	@Override
	public int getItemCount() {
		return filteredList.size();
	}

	@SuppressLint("NotifyDataSetChanged")
	public void filterBasic(String input){
		filteredList.clear();
		if (input.isEmpty()) {
			filteredList.addAll(itemList);
		} else {
			input = input.toLowerCase().trim();
			for (DataPengguna item : itemList) {
				if (item.getNama().toLowerCase().contains(input)) {
					filteredList.add(item);
				}
			}
		}
		notifyDataSetChanged();
	}

	public interface OnItemClickListener {
		void onItemClick(DataPengguna item);
	}
}
