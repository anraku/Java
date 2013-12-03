import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;  //BufferedImageクラスを使うため
import javax.imageio.*;   //ImageIOクラスを使うため
import java.io.*;

public class ImageFilter extends Frame implements ActionListener {
	int div = 9;
	int weight = 1;
	int offset = 0;
	
	private Button btnLowPass = new Button("ローパスフィルタ");
	private Button btnHighPass = new Button("ハイパスフィルタ");
	private Button btnHighboostPass = new Button("ハイブーストフィルタ");
	private Button btnDiffPass = new Button("差分フィルタ");
	private Button btnPrewittPass = new Button("Prewittフィルタ");
	private Button btnSobelPass = new Button("Sobelフィルタ");
	private Button btnEmbossPass = new Button("エンボスフィルタ");
	private Button btnLaplacianPass = new Button("ラプラシアンフィルタ");
	private Button btnOriginal = new Button("オリジナル");
	private Button con33 = new Button("コンボリーション３×３");
	private Button con55 = new Button ("コンボリーション５×５");
	
	Panel panel1 = new Panel(new GridLayout(6,5));
	Panel panel2 = new Panel(new GridLayout(6,5));
	
	private TextField txt1 = new TextField("", 5);
	private TextField txt2 = new TextField("", 5);
	private TextField txt3 = new TextField("", 5);
	private TextField txt4 = new TextField("", 5);
	private TextField txt5 = new TextField("", 5);
	private TextField txt6 = new TextField("", 5);
	private TextField txt7 = new TextField("", 5);
	private TextField txt8 = new TextField("", 5);
	private TextField txt9 = new TextField("", 5);
	private TextField txt10 = new TextField("", 5);
	private TextField txt11 = new TextField("", 5);
	private TextField txt12 = new TextField("", 5);
	private TextField txt13 = new TextField("", 5);
	private TextField txt14 = new TextField("", 5);
	private TextField txt15 = new TextField("", 5);
	private TextField txt16 = new TextField("", 5);
	private TextField txt17 = new TextField("", 5);
	private TextField txt18 = new TextField("", 5);
	private TextField txt19 = new TextField("", 5);
	private TextField txt20 = new TextField("", 5);
	private TextField txt21 = new TextField("", 5);
	private TextField txt22 = new TextField("", 5);
	private TextField txt23 = new TextField("", 5);
	private TextField txt24 = new TextField("", 5);
	private TextField txt25 = new TextField("", 5);
	private TextField Tdiv = new TextField("", 5);
	private TextField Toffset = new TextField("",5);
	
	//メニュー
	private MenuBar mbar = new MenuBar();
	private Menu menuFile = new Menu("ファイル");
	private MenuItem mitemOpen = new MenuItem("開く");
	private MenuItem mitemSave = new MenuItem("保存");
	private MenuItem mitemExit = new MenuItem("終了");
	
	
	
	//読み込み用，表示用のBufferedImageクラス
	BufferedImage readImage = null;
	BufferedImage dispImage = null;
	
	//mainメソッド
	public static void main(String args[]){
		ImageFilter fm = new ImageFilter();
		fm.setSize(600, 500);
		fm.setVisible(true);
	}
	
	//コンストラクタ
	public ImageFilter(){
		
		//終了ボタンを有効にする
		addWindowListener(
			new WindowAdapter() {	
				public void windowClosing(WindowEvent evt) {
					System.exit(0);
				}
			}
		);
		//タイトルを設定
		setTitle("画像フィルタプログラム");
		//レイアウトの設定
		setLayout(new BorderLayout(20,10));
		//フレームのサイズを設定
		
//		ボタン (コンポーネントの登録)
		add(btnOriginal);
		add(btnLowPass);
		add(btnHighPass);
		add(btnHighboostPass);
		add(btnDiffPass);
		add(btnPrewittPass);
		add(btnSobelPass);
		add(btnEmbossPass);
		add(btnLaplacianPass);
		
		//(ボタンのリスナークラスの登録)
		btnLowPass.addActionListener(this);
		btnHighPass.addActionListener(this);
		btnHighboostPass.addActionListener(this);
		btnDiffPass.addActionListener(this);
		btnPrewittPass.addActionListener(this);
		btnSobelPass.addActionListener(this);
		btnEmbossPass.addActionListener(this);
		btnLaplacianPass.addActionListener(this);
		btnOriginal.addActionListener(this);
		con33.addActionListener(this);
		con55.addActionListener(this);
		

		
		//コンポーネントを登録
		//メニュー
		menuFile.add(mitemOpen);
		menuFile.add(mitemSave);
		menuFile.add(mitemExit);
		mbar.add(menuFile);
		setMenuBar(mbar);
		
		//リスナークラスの登録
		mitemOpen.addActionListener(this);
		mitemSave.addActionListener(this);
		mitemExit.addActionListener(this);
		
		panel1.add(btnOriginal);
		panel1.add(btnLowPass);
		panel1.add(btnHighPass);
		panel1.add(btnHighboostPass);
		panel1.add(btnDiffPass);
		panel1.add(btnPrewittPass);
		panel1.add(btnSobelPass);
		panel1.add(btnEmbossPass);
		panel1.add(btnLaplacianPass);
		panel1.add(con33);
		panel1.add(con55);
		add(BorderLayout.NORTH, panel1);
		
		panel2.add(txt1); panel2.add(txt2); panel2.add(txt3); panel2.add(txt4); panel2.add(txt5);
		panel2.add(txt6); panel2.add(txt7); panel2.add(txt8); panel2.add(txt9); panel2.add(txt10);
		panel2.add(txt11); panel2.add(txt12); panel2.add(txt13); panel2.add(txt14); panel2.add(txt15);
		panel2.add(txt16); panel2.add(txt17); panel2.add(txt18); panel2.add(txt19); panel2.add(txt20);
		panel2.add(txt21); panel2.add(txt22); panel2.add(txt23); panel2.add(txt24); panel2.add(txt25);
		panel2.add(new Label("割る値")); panel2.add(Tdiv); panel2.add(new Label("offset値"));
		panel2.add(Toffset);
		add(BorderLayout.SOUTH, panel2);
	}
	
	//イベントハンドラ
	public void actionPerformed(ActionEvent ev) {
		
		//開く を選択したときの処理
		if (ev.getSource() == mitemOpen){  //(1)

			// 画像ファイルの読み込み
			//ファイル名の取得
			FileDialog fdialog = new FileDialog(this, "開く", FileDialog.LOAD);
			fdialog.setVisible(true);
			String fileName = fdialog.getDirectory()+fdialog.getFile();
			
			//pngファイルを開き，readImageに読み込む
			try {
				//画像ファイルを読み込む
				readImage = ImageIO.read(new File( fileName ));
				//読み込んだ画像(元の画像)と同じサイズの
				//表示用画像dispImageを生成する
				dispImage = new BufferedImage( readImage.getWidth(),
				 readImage.getHeight(), BufferedImage.TYPE_INT_RGB );//(2)
				//元の画像を表示用画像にコピーする
				copyImage();   //(3)
			} catch (Exception err) {
				//何もしない
			}
			
			//表示用画像を描画する
			repaint();
		}
		//保存　を選択したときの処理
		else if (ev.getSource() == mitemSave){  //(4)
			
			// 画像ファイルの書き出し
			//ファイル名の取得
			FileDialog fdialog = new FileDialog(this, "保存", FileDialog.SAVE);
			fdialog.setVisible(true);
			String fileName = fdialog.getDirectory()+fdialog.getFile();
			
			//pngファイルを開き，readImageに読み込む
			try {
				ImageIO.write( dispImage, "png", new File( fileName ));
			} catch (Exception err) {
				//何もしない
			}
		}
		
//		終了　を選択したときの処理
		else if (ev.getSource() == mitemExit){
			//終了 を選択したら，プログラムを終了する
			System.exit(0);
		}
//		(ここから追加)
//		ローパスフィルタ　ボタンを押したときの処理
		else if (ev.getSource() == btnLowPass){  //(1)
			div = 9;
			//表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ 1, 1, 1 },   // S00, S01, S02
				{ 1, 1, 1 },   // S10, S11, S12
				{ 1, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		//ハイパスフィルタ　ボタンを押したときの処理
		else if(ev.getSource() == btnHighPass){
			div = 1;
//			表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ -1, 8, -1 },   // S10, S11, S12
				{ -1, -1, -1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if (ev.getSource() == btnHighboostPass){  //(1)
			div = 1;
			//表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ -1, 9, -1 },   // S10, S11, S12
				{ -1, -1, -1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if(ev.getSource() == btnDiffPass){
			div = 1;
//			表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, 0, 0 },   // S00, S01, S02
				{ 0, 1, 0 },   // S10, S11, S12
				{ 0, 0, 0 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if(ev.getSource() == btnPrewittPass){
			div = 1;
//			表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ 0, 0, 0 },   // S10, S11, S12
				{ 1, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if(ev.getSource() == btnSobelPass){
			div = 1;
//			表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, 0, 1 },   // S00, S01, S02
				{ -2, 0, 2 },   // S10, S11, S12
				{ -1, 0, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if (ev.getSource() == btnEmbossPass){  //(1)
			div = 2;
			offset = 128;
			//表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ -1, -1, 0 },   // S00, S01, S02
				{ -1, 0, 1 },   // S10, S11, S12
				{ 0, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div,offset);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if(ev.getSource() == btnLaplacianPass){
			div = 1;
//			表示用画像をクリアする
			clearImage();   //(2)
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ 0, -1, 0 },   // S00, S01, S02
				{ -1, 4, -1 },   // S10, S11, S12
				{ 0, -1, 0 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//フィルタ処理後の画像を描画する
			repaint();
		}
		
		else if(ev.getSource() == con33){
			div = Integer.parseInt(Tdiv.getText());
			offset = Integer.parseInt(Toffset.getText());
			
//			コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()) },   // S00, S01, S02
				{ Integer.parseInt(txt6.getText()), Integer.parseInt(txt7.getText()), Integer.parseInt(txt8.getText()) },   // S10, S11, S12
				{ Integer.parseInt(txt11.getText()), Integer.parseInt(txt12.getText()), Integer.parseInt(txt13.getText()) }    // S20, S21, S22
			};
			
			DrawImage(cmat,div,offset);
			repaint();
		}
		
		else if(ev.getSource() == con55){
			div = Integer.parseInt(Tdiv.getText());
			offset = Integer.parseInt(Toffset.getText());
			
			//コンボリューションマトリックスの定義
			int [][] cmat = {   //(3)
				{ Integer.parseInt(txt1.getText()), Integer.parseInt(txt2.getText()), Integer.parseInt(txt3.getText()),
					Integer.parseInt(txt4.getText()),Integer.parseInt(txt5.getText())},   // S00, S01, S02, S03, S04
				{ Integer.parseInt(txt6.getText()), Integer.parseInt(txt7.getText()), Integer.parseInt(txt8.getText()),
					Integer.parseInt(txt9.getText()),Integer.parseInt(txt10.getText())},   // S10, S11, S12,S13, S14
				{ Integer.parseInt(txt11.getText()), Integer.parseInt(txt12.getText()), Integer.parseInt(txt13.getText()),
					Integer.parseInt(txt14.getText()),Integer.parseInt(txt15.getText())},    // S20, S21, S22, S23, S24
				{ Integer.parseInt(txt11.getText()), Integer.parseInt(txt12.getText()), Integer.parseInt(txt13.getText()),
					Integer.parseInt(txt14.getText()),Integer.parseInt(txt15.getText())},    // S30, S31, S32, S33, S34
				{ Integer.parseInt(txt11.getText()), Integer.parseInt(txt12.getText()), Integer.parseInt(txt13.getText()),
					Integer.parseInt(txt14.getText()),Integer.parseInt(txt15.getText())}    // S40, S41, S42, S43, S44
			};
			
			DrawImage(cmat,div,offset);
			repaint();
		}
		
//		オリジナル　ボタンを押したときの処理
		else if (ev.getSource() == btnOriginal){  //(13)
			//元画像をdispImageにコピーする
			copyImage();
			
			//フィルタ処理後の画像を描画する
			repaint();
		}
		//終了　を選択したときの処理
		else if (ev.getSource() == mitemExit){  //(5)
			//終了 を選択したら，プログラムを終了する
			System.exit(0);
		}
	}
	
	//元の画像を表示用画像にコピーする
	public void copyImage(){  //(6)
		if (dispImage != null){
			dispImage.setData( readImage.getData() );  //(7)
		}
	}
	
	//paintメソッド
	public void paint(Graphics g){  //(8)
		//BufferedImageオブジェクトdispImageの画像を表示する
		if (dispImage != null){
			g.drawImage(dispImage, 60, 200, this);   //(9)
		}
	}
	
//	表示用画像をクリアする(グレーで塗りつぶす)
	public void clearImage(){
		if (dispImage != null){
			//横方向のループ
			for (int i = 0; i < dispImage.getWidth(); i++){
				//縦方向のループ
				for (int j = 0; j < dispImage.getHeight(); j++){
					//点(i, j)の色をグレーに設定する
					dispImage.setRGB( i, j, Color.GRAY.getRGB() );
				}
			}
		}
	}
	
	public void DrawImage(int c[][],int d){
//		平均フィルタの処理
		for (int i = 1; i < readImage.getWidth() - 1; i++){     //(4)
			for (int j = 1; j < readImage.getHeight() - 1; j++){
				
				//3原色を記憶する変数を宣言し0で初期化
				int red=0, green=0, blue=0;  //(5)
				//中央と周囲の9つの画素ごとにフィルタの重みをかけて加えていく(各3色)
				for (int k = 0; k < 3; k++){            //(6)
					for (int l = 0; l < 3; l++){
						//点(i,j)を中心とした周囲の3x3の点
						//の色を1つずつ取得する
						Color pxColor = new Color( 
							readImage.getRGB(i+k-1, j+l-1) );  //(7)
						//取得した色に対して， 3原色ごとに重みを
						//かけて合計していく
						red   += pxColor.getRed()    * c[k][l];  //(8)
						green += pxColor.getGreen()  * c[k][l];
						blue  += pxColor.getBlue()   * c[k][l];
					}	
				}
				//平均を求める
				red /= d;     //(9)
				green /= d;
				blue /= d;
				//各色が0?255の範囲に収まるように調整する
				if (red > 255) red = 255;      //(10)
				if (green > 255) green = 255;
				if (blue > 255) blue = 255;
				if (red < 0) red = 0;
				if (green < 0) green = 0;
				if (blue < 0) blue = 0;
				//表示用画像の点(i, j)に設定する色を生成する
				Color color = new Color(red, green, blue);   //(11)
				//点(i, j)に，生成した色を設定する
				dispImage.setRGB( i, j, color.getRGB() );   //(12)
			}
		}
	}
	
	public void DrawImage(int c[][],int d,int os){
		//平均フィルタの処理
		for (int i = 1; i < readImage.getWidth() - 1; i++){     //(4)
			for (int j = 1; j < readImage.getHeight() - 1; j++){
				
				//3原色を記憶する変数を宣言し0で初期化
				int red=0, green=0, blue=0;  //(5)
				//中央と周囲の9つの画素ごとにフィルタの重みをかけて加えていく(各3色)
				for (int k = 0; k < 3; k++){            //(6)
					for (int l = 0; l < 3; l++){
						//点(i,j)を中心とした周囲の3x3の点
						//の色を1つずつ取得する
						Color pxColor = new Color( 
							readImage.getRGB(i+k-1, j+l-1) );  //(7)
						//取得した色に対して， 3原色ごとに重みを
						//かけて合計していく
						red   += pxColor.getRed()    * c[k][l];  //(8)
						green += pxColor.getGreen()  * c[k][l];
						blue  += pxColor.getBlue()   * c[k][l];
					}	
				}
				//平均を求める
				red /= d;     //(9)
				green /= d;
				blue /= d;
				//オフセット値を足していく
				red += os;
				green += os;
				blue += os;
				//各色が0?255の範囲に収まるように調整する
				if (red > 255) red = 255;      //(10)
				if (green > 255) green = 255;
				if (blue > 255) blue = 255;
				if (red < 0) red = 0;
				if (green < 0) green = 0;
				if (blue < 0) blue = 0;
				//表示用画像の点(i, j)に設定する色を生成する
				Color color = new Color(red, green, blue);   //(11)
				//点(i, j)に，生成した色を設定する
				dispImage.setRGB( i, j, color.getRGB() );   //(12)
			}
		}
	}
}