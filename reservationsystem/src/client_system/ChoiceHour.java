package client_system;

import java.awt.*;

public class ChoiceHour extends Choice{
	
	ChoiceHour(){
		//�����͈̔͂̏����l��9���`21���Ƃ���
		resetRange(9,21);
	}
	
	//�w��ł��鎞���͈̔͂�ݒ肷�郁�\�b�h
	public void resetRange(int start,int end){
		int number = end -start + 1;
		
		//�I���{�b�N�X�̓��e���N���A����
		removeAll();
		
		//�w��ł���͈͂̎�����ݒ肷��
		while(start<=end){
			String h = String.valueOf(start);
			//�ꌅ�̏ꍇ�A�O�ɂO������
			if(h.length() == 1){
				h = "0" + h;
			}
			//�I���{�b�N�X�ɒǉ��i������͕�����j
			add(h);
			//start��1���₷
			start++;
		}
	}
	
	public String getLast(){
		return getItem(getItemCount()-1);
	}
}
