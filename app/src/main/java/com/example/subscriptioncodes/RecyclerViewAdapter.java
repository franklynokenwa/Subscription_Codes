package com.example.subscriptioncodes;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";
    private static final int REQUEST_CALL = 1;

    private ArrayList<String> mImageViews = new ArrayList<>();
    private ArrayList<String> mBlogDescriptions = new ArrayList<>();
    public ArrayList<String> mSendIcons = new ArrayList<>();
    private Context mContext;
    public RecyclerViewAdapter mViewAdapter;

    public RecyclerViewAdapter(ArrayList<String> imageViews, ArrayList<String> blogDescriptions, ArrayList<String> sendIcons, Context context) {
        mImageViews = imageViews;
        mBlogDescriptions = blogDescriptions;
        mSendIcons = sendIcons;
        mContext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        Glide.with(mContext)
                .asBitmap()
                .load(mImageViews.get(position))
                .into(holder.mImageView);

        holder.mBlogDescription.setText(mBlogDescriptions.get(position));
    }

    @Override
    public int getItemCount() {
        return mSendIcons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView mImageView;
        TextView mBlogDescription;
        ImageButton mSendIcon;
        RelativeLayout mRelativeLayout;
        MainActivity mMainActivity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.circular_image);
            mBlogDescription = itemView.findViewById(R.id.blog_description);
            mSendIcon = itemView.findViewById(R.id.send_icon);
            mRelativeLayout = itemView.findViewById(R.id.parent_layout);

            mSendIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makePhoneCall();

                }
            });
        }

        private void makePhoneCall() {
            if (ContextCompat.checkSelfPermission(mContext,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(mMainActivity,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + Uri.encode(mSendIcons.get(getAdapterPosition()))));
                mContext.startActivity(callIntent);
            }
        }
            public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults){
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        makePhoneCall();
                    }
                }
            }
        }