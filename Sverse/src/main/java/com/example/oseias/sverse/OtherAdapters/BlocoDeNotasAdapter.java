package com.example.oseias.sverse.OtherAdapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.oseias.sverse.OthersActivitys.CriadorDeNotas;
import com.versaplications.prodesenvelopment.sverse.R;
import com.example.oseias.sverse.SQLite.model.NotaModel;

import java.util.ArrayList;

/**
 * Created by oseias on 03/04/2018.
 */

public class BlocoDeNotasAdapter extends BaseAdapter {
    Activity act;
    ArrayList<NotaModel> notas;
    boolean isLongeClick;

    public BlocoDeNotasAdapter(Activity act, ArrayList<NotaModel> notas) {
        this.act = act;
        this.notas = notas;
        isLongeClick = false;
    }

    @Override
    public int getCount() {
        return notas.size();
    }

    @Override
    public Object getItem(int i) {
        return notas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @RequiresApi(api = Build.VERSION_CODES.DONUT)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = LayoutInflater.from(act).inflate(R.layout.nota_model, viewGroup, false);
        }
        final NotaModel notaModel = (NotaModel) this.getItem(i);

        LinearLayout llNota = (LinearLayout) view.findViewById(R.id.llNota);
        TextView titleNota = (TextView) view.findViewById(R.id.perfilNome);
        TextView textNota = (TextView) view.findViewById(R.id.txtNota);
        TextView nNotifications = (TextView) view.findViewById(R.id.nNotifications);
        ImageView imgNotaNotifi = (ImageView) view.findViewById(R.id.imgConfig);

        //Add Values
        //card.setPreventCornerOverlap(false);

        //Bitmap bipmap = BitmapFactory.decodeResource(c.getResources(), notaModel.getImageBg());

        //img.setImageBitmap(getRoundedCornerBitmap(getResizedBitmap(bipmap, 570, 416), 60));
        //img.setImageDrawable(getBGResized(c.getResources(), containerModel.getImageBg()));
        titleNota.setText(notaModel.getTitulo());
        textNota.setText(notaModel.getNota());

        if(notaModel.getTitulo().equals("")){
            titleNota.setVisibility(View.GONE);
            ViewGroup.LayoutParams lp = textNota.getLayoutParams();
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.topMargin = 12;
            textNota.requestLayout();
        }

        View finalView = view;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLongeClick) {
                    isLongeClick = false;
                } else {
                    Toast.makeText(act, notaModel.getTitulo(), Toast.LENGTH_SHORT).show();
                    YoYo.with(Techniques.Landing)
                            .duration(700)
                            .repeat(0)
                            .playOn(finalView);
                    Intent it = new Intent(act, CriadorDeNotas.class);
                    Bundle b = new Bundle();
                    b.putInt("id", notaModel.get_id());
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
                Toast.makeText(act, "Você precionou " + notaModel.getTitulo(), Toast.LENGTH_SHORT).show();
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

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int Width = bm.getWidth();
        int Height = bm.getHeight();
        float scaleWidth = ((float) newWidth / Width);
        float scaleHeight = ((float) newHeight / Height);

        //Create a matrix for manipulation
        Matrix matrix = new Matrix();
        //Resize the bitmap
        matrix.postScale(scaleWidth, scaleHeight);

        //Recreate bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, Width, Height, matrix, false);
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
