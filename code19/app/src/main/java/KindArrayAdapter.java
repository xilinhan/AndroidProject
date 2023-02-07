import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.code19.ItemClickCallback;
import com.example.code19.R;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @author xilinhan
 * @description:
 * @date :2022/6/23 19:31
 */
public class KindArrayAdapter extends RecyclerView.Adapter<KindContentHolder> {
    //需要包装的JSONArray
    private JSONArray mJSONArray;
    private Context mContext;
    private ItemClickCallback mItemClickCallback;

    KindArrayAdapter(JSONArray JSONArray, Context context, ItemClickCallback itemClickCallback) {
        mJSONArray = JSONArray;
        mContext = context;
        mItemClickCallback = itemClickCallback;
    }

    @NonNull
    @Override
    public KindContentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        LayoutInflater.from(mContext).inflate(R.layout.manage_kind, linearLayout);
        return new KindContentHolder(linearLayout, null);
    }

    @Override
    public void onBindViewHolder(@NonNull  KindContentHolder holder, int position) {
        try {
            String kindName = mJSONArray.optJSONObject(position).getString("kindName");
            holder.descTV.setText("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mJSONArray.length();
    }
}

class KindContentHolder extends RecyclerView.ViewHolder {
    TextView nameTV;
    TextView descTV;
    KindContentHolder(View itemView, ItemClickCallback callback){
        super(itemView);
        nameTV = itemView.findViewById(R.id.kindList);
        descTV = itemView.findViewById(R.id.bnAdd);
    }
}
