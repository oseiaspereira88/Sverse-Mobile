package com.example.oseias.sverse.OtherAdapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OthersActivitys.Teste;
import com.exemple.oseias.sverse.R;
import com.example.oseias.sverse.SQLite.model.Container;

import java.util.ArrayList;

/**
 * Created by oseias on 03/04/2018.
 */

public class GrupAdapter extends BaseAdapter {
    Context c;
    ArrayList<Container> containers;
    boolean isLongeClick;

    public GrupAdapter(Teste act, Context c, ArrayList<Container> containers) {
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

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(c).inflate(R.layout.container_grid_model, viewGroup, false);
        }

        final Container container = (Container) this.getItem(i);

        CardView card = (CardView) view.findViewById(R.id.cardContainer);
        TextView nameTxt = (TextView) view.findViewById(R.id.tvNomePerfil);
        TextView nNotifi = (TextView) view.findViewById(R.id.nNotifiContainer);
        ImageView img = (ImageView) view.findViewById(R.id.imgBG);

        //Add Values
        card.setPreventCornerOverlap(false);




        Bitmap bipmap = BitmapFactory.decodeResource(c.getResources(), container.getImageBg());
        Bitmap tempBitmap = Bitmap.createBitmap(bipmap.getWidth(), bipmap.getHeight(), Bitmap.Config.ARGB_8888);
        //BitmapShader shader;
        //shader = new BitmapShader(bipmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        //Paint paint = new Paint();
        //paint.setAntiAlias(true);
        //paint.setShader(shader);
        //RectF rect = new RectF(0.0f, 0.0f, bipmap.getWidth(), bipmap.getHeight());

        // rect contains the bounds of the shape
        // radius is the radius in pixels of the rounded corners
        // paint contains the shader that will texture the shape
        //Canvas canvas = new Canvas(tempBitmap);
        //Draw the image bitmap into the cavas
        //canvas.drawRoundRect(rect, 100, 100, paint);
        //img.setImageDrawable(new BitmapDrawable(c.getResources(), bipmap));

        //setImageRadius(card, container.getImageBg());
        //card.setBackgroundResource(container.getImageBg());


        img.setImageBitmap(getRoundedCornerBitmap(getResizedBitmap(bipmap, 570, 416), 60));
        //img.setImageDrawable(getBGResized(c.getResources(), container.getImageBg()));
        nameTxt.setText(container.getName());
        nNotifi.setText(container.getnNotifications());

        //Blurry.with(c).radius(25).sampling(2).onto((ViewGroup) rootView);
        //// from View
        //Blurry.with(context).capture(view).into(imageView);
        //// from Bitmap
        //Blurry.with(context).from(bitmap).into(img);
        /*Blurry.with(c)
                .radius(10)
                .sampling(8)
                .color(Color.argb(66, 255, 255, 0))
                .async()
                .animate(500)
                .onto(card);*/


        final View finalView = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(c, container.getName(), Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(700)
                            .repeat(0)
                            .playOn(finalView);
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
                return false;
            }
        });

        return view;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap
                .getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;

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
