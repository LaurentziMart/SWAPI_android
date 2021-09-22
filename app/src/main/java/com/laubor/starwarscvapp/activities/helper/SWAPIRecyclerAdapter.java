package com.laubor.starwarscvapp.activities.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laubor.starwarscvapp.R;
import com.laubor.starwarscvapp.model.Character;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class SWAPIRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
  private static final int VIEW_TYPE_LOADING = 0;
  private static final int VIEW_TYPE_NORMAL = 1;
  private boolean isLoaderVisible = false;
  private SortType mSortType= SortType.ALPHABETICALLY;
  private List<Character> mCharacters;
  private Context mContext;
  private ItemClickListener mClickListener;

  public SWAPIRecyclerAdapter(List<Character> characters, Context context,ItemClickListener listener) {
    this.mClickListener= listener;
    this.mCharacters = getSortedItemsBySortType(characters,mSortType);
    this.mContext=context;
  }
  @NonNull @Override
  public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)  {
    switch (viewType) {
      case VIEW_TYPE_NORMAL:
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.swapi_item, parent, false));
      case VIEW_TYPE_LOADING:
        return new ProgressHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
      default:
        return null;
    }
  }
  @Override
  public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
    holder.onBind(position,mClickListener);


  }
  @Override
  public int getItemViewType(int position) {
    if (isLoaderVisible) {
      return position == mCharacters.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
    } else {
      return VIEW_TYPE_NORMAL;
    }
  }
  @Override
  public int getItemCount() {
    return mCharacters == null ? 0 : mCharacters.size();
  }
  public void addItems(List<Character> characters) {
    mCharacters.addAll(characters);
    mCharacters=getSortedItemsBySortType(mCharacters,mSortType);
    notifyDataSetChanged();
  }
  public void setItems(List<Character> characters) {
    mCharacters=getSortedItemsBySortType(characters,mSortType);
    notifyDataSetChanged();
  }

  public void addLoading() {
    isLoaderVisible = true;
    mCharacters.add(new Character());
    notifyItemInserted(mCharacters.size() - 1);
  }
  public void removeLoading() {
    isLoaderVisible = false;
    int position = mCharacters.size() - 1;
    Character item = getItem(position);
    if (item != null) {
      mCharacters.remove(position);
      notifyItemRemoved(position);
    }
  }
  public void clear() {
    mCharacters.clear();
    notifyDataSetChanged();
  }
  public Character getItem(int position) {
    return mCharacters.get(position);

  }
  private List<Character> getSortedItemsBySortType(List<Character> items, SortType sortType) {

    Collections.sort(items, new Comparator<Character>() {
      @Override
      public int compare(Character item, Character nextItem) {
        int comparison=0;
        if(nextItem==null || nextItem.getName()==null || nextItem.getFilms()== null || item==null || item.getName()==null || item.getFilms()==null)return 100;
        switch (sortType){
          case BY_FILMS:
            int filmsAscending=nextItem.getFilms().size()-item.getFilms().size();
            comparison= filmsAscending;
            break;
          case ALPHABETICALLY:
            int alphabetAscending= item.getName().compareTo(nextItem.getName());
            comparison=alphabetAscending;
            break;

        }
        return comparison;
      }
    });
    return items;
  }

  public void sortCurrentItemsByType(SortType sortType) {
    mSortType=sortType;
    mCharacters=getSortedItemsBySortType(mCharacters,sortType);
    notifyDataSetChanged();

  }


  public class ViewHolder extends BaseViewHolder implements View.OnClickListener {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNumberOfFilms)
    TextView tvNumberOfFilms;
    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
    protected void clear() {
    }
    public void onBind(int position, ItemClickListener mClickListener) {
      super.onBind(position, mClickListener);
      Character item = mCharacters.get(position);
      tvName.setText(item.getName());
      tvNumberOfFilms.setText(mContext.getString(R.string.character_item_films,String.valueOf(item.getFilms().size())));
    }

    @Override
    public void onClick(View v) {
      if (mClickListener != null) mClickListener.onCharacterClick(v, getAdapterPosition());
    }
  }
  public void setClickListener(ItemClickListener itemClickListener) {
    this.mClickListener = itemClickListener;
  }


  public interface ItemClickListener {
    void onCharacterClick(View view, int position);
  }

  public class ProgressHolder extends BaseViewHolder {
    ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
    @Override
    protected void clear() {
    }
  }

  public enum SortType{
    ALPHABETICALLY,BY_FILMS

  }
}