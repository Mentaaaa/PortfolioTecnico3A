package com.example.bizon;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(
            this, Manifest.permission.READ_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},0);
        }

        iv = (ImageView) findViewById(R.id.f);
    }

    public void tiraFoto(View view){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 2);
    }

    public void botao(View view){
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            Uri imagemSelecionada = data.getData();
            String[] caminhoArquivo = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(imagemSelecionada,caminhoArquivo,null,null,null);
            c.moveToFirst();
            int coluna = c.getColumnIndex(caminhoArquivo[0]);
            String caminho = c.getString(coluna);
            c.close();
            Bitmap imagem = (BitmapFactory.decodeFile(caminho));
            System.out.println(caminho);
            iv.setImageBitmap(imagem);
        }
        if(requestCode == 2 && resultCode == RESULT_OK){
            Bundle b = data.getExtras();
            Bitmap imagem = (Bitmap) b.get("data");
            iv.setImageBitmap(imagem);
        }

    }
}