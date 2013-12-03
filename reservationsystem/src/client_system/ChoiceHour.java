package client_system;

import java.awt.*;

public class ChoiceHour extends Choice{
	
	ChoiceHour(){
		//時刻の範囲の初期値は9時～21時とする
		resetRange(9,21);
	}
	
	//指定できる時刻の範囲を設定するメソッド
	public void resetRange(int start,int end){
		int number = end -start + 1;
		
		//選択ボックスの内容をクリアする
		removeAll();
		
		//指定できる範囲の時刻を設定する
		while(start<=end){
			String h = String.valueOf(start);
			//一桁の場合、前に０をつける
			if(h.length() == 1){
				h = "0" + h;
			}
			//選択ボックスに追加（こちらは文字列）
			add(h);
			//startを1増やす
			start++;
		}
	}
	
	public String getLast(){
		return getItem(getItemCount()-1);
	}
}
