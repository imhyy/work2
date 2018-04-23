package com.example.hyy.work2;

        import android.content.Context;
        import android.os.Build;
        import android.support.annotation.RequiresApi;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import junit.framework.Test;

        import java.io.BufferedInputStream;
        import java.io.BufferedOutputStream;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.OutputStream;
        import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button write = (Button)findViewById(R.id.button);
        Button read = (Button)findViewById(R.id.button2);
        final EditText text = (EditText)findViewById(R.id.textView);
        final String FILE_NAME = "test.txt";
        try {
            FileOutputStream fos = openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        write.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                String Str = text.getText().toString();
                OutputStream out=null;
                try {
                    FileOutputStream fileOutputStream=openFileOutput(FILE_NAME,MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    try {
                        try {
                            out.write(Str.getBytes(StandardCharsets.UTF_8));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    finally {
                        if(out!=null)
                            try {
                                out.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
        read.setOnClickListener(new View.OnClickListener() {//du
            @Override
            public void onClick(View view) {
                InputStream in=null;
                try {
                    FileInputStream fileInputStream = openFileInput(FILE_NAME);
                    in=new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while ((c=in.read())!=-1) {
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();//toushitishikuang
                    }
                    finally {
                        if(in!=null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

        });
    }
}

