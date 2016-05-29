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
	public boolean notationOn = false;
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
	private int width;
	private int height;
	//Letters and numbers:
	private Drawable a;
	private Drawable b;
	private Drawable c;
	private Drawable d;
	private Drawable e;
	private Drawable f;
	private Drawable g;
	private Drawable h;
	private Drawable one;
	private Drawable two;
	private Drawable three;
	private Drawable four;
	private Drawable five;
	private Drawable six;
	private Drawable seven;
	private Drawable eight;
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
		width = MainActivity.width;
		height = MainActivity.height;
		//Log.d("this is MainActivity.width",""+width);
		//Log.d("this is MainActivity.height",""+height);
		Bitmap bitmap = Bitmap.createBitmap(height,width,Bitmap.Config.RGB_565);
		if(width<height)
		{
			if(!notationOn)
			{
				squareWidthF = width / 8;
			}
			else
			{
				squareWidthF = width / 9;
			}
		}
		else
		{	if(!notationOn)
			{
			squareWidthF = height / 8;
			}
			else
			{
			squareWidthF = width / 9;
			}
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
		if(width<height)
		{
			if(!notationOn)
			{
				squareWidthF = width / 8;
			}
			else
			{
				squareWidthF = width / 9;
			}
		}
		else
		{	if(!notationOn)
		{
			squareWidthF = height / 8;
		}
		else
		{
			squareWidthF = width / 9;
		}
		}
		squareWidthI=(int)squareWidthF;
		Canvas singleUseCanvas = new Canvas();

		//is this bitmap ever used?:a
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
		float temp=squareWidthF/2;
		//set margin:
		int m= (int)temp;
		if(!notationOn)
		{
			m=0;
		}
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
					if(!notationOn)
					{
						canvas.drawRect(new RectF(0 + i * squareWidthI, 0 + j * squareWidthI, 0 + i * squareWidthI + squareWidthI, 0 + squareWidthI + j * squareWidthI), p);

					}
					else
					{
						canvas.drawRect(new RectF(m + i * squareWidthI, m + j * squareWidthI, m + i * squareWidthI + squareWidthI, m + squareWidthI + j * squareWidthI), p);
					}
				}
				else
				{
					if(color.equals("white"))
					{
						if(!notationOn)
						{
							canvas.drawRect(new RectF(m + i * squareWidthI, m + j * squareWidthI, m + i * squareWidthI + squareWidthI, m + squareWidthI + j * squareWidthI), p);

						}
						else {
							canvas.drawRect(new RectF(m + i * squareWidthI, m + j * squareWidthI, m + i * squareWidthI + squareWidthI, m + squareWidthI + j * squareWidthI), p);
						}
						//whiteMarble.setBounds(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI + squareWidthI,squareWidthI+j*squareWidthI);
						//whiteMarble.draw(canvas);
					}
					else if(color.equals("black"))
					{
						canvas.drawRect(new RectF(m+i*squareWidthI,m+j*squareWidthI,m+i*squareWidthI+squareWidthI,m+squareWidthI+j*squareWidthI),p);
						//blackMarble.setBounds(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI);
						//blackMarble.draw(canvas);
					}
				}
				//canvas.drawRect(new RectF(0+i*squareWidthI,0+j*squareWidthI,i*squareWidthI+squareWidthI,squareWidthI+j*squareWidthI),p);

			}
		}
		if(notationOn)
		{
			if(orientation.equals("white_down")) {
				a.setBounds(m, 0, m + squareWidthI, m);
				b.setBounds(m + squareWidthI, 0, m + 2 * squareWidthI, m);
				c.setBounds(m + 2 * squareWidthI, 0, m + 3 * squareWidthI, m);
				d.setBounds(m + 3 * squareWidthI, 0, m + 4 * squareWidthI, m);
				e.setBounds(m + 4 * squareWidthI, 0, m + 5 * squareWidthI, m);
				f.setBounds(m + 5 * squareWidthI, 0, m + 6 * squareWidthI, m);
				g.setBounds(m + 6 * squareWidthI, 0, m + 7 * squareWidthI, m);
				h.setBounds(m + 7 * squareWidthI, 0, m + 8 * squareWidthI, m);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
				a.setBounds(m, m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9);
				b.setBounds(m + squareWidthI, m + squareWidthI * 8, m + 2 * squareWidthI, squareWidthI * 9);
				c.setBounds(m + 2 * squareWidthI, m + squareWidthI * 8, m + 3 * squareWidthI, squareWidthI * 9);
				d.setBounds(m + 3 * squareWidthI, m + squareWidthI * 8, m + 4 * squareWidthI, squareWidthI * 9);
				e.setBounds(m + 4 * squareWidthI, m + squareWidthI * 8, m + 5 * squareWidthI, squareWidthI * 9);
				f.setBounds(m + 5 * squareWidthI, m + squareWidthI * 8, m + 6 * squareWidthI, squareWidthI * 9);
				g.setBounds(m + 6 * squareWidthI, m + squareWidthI * 8, m + 7 * squareWidthI, squareWidthI * 9);
				h.setBounds(m + 7 * squareWidthI, m + squareWidthI * 8, m + 8 * squareWidthI, squareWidthI * 9);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
				eight.setBounds(0, m, m, m + squareWidthI);
				seven.setBounds(0, m + squareWidthI, m, m + 2 * squareWidthI);
				six.setBounds(0, m + 2 * squareWidthI, m, m + 3 * squareWidthI);
				five.setBounds(0, m + 3 * squareWidthI, m, m + 4 * squareWidthI);
				four.setBounds(0, m + 4 * squareWidthI, m, m + 5 * squareWidthI);
				three.setBounds(0, m + 5 * squareWidthI, m, m + 6 * squareWidthI);
				two.setBounds(0, m + 6 * squareWidthI, m, m + 7 * squareWidthI);
				one.setBounds(0, m + 7 * squareWidthI, m, m + 8 * squareWidthI);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
				eight.setBounds(m + squareWidthI * 8, m, squareWidthI * 9, m + squareWidthI);
				seven.setBounds(m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9, m + squareWidthI * 2);
				six.setBounds(m + squareWidthI * 8, m + squareWidthI * 2, squareWidthI * 9, m + squareWidthI * 3);
				five.setBounds(m + squareWidthI * 8, m + squareWidthI * 3, squareWidthI * 9, m + squareWidthI * 4);
				four.setBounds(m + squareWidthI * 8, m + squareWidthI * 4, squareWidthI * 9, m + squareWidthI * 5);
				three.setBounds(m + squareWidthI * 8, m + squareWidthI * 5, squareWidthI * 9, m + squareWidthI * 6);
				two.setBounds(m + squareWidthI * 8, m + squareWidthI * 6, squareWidthI * 9, m + squareWidthI * 7);
				one.setBounds(m + squareWidthI * 8, m + squareWidthI * 7, squareWidthI * 9, m + squareWidthI * 8);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
			}
			else if(orientation.equals("white_left"))
			{
				one.setBounds(m, 0, m + squareWidthI, m);
				two.setBounds(m + squareWidthI, 0, m + 2 * squareWidthI, m);
				three.setBounds(m + 2 * squareWidthI, 0, m + 3 * squareWidthI, m);
				four.setBounds(m + 3 * squareWidthI, 0, m + 4 * squareWidthI, m);
				five.setBounds(m + 4 * squareWidthI, 0, m + 5 * squareWidthI, m);
				six.setBounds(m + 5 * squareWidthI, 0, m + 6 * squareWidthI, m);
				seven.setBounds(m + 6 * squareWidthI, 0, m + 7 * squareWidthI, m);
				eight.setBounds(m + 7 * squareWidthI, 0, m + 8 * squareWidthI, m);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
				one.setBounds(m, m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9);
				two.setBounds(m + squareWidthI, m + squareWidthI * 8, m + 2 * squareWidthI, squareWidthI * 9);
				three.setBounds(m + 2 * squareWidthI, m + squareWidthI * 8, m + 3 * squareWidthI, squareWidthI * 9);
				four.setBounds(m + 3 * squareWidthI, m + squareWidthI * 8, m + 4 * squareWidthI, squareWidthI * 9);
				five.setBounds(m + 4 * squareWidthI, m + squareWidthI * 8, m + 5 * squareWidthI, squareWidthI * 9);
				six.setBounds(m + 5 * squareWidthI, m + squareWidthI * 8, m + 6 * squareWidthI, squareWidthI * 9);
				seven.setBounds(m + 6 * squareWidthI, m + squareWidthI * 8, m + 7 * squareWidthI, squareWidthI * 9);
				eight.setBounds(m + 7 * squareWidthI, m + squareWidthI * 8, m + 8 * squareWidthI, squareWidthI * 9);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
				a.setBounds(0, m, m, m + squareWidthI);
				b.setBounds(0, m + squareWidthI, m, m + 2 * squareWidthI);
				c.setBounds(0, m + 2 * squareWidthI, m, m + 3 * squareWidthI);
				d.setBounds(0, m + 3 * squareWidthI, m, m + 4 * squareWidthI);
				e.setBounds(0, m + 4 * squareWidthI, m, m + 5 * squareWidthI);
				f.setBounds(0, m + 5 * squareWidthI, m, m + 6 * squareWidthI);
				g.setBounds(0, m + 6 * squareWidthI, m, m + 7 * squareWidthI);
				h.setBounds(0, m + 7 * squareWidthI, m, m + 8 * squareWidthI);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
				a.setBounds(m + squareWidthI * 8, m, squareWidthI * 9, m + squareWidthI);
				b.setBounds(m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9, m + squareWidthI * 2);
				c.setBounds(m + squareWidthI * 8, m + squareWidthI * 2, squareWidthI * 9, m + squareWidthI * 3);
				d.setBounds(m + squareWidthI * 8, m + squareWidthI * 3, squareWidthI * 9, m + squareWidthI * 4);
				e.setBounds(m + squareWidthI * 8, m + squareWidthI * 4, squareWidthI * 9, m + squareWidthI * 5);
				f.setBounds(m + squareWidthI * 8, m + squareWidthI * 5, squareWidthI * 9, m + squareWidthI * 6);
				g.setBounds(m + squareWidthI * 8, m + squareWidthI * 6, squareWidthI * 9, m + squareWidthI * 7);
				h.setBounds(m + squareWidthI * 8, m + squareWidthI * 7, squareWidthI * 9, m + squareWidthI * 8);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
			}
			else if (orientation.equals("white_up"))
			{
				h.setBounds(m, 0, m + squareWidthI, m);
				g.setBounds(m + squareWidthI, 0, m + 2 * squareWidthI, m);
				f.setBounds(m + 2 * squareWidthI, 0, m + 3 * squareWidthI, m);
				e.setBounds(m + 3 * squareWidthI, 0, m + 4 * squareWidthI, m);
				d.setBounds(m + 4 * squareWidthI, 0, m + 5 * squareWidthI, m);
				c.setBounds(m + 5 * squareWidthI, 0, m + 6 * squareWidthI, m);
				b.setBounds(m + 6 * squareWidthI, 0, m + 7 * squareWidthI, m);
				a.setBounds(m + 7 * squareWidthI, 0, m + 8 * squareWidthI, m);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
				h.setBounds(m, m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9);
				g.setBounds(m + squareWidthI, m + squareWidthI * 8, m + 2 * squareWidthI, squareWidthI * 9);
				f.setBounds(m + 2 * squareWidthI, m + squareWidthI * 8, m + 3 * squareWidthI, squareWidthI * 9);
				e.setBounds(m + 3 * squareWidthI, m + squareWidthI * 8, m + 4 * squareWidthI, squareWidthI * 9);
				d.setBounds(m + 4 * squareWidthI, m + squareWidthI * 8, m + 5 * squareWidthI, squareWidthI * 9);
				c.setBounds(m + 5 * squareWidthI, m + squareWidthI * 8, m + 6 * squareWidthI, squareWidthI * 9);
				b.setBounds(m + 6 * squareWidthI, m + squareWidthI * 8, m + 7 * squareWidthI, squareWidthI * 9);
				a.setBounds(m + 7 * squareWidthI, m + squareWidthI * 8, m + 8 * squareWidthI, squareWidthI * 9);
				a.draw(canvas);
				b.draw(canvas);
				c.draw(canvas);
				d.draw(canvas);
				e.draw(canvas);
				f.draw(canvas);
				g.draw(canvas);
				h.draw(canvas);
				one.setBounds(0, m, m, m + squareWidthI);
				two.setBounds(0, m + squareWidthI, m, m + 2 * squareWidthI);
				three.setBounds(0, m + 2 * squareWidthI, m, m + 3 * squareWidthI);
				four.setBounds(0, m + 3 * squareWidthI, m, m + 4 * squareWidthI);
				five.setBounds(0, m + 4 * squareWidthI, m, m + 5 * squareWidthI);
				six.setBounds(0, m + 5 * squareWidthI, m, m + 6 * squareWidthI);
				seven.setBounds(0, m + 6 * squareWidthI, m, m + 7 * squareWidthI);
				eight.setBounds(0, m + 7 * squareWidthI, m, m + 8 * squareWidthI);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
				one.setBounds(m + squareWidthI * 8, m, squareWidthI * 9, m + squareWidthI);
				two.setBounds(m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9, m + squareWidthI * 2);
				three.setBounds(m + squareWidthI * 8, m + squareWidthI * 2, squareWidthI * 9, m + squareWidthI * 3);
				four.setBounds(m + squareWidthI * 8, m + squareWidthI * 3, squareWidthI * 9, m + squareWidthI * 4);
				five.setBounds(m + squareWidthI * 8, m + squareWidthI * 4, squareWidthI * 9, m + squareWidthI * 5);
				six.setBounds(m + squareWidthI * 8, m + squareWidthI * 5, squareWidthI * 9, m + squareWidthI * 6);
				seven.setBounds(m + squareWidthI * 8, m + squareWidthI * 6, squareWidthI * 9, m + squareWidthI * 7);
				eight.setBounds(m + squareWidthI * 8, m + squareWidthI * 7, squareWidthI * 9, m + squareWidthI * 8);
				one.draw(canvas);
				two.draw(canvas);
				three.draw(canvas);
				four.draw(canvas);
				five.draw(canvas);
				six.draw(canvas);
				seven.draw(canvas);
				eight.draw(canvas);
			}
			else if(orientation.equals("white_right"))
			{
				eight.setBounds(m, 0, m + squareWidthI, m);
				seven.setBounds(m + squareWidthI, 0, m + 2 * squareWidthI, m);
				six.setBounds(m + 2 * squareWidthI, 0, m + 3 * squareWidthI, m);
				five.setBounds(m + 3 * squareWidthI, 0, m + 4 * squareWidthI, m);
				four.setBounds(m + 4 * squareWidthI, 0, m + 5 * squareWidthI, m);
				three.setBounds(m + 5 * squareWidthI, 0, m + 6 * squareWidthI, m);
				two.setBounds(m + 6 * squareWidthI, 0, m + 7 * squareWidthI, m);
				one.setBounds(m + 7 * squareWidthI, 0, m + 8 * squareWidthI, m);
				eight.draw(canvas);
				seven.draw(canvas);
				six.draw(canvas);
				five.draw(canvas);
				four.draw(canvas);
				three.draw(canvas);
				two.draw(canvas);
				one.draw(canvas);
				eight.setBounds(m, m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9);
				seven.setBounds(m + squareWidthI, m + squareWidthI * 8, m + 2 * squareWidthI, squareWidthI * 9);
				six.setBounds(m + 2 * squareWidthI, m + squareWidthI * 8, m + 3 * squareWidthI, squareWidthI * 9);
				five.setBounds(m + 3 * squareWidthI, m + squareWidthI * 8, m + 4 * squareWidthI, squareWidthI * 9);
				four.setBounds(m + 4 * squareWidthI, m + squareWidthI * 8, m + 5 * squareWidthI, squareWidthI * 9);
				three.setBounds(m + 5 * squareWidthI, m + squareWidthI * 8, m + 6 * squareWidthI, squareWidthI * 9);
				two.setBounds(m + 6 * squareWidthI, m + squareWidthI * 8, m + 7 * squareWidthI, squareWidthI * 9);
				one.setBounds(m + 7 * squareWidthI, m + squareWidthI * 8, m + 8 * squareWidthI, squareWidthI * 9);
				eight.draw(canvas);
				seven.draw(canvas);
				six.draw(canvas);
				five.draw(canvas);
				four.draw(canvas);
				three.draw(canvas);
				two.draw(canvas);
				one.draw(canvas);
				h.setBounds(0, m, m, m + squareWidthI);
				g.setBounds(0, m + squareWidthI, m, m + 2 * squareWidthI);
				f.setBounds(0, m + 2 * squareWidthI, m, m + 3 * squareWidthI);
				e.setBounds(0, m + 3 * squareWidthI, m, m + 4 * squareWidthI);
				d.setBounds(0, m + 4 * squareWidthI, m, m + 5 * squareWidthI);
				c.setBounds(0, m + 5 * squareWidthI, m, m + 6 * squareWidthI);
				b.setBounds(0, m + 6 * squareWidthI, m, m + 7 * squareWidthI);
				a.setBounds(0, m + 7 * squareWidthI, m, m + 8 * squareWidthI);
				h.draw(canvas);
				g.draw(canvas);
				f.draw(canvas);
				e.draw(canvas);
				d.draw(canvas);
				c.draw(canvas);
				b.draw(canvas);
				a.draw(canvas);
				h.setBounds(m + squareWidthI * 8, m, squareWidthI * 9, m + squareWidthI);
				g.setBounds(m + squareWidthI * 8, m + squareWidthI, squareWidthI * 9, m + squareWidthI * 2);
				f.setBounds(m + squareWidthI * 8, m + squareWidthI * 2, squareWidthI * 9, m + squareWidthI * 3);
				e.setBounds(m + squareWidthI * 8, m + squareWidthI * 3, squareWidthI * 9, m + squareWidthI * 4);
				d.setBounds(m + squareWidthI * 8, m + squareWidthI * 4, squareWidthI * 9, m + squareWidthI * 5);
				c.setBounds(m + squareWidthI * 8, m + squareWidthI * 5, squareWidthI * 9, m + squareWidthI * 6);
				b.setBounds(m + squareWidthI * 8, m + squareWidthI * 6, squareWidthI * 9, m + squareWidthI * 7);
				a.setBounds(m + squareWidthI * 8, m + squareWidthI * 7, squareWidthI * 9, m + squareWidthI * 8);
				h.draw(canvas);
				g.draw(canvas);
				f.draw(canvas);
				e.draw(canvas);
				d.draw(canvas);
				c.draw(canvas);
				b.draw(canvas);
				a.draw(canvas);
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
		float temp = squareWidthF/2;
		int margin= (int)temp;
		if(!notationOn)
		{
			margin=0;
		}
		//Draw white pawns on top row:
		if(orientation.equals("white_left"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,margin+squareWidthI,margin+i*squareWidthI);
				drawBlackPawn(canvas,margin+squareWidthI*6,margin+i*squareWidthI);
			}
			drawWhiteRook(canvas,margin,margin);
			drawWhiteRook(canvas,margin+0,margin+7*squareWidthI);
			drawWhiteKnight(canvas,margin,margin+squareWidthI);
			drawWhiteKnight(canvas,margin,margin+squareWidthI*6);
			drawWhiteBishop(canvas,margin,margin+2*squareWidthI);
			drawWhiteBishop(canvas,margin,margin+5*squareWidthI);
			drawWhiteQueen(canvas,margin,margin+3*squareWidthI);
			drawWhiteKing(canvas,margin,margin+4*squareWidthI);
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,margin+squareWidthI*7,margin);
			drawBlackRook(canvas,margin+squareWidthI*7,margin+7*squareWidthI);
			drawBlackKnight(canvas,margin+squareWidthI*7,margin+squareWidthI);
			drawBlackKnight(canvas,margin+squareWidthI*7,margin+squareWidthI*6);
			drawBlackBishop(canvas,margin+squareWidthI*7,margin+2*squareWidthI);
			drawBlackBishop(canvas,margin+squareWidthI*7,margin+5*squareWidthI);
			drawBlackQueen(canvas,margin+squareWidthI*7,margin+3*squareWidthI);
			drawBlackKing(canvas,margin+squareWidthI*7,margin+4*squareWidthI);
			Log.d("inDrawGameStart","extra pieces follow now.");
		}
		else if(orientation.equals("white_up"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,margin+i*squareWidthI,margin+squareWidthI);
				drawBlackPawn(canvas,margin+i*squareWidthI,margin+squareWidthI*6);
			}
			drawWhiteRook(canvas,margin,margin);
			drawWhiteRook(canvas,margin+7*squareWidthI,margin);
			drawWhiteKnight(canvas,margin+squareWidthI,margin);
			drawWhiteKnight(canvas,margin+squareWidthI*6,margin);
			drawWhiteBishop(canvas,margin+2*squareWidthI,margin);
			drawWhiteBishop(canvas,margin+5*squareWidthI,margin);
			drawWhiteQueen(canvas,margin+4*squareWidthI,margin);
			drawWhiteKing(canvas,margin+3*squareWidthI,margin);
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,margin,margin+squareWidthI*7);
			drawBlackRook(canvas,margin+squareWidthI*7,margin+7*squareWidthI);
			drawBlackKnight(canvas,margin+squareWidthI,margin+squareWidthI*7);
			drawBlackKnight(canvas,margin+squareWidthI*6,margin+squareWidthI*7);
			drawBlackBishop(canvas,margin+squareWidthI*2,margin+7*squareWidthI);
			drawBlackBishop(canvas,margin+squareWidthI*5,margin+7*squareWidthI);
			drawBlackQueen(canvas,margin+squareWidthI*4,margin+7*squareWidthI);
			drawBlackKing(canvas,margin+squareWidthI*3,margin+7*squareWidthI);
			Log.d("inDrawGameStart","extra pieces follow now."); 
		}
		else if(orientation.equals("white_down"))
		{
			for(i=0;i<8;i++)
			{
				drawWhitePawn(canvas,margin+i*squareWidthI,margin+squareWidthI*6);
				drawBlackPawn(canvas,margin+i*squareWidthI,margin+squareWidthI);
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
			drawWhiteRook(canvas,margin,margin+squareWidthI*7);
			drawWhiteRook(canvas,margin+squareWidthI*7,margin+7*squareWidthI);
			drawWhiteKnight(canvas,margin+squareWidthI,margin+squareWidthI*7);
			drawWhiteKnight(canvas,margin+squareWidthI*6,margin+squareWidthI*7);
			drawWhiteBishop(canvas,margin+squareWidthI*2,margin+7*squareWidthI);
			drawWhiteBishop(canvas,margin+squareWidthI*5,margin+7*squareWidthI);
			drawWhiteQueen(canvas,margin+squareWidthI*3,margin+7*squareWidthI);
			drawWhiteKing(canvas,margin+squareWidthI*4,margin+7*squareWidthI);
			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,margin,margin);
			drawBlackRook(canvas,margin+7*squareWidthI,margin);
			drawBlackKnight(canvas,margin+squareWidthI,margin);
			drawBlackKnight(canvas,margin+squareWidthI*6,margin);
			drawBlackBishop(canvas,margin+2*squareWidthI,margin);
			drawBlackBishop(canvas,margin+5*squareWidthI,margin);
			drawBlackQueen(canvas,margin+3*squareWidthI,margin);
			drawBlackKing(canvas,margin+4*squareWidthI,margin);
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
				drawWhitePawn(canvas,margin+squareWidthI*6,margin+i*squareWidthI);
				drawBlackPawn(canvas,margin+squareWidthI,margin+i*squareWidthI);
			}
			drawWhiteRook(canvas,margin+squareWidthI*7,margin);
			drawWhiteRook(canvas,margin+squareWidthI*7,margin+7*squareWidthI);
			drawWhiteKnight(canvas,margin+squareWidthI*7,margin+squareWidthI);
			drawWhiteKnight(canvas,margin+squareWidthI*7,margin+squareWidthI*6);
			drawWhiteBishop(canvas,margin+squareWidthI*7,margin+2*squareWidthI);
			drawWhiteBishop(canvas,margin+squareWidthI*7,margin+5*squareWidthI);
			drawWhiteQueen(canvas,margin+squareWidthI*7,margin+4*squareWidthI);
			drawWhiteKing(canvas,margin+squareWidthI*7,margin+3*squareWidthI);


			//Black Pieces:

			//Draw  pawns on top and bottom row:

			drawBlackRook(canvas,margin,margin);
			drawBlackRook(canvas,margin,margin+7*squareWidthI);
			drawBlackKnight(canvas,margin,margin+squareWidthI);
			drawBlackKnight(canvas,margin,margin+squareWidthI*6);
			drawBlackBishop(canvas,margin,margin+2*squareWidthI);
			drawBlackBishop(canvas,margin,margin+5*squareWidthI);
			drawBlackQueen(canvas,margin,margin+4*squareWidthI);
			drawBlackKing(canvas,margin,margin+3*squareWidthI);

			Log.d("inDrawGameStart","extra pieces follow now.");
		}
		//Drawing white extra pieces:
		float tempTwo = temp*2;
		int lowerMargin = (int)tempTwo;
		//Adding more space around each piece so that it will be easier to hit when the space shrinks.
		int extraMargin = (int)(width/24);
		int k=0;
		if(!notationOn)
		{
			extraMargin = 0;
			k=1;
			lowerMargin = 0;
		}
		drawWhiteKing(canvas, margin, lowerMargin + (8 * squareWidthI));
		drawWhiteQueen(canvas, extraMargin + margin + squareWidthI,lowerMargin + (8 * squareWidthI));
		drawWhiteBishop(canvas, 2 * extraMargin + margin + squareWidthI * 2,lowerMargin + (8 * squareWidthI));
		drawWhiteKnight(canvas, margin, extraMargin+lowerMargin + (9 * squareWidthI));
		drawWhiteRook(canvas, extraMargin + margin + squareWidthI, extraMargin+lowerMargin + (9 * squareWidthI));
		drawWhitePawn(canvas, 2 * extraMargin + margin + squareWidthI * 2, extraMargin+lowerMargin+(9*squareWidthI));
		//Drawing black extra pieces:
		drawBlackKing(canvas, margin + squareWidthI * (4+k),lowerMargin+ (8 * squareWidthI));
		drawBlackQueen(canvas, extraMargin + margin + squareWidthI * (5+k), lowerMargin+(8 * squareWidthI));
		drawBlackBishop(canvas, 2 * extraMargin + margin + squareWidthI * (6+k), lowerMargin+(8 * squareWidthI));
		drawBlackKnight(canvas, margin + squareWidthI * (4+k),extraMargin+ lowerMargin+(9 * squareWidthI));
		drawBlackRook(canvas,extraMargin+margin + squareWidthI * (5+k), extraMargin+lowerMargin+(9 * squareWidthI));
		drawBlackPawn(canvas,2*extraMargin+ margin + squareWidthI * (6+k), extraMargin+lowerMargin+(9 * squareWidthI));
		drawLeftArrow(canvas, margin + squareWidthI * 2, 2*extraMargin+lowerMargin+(squareWidthI * 10));
		drawRightArrow(canvas, margin + squareWidthI * 5,2*extraMargin+ lowerMargin+(squareWidthI * 10));
		drawClearButton(canvas, margin, 2*extraMargin+lowerMargin+(squareWidthI*10));
		drawResetButton(canvas,margin+2+squareWidthI*6+2,2*extraMargin+lowerMargin+(squareWidthI*10));

	}
	//Could add a check here to see which orientation has been chosen. 
	//Could add a new method here 'flipbBoard(Canvas canvas)'. 
	private void loadDrawable(String chosenSet, Context context)
	{
		leftArrow=context.getResources().getDrawable(R.drawable.left_button);
		rightArrow=context.getResources().getDrawable(R.drawable.right_button);
		resetButton=context.getResources().getDrawable(R.drawable.reset_button);
		clearButton=context.getResources().getDrawable(R.drawable.clear_button);

		a=context.getResources().getDrawable(R.drawable.a);
		b=context.getResources().getDrawable(R.drawable.b);
		c=context.getResources().getDrawable(R.drawable.c);
		d=context.getResources().getDrawable(R.drawable.d);
		e=context.getResources().getDrawable(R.drawable.e);
		f=context.getResources().getDrawable(R.drawable.f);
		g=context.getResources().getDrawable(R.drawable.g);
		h=context.getResources().getDrawable(R.drawable.h);
		one=context.getResources().getDrawable(R.drawable.one);
		two=context.getResources().getDrawable(R.drawable.two);
		three=context.getResources().getDrawable(R.drawable.three);
		four=context.getResources().getDrawable(R.drawable.four);
		five=context.getResources().getDrawable(R.drawable.five);
		six=context.getResources().getDrawable(R.drawable.six);
		seven=context.getResources().getDrawable(R.drawable.seven);
		eight=context.getResources().getDrawable(R.drawable.eight);

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
		float temp = squareWidthF/2;
		int margin= (int)temp;
		if(!notationOn)
		{
			margin=0;
		}
		else
		{
			squareWidthF = width / 9;
		}
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
					String test = rows[i]+columns[j];
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
							drawWhitePawn(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black bishop"))
						{
						drawBlackBishop(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
						}
						else if(piece.equals("black king"))
						{
							drawBlackKing(canvas,margin+squareWidthI*j,margin+squareWidthI*i);
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
							drawWhitePawn(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,margin+squareWidthI*i,margin+squareWidthI*j);
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
							drawWhitePawn(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,margin+squareWidthI*i,margin+squareWidthI*l);
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
							drawWhitePawn(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("white rook"))
						{
							drawWhiteRook(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("white knight"))
						{
							drawWhiteKnight(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("white bishop"))
						{
							drawWhiteBishop(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("white queen"))
						{
							drawWhiteQueen(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("white king"))
						{
							drawWhiteKing(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black pawn"))
						{
							drawBlackPawn(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black rook"))
						{
							drawBlackRook(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black knight"))
						{
							drawBlackKnight(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black bishop"))
						{
							drawBlackBishop(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black queen"))
						{
							drawBlackQueen(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
						if(piece.equals("black king"))
						{
							drawBlackKing(canvas,margin+squareWidthI*m,margin+squareWidthI*n);
						}
					}
				}
			}
		}
		else {Log.d("in DrawCurrentPosition","orientation string not recognized");
		}
		//Drawing white extra pieces:
		float tempTwo = temp*2;
		int lowerMargin = (int)tempTwo;
		//Adding more space around each piece so that it will be easier to hit when the space shrinks.
		int extraMargin = (int)(width/24);
		int k=0;
		if(!notationOn)
		{
			extraMargin = 0;
			k=1;
			lowerMargin = 0;
		}
		drawWhiteKing(canvas, margin, lowerMargin + (8 * squareWidthI));
		drawWhiteQueen(canvas, extraMargin + margin + squareWidthI,lowerMargin + (8 * squareWidthI));
		drawWhiteBishop(canvas, 2 * extraMargin + margin + squareWidthI * 2,lowerMargin + (8 * squareWidthI));
		drawWhiteKnight(canvas, margin, extraMargin+lowerMargin + (9 * squareWidthI));
		drawWhiteRook(canvas, extraMargin + margin + squareWidthI, extraMargin+lowerMargin + (9 * squareWidthI));
		drawWhitePawn(canvas, 2 * extraMargin + margin + squareWidthI * 2, extraMargin+lowerMargin+(9*squareWidthI));
		//Drawing black extra pieces:
		drawBlackKing(canvas, margin + squareWidthI * (4+k),lowerMargin+ (8 * squareWidthI));
		drawBlackQueen(canvas, extraMargin + margin + squareWidthI * (5+k), lowerMargin+(8 * squareWidthI));
		drawBlackBishop(canvas, 2 * extraMargin + margin + squareWidthI * (6+k), lowerMargin+(8 * squareWidthI));
		drawBlackKnight(canvas, margin + squareWidthI * (4+k),extraMargin+ lowerMargin+(9 * squareWidthI));
		drawBlackRook(canvas,extraMargin+margin + squareWidthI * (5+k), extraMargin+lowerMargin+(9 * squareWidthI));
		drawBlackPawn(canvas,2*extraMargin+ margin + squareWidthI * (6+k), extraMargin+lowerMargin+(9 * squareWidthI));
		drawLeftArrow(canvas, margin + squareWidthI * 2, 2*extraMargin+lowerMargin+(squareWidthI * 10));
		drawRightArrow(canvas, margin + squareWidthI * 5,2*extraMargin+ lowerMargin+(squareWidthI * 10));
		drawClearButton(canvas, margin, 2*extraMargin+lowerMargin+(squareWidthI*10));
		drawResetButton(canvas,margin+2+squareWidthI*6+2,2*extraMargin+lowerMargin+(squareWidthI*10));
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
	void drawLetters(Canvas canvas)
	{

	}
	void drawNumbers(Canvas canvas)
	{

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
