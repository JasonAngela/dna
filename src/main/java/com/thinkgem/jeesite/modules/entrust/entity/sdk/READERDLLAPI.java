package com.thinkgem.jeesite.modules.entrust.entity.sdk;

//Decompiled by DJ v3.7.7.81 Copyright 2004 Atanas Neshkov  Date: 2017/5/19 15:39:35
//Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//Decompiler options: packimports(3) 
//Source File Name:   READERDLLAPI.java



import com.sun.jna.*;

public class READERDLLAPI
{
 public static interface READERDLL
     extends Library
 {
     public static class BYTEA extends Structure
     {

         public byte Data[];

         public BYTEA()
         {
             Data = new byte[256];
         }
     }

     public static class HandsetParam extends Structure
     {

         public byte TagType;
         public byte Alarm;
         public byte OutputMode;
         public byte USBBaudRate;
         public byte BtBaudRate;
         public byte Min_Frequence;
         public byte Max_Frequence;
         public byte Power;
         public byte RFhrdVer1;
         public byte RFhrdVer2;
         public byte RFSoftVer1;
         public byte RFSoftVer2;
         public byte ISTID;
         public byte TIDAddr;
         public byte TIDLen;
         public byte ISUSER;
         public byte USERAddr;
         public byte USERLen;
         public byte ISVibrate;
         public byte Modutype;
         public byte Reserve21;
         public byte Reserve22;
         public byte Reserve23;
         public byte Reserve24;
         public byte Reserve25;
         public byte Reserve26;
         public byte Reserve27;
         public byte Reserve28;
         public byte Reserve29;
         public byte Reserve30;
         public byte Reserve31;
         public byte Reserve32;

         public HandsetParam()
         {
         }
     }

     public static interface MyCallInterface
         extends Callback
     {

         public abstract short invoke(byte abyte0[]);
     }

     public static interface MyCallInterfaceTest
         extends Callback
     {

         public abstract short invoke();
     }

     public static interface MyCallInterfaceTestS
         extends Callback
     {

         public abstract short invoke(String s);
     }

     public static class ReaderDate extends Structure
     {

         public byte Year;
         public byte Month;
         public byte Day;
         public byte Hour;
         public byte Minute;
         public byte Second;

         public ReaderDate()
         {
         }
     }


     public abstract short ConnectScanner(int ai[], String s, int i);

     public abstract short DisconnectScanner(int i);

     public abstract short VH_ConnectScannerUsb(int ai[]);

     public abstract short VH_DisconnectScannerUsb(int i);

     public abstract short VH_GetVersion(int i, short aword0[], short aword1[]);

     public abstract short VH_SetLogFile(String s);

     public abstract short VH_SetCommunicationOpt(long l, char ac[]);

     public abstract short ReadHanderParam(int i, HandsetParam ahandsetparam[]);

     public abstract short WriteHanderParam(int i, HandsetParam ahandsetparam[]);

     public abstract short EPC1G2_ReadLabelID(int i, int j, int k, int l, byte abyte0[], byte abyte1[], int ai[], 
             int i1);

     public abstract short EPC1G2_WriteEPC(int i, byte byte0, byte abyte0[], byte abyte1[], int j);

     public abstract short SetReaderMode(int i, byte byte0);

     public abstract short EPC1G2_KillTag(int i, int j, byte abyte0[], byte abyte1[], int k);

     public abstract short EPC1G2_ReadWordBlock(int i, int j, byte abyte0[], int k, int l, int i1, byte abyte1[], 
             byte abyte2[], int j1);

     public abstract short EPC1G2_WriteWordBlock(int i, int j, byte abyte0[], int k, int l, int i1, byte abyte1[], 
             byte abyte2[], int j1);

     public abstract short EPC1G2_SetLock(int i, int j, Object obj, int k, int l, byte abyte0[], int i1);

     public abstract short EPC1G2_BlockLock(int i, int j, byte abyte0[], int k, byte abyte1[], int l);

     public abstract short EPC1G2_ChangeEas(int i, int j, byte abyte0[], int k, byte abyte1[], int l);

     public abstract short EPC1G2_EasAlarm(int i, int j);

     public abstract short EPC1G2_ReadProtect(int i, byte abyte0[], int j, byte abyte1[], int k);

     public abstract short EPC1G2_RStreadProtect(int i, byte abyte0[], int j);

     public abstract short SetHandsetID(int i, byte abyte0[]);

     public abstract short GetHandsetID(int i, byte abyte0[]);

     public abstract short SetReaderTime(int i, ReaderDate readerdate);

     public abstract short GetReaderTime(int i, byte abyte0[]);

     public abstract short SetReportFilter(int i, int j, int k, byte abyte0[]);

     public abstract short GetReportFilter(int i, int ai[], int ai1[], byte abyte0[]);

     public abstract short SetBtBaudRate(int i, int j);

     public abstract short GetBtBaudRate(int i, int ai[]);

     public abstract short SetBluetoothName(int i, int j, byte abyte0[]);

     public abstract short GetBluetoothName(int i, byte abyte0[]);

     public abstract short VH_GetRecordNum(int i, ReaderDate readerdate, ReaderDate readerdate1, int ai[]);

     public abstract short VH_GetRecord(int i, ReaderDate readerdate, ReaderDate readerdate1, int ai[], int ai1[], byte abyte0[]);

     public abstract short DeleteAllRecord(int i);

     public abstract short VH_GetRecordHigh(int i, MyCallInterface mycallinterface);

     public abstract short VH_GetRecordHighTest(int i, MyCallInterfaceTest mycallinterfacetest);

     public abstract short VH_GetRecordHighTestS(int i, MyCallInterfaceTestS mycallinterfacetests);

     public abstract short AddLableID(int i, int j, int k, byte abyte0[]);

     public abstract short DelLableID(int i);

     public abstract short DelSingleLableID(int i, int j, int k, byte abyte0[]);

     public abstract short GetLableID(int i, int j, int k, int ai[], int ai1[], byte abyte0[]);

     public abstract short SaveLableID(int i);

     public static final short _OK = 0;
 }


 
}
