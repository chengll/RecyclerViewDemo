package test.dmdfchina.com.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private List<StyleData> mListStyle = new ArrayList<>();

    private StyleAdapter styleAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initStyle();

    }

    private void initStyle() {
        for (int i = 0; i < 24; i++) {
            StyleData styleData = new StyleData();
            styleData.item = "" + i;
            if (i > 3 && i < 7 || i == 11 || i == 13) {
                styleData.type = 2;
            } else if (i == 15 || i == 18) {
                styleData.type = 3;
            } else if (i == 19) {
                styleData.type = 4;
            } else {
                styleData.type = 1;
            }
            mListStyle.add(styleData);
        }
        mRecycler=findViewById(R.id.mRecycler);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        mRecycler.setLayoutManager(gridLayoutManager);
        styleAdapter=new StyleAdapter(this,gridLayoutManager,mListStyle);
        mRecycler.setAdapter(styleAdapter);
    }

    //写适配器
    class StyleAdapter extends RecyclerView.Adapter<StyleAdapter.StyleHolder> {
        private List<StyleData> mStyleList;
        private Context mContext;
        private GridLayoutManager gridLayoutManager;
        public StyleAdapter(Context mContext,GridLayoutManager gridLayoutManager,List<StyleData> mStyleList){
            this.mContext=mContext;
            this.mStyleList=mStyleList;
            this.gridLayoutManager=gridLayoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridSpanSizeLook());
        }

        @NonNull
        @Override
        public StyleHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            StyleHolder mHolder = null;
            if (viewType == 1) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_one, parent, false);
                mHolder = new ViewHolderOne(view);
            } else if (viewType == 2) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_two, parent, false);
                mHolder = new ViewHolderTwo(view);
            } else if (viewType == 3) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_three, parent, false);
                mHolder = new ViewHolderThree(view);
            } else if (viewType == 4) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_type_four, parent, false);
                mHolder = new ViewHolderFour(view);
            }
            return mHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull StyleHolder holder, int position) {
            if (mStyleList.get(position).type==1){
                holder.text_view.setText("第一种样式");
            }else if (mStyleList.get(position).type==2){
                holder.text_view2.setText("样式2");
            }else if (mStyleList.get(position).type==3){
                holder.text_view3.setText("样式3");
            }else if (mStyleList.get(position).type==4){
                holder.img_our.setImageResource(R.mipmap.ic_launcher);
            }

        }

        @Override
        public int getItemViewType(int position) {
            return mStyleList.get(position).type;
        }

        @Override
        public int getItemCount() {
            return mStyleList.size();
        }

        class StyleHolder extends RecyclerView.ViewHolder {
            TextView text_view;
            TextView text_view2;
            EditText text_view3;
            ImageView img_our;

            public StyleHolder(View itemView) {
                super(itemView);
            }
        }

        //第一种布局
        class ViewHolderOne extends StyleAdapter.StyleHolder {

            public ViewHolderOne(View itemView) {
                super(itemView);
                text_view = itemView.findViewById(R.id.text_view);
            }
        }

        //第二种布局
        class ViewHolderTwo extends StyleAdapter.StyleHolder {
            public ViewHolderTwo(View itemView) {
                super(itemView);
                //text_view = itemView.findViewById(R.id.text_view);
                text_view2=itemView.findViewById(R.id.text_view2);
            }
        }


        //第三种布局
        class ViewHolderThree extends StyleAdapter.StyleHolder {
            public ViewHolderThree(View itemView) {
                super(itemView);
                //text_view = itemView.findViewById(R.id.text_view);
                text_view3=itemView.findViewById(R.id.text_view3);
            }
        }

        //第四种布局
        class ViewHolderFour extends StyleAdapter.StyleHolder {
            public ViewHolderFour(View itemView) {
                super(itemView);
              //  text_view = itemView.findViewById(R.id.text_view);
                img_our=itemView.findViewById(R.id.img_our);

            }
        }

        public class GridSpanSizeLook extends GridLayoutManager.SpanSizeLookup{
            @Override
            public int getSpanSize(int position) {
                return mListStyle.get(position).type;
            }
        }


    }


    class StyleData {
        int type;
        String item;
    }
}
