package com.thinkgem.jeesite.modules.entrust.entity;

import com.sun.jna.Native;
import com.thinkgem.jeesite.modules.entrust.entity.sdk.READERDLLAPI;
import com.thinkgem.jeesite.modules.entrust.entity.sdk.Utility;
import com.thinkgem.jeesite.modules.entrust.entity.sdk.READERDLLAPI.READERDLL;


//import com.JS.action.XuekaAction;


public class ReadCard {
	/*
	 * 读卡的参数
	 */
	public static Object ReaderAPI = null;
	public int hScanner[] = new int[1];
	public int mem = 1;// 哪个区,EPC,TID,USER,PASSWORD
	public int ptr = 0;// 从哪儿开始
	public int len = 0;// 取多长
	// 将字符串变为字节数组
	public byte[] mask = new byte[512];// 掩措hjghg

	// n

	public byte[] temp = new byte[64 * 2];
	public byte[] IDBuffer = new byte[30 * 256];
	public int[] nCounter = new int[2];
	public int Address = 0;
	public byte[][] EPCC1G2_IDBuffer = new byte[100][96];
	public int ID_len = 0, ID_len_temp = 0;

	public String res;// 读卡的结果

	// 读卡参数
	/**
	 * @param args
	 */
	public String ss() {
		ReaderAPI = Native.loadLibrary("VHLib", READERDLLAPI.READERDLL.class);
		return read();
	}
	public String read() {
		// 连接USB
		int result = 0;
		result = ((READERDLL) ReaderAPI).VH_ConnectScannerUsb(hScanner);
		// 读卡

		result = ((READERDLL) ReaderAPI).EPC1G2_ReadLabelID(hScanner[0], mem,
				ptr, len, mask, IDBuffer, nCounter, Address);
		for (int i = 0; i < nCounter[0]; i++) {
			if (IDBuffer[ID_len] > 32) {
				nCounter[0] = 0;
				break;
			}
			ID_len_temp = IDBuffer[ID_len] * 2 + 1;// 1word=16bit
			System.arraycopy(IDBuffer, ID_len, EPCC1G2_IDBuffer[i], 0,
					ID_len_temp);
			ID_len += ID_len_temp;
		}
		for (int i = 0; i < nCounter[0]; i++) {
			ID_len = EPCC1G2_IDBuffer[i][0] * 2;
			System.arraycopy(EPCC1G2_IDBuffer[i], 1, temp, 0, ID_len);
			// 将字节转16进制字符串 0x31 0x32 ===> "3132"
			res = Utility.bytes2HexString(temp, ID_len);
		}
		if (res == null) {
			System.out.println("空");
			return null;
		} else {
			System.out.println(res);
			return res;
		}
	}
	public static void main(String[] args) {
		
		
		for (int i = 0; i < 10000; i++) {
			ReadCard t = new ReadCard();
			if(t.ss()!=null){
				break;
			}
			
		}
	}
	
	
}
