package com.laubor.starwarscvapp.activities.helper;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
  private int mCurrentPosition;
  public BaseViewHolder(View itemView) {
    super(itemView);
  }
  protected abstract void clear();
  public void onBind(int position, SWAPIRecyclerAdapter.ItemClickListener mClickListener) {
    mCurrentPosition = position;
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mClickListener.onCharacterClick(itemView,mCurrentPosition);
      }
    });
    clear();

  }
  public int getCurrentPosition() {
    return mCurrentPosition;
  }
}