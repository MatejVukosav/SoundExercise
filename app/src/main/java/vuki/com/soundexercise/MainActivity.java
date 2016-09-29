package vuki.com.soundexercise;

import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import vuki.com.soundexercise.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp = new MediaPlayer();
    ActivityMainBinding binding;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        binding = DataBindingUtil.setContentView( this, R.layout.activity_main );

        for( int i = 0; i < 2; i++ ) {
            binding.root.addView( getButton( "Zvuk" + ( i + 1 ), i+1 ) );
        }

    }

    private Button getButton( String title, final int i ) {
        Button button = new Button( this );
        button.setText( title );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                if( mp.isPlaying() ) {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getAssets().openFd( "sound" + i + ".wav" );
                    mp.setDataSource( afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                    mp.start();
                } catch( IllegalStateException e ) {
                    e.printStackTrace();
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        } );
        return button;
    }
}
