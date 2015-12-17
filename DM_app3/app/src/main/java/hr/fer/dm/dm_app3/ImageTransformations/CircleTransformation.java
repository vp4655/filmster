package hr.fer.dm.dm_app3.ImageTransformations;

/**
 * Created by Valentino on 16.12.2015..
 */
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.squareup.picasso.Transformation;

public class CircleTransformation implements Transformation {

    public static final float borderWidth = 15f;

    @Override
    public Bitmap transform(Bitmap source) {
        int size = Math.min(source.getWidth(), source.getHeight());

        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 8;

        Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
        if (squaredBitmap != source) {
            source.recycle();
        }

        Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader = new BitmapShader(squaredBitmap,
                BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);

        Paint paint1 = new Paint();
        paint1.setColor(Color.parseColor("#76FFFFFF"));
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setAntiAlias(true);
        paint1.setStrokeWidth(borderWidth);
        canvas.drawCircle(r, r, r - (borderWidth / 2f), paint1);


        squaredBitmap.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "circle";
    }
}
