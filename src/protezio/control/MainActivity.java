// 
// aggiungere settings activity
// migliorare grafica
// migliorare funzione di update
//

package protezio.control;
//import protezio.control.RestClient;



import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import co.teubi.raspberrypi.io.GPIO;
import co.teubi.raspberrypi.io.GPIO.ConnectionInfo;




public class MainActivity extends Activity {

	
	final ConnectionInfo conn = new ConnectionInfo("protezio.no-ip.org",7000,"roberto","roberto");
	GPIO gpioPort = new GPIO (conn);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	//gpioPort.UpdateGPios();
    	update_toggle ();

		final ToggleButton tb1 = (ToggleButton)findViewById(R.id.ToggleButton1);
		final ToggleButton tb2 = (ToggleButton)findViewById(R.id.ToggleButton2);
		final ToggleButton tb3 = (ToggleButton)findViewById(R.id.ToggleButton3);
		final ToggleButton tb4 = (ToggleButton)findViewById(R.id.ToggleButton4);
		final SeekBar      sk1 = (SeekBar)findViewById(R.id.seekBar1);
		final EditText  LogText = (EditText)findViewById(R.id.Log);
		final ToggleButton tbled = (ToggleButton)findViewById(R.id.toggleButtonLed);
		LogText.setCursorVisible(false);
		LogText.setHint("Log");
		
		
		tb1.setOnClickListener(listener);
		tb2.setOnClickListener(listener);
		tb3.setOnClickListener(listener);
		tb4.setOnClickListener(listener);
		tbled.setOnClickListener(listener);
		sk1.setOnSeekBarChangeListener(listen_seek);
		
		    
	}

	
OnSeekBarChangeListener listen_seek = new OnSeekBarChangeListener() {
	
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
	    int value = (seekBar.getProgress());
	    Log.i("BAR",Float.toString(value));
		final EditText LogText = (EditText)findViewById(R.id.Log);
		
		if (((ToggleButton)findViewById(R.id.toggleButtonLed)).isChecked()) 
		{
			LogText.append("Led Value: " + Integer.toString(value)+ "\n");
			gpioPort.callMacro("pwmled", Integer.toString(value) );
		}
		else LogText.append("Strip Led is disabled...\n");
	}
	
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		final EditText LogText = (EditText)findViewById(R.id.Log);
		LogText.append("Setting led...\n");
		
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
		 boolean fromUser) {
		//int seekValue = seekBar.getProgress();
		//Log.i("BAR","Value: "+  Integer.toString(seekValue));
		int seekValue = seekBar.getProgress();
		final TextView sk_val = (TextView)findViewById(R.id.seek_value);		
		sk_val.setText(Integer.toString(seekValue));
	}
};
	
	
View.OnClickListener listener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			final EditText LogText = (EditText)findViewById(R.id.Log);
			
			// TODO Auto-generated method stub
			switch (v.getId()){
			

			case R.id.ToggleButton1:
			
				if (((ToggleButton)findViewById(R.id.ToggleButton1)).isChecked())	
										{gpioPort.SetValue(22, 1); Log.i("INFO","SET GPIO 22");
											LogText.append("Setting K1... \n");

										}
				else					{gpioPort.SetValue(22, 0); Log.i("INFO","RESET GPIO 22");
											LogText.append("Resetting K1... \n");

										}
				gpioPort.UpdateGPios();			
				break;
				
			case R.id.ToggleButton2:
					if (((ToggleButton)findViewById(R.id.ToggleButton2)).isChecked()) 
					{gpioPort.SetValue(23, 1); Log.i("INFO","SET GPIO 23");LogText.append("Setting K2... \n");}
			else					{gpioPort.SetValue(23, 0); Log.i("INFO","RESET GPIO 23");LogText.append("Resetting K2... \n");}
					
					gpioPort.UpdateGPios();				
					break;
			
			case R.id.ToggleButton3:
					if (((ToggleButton)findViewById(R.id.ToggleButton3)).isChecked()) 	
										{gpioPort.SetValue(24, 1); Log.i("INFO","SET GPIO 24");LogText.append("Setting K3... \n");}
			else					{gpioPort.SetValue(24, 0); Log.i("INFO","RESET GPIO 24");LogText.append("Resetting K3... \n");}
					gpioPort.UpdateGPios();					
					break;
				
			case R.id.ToggleButton4:
					if (((ToggleButton)findViewById(R.id.ToggleButton4)).isChecked()) 
					{gpioPort.SetValue(27, 1); Log.i("INFO","SET GPIO 27");LogText.append("Setting K4... \n");}
			else					{gpioPort.SetValue(27, 0); Log.i("INFO","RESET GPIO 27");LogText.append("Resetting K4... \n");}
					gpioPort.UpdateGPios();				
					break;
					
			case R.id.toggleButtonLed:
				if (((ToggleButton)findViewById(R.id.toggleButtonLed)).isChecked()) 
				{  
					SeekBar seek =  (SeekBar)(findViewById(R.id.seekBar1));
					gpioPort.callMacro("pwmled", Integer.toString(seek.getProgress()) );
					
				}
				else 
				{
					gpioPort.callMacro("pwmled","0");
				}
				gpioPort.UpdateGPios();				
				break;		
					
					
										
			}
		}
		
		
	};
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	    // action with ID action_refresh was selected
	    case R.id.action_update:
	    	gpioPort.UpdateGPios();
		    Toast.makeText(this, "Aggiornamento...", Toast.LENGTH_SHORT).show();

	    	//Update update = new GPIO(new ConnectionInfo("192.168.1.102", 7000, "roberto", "roberto")).Update();
	    	update_toggle();
	    	Log.i("ACT","main GPIO_22: " + gpioPort.GetValue(22));
	    	Log.i("ACT","main GPIO_23: " + gpioPort.GetValue(23));
	    	Log.i("ACT","main GPIO_24: " + gpioPort.GetValue(24));
	    	Log.i("ACT","main GPIO_27: " + gpioPort.GetValue(27));
	    			
	    break;
	    // action with ID action_settings was selected
	    case R.id.action_settings:
	      Toast.makeText(this, "ConTrols v0.02", Toast.LENGTH_SHORT).show();
	      gpioPort.callMacro("getTemperature", "");
          
	      break;
	    default:
	      break;
	    }

	    return true;
	}
	
	


	public void  update_toggle ()
	{		
		   gpioPort.UpdateGPios();
		   final EditText LogText = (EditText)findViewById(R.id.Log);
		   LogText.append("K1 value: " + gpioPort.GetValue(22)+ "\n");
		   LogText.append("K2 value: " + gpioPort.GetValue(23)+ "\n");
		   LogText.append("K3 value: " + gpioPort.GetValue(24)+ "\n");
		   LogText.append("K4 value: " + gpioPort.GetValue(27)+ "\n");
		   
		   if (gpioPort.GetValue(22)==PORTVALUE.LOGIC_1) 
			   
		   {   
			   ((ToggleButton)findViewById(R.id.ToggleButton1)).setChecked(true);
				//((ToggleButton)findViewById(R.id.ToggleButton1)).setTextColor(Color.GREEN);

		   }
		   else 
			   {
			   		((ToggleButton)findViewById(R.id.ToggleButton1)).setChecked(false);
					//((ToggleButton)findViewById(R.id.ToggleButton1)).setTextColor(Color.RED);

			   }
		   
		   if (gpioPort.GetValue(23)==PORTVALUE.LOGIC_1) 
			{	
			   	((ToggleButton)findViewById(R.id.ToggleButton2)).setChecked(true);
				//((ToggleButton)findViewById(R.id.ToggleButton2)).setTextColor(Color.GREEN);
			}
		   else {
		   		((ToggleButton)findViewById(R.id.ToggleButton2)).setChecked(false);
		   		//((ToggleButton)findViewById(R.id.ToggleButton2)).setTextColor(Color.RED);
		   }
		   
		   if (gpioPort.GetValue(24)==PORTVALUE.LOGIC_1) 
			   {
			   		((ToggleButton)findViewById(R.id.ToggleButton3)).setChecked(true);
			   		//((ToggleButton)findViewById(R.id.ToggleButton3)).setTextColor(Color.GREEN);
			   }
			   else{
			   		((ToggleButton)findViewById(R.id.ToggleButton3)).setChecked(false);
			   		//((ToggleButton)findViewById(R.id.ToggleButton3)).setTextColor(Color.RED);
			   }
		   
		   if (gpioPort.GetValue(27)==PORTVALUE.LOGIC_1)
			   {	
			   		((ToggleButton)findViewById(R.id.ToggleButton4)).setChecked(true);
			   		//((ToggleButton)findViewById(R.id.ToggleButton4)).setTextColor(Color.GREEN);
			   }
		   else{
		   		((ToggleButton)findViewById(R.id.ToggleButton4)).setChecked(false);
		   		//((ToggleButton)findViewById(R.id.ToggleButton4)).setTextColor(Color.RED);
		   }
			
	}
	
	
	float x1 = 0f;
	float x2 = 0f ;
	float y1 = 0f,y2 = 0f ;
	@Override
	public boolean onTouchEvent(MotionEvent touchevent) 
    {
               
			
				switch (touchevent.getAction())
                 {
                        // when user first touches the screen we get x and y coordinate
                         case MotionEvent.ACTION_DOWN: 
                         {
                             x1 = touchevent.getX();
                             y1 = touchevent.getY();
                             
                             Log.i("POINT","x1: " +  x1);
                             Log.i("POINT","y1: " +  y1);
                            

                             break;
                        }
                         case MotionEvent.ACTION_UP: 
                         {
                            x2 = touchevent.getX();
                            y2 = touchevent.getY(); 
                            Log.i("POINT","x2: " +  x2);
                            Log.i("POINT","y2: " +  y2);
                            y1 =  (y1 + 300);
                            Log.i("POINT","y1 offset: " + y1);
                            
                            //if Down to UP sweep event on screen
                             if (   y1 < y2 
                            	)
                             {
                                 Toast.makeText(this, "Aggiornamento...", Toast.LENGTH_SHORT).show();
                                 Log.i("POINT","Aggiornamento");
                                 gpioPort.UpdateGPios();
                                 update_toggle();
                             }
                             break;
                         }
                 }
                 return false;
    }
	
}
