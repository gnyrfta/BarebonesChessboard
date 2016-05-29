package shumpi.chesstwo;
/*movePiece is called twice in both ChessBoard and RetardedChess.*/


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnTouchListener;
import android.widget.TextView;
//so far this below does not seem necessary?


public class MainActivity extends Activity implements OnTouchListener {
	public static int width;
	public static int height;
	public static HashMap<String,String> hm = new HashMap<String,String>();
	boolean starting=true;
	public String fromSquare="";//Square to move a piece from.
	public String toSquare="";	//Square to move a piece to.
	public String square; 		//Current touched square.
	public String thisIsThePieceToBeMoved;//Exactly what it sounds like.
	public String thisIsOnSquare="";
	public String pieceMovement;
	String movingPiece="";
	public float squareWidthF;

	boolean soundOn = true;
	public boolean notationOn=false;
	TextView tv;
	SoundPool sp;
	int soundId;
	public static ChessBoard chessboard;//class that paints the chess board.
	//ArrayLists that left arrow reads from:
	static ArrayList<String> fromList = new ArrayList<String>();
	static ArrayList<String> toList = new ArrayList<String>();
	static ArrayList<String> pieceMovedList = new ArrayList<String>();
	static ArrayList<String> pieceCapturedList = new ArrayList<String>();
	//ArrayLists that right arrow reads from: 
	static ArrayList<String> fromListForward = new ArrayList<String>();
	static ArrayList<String> toListForward = new ArrayList<String>();
	static ArrayList<String> pieceMovedListForward = new ArrayList<String>();
	static ArrayList<String> pieceCapturedListForward = new ArrayList<String>();
	//MovePiece is called double - this is to filter away the second call:
	static String lastFrom="";
	static String lastTo="";
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Log.d("in onCreate", "entering");
		DisplayMetrics metrics = new DisplayMetrics();//Get the dimensions of the screen.
		getWindowManager().getDefaultDisplay().getMetrics(metrics);       
		width=metrics.widthPixels;
		height=metrics.heightPixels;
		sp = new SoundPool(5,AudioManager.STREAM_MUSIC,0);
		soundId = sp.load(this.getApplicationContext(), R.raw.movement, 1);
		if(starting)//run this when the game is first launched. Maybe onCreate is only run when game is first launched? 
		{
			gameStart();//fills the hash map with strings "white king" "black pawn" et.c with keys "e1" "f7" etc.
			starting=false;
		}
		chessboard = new ChessBoard(this);
		setContentView(chessboard);
		chessboard.setOnTouchListener(this);
		//chessboard.setOnDragListener(new MyDragListener());
		Log.d("on Create","exiting");
		PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
		SharedPreferences.OnSharedPreferenceChangeListener listener =
				new SharedPreferences.OnSharedPreferenceChangeListener() {
			public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
				// listener implementation
			}
		};
		// mp.start();
	}

	protected void onResume() {
		super.onResume();
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		//String background = sharedPref.getString(SettingsActivity.KEY_BACKGROUND_COLOR, "");
		//Log.d("in MainActivity.onResume(), this is background:",""+background+"");
		String chosenSet = sharedPref.getString(SettingsActivity.KEY_CHESS_SET, "");
		Log.d("in MainActivity.onResume(), this is chess set:",""+chosenSet+"");
		boolean sound = sharedPref.getBoolean(SettingsActivity.KEY_SOUND_ON, true);
		boolean notation = sharedPref.getBoolean(SettingsActivity.KEY_NOTATION,false);
		Log.d("notation: ", "" + notation + "");
		//this works - notation is set to the right value from the chosen preference.
		String orientation = sharedPref.getString(SettingsActivity.KEY_ORIENTATION, "");
		pieceMovement = sharedPref.getString(SettingsActivity.KEY_MOVEMENT, "");
		setSound(sound);
		setNotation(notation);
		Log.d("notationOn",""+notationOn+"");
		Log.d("chessboard.no",""+chessboard.notationOn+"");
		//this works too, both booleans are set to true when notation is selected and false when deselected.
		//setBackground(background);
		setChessSet(chosenSet);
		setOrientation(orientation);
		Log.d("Orientation: ", "" + orientation + "");
		chessboard.invalidate();
		//mp.start();
		Log.d("in onResume", "sound: " + sound + "");
		//Here should release resources and load new images. 
		/*getPreferenceScreen().getSharedPreferences()
	            .registerOnSharedPreferenceChangeListener(this);*/
	}


	protected void onPause() {
		super.onPause();
		/* getPreferenceScreen().getSharedPreferences()
	            .unregisterOnSharedPreferenceChangeListener(this);*/
	}
	/*void setBackground(String colorChosen)
	{
		if(colorChosen.equals("grey"))
		{
			chessboard.backgroundColor=Color.GRAY;
		}
		else if(colorChosen.equals("blue"))
		{
			chessboard.backgroundColor=Color.BLUE;
		}
		else if(colorChosen.equals("green"))
		{
			chessboard.backgroundColor=Color.GREEN;
		}
	}*/
	void setChessSet(String chosenSet)
	{
		if(chosenSet.equals("wood"))
		{
			chessboard.chessSet="wood";
		}
		else if(chosenSet.equals("eyes"))
		{
			chessboard.chessSet="eyes";
		}
		else if(chosenSet.equals("staunton"))
		{
			chessboard.chessSet="staunton";
		}
		else if(chosenSet.equals("freak"))
		{
			chessboard.chessSet="freak";
		}
	}
	void setSound(boolean sound)
	{
		if(sound)
		{
			soundOn = true;
		}
		else
		{
			soundOn = false;
		}
	}
	void setNotation(boolean notation)
	{
		if(notation)
		{
			chessboard.notationOn = true;
			notationOn=true;
		}
		else
		{
			chessboard.notationOn = false;
			notationOn=false;
		}
	}
	void setOrientation(String orientation)   
	{
		chessboard.orientation=orientation;
	}
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.action_preferences:
			invokeSettings();
			return true;
		}
		return true;
	}
	private void invokeSettings() {
		Intent settingsIntent = new Intent(this, SettingsActivity.class);
		startActivity(settingsIntent);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);//Mumbo Jumbo
		Log.d("in OnCreateOptionsMenu", "");
		return true;
	}
	public String querySquare(String coordinates) //What piece is on the square?
	{
		Log.d("inquerySquare","entering");
		String thisIsAnswer = hm.get(coordinates);
		Log.d("in querySquare", "this is answer_ " + thisIsAnswer);
		Log.d("inquerySquare","exiting");
		if (thisIsAnswer==null)
		{
			thisIsAnswer="empty";
		}
		return thisIsAnswer;
	}
	public static void gameStart()//Fills the hash map according to the form <key> <value> = "a1","white rook"
	{
		Log.d("in gameStart","entering");	
		hm.put("a2","white pawn");
		hm.put("b2","white pawn");
		hm.put("c2","white pawn");
		hm.put("d2","white pawn");
		hm.put("e2","white pawn");
		hm.put("f2","white pawn");
		hm.put("g2","white pawn");
		hm.put("h2","white pawn");
		hm.put("a1","white rook");
		hm.put("b1","white knight");
		hm.put("c1","white bishop");
		hm.put("d1","white queen");
		hm.put("e1","white king");
		hm.put("f1","white bishop");
		hm.put("g1","white knight");
		hm.put("h1","white rook");
		//
		hm.put("a7", "black pawn");
		hm.put("b7", "black pawn");
		hm.put("c7", "black pawn");
		hm.put("d7", "black pawn");
		hm.put("e7", "black pawn");
		hm.put("f7", "black pawn");
		hm.put("g7", "black pawn");
		hm.put("h7", "black pawn");
		hm.put("a8", "black rook");
		hm.put("b8","black knight");
		hm.put("c8","black bishop");
		hm.put("d8","black queen");
		hm.put("e8","black king");
		hm.put("f8", "black bishop");
		hm.put("g8", "black knight");
		hm.put("h8","black rook");
		//
		hm.put("a3","empty");
		hm.put("b3","empty");
		hm.put("c3","empty");
		hm.put("d3","empty");
		hm.put("e3","empty");
		hm.put("f3","empty");
		hm.put("g3","empty");
		hm.put("h3","empty");
		//
		hm.put("a4","empty");
		hm.put("b4","empty");
		hm.put("c4","empty");
		hm.put("d4","empty");
		hm.put("e4","empty");
		hm.put("f4","empty");
		hm.put("g4","empty");
		hm.put("h4","empty");
		//
		hm.put("a5","empty");
		hm.put("b5","empty");
		hm.put("c5","empty");
		hm.put("d5","empty");
		hm.put("e5","empty");
		hm.put("f5","empty");
		hm.put("g5","empty");
		hm.put("h5","empty");
		//
		hm.put("a6","empty");
		hm.put("b6","empty");
		hm.put("c6","empty");
		hm.put("d6","empty");
		hm.put("e6","empty");
		hm.put("f6","empty");
		hm.put("g6","empty");
		hm.put("h6","empty");
		Log.d("testing","testing");
		//placing the extra pieces
		hm.put("i1","white king");
		hm.put("i2","white queen");
		hm.put("i3","white bishop");
		hm.put("j1","white knight");
		hm.put("j2","white rook");
		hm.put("j3","white pawn");
		//placing the extra black pieces:
		hm.put("i6","black king");
		hm.put("i7","black queen");
		hm.put("i8","black bishop");
		hm.put("j6","black knight");
		hm.put("j7","black rook");
		hm.put("j8","black pawn");
		//empty squares rows 9 and 10:
		hm.put("i4","empty");
		hm.put("i5","empty");
		hm.put("j4","empty");
		hm.put("j5","empty");

		Log.d("ingameStart","exiting");
	}
	public static void clearBoard()
	{
		Log.d("in clear board", "entering");
		hm.put("a2","empty");
		hm.put("b2","empty");
		hm.put("c2","empty");
		hm.put("d2","empty");
		hm.put("e2","empty");
		hm.put("f2","empty");
		hm.put("g2","empty");
		hm.put("h2","empty");
		hm.put("a1","empty");
		hm.put("b1","empty");
		hm.put("c1","empty");
		hm.put("d1","empty");
		hm.put("e1","empty");
		hm.put("f1","empty");
		hm.put("g1","empty");
		hm.put("h1","empty");
		//
		hm.put("a7", "empty");
		hm.put("b7", "empty");
		hm.put("c7", "empty");
		hm.put("d7", "empty");
		hm.put("e7", "empty");
		hm.put("f7", "empty");
		hm.put("g7", "empty");
		hm.put("h7", "empty");
		hm.put("a8", "empty");
		hm.put("b8","empty");
		hm.put("c8","empty");
		hm.put("d8","empty");
		hm.put("e8","empty");
		hm.put("f8", "empty");
		hm.put("g8", "empty");
		hm.put("h8","empty");
		//
		hm.put("a3","empty");
		hm.put("b3","empty");
		hm.put("c3","empty");
		hm.put("d3","empty");
		hm.put("e3","empty");
		hm.put("f3","empty");
		hm.put("g3","empty");
		hm.put("h3","empty");
		//
		hm.put("a4","empty");
		hm.put("b4","empty");
		hm.put("c4","empty");
		hm.put("d4","empty");
		hm.put("e4","empty");
		hm.put("f4","empty");
		hm.put("g4","empty");
		hm.put("h4","empty");
		//
		hm.put("a5","empty");
		hm.put("b5","empty");
		hm.put("c5", "empty");
		hm.put("a2","empty");
		hm.put("b2","empty");
		hm.put("c2","empty");
		hm.put("d2","empty");
		hm.put("e2","empty");
		hm.put("f2","empty");
		hm.put("g2","empty");
		hm.put("h2","empty");
		hm.put("a1","empty");
		hm.put("b1","empty");
		hm.put("c1","empty");
		hm.put("d1","empty");
		hm.put("e1","empty");
		hm.put("f1","empty");
		hm.put("g1","empty");
		hm.put("h1","empty");
		//
		hm.put("a7", "empty");
		hm.put("b7", "empty");
		hm.put("c7", "empty");
		hm.put("d7", "empty");
		hm.put("e7", "empty");
		hm.put("f7", "empty");
		hm.put("g7", "empty");
		hm.put("h7", "empty");
		hm.put("a8", "empty");
		hm.put("b8","empty");
		hm.put("c8","empty");
		hm.put("d8","empty");
		hm.put("e8","empty");
		hm.put("f8", "empty");
		hm.put("g8", "empty");
		hm.put("h8","empty");
		//
		hm.put("a3","empty");
		hm.put("b3","empty");
		hm.put("c3","empty");
		hm.put("d3","empty");
		hm.put("e3","empty");
		hm.put("f3","empty");
		hm.put("g3","empty");
		hm.put("h3","empty");
		//
		hm.put("a4","empty");
		hm.put("b4","empty");
		hm.put("c4","empty");
		hm.put("d4","empty");
		hm.put("e4","empty");
		hm.put("f4","empty");
		hm.put("g4","empty");
		hm.put("h4","empty");
		//
		hm.put("a5","empty");
		hm.put("b5","empty");
		hm.put("c5","empty");
		hm.put("d5","empty");
		hm.put("e5","empty");
		hm.put("f5","empty");
		hm.put("g5","empty");
		hm.put("h5","empty");
		//
		hm.put("a6","empty");
		hm.put("b6","empty");
		hm.put("c6","empty");
		hm.put("d6","empty");
		hm.put("e6","empty");
		hm.put("f6","empty");
		hm.put("g6","empty");
		hm.put("h6","empty");
		Log.d("testing","testing");
		//placing the extra pieces
		hm.put("i1","white king");
		hm.put("i2","white queen");
		hm.put("i3","white bishop");
		hm.put("j1","white knight");
		hm.put("j2","white rook");
		hm.put("j3","white pawn");
		//placing the extra black pieces:
		hm.put("i6","black king");
		hm.put("i7","black queen");
		hm.put("i8","black bishop");
		hm.put("j6","black knight");
		hm.put("j7","black rook");
		hm.put("j8","black pawn");
		//empty squares rows 9 and 10:
		hm.put("i4","empty");
		hm.put("i5","empty");
		hm.put("j4","empty");
		hm.put("j5","empty");

		Log.d("ingameStart","exiting");
		hm.put("d5","empty");
		hm.put("e5","empty");
		hm.put("f5","empty");
		hm.put("g5","empty");
		hm.put("h5","empty");
		//
		hm.put("a6","empty");
		hm.put("b6","empty");
		hm.put("c6","empty");
		hm.put("d6","empty");
		hm.put("e6","empty");
		hm.put("f6","empty");
		hm.put("g6","empty");
		hm.put("h6","empty");
		Log.d("testing","testing");
		//placing the extra pieces
		hm.put("i1","white king");
		hm.put("i2","white queen");
		hm.put("i3","white bishop");
		hm.put("j1","white knight");
		hm.put("j2","white rook");
		hm.put("j3","white pawn");
		//placing the extra black pieces:
		hm.put("i6","black king");
		hm.put("i7","black queen");
		hm.put("i8","black bishop");
		hm.put("j6","black knight");
		hm.put("j7","black rook");
		hm.put("j8","black pawn");
		//empty squares rows 9 and 10:
		hm.put("i4","empty");
		hm.put("i5","empty");
		hm.put("j4","empty");
		hm.put("j5","empty");

		Log.d("ingameStart","exiting");
	}
	public void movePiece(String from,String to, boolean movingBack, boolean movingInForwardList )//Make changes to the hash map, vacating one square and occupying a
	{
		if(lastFrom.equals(from)&&lastTo.equals(to))
		{
			//This is a duplicate run, do nothing.Added because somewhere movePiece was being called twice per move.
		}

		else
		{
			Log.d("In movePiece()",""+"this is from square "+from+", this is to square "+to);
			String column = from.substring(0,1);
			String column2 = to.substring(0,1);
			String capturedPiece = querySquare(to);
			Log.d("in movePiece","this is column: "+column);
			//if moving from an area inside the board, vacate from-square, otherwise not:
			if(!((column.equals("i"))||(column.equals("j"))))
			{	
				hm.put(from,"empty");//piece leaving square
				hm.put(to,thisIsThePieceToBeMoved);//piece arriving at square.
			}
			else
			{	
				hm.put(to,thisIsThePieceToBeMoved);	
				Log.d("in movePiece","this is outside the board");	
			}
			if(!movingBack)
			{
				Log.d("in movePiece","in ! movingBack-block");
				/*
				{*/
				//Would actually only have to do this the first time a movement that is not backwards is called, 
				//but it is easier programming-wise to just do it every time. 
				if(!(((column.equals("i"))||(column.equals("j")))&&((column2.equals("i"))||(column2.equals("j")))))
				{
					Log.d("in movePiece, after complicated logic","column: "+column+" column2: "+column2+" ");
					if(!movingInForwardList)
					{
						fromListForward.clear();
						toListForward.clear();
						pieceMovedListForward.clear();
						pieceCapturedListForward.clear();
					}
				}
				Log.d("in movePiece","adding to lists");
				fromList.add(from);
				toList.add(to);
				pieceMovedList.add(thisIsThePieceToBeMoved);
				pieceCapturedList.add(capturedPiece);
			}
			else if(movingBack)
			{
				Log.d("in movePiece","in movingBack-block");
				fromListForward.add(from);
				toListForward.add(to);
				pieceMovedListForward.add(thisIsThePieceToBeMoved);
				pieceCapturedListForward.add(capturedPiece);
			}
			if(soundOn)
			{ 
				sp.play(soundId, 1, 1, 0, 0, 1);			
				//playSoundClip();
			}
			lastFrom=from;
			lastTo=to;
		}
	}
	void playSoundClip()
	{
		/*if(movementSound!=null) 
		{   movementSound.reset();
			movementSound.release(); } 
		movementSound = MediaPlayer.create(this, R.raw.movement);
		movementSound.start();*/

	}
	@Override
	public boolean onTouch(View v, MotionEvent event) //is called on Touch.
	{
		//Figure out which square touch is on and assign it to fromSquare:
		float temp=squareWidthF/2;
		int margin = (int)temp/2;
		if(width<height)
		{
			if(!notationOn)
			{
				squareWidthF = width / 8;
				margin=0;
			}
			else
			{
				squareWidthF = width /9;
			}
		}
		else
		{
			if(!notationOn)
			{
				squareWidthF = height / 8;
				margin=0;
			}
			else
			{
				squareWidthF = height/9;
			}
		}
		//Här är det!
		if(event.getY()<margin+squareWidthF*11)
		{
			if(pieceMovement.equals("Drag and Drop"))
			{
				float xCo=0;
				float yCo=0;
				switch(event.getAction() & MotionEvent.ACTION_MASK)
				{	
				case MotionEvent.ACTION_DOWN: 

					//Assigning the coordinates using the 'internal' coord. 
					//system where A1 is at 0,0.
					if(chessboard.orientation.equals("white_left"))
					{	
						if(event.getY()<width)
						{
							xCo = event.getX();
							yCo = event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_up"))
					{
						if(event.getY()<width)
						{
							yCo = width-event.getX();
							xCo = event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_down"))
					{
						if(event.getY()<width)
						{
							yCo=event.getX();
							xCo = width - event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_right"))
					{
						if(event.getY()<width)
						{
							xCo=width-event.getX();
							yCo=width-event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					Log.d("in MainActivity.onTouch()","yCo: "+yCo+" xCo: "+xCo+" ");
					float squareWidthF;
					String row="";
					int i;
					int j;
					if(width<height)
					{
						if(!notationOn)
						{
							squareWidthF = width / 8;
							margin=0;
						}
						else
						{
							squareWidthF = width/9;
						}
					}
					else
					{
						if(!notationOn)
						{
							squareWidthF = height / 8;
							margin=0;
						}
						else
						{
							squareWidthF = height / 9;
						}
					}
					i=0;
					j=0;
					for (i=1;i<9;i++)
					{	
						if (margin+squareWidthF*i-squareWidthF < xCo && xCo < margin+squareWidthF*i)
						{
							int k=i; 
							Log.d("in OnTouch","this is column "+i);
							for (j=1;j<13;j++)
							{	
								if (margin+squareWidthF*j-squareWidthF < yCo && yCo < margin+squareWidthF*j)
								{	
									switch(j)
									{
									case 1: row="a";
									break;
									case 2: row="b";
									break;
									case 3: row="c";
									break;
									case 4: row="d";
									break;
									case 5: row="e";
									break;
									case 6: row="f";
									break;
									case 7: row="g";
									break;
									case 8: row="h";
									break;
									case 9: row="i";
									break;
									case 10: row="j";
									break;   			
									}
									Log.d("in OnTouch","this is "+row+k);
									square = row+k;
									if(yCo>squareWidthF*8)
									{
										if(notationOn)
										{
											k=findCorrectColumn(xCo);
											row=findCorrectRow(yCo);
											square=row+k;
											Log.d("ran findCorrectColumn","square: "+square+"");
										}
									}
									Log.d("obladi","this is square");
								}
							}
						}
					}
					fromSquare=square;
					Log.d("in onTouch","B touch registered");
					thisIsOnSquare = querySquare(square);
					if(!thisIsOnSquare.equals("empty"))
					{
						if(thisIsOnSquare.equals("black rook"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("black rook",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("black knight"))
						{
							//chessboard.blackKnight.setVisible(false, false);
							chessboard.setMovingPiece("black knight",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("black bishop"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("black bishop",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("black queen"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("black queen",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("black king"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("black king",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("black pawn"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("black pawn",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white rook"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("white rook",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white knight"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("white knight",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white bishop"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("white bishop",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white queen"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("white queen",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white king"))
						{
							//chessboard.blackRook.setVisible(false, faevent.getY()>extraMargin+margin+squareWidthF*verticallse);
							chessboard.setMovingPiece("white king",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						if(thisIsOnSquare.equals("white pawn"))
						{
							//chessboard.blackRook.setVisible(false, false);
							chessboard.setMovingPiece("white pawn",(int)event.getX(),(int)event.getY());
							chessboard.setMovingFromSquare(square);
						}
						chessboard.invalidate();
					}
					//hm.put(square, "empty");
					break;

				case MotionEvent.ACTION_UP: 
					chessboard.up=true;
					chessboard.setDragging(false);
					chessboard.dragging=false;
					thisIsThePieceToBeMoved=chessboard.getMovingPiece();
					//chessboard.setMovingPiece("", 0, 0);
					chessboard.setMovingFromSquare("");
					Log.d("onTouch","ACTION_UP has been called");
					//
					//Assigning the coordinates using the 'internal' coord. 
					//system where A1 is at 0,0.
					if(chessboard.orientation.equals("white_left"))
					{	
						if(event.getY()<width)
						{
							xCo = event.getX();
							yCo = event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_up"))
					{
						if(event.getY()<width)
						{
							yCo = width-event.getX();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();Log.d("in gameStart","entering");
						}
					}
					else if(chessboard.orientation.equals("white_down"))
					{
						if(event.getY()<width)
						{
							yCo=event.getX();
							xCo = width - event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_right"))
					{
						if(event.getY()<width)
						{
							xCo=width-event.getX();
							yCo=width-event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					Log.d("in MainActivity.onTouch()","yCo: "+yCo+" xCo: "+xCo+" ");
					row="";
					if(width<height)
					{
						if(!notationOn)
						{
							squareWidthF = width / 8;
							margin=0;
						}
						else
						{
							squareWidthF = width / 9;
						}
					}
					else
					{
						if(!notationOn)
						{
							squareWidthF = height / 8;
							margin=0;
						}
						else
						{
							squareWidthF = height / 9;
						}
					}

					for (i=1;i<9;i++)
					{	
						if (2*margin+squareWidthF*i-squareWidthF < xCo && xCo < 2*margin+squareWidthF*i)
						{
							int k=i; 
							Log.d("in OnTouch","this is column "+i);
							for (j=1;j<11;j++)
							{	
								if (margin+squareWidthF*j-squareWidthF < yCo && yCo < margin+squareWidthF*j)
								{	
									switch(j)
									{
									case 1: row="a";
									break;
									case 2: row="b";
									break;
									case 3: row="c";
									break;
									case 4: row="d";
									break;
									case 5: row="e";
									break;
									case 6: row="f";
									break;
									case 7: row="g";
									break;
									case 8: row="h";
									break;
									case 9: row="i";
									break;
									case 10: row="j";
									break;   			
									}
									Log.d("in OnTouch","this is "+row+k);
									square = row+k;
								}
							}
						}

					}
					toSquare=square;
					Log.d("obladi","fromSquare: "+fromSquare+"");
					Log.d("obladi","toSquare: "+toSquare+"");
					/*
					if (!fromSquare.equals(toSquare))
					{
						movePiece(fromSquare,toSquare,false,false);
						chessboard.invalidate();
					}*/
					/*if(fromSquare.equals(toSquare))
					{
						//chessboard.setMovingPiece("",0,0);
						//chessboard.setMovingFromSquare("");
						//thisIsThePieceToBeMoved="";
					}*/

						movePiece(fromSquare, toSquare, false, false);
						//hm.put(toSquare,"black rook");
						//	fromSquare="";
						chessboard.invalidate();
					Log.d("in onTouch","B touch registered");
					chessboard.setMovingPiece("",0,0);

					break;
					//

				case MotionEvent.ACTION_MOVE: //dO STUFF.
					if(thisIsOnSquare.equals("black rook"))
					{
						chessboard.setDragging(true);
						chessboard.setMovingPiece("black rook",(int)event.getX(),(int)event.getY());
						chessboard.invalidate();
						movingPiece="black rook";
					}
					break;

				}
			}

			//
			else if(pieceMovement.equals("Two Touch"))
			{


				Log.d("in onTouch","entering");
				if (event.getAction() == MotionEvent.ACTION_DOWN)
				{
					float xCo=0;
					float yCo=0;
					//Assigning the coordinates using the 'internal' coord. 
					//system where A1 is at 0,0.
					if(chessboard.orientation.equals("white_left"))
					{	
						if(event.getY()<width)
						{
							xCo = event.getX();
							yCo = event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_up"))
					{
						if(event.getY()<width)
						{
							yCo = width-event.getX();
							xCo = event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_down"))
					{
						if(event.getY()<width)
						{
							yCo=event.getX();
							xCo = width - event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					else if(chessboard.orientation.equals("white_right"))
					{
						if(event.getY()<width)
						{
							xCo=width-event.getX();
							yCo=width-event.getY();
						}
						else
						{
							xCo = event.getX();
							yCo = event.getY();
						}
					}
					Log.d("in MainActivity.onTouch()","yCo: "+yCo+" xCo: "+xCo+" ");
					float squareWidthF;
					String row="";
					int i;
					int j;
					if(width<height)
					{
						if(!notationOn)
						{
							squareWidthF = width / 8;
							margin=0;
						}
						else
						{
							squareWidthF = width / 9;
						}
					}
					else
					{
						if(!notationOn)
						{
							squareWidthF = height / 8;
							margin=0;
						}
						else
						{
							squareWidthF = height / 9;
						}
					}
					Log.d("in OnTouch","A");
					for (i=1;i<9;i++)
					{	
						if (margin+squareWidthF*i-squareWidthF < xCo && xCo < margin+squareWidthF*i)
						{
							int k=i; 
							Log.d("in OnTouch","this is column "+i);
							Log.d("this is margin","this is margin:"+margin+"");
							for (j=1;j<13;j++)
							{
								if (margin+squareWidthF*j-squareWidthF < yCo && yCo < margin+squareWidthF*j)
								{
									switch(j)
									{
									case 1: row="a";
									break;
									case 2: row="b";
									break;
									case 3: row="c";
									break;
									case 4: row="d";
									break;
									case 5: row="e";
									break;
									case 6: row="f";
									break;
									case 7: row="g";
									break;
									case 8: row="h";
									break;
									case 9: row="i";
									break;
									case 10: row="j";
									break;
									}
									square = row+k;
									if(yCo>squareWidthF*8)
									{
										if(notationOn)
										{
											k=findCorrectColumn(xCo);
											row=findCorrectRow(yCo);
											square=row+k;
											Log.d("ran findCorrectColumn","square: "+square+"");
										}
									}
								}
							}
							/*for(j=9;j<11;j++)
							{
								if (2*margin+squareWidthF*j-squareWidthF < yCo && yCo < 2*margin+squareWidthF*j)
								{
									switch(j)
									{event.getY()>extraMargin+margin+squareWidthF*vertical
										case 10: row="i";
											break;
										case 11: row="j";
											break;
									}
									Log.d("in OnTouch","this is "+row+k);
									square = row+k;
								}
							}*/
						}
					}

					Log.d("in onTouch","B touch registered");
					String thisIsOnSquare = querySquare(square);
					Log.d("in onTouch","this is on this square: "+thisIsOnSquare);
					if (thisIsOnSquare.equals("empty"))
					{
						Log.d("in OnTouch","C EMPTY ");
						if(fromSquare.equals(""))
						{
							/*do nothing*/
						}
						else 
						{ 
							toSquare=square;
							thisIsThePieceToBeMoved=querySquare(fromSquare);
							if(yCo<squareWidthF*10)
							{
								movePiece(fromSquare,toSquare,false,false);					
								chessboard.invalidate();
							}
						}
						Log.d("in OnTouch","D");
					} 

					else
					{
						if(fromSquare.equals(""))
						{	
							fromSquare=square;
							Log.d("in OnTouch","fromSquare: "+square);
						}
						else
						{ 
							toSquare=square;
							/*        		fromSquare="e2";
        		toSquare="e4";*/
							Log.d("in onTouch","this is toSquare"+toSquare);
							thisIsThePieceToBeMoved=querySquare(fromSquare);
							if(yCo<squareWidthF*10)
							{
								movePiece(fromSquare,toSquare,false,false);					
								chessboard.invalidate();
							}
						}	
					}
					//Does this do anything?:
					boolean moveItConditionOne = !(fromSquare.equals(""))&&!(fromSquare.equals("empty"));
					boolean moveItConditionTwo = !(toSquare.equals(""))&&!(fromSquare.equals("empty"));     
					System.out.println("c1"+moveItConditionOne);
					System.out.println("c2"+moveItConditionTwo);
					if(moveItConditionTwo&&moveItConditionOne)
					{
						Log.d("in OnTouch","E");
						if(yCo<squareWidthF*10)
						{
							movePiece(fromSquare,toSquare,false,false);					
						}
						Log.d("in OnTouch","F");
						chessboard.invalidate();
						Log.d("in conditionloop","invalidating");
						Log.d("in OnTouch","G");
						fromSquare="";
						toSquare="";
					}
				}
			}
		}
		//From here it does not matter if it is drag and drop or two touch.
		//Check if any of the left or right arrows have been tapped: 
		//MotionEvent.ACTION_DOWN
		//MotionEvent.ACTION_UP
		int action = event.getAction() & MotionEvent.ACTION_MASK;
//STUFF AND SHIT.
		int vertical;
		int extraMargin=0;
		if(!notationOn)
		{
			vertical=10;
		}
		else
		{
			vertical=11;
			extraMargin = (int)(width/24);
		}
		switch(action)
		{
		case MotionEvent.ACTION_DOWN:
			if((event.getX()>margin+squareWidthF*2) && (event.getX()<margin+squareWidthF*4))
			{
				if((event.getY()>extraMargin+margin+squareWidthF*vertical) && (event.getY()<extraMargin+margin+squareWidthF*(vertical+1)))
				{
					if(fromList.size()>0)
					{
						Log.d("In MainActivity.onTouch","left arrow tapped.");
						thisIsThePieceToBeMoved=pieceMovedList.get(pieceMovedList.size()-1);
						movePiece(toList.get(toList.size()-1),fromList.get(fromList.size()-1),true,false);
						hm.put(toList.get(toList.size()-1),pieceCapturedList.get(pieceCapturedList.size()-1));
						Log.d("in mainActivity.onTouch","should have moved "+thisIsThePieceToBeMoved+" from "+toList.get(toList.size()-1)+" to "+fromList.get(fromList.size()-1)+"");
						pieceMovedList.remove(pieceMovedList.size()-1);
						toList.remove(toList.size()-1);
						fromList.remove(fromList.size()-1);
						pieceCapturedList.remove(pieceCapturedList.size()-1);
						chessboard.invalidate();
					}
					else{//do nothing
					}

				}
			}
			//Here is the place to put in the reaction to clear the board or reset the board.
			if(event.getX()<margin+squareWidthF*2)
			{
				if((event.getY()>extraMargin+margin+squareWidthF*vertical) && (event.getY()<extraMargin+margin+squareWidthF*(vertical+1)))
				{
					new AlertDialog.Builder(this)
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setTitle("Closing Activity")
							.setMessage("Clear board and movement history?")
							.setPositiveButton("Yes", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which) {
									clearBoard();clearLists();chessboard.invalidate();
								}

							})
							.setNegativeButton("No", null)
							.show();
					//clear board.
				}
			}
			if(event.getX()>margin+squareWidthF*6)
			{
				if((event.getY()>extraMargin+margin+squareWidthF*vertical) && (event.getY()<extraMargin+margin+squareWidthF*(vertical+1)))
				{
					new AlertDialog.Builder(this)
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setTitle("Closing Activity")
							.setMessage("Reset board and movement history?")
							.setPositiveButton("Yes", new DialogInterface.OnClickListener()
							{
								@Override
								public void onClick(DialogInterface dialog, int which) {
									gameStart();clearLists();chessboard.invalidate();
								}

							})
							.setNegativeButton("No", null)
							.show();
					//reset board.

				}
			}



			if((event.getX()>margin+squareWidthF*4) && (event.getX()<margin+squareWidthF*6))
			{
				if((event.getY()>extraMargin+margin+squareWidthF*vertical) && (event.getY()<extraMargin+margin+squareWidthF*(vertical+1)))
				{
					if(toListForward.size()>0)
					{
						Log.d("In MainActivity.movePiece()","right arrow tapped.");
						thisIsThePieceToBeMoved=pieceMovedListForward.get(pieceMovedListForward.size()-1);
						movePiece(toListForward.get(toListForward.size()-1),fromListForward.get(fromListForward.size()-1),false,true);
						hm.put(toListForward.get(toListForward.size()-1),pieceCapturedListForward.get(pieceCapturedListForward.size()-1));
						pieceMovedListForward.remove(pieceMovedListForward.size()-1);
						toListForward.remove(toListForward.size()-1);
						fromListForward.remove(fromListForward.size()-1);	
						pieceCapturedListForward.remove(pieceCapturedListForward.size()-1);
						chessboard.invalidate();
					}
					else
					{
						//do nothing
					}
				}
			}
			Log.d("at end of on touch","this should only show once for every touch");

		case MotionEvent.ACTION_UP:break;
		}
		Log.d("in onTouch","exiting");
		return true;

	}
	void dragAndDropMove(String piece,String fromSquare, String toSquare, View view)

	{
		ClipData data = ClipData.newPlainText("", "");
		DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
		view.startDrag(data, shadowBuilder, view, 0);
		view.setVisibility(View.INVISIBLE);
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
		.setIcon(android.R.drawable.ic_dialog_alert)
		.setTitle("Closing Activity")
		.setMessage("Are you sure you want to exit Chessboard?")
		.setPositiveButton("Yes", new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();    
			}

		})
		.setNegativeButton("No", null)
		.show();
	}
public void clearLists()
{
	fromList.clear();
	toList.clear();
	pieceMovedList.clear();
	pieceCapturedList.clear();
	fromList.clear();
	fromListForward.clear();
	toListForward.clear();
	pieceMovedListForward.clear();
	pieceCapturedListForward.clear();
}
	public int findCorrectColumn(float x)
	{
		int extraMargin=(int)(width/24);
		float temp = squareWidthF/2;
		int margin= (int)temp;
		int column=0;
		if(x<extraMargin+margin+squareWidthF)
		{
			column = 1;
		}
		else if(x>extraMargin+margin+squareWidthF && x<2*extraMargin+margin+2*squareWidthF)
		{
			column = 2;
		}
		else if(x>2*extraMargin+margin+2*squareWidthF && x<3*extraMargin+margin+3*squareWidthF)
		{
			column = 3;
		}
		else if(x>3*extraMargin+margin+3*squareWidthF && x<extraMargin+margin+squareWidthF*5)
		{
			column=6;
		}
		else if(x>extraMargin+margin+squareWidthF*5 && x<2*extraMargin+margin+squareWidthF*6)
		{
			column=7;
		}
		else if(x>2*extraMargin+margin+squareWidthF*6 && x<3*extraMargin+margin+squareWidthF*7)
		{
			column=8;
		}
		return column;
	}
	public String findCorrectRow(float y)
	{
		int extraMargin=(int)(width/24);
		float temp = squareWidthF/2;
		int margin= (int)temp;
		float tempTwo = temp*2;
		int lowerMargin = (int)tempTwo;
		String row = "";
		if(y>lowerMargin+squareWidthF*8 && y<lowerMargin+extraMargin+squareWidthF*9)
		{
			row="i";
		}
		else if(y>lowerMargin+extraMargin+squareWidthF*9 && y<2*extraMargin + lowerMargin + squareWidthF*10)
		{
			row="j";
		}
		return row;
	}

}
