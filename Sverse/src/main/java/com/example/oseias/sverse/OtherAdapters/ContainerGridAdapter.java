package com.example.oseias.sverse.OtherAdapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OthersActivitys.GroupArea;
import com.example.oseias.sverse.OthersActivitys.WorkGroupArea;
import com.example.oseias.sverse.OthersActivitys.GroupCreator;
import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.SQLite.model.Container;

import java.util.ArrayList;

/**
 * Created by oseias on 03/04/2018.
 */

public class ContainerGridAdapter extends BaseAdapter {
    Activity act;
    Context c;
    ArrayList<Container> containers;
    boolean isLongeClick;

    public ContainerGridAdapter(Activity act, Context c, ArrayList<Container> containers) {
        this.act = act;
        this.c = c;
        this.containers = containers;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return containers.size();
    }

    @Override
    public Object getItem(int i) {
        return containers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.container_grid_model, viewGroup, false);
        }

        final Container container = (Container) this.getItem(i);
        //CardView card = (CardView) view.findViewById(R.id.cardContainer);
        TextView nameTxt = (TextView) view.findViewById(R.id.tvNomePerfil);
        TextView nNotifi = (TextView) view.findViewById(R.id.nNotifiContainer);
        ImageView imgContainerNotifi = (ImageView) view.findViewById(R.id.imgExcluir);
        ImageView img = (ImageView) view.findViewById(R.id.imgBG);

        //Add Values
        //card.setPreventCornerOverlap(false);
        img.setImageResource(container.getImageBg());
        nameTxt.setText(container.getName());
        if(container.isHaveNotifications()){
            nNotifi.setText(String.valueOf(container.getnNotifications()));
        } else{
            nNotifi.setVisibility(TextView.INVISIBLE);
            imgContainerNotifi.setVisibility(View.INVISIBLE);
        }


        final View finalView = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(c, container.getName(), Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(400)
                            .repeat(0)
                            .playOn(finalView);

                    Intent it = new Intent(act, GroupArea.class);
                    Bundle b = new Bundle();
                    b.putInt("id", container.get_id());
                    it.putExtra("id", b);
                    act.startActivity(it);
                    act.finish();
                }
            }
        });

        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isLongeClick = true;
                YoYo.with(Techniques.Wave)
                        .duration(700)
                        .repeat(0)
                        .playOn(finalView);

                Toast.makeText(c, "Você precionou " + container.getName(), Toast.LENGTH_SHORT).show();

                Intent it = new Intent(act, GroupCreator.class);
                Bundle b = new Bundle();
                b.putInt("id", container.get_id());
                it.putExtra("id", b);
                act.startActivity(it);
                act.finish();
                return false;
            }
        });

        return view;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight){
        int Width = bm.getWidth();
        int Height = bm.getHeight();
        float scaleWidth = ((float) newWidth / Width);
        float scaleHeight = ((float) newHeight / Height);

        //Create a matrix for manipulation
        Matrix matrix = new Matrix();
        //Resize the bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        //Recreate bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap( bm, 0, 0, Width, Height, matrix, false );
        bm.recycle();

        return resizedBitmap;
    }



    public static Bitmap cropBitmap(Bitmap original, int height, int width) {
        Bitmap croppedImage = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(croppedImage);

        Rect srcRect = new Rect(0, 0, original.getWidth(), original.getHeight());
        Rect dstRect = new Rect(0, 0, width, height);

        int dx = (srcRect.width() - dstRect.width()) / 2;
        int dy = (srcRect.height() - dstRect.height()) / 2;

        // If the srcRect is too big, use the center part of it.
        srcRect.inset(Math.max(0, dx), Math.max(0, dy));

        // If the dstRect is too big, use the center part of it.
        dstRect.inset(Math.max(0, -dx), Math.max(0, -dy));

        // Draw the cropped bitmap in the center
        canvas.drawBitmap(original, srcRect, dstRect, null);

        original.recycle();

        return croppedImage;
    }

}
