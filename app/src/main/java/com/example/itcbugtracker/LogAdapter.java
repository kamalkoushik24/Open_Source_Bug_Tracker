package com.example.itcbugtracker;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.itcbugtracker.MainActivity.logDatabase;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        LinearLayout containerView;
        TextView textView;

        LogViewHolder(final View view) {
            super(view);
            containerView = view.findViewById(R.id.log_row);
            textView = view.findViewById(R.id.log_row_text);
            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log current = (Log) containerView.getTag();
                    Intent intent = new Intent(view.getContext(), LogActivity.class);
                    intent.putExtra("id", current.id);
                    intent.putExtra("desc", current.desc);
                    intent.putExtra("category1", current.category1);
                    intent.putExtra("category2", current.category2);
                    intent.putExtra("pDetails", current.pDetails);
                    intent.putExtra("archived", current.archived);
                    view.getContext().startActivity(intent);
                }
            });


        }
    }

    List<Log> logs = new ArrayList<>();

    @NonNull
    @Override
    public LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_row, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogViewHolder holder, int position) {
        Log current = logs.get(position);
        holder.textView.setText(current.desc);
        holder.containerView.setTag(current);

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }
    public void reload(){
        logs = logDatabase.logDao().getAllLogs();
        notifyDataSetChanged();
    }


}
