package shumpi.chesstwo;
//
//Draws the chess board and the pieces.
//


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
public class ChessBoard extends View {
	//Constructor: 
	Paint p = new Paint();
	//Define colors:
	private int lightBlue = Color.rgb(9,230,226);//Hex, close to 21FCF1
	private int white = Color.rgb(255, 255, 255);
	//hex color code: 8C3F1D brown
	//lighter: CC6537
	//eggshell for the brown: FFE0C7
	public boolean up=false;
	Drawable whitePawn;
	private Drawable blackPawn;
	private Drawable whiteRook;
	Drawable blackRook;
	private Drawable whiteKnight;
	private Drawable blackKnight;
	private Drawable whiteBishop;
	private Drawable blackBishop;
	private Drawable whiteQueen;
	private Drawable blackQueen;
	private Drawable whiteKing;
	private Drawable blackKing;

	//Changeable by settings: 
	public int backgroundColor = Color.GRAY;
	public String chessSet = "wood";
	public String orientation ="white_left";
	public String checkMovingFromSquare="";
	private Drawable whiteMarble;
	private Drawable blackMarble;
	private Drawable whitePawnEyes;
	private Drawable whiteRookEyes;
	private Drawable whiteKnightEyes;
	private Drawable whiteBishopEyes;
	private Drawable whiteQueenEyes;
	private Drawable whiteKingEyes;
	private Drawable blackPawnEyes;
	private Drawable blackRookEyes;
	private Drawable blackKnightEyes;
	private Drawable blackBishopEyes;
	private Drawable blackQueenEyes;
	private Drawable blackKingEyes;
	private Drawable blackPawnStaunton;
	private Drawable blackRookStaunton;
	private Drawable blackKnightStaunton; 
	private Drawable blackBishopStaunton;
	private Drawable blackQueenStaunton;
	private Drawable blackKingStaunton;
	private Drawable whitePawnStaunton;
	private Drawable whiteRookStaunton;
	private Drawable whiteKnightStaunton; 
	private Drawable whiteBishopStaunton;
	private Drawable whiteQueenStaunton;
	private Drawable whiteKingStaunton;
	private Drawable leftArrow;
	private Drawable rightArrow;
	private Drawable resetButton;
	private Drawable clearButton;
	//
	private String movingPiece="";
	public float squareWidthF;
	public int squareWidthI;
	private Bitmap drawGraph;
	private boolean starting=true;
	private int movingPieceX;
	private int movingPieceY;
	public boolean dragging=false;
	public ChessBoard(Context context) {
		super(context);
		setFocusable(true);
		Log.d("in constructor","beginning");
		//Define square width:
		int width = MainActivity.width;
		int height = MainActivity.height;
		//Log.d("this is MainActivity.width",""+width);
		//Log.d("this is MainActivity.height",""+height);
		Bitmap bitmap = Bitmap.createBitmap(height,width,Bitmap.Config.RGB_565);
		if(width<height)
		{
			squareWidthF=width/8;
		}
		else
		{
			squareWidthF=height/8;
		}
		squareWidthI=(int)squareWidthF;
		loadDrawable(chessSet, context);
		//Get the pawn image:
		/*whitePawn = context.getResources().getDrawable(R.drawable.pawnwwooden);
		whiteRook = context.getResources().getDrawable(R.drawable.rookwwooden);
		whiteKnight = context.getResources().getDrawable(R.drawable.knightwwooden);
		blackPawn = context.getResources().getDrawable(R.drawable.pawnbwooden);
		blackRook = context.getResources().getDrawable(R.drawable.rookbwooden);
		blackKnight = context.getResources().getDrawable(R.drawable.knightbwooden);
		whiteBishop = context.getResources().getDrawable(R.drawable.bishopwwooden);
		blackBishop = context.getResources().getDrawable(R.drawable.bishopbwooden);
		whiteQueen = context.getResources().getDrawable(R.drawable.queenwwooden);
		blackQueen = context.getResources().getDrawable(R.drawable.queenbwooden);
		whiteKing = context.getResources().getDrawable(R.drawable.kingwwooden);
		blackKing = context.getResources().getDrawable(R.drawable.kingbwooden);*/
		whiteMarble = context.getResources().getDrawable(R.drawable.marble_low_res_white);
		blackMarble = context.getResources().getDrawable(R.drawable.marble_low_res);
		/*whitePawnEyes = context.getResources().getDrawable(R.drawable.pawn_w_eyes);
		whiteRookEyes = context.getResources().getDrawable(R.drawable.rook_w_eyes);
		whiteKnightEyes = context.getResources().getDrawable(R.drawable.knight_w_eyes);
		whiteBishopEyes = context.getResources().getDrawable(R.drawable.bishop_w_eyes);
		whiteQueenEyes = context.getResources().getDrawable(R.drawable.queen_w_eyes);
		whiteKingEyes = context.getResources().getDrawable(R.drawable.king_w_eyes);
		blackPawnEyes = context.getResources().getDrawable(R.drawable.pawn_b_eyes);
		blackRookEyes = context.getResources().getDrawable(R.drawable.rook_b_eyes);
		blackKnightEyes = context.getResources().getDrawable(R.drawable.knight_b_eyes);
		blackQueenEyes = context.getResources().getDrawable(R.drawable.queen_b_eyes);
		blackKingEyes = context.getResources().getDrawable(R.drawable.king_b_eyes);
		blackBishopEyes = context.getResources().getDrawable(R.drawable.bishop_b_eyes);
	/*	blackPawnStaunton = context.getResources().getDrawable(R.drawable.black_pawn_free_staunton);
		blackRookStaunton = context.getResources().getDrawable(R.drawable.black_rook_free_staunton);
		blackKnightStaunton = context.getResources().getDrawable(R.drawable.black_knight_free_staunton);
		blackBishopStaunton = context.getResources().getDrawable(R.drawable.black_bishop_free_staunton);
		blackQueenStaunton = context.getResources().getDrawable(R.drawable.black_queen_free_staunton);
		blackKingStaunton = context.getResources().getDrawable(R.drawable.black_king_free_staunton);
		whitePawnStaunton = context.getResources().getDrawable(R.drawable.white_pawn_free_staunton);
		whiteRookStaunton = context.getResources().getDrawable(R.drawable.white_rook_free_staunton);
		whiteKnightStaunton = context.getResources().getDrawable(R.drawable.white_knight_free_staunton);
		whiteBishopStaunton = context.getResources().getDrawable(R.drawable.white_bishop_free_staunton);
		whiteQueenStaunton = context.getResources().getDrawable(R.drawable.white_queen_free_staunton);
		whiteKingStaunton = context.getResources().getDrawable(R.drawable.white_king_free_staunton);*/
		Log.d("in constructor","exiting");
	}
	protected void onDraw(Canvas canvas) {
		Log.d("in onDraw", "entering");
		// super.onDraw(canvas);
		Canvas singleUseCanvas = new Canvas();      
		//is this bitmap ever used?:
		drawGraph = Bitmap.createBitmap(canvas.getWidth(),canvas.getHeight(),Bitmap.Config.ARGB_8888);      
		singleUseCanvas.setBitmap(drawGraph);   

		canvas.drawBitmap(drawGraph, 100, 100, null);
		canvas.drawColor(backgroundColor);//Background      
		p.setColor(Color.WHITE);
		drawBoard(canvas);
		loadDrawable(chessSet, this.getContext());
		if(starting)
		{	
			drawGameStart(canvas);
			starting=false;
		}
		else
		{
			drawCurrentPosition(canvas);
		}

		Log.d("in onDraw","exiting");
	}
	private void drawBoard(Canvas canvas)
	{
		Log.d("In drawBoard", "entering");
		String color = "";
		int i=0;
		int j=0;
		for (j=0;j<8;j++)
		{
			for (i=0;i<8;i++)
			{
				if(i%2==0)
				{   
					if(j%2==0)
					{
						if(orientation.equals("white_left") || orientation.equals("white_right")) {
							color = "black";
							p.setColor(Color.BLACK);
						}
						else
						{
							color="white";
							p.setColor(Color.WHITE);
						}
					}
					else if (j%2==1)
					{
						if(orientation.equals("white_left") || orientation.equals("white_right")) {
							p.setColor(Color.WHITE);
							color = "white";
						}
						else
						{
							p.setColor(Color.BLACK);
							color="black";
						}
					}
				}
				else if(i%2==1)
				{   
					if(j%2==0)
					{
						if(orientation.equals("white_left") || orientation.equals("white_right")) {
							p.setColor(Color.WHITE);
							color = "white";
						}
						else
						{
							p.setColor(Color.BLACK);
							color="black";
						}
					}
					else if(j%2==1)
					{
						if(orientation.equals("white_left") || orientation.equals("white_right")) {
							p.setColor(Color.BLACK);
							color = "black";
						}
						else
						{
							p.setColor(Color.WHITE);
							color="white";
						}
					}
				}
				if(color.equals("black"))
				{
					p.setColor(Color.rgb(115,69,35));
				}
				if(color.equals("white"))
				{
					//eggshell:
					p.setColor(Color.rgb(240,234,214));
				}
				if(chessSet.equals("freak"))
				{
					if(color.equals("black"))
					{
						/*Color lightBlue = new Color();
						float[] floatArray = {(float)196,(float)0.47,(float)0.95};
						int finalColour = Color.HSVToColor(1,floatArray);
						 */
						p.setColor(Color.rgb(129,212,243));
						//p.setColor(Color.BLUE);
					}
					else if(color.equals("white"))
					{
						//p.setColor(Color.rgb(190,203,228));
						//p.setColor(Color.rgb(255,255,231));
						p.setColor(Color.WHITE);
					}
					canvas.drawRect(new RectF(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI),p);
				}
				else
				{
					if(color.equals("white"))
					{
						canvas.drawRect(new RectF(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI),p);
						//whiteMarble.setBounds(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI + squareWidthI,squareWidthI+j*squareWidthI);
						//whiteMarble.draw(canvas);
					}
					else if(color.equals("black"))
					{
						canvas.drawRect(new RectF(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI),p);
						//blackMarble.setBounds(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI);
						//blackMarble.draw(canvas);
					}
				}
				//canvas.drawRect(new RectF(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI),p);

			}
		} 
		int w = canvas.getWidth();
		int h = canvas.getHeight();
		int boardLength = squareWidthI*8;

		Log.d("this is canvas width", "" + w);
		Log.d("this is canvas height", "" + h);
		Log.d("this is board length: ", "" + boardLength);
		Log.d("in drawBoard","exiting");
	}
	private void drawWhitePawn(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whitePawn.setBounds(posX, posY, posX + squareWidthI, posY + squareWidthI);
		whitePawn.draw(canvas);

		/*
		else if(chessSet.equals("eyes"))
		{
			whitePawnEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whitePawnEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{

		}*/

	}
	private void drawWhiteRook(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whiteRook.setBounds(posX, posY, posX + squareWidthI, posY + squareWidthI);
		whiteRook.draw(canvas);

		/*
		else if(chessSet.equals("eyes"))
		{
			whiteRookEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whiteRookEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{

		}*/
	}
	private void drawBlackRook(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackRook.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackRook.draw(canvas);

		/*
		else if(chessSet.equals("eyes"))
		{
			blackRookEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackRookEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{

		}*/
	}
	private void drawWhiteKnight(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whiteKnight.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		whiteKnight.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			whiteKnightEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whiteKnightEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//whiteKnightStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//whiteKnightStaunton.draw(canvas);
		}*/
	}
	private void drawBlackKnight(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackKnight.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackKnight.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			blackKnightEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackKnightEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//blackKnightStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//blackKnightStaunton.draw(canvas);
		}*/
	}
	private void drawWhiteBishop(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whiteBishop.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		whiteBishop.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			whiteBishopEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whiteBishopEyes.draw(canvas);	
		}
		else if(chessSet.equals("staunton"))
		{
				//whiteBishopStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
				//whiteBishopStaunton.draw(canvas);
		}*/
	}
	private void drawBlackBishop(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackBishop.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackBishop.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			blackBishopEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackBishopEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//blackBishopStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//blackBishopStaunton.draw(canvas);
		}*/
	}
	private void drawBlackQueen(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackQueen.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackQueen.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			blackQueenEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackQueenEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//blackQueenStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//blackQueenStaunton.draw(canvas);	
		}*/
	}
	private void drawWhiteQueen(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whiteQueen.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		whiteQueen.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			whiteQueenEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whiteQueenEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//whiteQueenStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//whiteQueenStaunton.draw(canvas);	
		}*/
	}
	private void drawWhiteKing(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		whiteKing.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		whiteKing.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			whiteKingEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			whiteKingEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//whiteKingStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//whiteKingStaunton.draw(canvas);
		}*/
	}
	private void drawBlackKing(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackKing.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackKing.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			blackKingEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackKingEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//blackKingStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//blackKingStaunton.draw(canvas);
		}*/
	}
	private void drawBlackPawn(Canvas canvas,int posX, int posY)
	{
		//I want the image to be the same size as the square, so I don't need to worry about positioning.  
		// Rect imageBounds = canvas.getClipBounds();  // Adjust this for where you want it

		blackPawn.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		blackPawn.draw(canvas);
		/*
		else if(chessSet.equals("eyes"))
		{
			blackPawnEyes.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			blackPawnEyes.draw(canvas);
		}
		else if(chessSet.equals("staunton"))
		{
			//blackPawnStaunton.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
			//blackPawnStaunton.draw(canvas);
		}*/
	}
	private void drawGameStart(Canvas canvas)
	{
		Log.d("in drawGameStart","entering");
		//White Pieces:
		int i=0;
		//Draw white pawns on top row:
		if(orientation.equals("white_left"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,squareWidthI,i*squareWidthI);
				drawBlackPawn(canvas,squareWidthI*6,i*squareWidthI);
			}
			drawWhiteRook(canvas,0,0);
			drawWhiteRook(canvas,0,7*squareWidthI);
			drawWhiteKnight(canvas,0,squareWidthI);
			drawWhiteKnight(canvas,0,squareWidthI*6);
			drawWhiteBishop(canvas,0,2*squareWidthI);   	
			drawWhiteBishop(canvas,0,5*squareWidthI); 
			drawWhiteQueen(canvas,0,3*squareWidthI);
			drawWhiteKing(canvas,0,4*squareWidthI);    
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,squareWidthI*7,0);
			drawBlackRook(canvas,squareWidthI*7,7*squareWidthI);
			drawBlackKnight(canvas,squareWidthI*7,squareWidthI);
			drawBlackKnight(canvas,squareWidthI*7,squareWidthI*6);
			drawBlackBishop(canvas,squareWidthI*7,2*squareWidthI);   	
			drawBlackBishop(canvas,squareWidthI*7,5*squareWidthI); 
			drawBlackQueen(canvas,squareWidthI*7,3*squareWidthI);
			drawBlackKing(canvas,squareWidthI*7,4*squareWidthI);
			Log.d("inDrawGameStart","extra pieces follow now.");
		}
		else if(orientation.equals("white_up"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,i*squareWidthI,squareWidthI);
				drawBlackPawn(canvas,i*squareWidthI,squareWidthI*6);
			}
			drawWhiteRook(canvas,0,0);
			drawWhiteRook(canvas,7*squareWidthI,0);
			drawWhiteKnight(canvas,squareWidthI,0);
			drawWhiteKnight(canvas,squareWidthI*6,0);
			drawWhiteBishop(canvas,2*squareWidthI,0);   	
			drawWhiteBishop(canvas,5*squareWidthI,0); 
			drawWhiteQueen(canvas,4*squareWidthI,0);
			drawWhiteKing(canvas,3*squareWidthI,0);    
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,0,squareWidthI*7);
			drawBlackRook(canvas,squareWidthI*7,7*squareWidthI);
			drawBlackKnight(canvas,squareWidthI,squareWidthI*7);
			drawBlackKnight(canvas,squareWidthI*6,squareWidthI*7);
			drawBlackBishop(canvas,squareWidthI*2,7*squareWidthI);   	
			drawBlackBishop(canvas,squareWidthI*5,7*squareWidthI); 
			drawBlackQueen(canvas,squareWidthI*4,7*squareWidthI);
			drawBlackKing(canvas,squareWidthI*3,7*squareWidthI);
			Log.d("inDrawGameStart","extra pieces follow now."); 
		}
		else if(orientation.equals("white_down"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,i*squareWidthI,squareWidthI*6);
				drawBlackPawn(canvas,i*squareWidthI,squareWidthI);
			}
			/*
			drawWhiteRook(canvas,0,0);
			drawWhiteRook(canvas,7*squareWidthI,0);
			drawWhiteKnight(canvas,squareWidthI,0);
			drawWhiteKnight(canvas,squareWidthI*6,0);
			drawWhiteBishop(canvas,2*squareWidthI,0);   	
			drawWhiteBishop(canvas,5*squareWidthI,0); 
			drawWhiteQueen(canvas,3*squareWidthI,0);
			drawWhiteKing(canvas,4*squareWidthI,0);    
			 */
			drawWhiteRook(canvas,0,squareWidthI*7);
			drawWhiteRook(canvas,squareWidthI*7,7*squareWidthI);
			drawWhiteKnight(canvas,squareWidthI,squareWidthI*7);
			drawWhiteKnight(canvas,squareWidthI*6,squareWidthI*7);
			drawWhiteBishop(canvas,squareWidthI*2,7*squareWidthI);   	
			drawWhiteBishop(canvas,squareWidthI*5,7*squareWidthI); 
			drawWhiteQueen(canvas,squareWidthI*3,7*squareWidthI);
			drawWhiteKing(canvas,squareWidthI*4,7*squareWidthI); 
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,0,0);
			drawBlackRook(canvas,7*squareWidthI,0);
			drawBlackKnight(canvas,squareWidthI,0);
			drawBlackKnight(canvas,squareWidthI*6,0);
			drawBlackBishop(canvas,2*squareWidthI,0);   	
			drawBlackBishop(canvas,5*squareWidthI,0); 
			drawBlackQueen(canvas,3*squareWidthI,0);
			drawBlackKing(canvas,4*squareWidthI,0);
			/*
			drawBlackRook(canvas,0,squareWidthI*7);
			drawBlackRook(canvas,squareWidthI*7,7*squareWidthI);
			drawBlackKnight(canvas,squareWidthI,squareWidthI*7);
			drawBlackKnight(canvas,squareWidthI*6,squareWidthI*7);
			drawBlackBishop(canvas,squareWidthI*2,7*squareWidthI);   	
			drawBlackBishop(canvas,squareWidthI*5,7*squareWidthI); 
			drawBlackQueen(canvas,squareWidthI*3,7*squareWidthI);
			drawBlackKing(canvas,squareWidthI*4,7*squareWidthI);*/
			Log.d("inDrawGameStart","extra pieces follow now."); 
		}
		else if(orientation.equals("white_right"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,squareWidthI*6,i*squareWidthI);
				drawBlackPawn(canvas,squareWidthI,i*squareWidthI);
			}
			drawWhiteRook(canvas,squareWidthI*7,0);
			drawWhiteRook(canvas,squareWidthI*7,7*squareWidthI);
			drawWhiteKnight(canvas,squareWidthI*7,squareWidthI);
			drawWhiteKnight(canvas,squareWidthI*7,squareWidthI*6);
			drawWhiteBishop(canvas,squareWidthI*7,2*squareWidthI);   	
			drawWhiteBishop(canvas,squareWidthI*7,5*squareWidthI); 
			drawWhiteQueen(canvas,squareWidthI*7,4*squareWidthI);
			drawWhiteKing(canvas,squareWidthI*7,3*squareWidthI); 


			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,0,0);
			drawBlackRook(canvas,0,7*squareWidthI);
			drawBlackKnight(canvas,0,squareWidthI);
			drawBlackKnight(canvas,0,squareWidthI*6);
			drawBlackBishop(canvas,0,2*squareWidthI);   	
			drawBlackBishop(canvas,0,5*squareWidthI); 
			drawBlackQueen(canvas,0,4*squareWidthI);
			drawBlackKing(canvas,0,3*squareWidthI);

			Log.d("inDrawGameStart","extra pieces follow now.");
		}
		//Drawing white extra pieces:
		drawWhiteKing(canvas,0,8*squareWidthI);
		drawWhiteQueen(canvas,squareWidthI,8*squareWidthI);
		drawWhiteBishop(canvas,squareWidthI*2,8*squareWidthI);
		drawWhiteKnight(canvas,0,9*squareWidthI);
		drawWhiteRook(canvas,squareWidthI,9*squareWidthI);
		drawWhitePawn(canvas,squareWidthI*2,9*squareWidthI);
		//Drawing black extra pieces:
		drawBlackKing(canvas,squareWidthI*5,8*squareWidthI);
		drawBlackQueen(canvas,squareWidthI*6,8*squareWidthI);
		drawBlackBishop(canvas,squareWidthI*7,8*squareWidthI);
		drawBlackKnight(canvas,squareWidthI*5,9*squareWidthI);
		drawBlackRook(canvas,squareWidthI*6,9*squareWidthI);
		drawBlackPawn(canvas,squareWidthI*7,9*squareWidthI);
		drawLeftArrow(canvas,squareWidthI*2,squareWidthI*10);
		drawRightArrow(canvas,squareWidthI*5,squareWidthI*10);
		drawClearButton(canvas,2,squareWidthI*10);
		drawResetButton(canvas,squareWidthI*6+2,squareWidthI*10);
		Log.d("inDrawGameStart","exiting");
		Log.d("inDrawGameStart","Orientation: "+orientation+"");

	}
	//Could add a check here to see which orientation has been chosen. 
	//Could add a new method here 'flipbBoard(Canvas canvas)'. 
	private void loadDrawable(String chosenSet, Context context)
	{
		leftArrow=context.getResources().getDrawable(R.drawable.left_button);
		rightArrow=context.getResources().getDrawable(R.drawable.right_button);
		resetButton=context.getResources().getDrawable(R.drawable.reset_button);
		clearButton=context.getResources().getDrawable(R.drawable.clear_button);

		if(chosenSet.equals("wood"))
		{
			whitePawn = context.getResources().getDrawable(R.drawable.pawnwwooden);
			whiteRook = context.getResources().getDrawable(R.drawable.rookwwooden);
			whiteKnight = context.getResources().getDrawable(R.drawable.knightwwooden);
			blackPawn = context.getResources().getDrawable(R.drawable.pawnbwooden);
			blackRook = context.getResources().getDrawable(R.drawable.rookbwooden);
			blackKnight = context.getResources().getDrawable(R.drawable.knightbwooden);
			whiteBishop = context.getResources().getDrawable(R.drawable.bishopwwooden);
			blackBishop = context.getResources().getDrawable(R.drawable.bishopbwooden);
			whiteQueen = context.getResources().getDrawable(R.drawable.queenwwooden);
			blackQueen = context.getResources().getDrawable(R.drawable.queenbwooden);
			whiteKing = context.getResources().getDrawable(R.drawable.kingwwooden);
			blackKing = context.getResources().getDrawable(R.drawable.kingbwooden);
		}
		else if(chosenSet.equals("eyes"))
		{
			whitePawn = context.getResources().getDrawable(R.drawable.pawn_w_eyes);
			whiteRook = context.getResources().getDrawable(R.drawable.rook_w_eyes);
			whiteKnight = context.getResources().getDrawable(R.drawable.knight_w_eyes);
			whiteBishop = context.getResources().getDrawable(R.drawable.bishop_w_eyes);
			whiteQueen = context.getResources().getDrawable(R.drawable.queen_w_eyes);
			whiteKing = context.getResources().getDrawable(R.drawable.king_w_eyes);
			blackPawn = context.getResources().getDrawable(R.drawable.pawn_b_eyes);
			blackRook = context.getResources().getDrawable(R.drawable.rook_b_eyes);
			blackKnight = context.getResources().getDrawable(R.drawable.knight_b_eyes);
			blackQueen = context.getResources().getDrawable(R.drawable.queen_b_eyes);
			blackKing = context.getResources().getDrawable(R.drawable.king_b_eyes);
			blackBishop = context.getResources().getDrawable(R.drawable.bishop_b_eyes);
		}
		else if(chosenSet.equals("staunton"))
		{
			blackPawn = context.getResources().getDrawable(R.drawable.black_pawn_free_staunton);
			blackRook = context.getResources().getDrawable(R.drawable.black_rook_free_staunton);
			blackKnight = context.getResources().getDrawable(R.drawable.black_knight_free_staunton);
			blackBishop = context.getResources().getDrawable(R.drawable.black_bishop_free_staunton);
			blackQueen = context.getResources().getDrawable(R.drawable.black_queen_free_staunton);
			blackKing = context.getResources().getDrawable(R.drawable.black_king_free_staunton);
			whitePawn = context.getResources().getDrawable(R.drawable.white_pawn_free_staunton);
			whiteRook = context.getResources().getDrawable(R.drawable.white_rook_free_staunton);
			whiteKnight = context.getResources().getDrawable(R.drawable.white_knight_free_staunton);
			whiteBishop = context.getResources().getDrawable(R.drawable.white_bishop_free_staunton);
			whiteQueen = context.getResources().getDrawable(R.drawable.white_queen_free_staunton);
			whiteKing = context.getResources().getDrawable(R.drawable.white_king_free_staunton);
		}
		if(chosenSet.equals("freak"))
		{
			whitePawn = context.getResources().getDrawable(R.drawable.wp_freak);
			whiteRook = context.getResources().getDrawable(R.drawable.wr_freak);
			whiteKnight = context.getResources().getDrawable(R.drawable.wk_freak);
			blackPawn = context.getResources().getDrawable(R.drawable.bp_freak);
			blackRook = context.getResources().getDrawable(R.drawable.br_freak);
			blackKnight = context.getResources().getDrawable(R.drawable.bk_freak);
			whiteBishop = context.getResources().getDrawable(R.drawable.wb_freak);
			blackBishop = context.getResources().getDrawable(R.drawable.bb_freak);
			whiteQueen = context.getResources().getDrawable(R.drawable.wq_freak);
			blackQueen = context.getResources().getDrawable(R.drawable.bq_freak);
			whiteKing = context.getResources().getDrawable(R.drawable.wk_freak);
			blackKing = context.getResources().getDrawable(R.drawable.bk_freak);
		}
	}
	private void drawCurrentPosition(Canvas canvas)
	{
		if(up){Log.d("wtf","wtf");
		Log.d("wtf","dragging: "+dragging+"");
		}
		Log.d("in drawCurrentPosition","entering");
		//make sure the reserve pieces are where they should be:
		MainActivity.hm.put("i1","white king");
		MainActivity.hm.put("i2","white queen");
		MainActivity.hm.put("i3","white bishop");
		MainActivity.hm.put("j1","white knight");
		MainActivity.hm.put("j2","white rook");
		MainActivity.hm.put("j3","white pawn");

		MainActivity.hm.put("i6","black king");
		MainActivity.hm.put("i7","black queen");
		MainActivity.hm.put("i8","black bishop");
		MainActivity.hm.put("j6","black knight");
		MainActivity.hm.put("j7","black rook");
		MainActivity.hm.put("j8","black pawn");
		//empty squares rows i and j:
		MainActivity.hm.put("i4","empty");
		MainActivity.hm.put("i5","empty");
		MainActivity.hm.put("j4","empty");
		MainActivity.hm.put("j5","empty");
		//iterate through the board squares: 
		String[] rows = {"a","b","c","d","e","f","g","h"};
		String[] columns = {"1","2","3","4","5","6","7","8"};
		String piece="";
		int i;
		int j;
		if(orientation.equals("white_left"))
		{
			for(i=0;i<8;i++)
			{	
				//this should go in an own method:
				for(j=0;j<8;j++)
				{	
		drawClearButton(canvas,2,squareWidthI*10);
		drawResetButton(canvas,squareWidthI*6+2,squareWidthI*10);					String test = rows[i]+columns[j];
					//Log.d("this is test: ",test);
					try
					{
						piece = MainActivity.hm.get(test);
						//Log.d("ole","the piece on the square is"+piece+"");
						//Log.d("ole","test: "+test+"");
					}catch(Exception ex){}
					if(checkMovingFromSquare.equals(test))
					{
						//Do nothing.
					}
					else
					{
						if(piece.equals("white pawn"))
						{
							drawWhitePawn(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black bishop"))
						{
						drawBlackBishop(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,squareWidthI*j,squareWidthI*i);
						}
						else if(piece.equals("black king"))
						{
							drawBlackKing(canvas,squareWidthI*j,squareWidthI*i);
						}
					}
				}
			}
		}
		else if(orientation.equals("white_up"))
		{
			for(i=0;i<8;i++)
			{	
				//this should go in an own method:
				for(j=0;j<8;j++)
				{	
					String test = rows[i]+columns[j];
					Log.d("this is test: ",test);
					try
					{
						piece = MainActivity.hm.get(test);
						Log.d("the piece on the square is"+piece,"in drawCurrenPosition");
					}catch(Exception ex){}
					i = invertRows(i);
					if(checkMovingFromSquare.equals(test))
					{
						//Do nothing.
					}
					else
					{
						if(piece.equals("white pawn"))
						{
							drawWhitePawn(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,squareWidthI*i,squareWidthI*j);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,squareWidthI*i,squareWidthI*j);
						}
					}
				}
			}
		}
		else if(orientation.equals("white_down"))
		{
			for(i=0;i<8;i++)
			{	
				//this should go in an own method:
				for(j=0;j<8;j++)
				{	
					String test = rows[i]+columns[j];
					Log.d("this is test: ",test);
					try
					{
						piece = MainActivity.hm.get(test);
						Log.d("the piece on the square is"+piece,"in drawCurrenPosition");
					}catch(Exception ex){}
					int l=invertColumn(j);
					if(checkMovingFromSquare.equals(test))
					{
						//Do nothing.
					}
					else
					{
						if(piece.equals("white pawn"))
						{
							drawWhitePawn(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,squareWidthI*i,squareWidthI*l);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,squareWidthI*i,squareWidthI*l);
						}
					}
				}
			}
		}
		else if(orientation.equals("white_right"))
		{
			for(i=0;i<8;i++)
			{	
				//this should go in an own method:
				for(j=0;j<8;j++)
				{	
					String test = rows[i]+columns[j];
					Log.d("this is test: ",test);
					try
					{
						piece = MainActivity.hm.get(test);
						Log.d("the piece on the square is"+piece,"in drawCurrenPosition");
					}catch(Exception ex){}
					int m =invertRows(j);
					int n = invertColumn(i);
					if(checkMovingFromSquare.equals(test))
					{
						//Do nothing.
					}
					else
					{
						if(piece.equals("white pawn"))
						{
							drawWhitePawn(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,squareWidthI*m,squareWidthI*n);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,squareWidthI*m,squareWidthI*n);
						}
					}
				}
			}
		}
		else {Log.d("in DrawCurrentPosition","orientation string not recognized");
		}
		//Drawing white extra pieces:
		drawWhiteKing(canvas,0,8*squareWidthI);
		drawWhiteQueen(canvas,squareWidthI,8*squareWidthI);
		drawWhiteBishop(canvas,squareWidthI*2,8*squareWidthI);
		drawWhiteKnight(canvas,0,9*squareWidthI);
		drawWhiteRook(canvas,squareWidthI,9*squareWidthI);
		drawWhitePawn(canvas,squareWidthI*2,9*squareWidthI);
		//Drawing black extra pieces:
		drawBlackKing(canvas,squareWidthI * 5, 8 * squareWidthI);
		drawBlackQueen(canvas,squareWidthI * 6, 8 * squareWidthI);
		drawBlackBishop(canvas, squareWidthI * 7, 8 * squareWidthI);
		drawBlackKnight(canvas, squareWidthI * 5, 9 * squareWidthI);
		drawBlackRook(canvas, squareWidthI * 6, 9 * squareWidthI);
		drawBlackPawn(canvas, squareWidthI * 7, 9 * squareWidthI);
		drawLeftArrow(canvas,squareWidthI * 2, squareWidthI * 10);
		drawRightArrow(canvas,squareWidthI*5,squareWidthI*10);
		drawClearButton(canvas,2,squareWidthI*10);
		drawResetButton(canvas,squareWidthI*6+2,squareWidthI*10);
		//there is drawn an extra piece here because the 
		//
		if(dragging)
		{
			drawMovingPosition(canvas,movingPiece,movingPieceX,movingPieceY);
			//Log.d("in Chessboard.drawCurrentPosition","dragging=true");
		}
		else
		{
			//Log.d("in Chessboard.drawCurrentPosition","dragging=false");
		}
		Log.d("in drawCurrentPosition","exiting");
	}
	private boolean checkPieceMoving(String movingPiece2) {
		if(movingPiece2.equals("movingPiece"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	private int invertRows(int i) {
		switch(i)
		{
		case 0: i=7;
		break;
		case 1: i=6;
		break;
		case 2: i=5;
		break;
		case 3: i=4;
		break;
		case 4: i=3;
		break;
		case 5: i=2;
		break;
		case 6: i=1;
		break;
		case 7: i=0;
		break;
		}
		return i;	
	}
	private int invertColumn(int j) {
		switch(j)
		{
		case 0: j=7;
		break;
		case 1: j=6;
		break;
		case 2: j=5;
		break;
		case 3: j=4;
		break;
		case 4: j=3;
		break;
		case 5: j=2;
		break;
		case 6: j=1;
		break;
		case 7: j=0;
		break;
		}
		return j;		
	}  
	void setMovingPiece(String piece, int x, int y)
	{
		movingPiece = piece;
		movingPieceX = x;
		movingPieceY = y;
	}
	String getMovingPiece()
	{
		return movingPiece;
	}
	void drawMovingPosition(Canvas canvas,String piece, int x, int y)
	{

		if(piece.equals("white pawn"))
		{
			drawWhitePawn(canvas,x,y);
		}
		if(piece.equals("white rook"))
		{
			drawWhiteRook(canvas,x,y);
		}
		if(piece.equals("white knight"))
		{
			drawWhiteKnight(canvas,x,y);
		}
		if(piece.equals("white bishop"))
		{
			drawWhiteBishop(canvas,x,y);
		}
		if(piece.equals("white queen"))
		{
			drawWhiteQueen(canvas,x,y);
		}
		if(piece.equals("white king"))
		{
			drawWhiteKing(canvas,x,y);
		}
		if(piece.equals("black pawn"))
		{
			drawBlackPawn(canvas,x,y);
		}
		if(piece.equals("black rook"))
		{
			drawBlackRook(canvas,x,y);
		}
		if(piece.equals("black knight"))
		{
			drawBlackKnight(canvas,x,y);
		}
		if(piece.equals("black bishop"))
		{
			drawBlackBishop(canvas,x,y);
		}
		if(piece.equals("black queen"))
		{
			drawBlackQueen(canvas,x,y);
		}
		if(piece.equals("black king"))
		{
			drawBlackKing(canvas,x,y);
		}
	}


	void drawLeftArrow(Canvas canvas,int posX, int posY)
	{
		leftArrow.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		leftArrow.draw(canvas);  	
	}
	void drawRightArrow(Canvas canvas,int posX, int posY)
	{
		rightArrow.setBounds(posX,posY,posX+squareWidthI,posY+squareWidthI);
		rightArrow.draw(canvas);  	
	}
	void drawResetButton(Canvas canvas, int posX, int posY)
	{
		//these are kind of improvised bounds and will squish the button a bit.
		resetButton.setBounds(posX,posY,posX+squareWidthI*2,posY+squareWidthI);
		resetButton.draw(canvas);
	}
	void drawClearButton(Canvas canvas, int posX, int posY)
	{
		//these are kind of improvised bounds and will squish the button a bit.
		clearButton.setBounds(posX, posY, posX + squareWidthI * 2, posY + squareWidthI);
		clearButton.draw(canvas);
	}
	void setDragging(boolean isDragging)
	{
		dragging=isDragging;
	}
	void setMovingFromSquare(String square)
	{
		checkMovingFromSquare = square;
	}
}
