import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;  //BufferedImage�N���X���g������
import javax.imageio.*;   //ImageIO�N���X���g������
import java.io.*;

public class ImageFilter extends Frame implements ActionListener {
	int div = 9;
	int weight = 1;
	int offset = 0;
	
	private Button btnLowPass = new Button("���[�p�X�t�B���^");
	private Button btnHighPass = new Button("�n�C�p�X�t�B���^");
	private Button btnHighboostPass = new Button("�n�C�u�[�X�g�t�B���^");
	private Button btnDiffPass = new Button("�����t�B���^");
	private Button btnPrewittPass = new Button("Prewitt�t�B���^");
	private Button btnSobelPass = new Button("Sobel�t�B���^");
	private Button btnEmbossPass = new Button("�G���{�X�t�B���^");
	private Button btnLaplacianPass = new Button("���v���V�A���t�B���^");
	private Button btnOriginal = new Button("�I���W�i��");
	private Button con33 = new Button("�R���{���[�V�����R�~�R");
	private Button con55 = new Button ("�R���{���[�V�����T�~�T");
	
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
	
	//���j���[
	private MenuBar mbar = new MenuBar();
	private Menu menuFile = new Menu("�t�@�C��");
	private MenuItem mitemOpen = new MenuItem("�J��");
	private MenuItem mitemSave = new MenuItem("�ۑ�");
	private MenuItem mitemExit = new MenuItem("�I��");
	
	
	
	//�ǂݍ��ݗp�C�\���p��BufferedImage�N���X
	BufferedImage readImage = null;
	BufferedImage dispImage = null;
	
	//main���\�b�h
	public static void main(String args[]){
		ImageFilter fm = new ImageFilter();
		fm.setSize(600, 500);
		fm.setVisible(true);
	}
	
	//�R���X�g���N�^
	public ImageFilter(){
		
		//�I���{�^����L���ɂ���
		addWindowListener(
			new WindowAdapter() {	
				public void windowClosing(WindowEvent evt) {
					System.exit(0);
				}
			}
		);
		//�^�C�g����ݒ�
		setTitle("�摜�t�B���^�v���O����");
		//���C�A�E�g�̐ݒ�
		setLayout(new BorderLayout(20,10));
		//�t���[���̃T�C�Y��ݒ�
		
//		�{�^�� (�R���|�[�l���g�̓o�^)
		add(btnOriginal);
		add(btnLowPass);
		add(btnHighPass);
		add(btnHighboostPass);
		add(btnDiffPass);
		add(btnPrewittPass);
		add(btnSobelPass);
		add(btnEmbossPass);
		add(btnLaplacianPass);
		
		//(�{�^���̃��X�i�[�N���X�̓o�^)
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
		

		
		//�R���|�[�l���g��o�^
		//���j���[
		menuFile.add(mitemOpen);
		menuFile.add(mitemSave);
		menuFile.add(mitemExit);
		mbar.add(menuFile);
		setMenuBar(mbar);
		
		//���X�i�[�N���X�̓o�^
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
		panel2.add(new Label("����l")); panel2.add(Tdiv); panel2.add(new Label("offset�l"));
		panel2.add(Toffset);
		add(BorderLayout.SOUTH, panel2);
	}
	
	//�C�x���g�n���h��
	public void actionPerformed(ActionEvent ev) {
		
		//�J�� ��I�������Ƃ��̏���
		if (ev.getSource() == mitemOpen){  //(1)

			// �摜�t�@�C���̓ǂݍ���
			//�t�@�C�����̎擾
			FileDialog fdialog = new FileDialog(this, "�J��", FileDialog.LOAD);
			fdialog.setVisible(true);
			String fileName = fdialog.getDirectory()+fdialog.getFile();
			
			//png�t�@�C�����J���CreadImage�ɓǂݍ���
			try {
				//�摜�t�@�C����ǂݍ���
				readImage = ImageIO.read(new File( fileName ));
				//�ǂݍ��񂾉摜(���̉摜)�Ɠ����T�C�Y��
				//�\���p�摜dispImage�𐶐�����
				dispImage = new BufferedImage( readImage.getWidth(),
				 readImage.getHeight(), BufferedImage.TYPE_INT_RGB );//(2)
				//���̉摜��\���p�摜�ɃR�s�[����
				copyImage();   //(3)
			} catch (Exception err) {
				//�������Ȃ�
			}
			
			//�\���p�摜��`�悷��
			repaint();
		}
		//�ۑ��@��I�������Ƃ��̏���
		else if (ev.getSource() == mitemSave){  //(4)
			
			// �摜�t�@�C���̏����o��
			//�t�@�C�����̎擾
			FileDialog fdialog = new FileDialog(this, "�ۑ�", FileDialog.SAVE);
			fdialog.setVisible(true);
			String fileName = fdialog.getDirectory()+fdialog.getFile();
			
			//png�t�@�C�����J���CreadImage�ɓǂݍ���
			try {
				ImageIO.write( dispImage, "png", new File( fileName ));
			} catch (Exception err) {
				//�������Ȃ�
			}
		}
		
//		�I���@��I�������Ƃ��̏���
		else if (ev.getSource() == mitemExit){
			//�I�� ��I��������C�v���O�������I������
			System.exit(0);
		}
//		(��������ǉ�)
//		���[�p�X�t�B���^�@�{�^�����������Ƃ��̏���
		else if (ev.getSource() == btnLowPass){  //(1)
			div = 9;
			//�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ 1, 1, 1 },   // S00, S01, S02
				{ 1, 1, 1 },   // S10, S11, S12
				{ 1, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		//�n�C�p�X�t�B���^�@�{�^�����������Ƃ��̏���
		else if(ev.getSource() == btnHighPass){
			div = 1;
//			�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ -1, 8, -1 },   // S10, S11, S12
				{ -1, -1, -1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if (ev.getSource() == btnHighboostPass){  //(1)
			div = 1;
			//�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ -1, 9, -1 },   // S10, S11, S12
				{ -1, -1, -1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if(ev.getSource() == btnDiffPass){
			div = 1;
//			�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, 0, 0 },   // S00, S01, S02
				{ 0, 1, 0 },   // S10, S11, S12
				{ 0, 0, 0 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if(ev.getSource() == btnPrewittPass){
			div = 1;
//			�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, -1, -1 },   // S00, S01, S02
				{ 0, 0, 0 },   // S10, S11, S12
				{ 1, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if(ev.getSource() == btnSobelPass){
			div = 1;
//			�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, 0, 1 },   // S00, S01, S02
				{ -2, 0, 2 },   // S10, S11, S12
				{ -1, 0, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if (ev.getSource() == btnEmbossPass){  //(1)
			div = 2;
			offset = 128;
			//�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ -1, -1, 0 },   // S00, S01, S02
				{ -1, 0, 1 },   // S10, S11, S12
				{ 0, 1, 1 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div,offset);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if(ev.getSource() == btnLaplacianPass){
			div = 1;
//			�\���p�摜���N���A����
			clearImage();   //(2)
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
			int [][] cmat = {   //(3)
				{ 0, -1, 0 },   // S00, S01, S02
				{ -1, 4, -1 },   // S10, S11, S12
				{ 0, -1, 0 }    // S20, S21, S22
			};
			
			DrawImage(cmat,div);
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		
		else if(ev.getSource() == con33){
			div = Integer.parseInt(Tdiv.getText());
			offset = Integer.parseInt(Toffset.getText());
			
//			�R���{�����[�V�����}�g���b�N�X�̒�`
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
			
			//�R���{�����[�V�����}�g���b�N�X�̒�`
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
		
//		�I���W�i���@�{�^�����������Ƃ��̏���
		else if (ev.getSource() == btnOriginal){  //(13)
			//���摜��dispImage�ɃR�s�[����
			copyImage();
			
			//�t�B���^������̉摜��`�悷��
			repaint();
		}
		//�I���@��I�������Ƃ��̏���
		else if (ev.getSource() == mitemExit){  //(5)
			//�I�� ��I��������C�v���O�������I������
			System.exit(0);
		}
	}
	
	//���̉摜��\���p�摜�ɃR�s�[����
	public void copyImage(){  //(6)
		if (dispImage != null){
			dispImage.setData( readImage.getData() );  //(7)
		}
	}
	
	//paint���\�b�h
	public void paint(Graphics g){  //(8)
		//BufferedImage�I�u�W�F�N�gdispImage�̉摜��\������
		if (dispImage != null){
			g.drawImage(dispImage, 60, 200, this);   //(9)
		}
	}
	
//	�\���p�摜���N���A����(�O���[�œh��Ԃ�)
	public void clearImage(){
		if (dispImage != null){
			//�������̃��[�v
			for (int i = 0; i < dispImage.getWidth(); i++){
				//�c�����̃��[�v
				for (int j = 0; j < dispImage.getHeight(); j++){
					//�_(i, j)�̐F���O���[�ɐݒ肷��
					dispImage.setRGB( i, j, Color.GRAY.getRGB() );
				}
			}
		}
	}
	
	public void DrawImage(int c[][],int d){
//		���σt�B���^�̏���
		for (int i = 1; i < readImage.getWidth() - 1; i++){     //(4)
			for (int j = 1; j < readImage.getHeight() - 1; j++){
				
				//3���F���L������ϐ���錾��0�ŏ�����
				int red=0, green=0, blue=0;  //(5)
				//�����Ǝ��͂�9�̉�f���ƂɃt�B���^�̏d�݂������ĉ����Ă���(�e3�F)
				for (int k = 0; k < 3; k++){            //(6)
					for (int l = 0; l < 3; l++){
						//�_(i,j)�𒆐S�Ƃ������͂�3x3�̓_
						//�̐F��1���擾����
						Color pxColor = new Color( 
							readImage.getRGB(i+k-1, j+l-1) );  //(7)
						//�擾�����F�ɑ΂��āC 3���F���Ƃɏd�݂�
						//�����č��v���Ă���
						red   += pxColor.getRed()    * c[k][l];  //(8)
						green += pxColor.getGreen()  * c[k][l];
						blue  += pxColor.getBlue()   * c[k][l];
					}	
				}
				//���ς����߂�
				red /= d;     //(9)
				green /= d;
				blue /= d;
				//�e�F��0?255�͈̔͂Ɏ��܂�悤�ɒ�������
				if (red > 255) red = 255;      //(10)
				if (green > 255) green = 255;
				if (blue > 255) blue = 255;
				if (red < 0) red = 0;
				if (green < 0) green = 0;
				if (blue < 0) blue = 0;
				//�\���p�摜�̓_(i, j)�ɐݒ肷��F�𐶐�����
				Color color = new Color(red, green, blue);   //(11)
				//�_(i, j)�ɁC���������F��ݒ肷��
				dispImage.setRGB( i, j, color.getRGB() );   //(12)
			}
		}
	}
	
	public void DrawImage(int c[][],int d,int os){
		//���σt�B���^�̏���
		for (int i = 1; i < readImage.getWidth() - 1; i++){     //(4)
			for (int j = 1; j < readImage.getHeight() - 1; j++){
				
				//3���F���L������ϐ���錾��0�ŏ�����
				int red=0, green=0, blue=0;  //(5)
				//�����Ǝ��͂�9�̉�f���ƂɃt�B���^�̏d�݂������ĉ����Ă���(�e3�F)
				for (int k = 0; k < 3; k++){            //(6)
					for (int l = 0; l < 3; l++){
						//�_(i,j)�𒆐S�Ƃ������͂�3x3�̓_
						//�̐F��1���擾����
						Color pxColor = new Color( 
							readImage.getRGB(i+k-1, j+l-1) );  //(7)
						//�擾�����F�ɑ΂��āC 3���F���Ƃɏd�݂�
						//�����č��v���Ă���
						red   += pxColor.getRed()    * c[k][l];  //(8)
						green += pxColor.getGreen()  * c[k][l];
						blue  += pxColor.getBlue()   * c[k][l];
					}	
				}
				//���ς����߂�
				red /= d;     //(9)
				green /= d;
				blue /= d;
				//�I�t�Z�b�g�l�𑫂��Ă���
				red += os;
				green += os;
				blue += os;
				//�e�F��0?255�͈̔͂Ɏ��܂�悤�ɒ�������
				if (red > 255) red = 255;      //(10)
				if (green > 255) green = 255;
				if (blue > 255) blue = 255;
				if (red < 0) red = 0;
				if (green < 0) green = 0;
				if (blue < 0) blue = 0;
				//�\���p�摜�̓_(i, j)�ɐݒ肷��F�𐶐�����
				Color color = new Color(red, green, blue);   //(11)
				//�_(i, j)�ɁC���������F��ݒ肷��
				dispImage.setRGB( i, j, color.getRGB() );   //(12)
			}
		}
	}
}