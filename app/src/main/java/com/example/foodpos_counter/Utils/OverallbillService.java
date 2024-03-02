package com.example.foodpos_counter.Utils;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.foodpos_counter.Model.Collectiondetails;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.Model.Fooddetails;
import com.example.foodpos_counter.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BmpUtils;

import java.util.ArrayList;

public class OverallbillService extends Service {

    String text = "";

    ArrayList<Collectiondetails> collectiondetails;
    ArrayList<Fooddetails> fooddetails;

    private IminPrintUtils mIminPrintUtils;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mIminPrintUtils == null) {
            mIminPrintUtils = IminPrintUtils.getInstance(OverallbillService.this);
        }
        mIminPrintUtils.resetDevice();

        collectiondetails = new ArrayList<>();
        fooddetails = new ArrayList<>();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        collectiondetails = new ArrayList<>();
        fooddetails = new ArrayList<>();

        int connectType = intent.getIntExtra("connectType", 0);

        String storename = intent.getStringExtra("storename");
        String companyaddress1 = intent.getStringExtra("companyaddress1");
        String companyaddress2 = intent.getStringExtra("companyaddress2");
        String companyaddress3 = intent.getStringExtra("companyaddress3");
        String printname = intent.getStringExtra("printname");
        String printdate = intent.getStringExtra("printdate");
        String timein = intent.getStringExtra("timein");
        String timeout = intent.getStringExtra("timeout");
        String bill_no = intent.getStringExtra("bill_no");
        String token_no = intent.getStringExtra("token_no");
        String grossfoodamount = intent.getStringExtra("grossfoodamount");
        String grossfoodcount = intent.getStringExtra("grossfoodcount");
        String itemsdiscount = intent.getStringExtra("itemsdiscount");
        String billdiscount = intent.getStringExtra("billdiscount");
        String salesreturn = intent.getStringExtra("salesreturn");
        String netsalesbefore = intent.getStringExtra("netsalesbefore");
        String servicecharge = intent.getStringExtra("servicecharge");
        String vat = intent.getStringExtra("vat");
        String roundind = intent.getStringExtra("roundind");
        String netsalesafter = intent.getStringExtra("netsalesafter");
        String arrayListJson = intent.getStringExtra("jsonArray");
        String totalcollectionamount = intent.getStringExtra("totalcollectionamount");
        String totalcreditcardamount = intent.getStringExtra("totalcreditcardamount");
        String billpaidcount = intent.getStringExtra("billpaidcount");
        String billpaidamount = intent.getStringExtra("billpaidamount");
        String billcancelcount = intent.getStringExtra("billcancelcount");
        String billcancelamount = intent.getStringExtra("billcancelamount");
        String billonholdcount = intent.getStringExtra("billonholdcount");
        String billonholdamount = intent.getStringExtra("billonholdamount");
        String billonordercount = intent.getStringExtra("billonordercount");
        String billonorderamount = intent.getStringExtra("billonorderamount");
        String totalbill = intent.getStringExtra("totalbill");
        String arrayListJson1 = intent.getStringExtra("jsonArray1");
        String totalitemscount = intent.getStringExtra("totalitemscount");
        String totalitemsamount = intent.getStringExtra("totalitemsamount");
        String totalcustomerpaidamount = intent.getStringExtra("totalcustomerpaidamount");
        String totalcustomerchangeamount = intent.getStringExtra("totalcustomerchangeamount");
        String totalbillamount = intent.getStringExtra("totalbillamount");
        String printeddate = intent.getStringExtra("printeddate");


        Gson gson = new Gson();

        collectiondetails= gson.fromJson(arrayListJson, new TypeToken<ArrayList<Collectiondetails>>() {
        }.getType());

        fooddetails= gson.fromJson(arrayListJson1, new TypeToken<ArrayList<Fooddetails>>() {
        }.getType());

        if (connectType == 1) {
            mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.USB);
        } else if (connectType == 2) {
            mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.BLUETOOTH);
        } else if (connectType == 3) {
            mIminPrintUtils.initPrinter(IminPrintUtils.PrintConnectType.SPI);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.divyacafelogo);
                mIminPrintUtils.printSingleBitmap(bitmap, 1);

                mIminPrintUtils.setTextStyle(1);
                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(40);
                mIminPrintUtils.printText(storename, 1);
                mIminPrintUtils.setTextLineSpacing(1.5f);

                mIminPrintUtils.setTextStyle(1);
                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText(companyaddress1 + ", " + companyaddress2, 1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

//                mIminPrintUtils.setAlignment(1);
//                mIminPrintUtils.setTextSize(22);
//                mIminPrintUtils.printText(companyaddress2, 1);
//                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText(companyaddress3, 1);
                mIminPrintUtils.setTextLineSpacing(0.5f);


                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(28);
                mIminPrintUtils.setTextStyle(1);
                mIminPrintUtils.printText("CLOSING SHIFT REPORT", 1);
                mIminPrintUtils.setTextStyle(0);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("*****************************", 1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                String[] strings = new String[]{"Date", ":", printdate};
                int[] colsWidthArr = new int[]{1, 1, 2};
                int[] colsAlign = new int[]{0, 1, 0};
                int[] colsSize = new int[]{24, 24, 24};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings, colsWidthArr,
                            colsAlign, colsSize);
                }
                String[] strings1 = new String[]{"Time In", ":", timein};
                int[] colsWidthArr1 = new int[]{1, 1, 2};
                int[] colsAlign1 = new int[]{0, 1, 0};
                int[] colsSize1 = new int[]{24, 24, 24};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings1, colsWidthArr1,
                            colsAlign1, colsSize1);
                }
                String[] strings2 = new String[]{"Time Out", ":", timeout};
                int[] colsWidthArr2 = new int[]{1, 1, 2};
                int[] colsAlign2 = new int[]{0, 1, 0};
                int[] colsSize2 = new int[]{24, 24, 24};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings2, colsWidthArr2,
                            colsAlign2, colsSize2);
                }
                String[] strings3 = new String[]{"Bill No", ":", bill_no};
                int[] colsWidthArr3 = new int[]{1, 1, 2};
                int[] colsAlign3 = new int[]{0, 1, 0};
                int[] colsSize3 = new int[]{24, 24, 24};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings3, colsWidthArr3,
                            colsAlign3, colsSize3);
                }
                String[] strings4 = new String[]{"Token No", ":", token_no};
                int[] colsWidthArr4 = new int[]{1, 1, 2};
                int[] colsAlign4 = new int[]{0, 1, 0};
                int[] colsSize4 = new int[]{24, 24, 24};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings4, colsWidthArr4,
                            colsAlign4, colsSize4);
                }
                mIminPrintUtils.printAndFeedPaper(3);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("*****************************", 1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.printAndFeedPaper(10);

                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                String[] stringss = new String[]{"Sales Summary", ""};
                int[] colsWidthArrs = new int[]{8, 0};
                int[] colsAligns = new int[]{0, 0};
                int[] colsSizes = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(stringss, colsWidthArrs,
                        colsAligns, colsSizes);

                mIminPrintUtils.printAndFeedPaper(5);

                mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                String[] stringst = new String[]{"", "Total"};
                int[] colsWidthArrt = new int[]{0, 8};
                int[] colsAlignt = new int[]{0, 2};
                int[] colsSizet = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(stringst, colsWidthArrt,
                        colsAlignt, colsSizet);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);


                String[] strings31 = new String[]{"Gross Sales ("+grossfoodcount+")", grossfoodamount};
                int[] colsWidthArr31 = new int[]{6, 2};
                int[] colsAlign31 = new int[]{0, 2};
                int[] colsSize31 = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(strings31, colsWidthArr31,
                        colsAlign31, colsSize31);

//                String[] strings32 = new String[]{"Item Discount", "0.00"};
//                int[] colsWidthArr32 = new int[]{6, 2};
//                int[] colsAlign32 = new int[]{0, 2};
//                int[] colsSize32 = new int[]{24, 24};
//                mIminPrintUtils.printColumnsText(strings32, colsWidthArr32,
//                        colsAlign32, colsSize32);

//                String[] strings33 = new String[]{"Bill Discount", "0.00"};
//                int[] colsWidthArr33 = new int[]{6, 2};
//                int[] colsAlign33 = new int[]{0, 2};
//                int[] colsSize33 = new int[]{24, 24};
//                mIminPrintUtils.printColumnsText(strings33, colsWidthArr33,
//                        colsAlign33, colsSize33);

                String[] strings34 = new String[]{"Sales Return", salesreturn};
                int[] colsWidthArr34 = new int[]{6, 2};
                int[] colsAlign34 = new int[]{0, 2};
                int[] colsSize34 = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(strings34, colsWidthArr34,
                        colsAlign34, colsSize34);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings35 = new String[]{"Net Sales Before ++", netsalesbefore};
                ;
                mIminPrintUtils.printColumnsText(strings35, colsWidthArr34,
                        colsAlign34, colsSize34);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings36 = new String[]{"Service Charge", servicecharge};
                ;
                mIminPrintUtils.printColumnsText(strings36, colsWidthArr34,
                        colsAlign34, colsSize34);

                String[] strings37 = new String[]{"Tax", vat};
                ;
                mIminPrintUtils.printColumnsText(strings37, colsWidthArr34,
                        colsAlign34, colsSize34);

//                String[] strings38 = new String[]{"Rounding", "0.00"};;
//                mIminPrintUtils.printColumnsText(strings38, colsWidthArr34,
//                        colsAlign34, colsSize34);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings39 = new String[]{"Net Sales After ++", netsalesafter};
                ;
                mIminPrintUtils.printColumnsText(strings39, colsWidthArr34,
                        colsAlign34, colsSize34);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(2);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(10);

                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                String[] strings40 = new String[]{"Collection Summary", ""};
                int[] colsWidthArr40 = new int[]{8, 0};
                int[] colsAlign40 = new int[]{0, 0};
                int[] colsSize40 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings40, colsWidthArr40,
                        colsAlign40, colsSize40);

                mIminPrintUtils.printAndFeedPaper(5);

                for (int i = 0 ; i < collectiondetails.size(); i++) {
                    mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                    String[] strings41 = new String[]{collectiondetails.get(i).getPaymentname()+ "(" + collectiondetails.get(i).getPaymenttotalorder()+")", collectiondetails.get(i).getPaymentamount()};
                    int[] colsWidthArr41 = new int[]{6, 2};
                    int[] colsAlign41 = new int[]{0, 2};
                    int[] colsSize41 = new int[]{24, 24};
                    mIminPrintUtils.printColumnsText(strings41, colsWidthArr41,
                            colsAlign41, colsSize41);
                }

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings43 = new String[]{"Total Collection", totalcollectionamount};
                int[] colsWidthArr43 = new int[]{6, 2};
                int[] colsAlign43 = new int[]{0, 2};
                int[] colsSize43 = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(strings43, colsWidthArr43,
                        colsAlign43, colsSize43);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(2);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings44 = new String[]{"Total Credit Card", totalcreditcardamount};
                int[] colsWidthArr44 = new int[]{6, 2};
                int[] colsAlign44 = new int[]{0, 2};
                int[] colsSize44 = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(strings44, colsWidthArr44,
                        colsAlign44, colsSize44);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(10);

                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                String[] strings45 = new String[]{"Bill Summary", ""};
                int[] colsWidthArr45 = new int[]{8, 0};
                int[] colsAlign45 = new int[]{0, 0};
                int[] colsSize45 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings45, colsWidthArr45,
                        colsAlign45, colsSize45);

                mIminPrintUtils.printAndFeedPaper(5);


                mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                String[] strings46 = new String[]{"", "No.of Bill", "Total Amount"};
                int[] colsWidthArr46 = new int[]{1, 1, 1};
                int[] colsAlign46 = new int[]{0, 1, 2};
                int[] colsSize46 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings46, colsWidthArr46,
                        colsAlign46, colsSize46);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings47 = new String[]{"Bill Paid", billpaidcount, billpaidamount};
                int[] colsWidthArr47 = new int[]{1, 1, 1};
                int[] colsAlign47 = new int[]{0, 1, 2};
                int[] colsSize47 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings47, colsWidthArr47,
                        colsAlign47, colsSize47);

//                String[] strings48 = new String[]{"Bill On Hold", "0", "0.00"};
//                int[] colsWidthArr48 = new int[]{1, 1, 1};
//                int[] colsAlign48 = new int[]{0, 1, 2};
//                int[] colsSize48 = new int[]{24, 24, 24};
//                mIminPrintUtils.printColumnsText(strings48, colsWidthArr48,
//                        colsAlign48, colsSize48);

                String[] strings49 = new String[]{"Bill Cancelled", billcancelcount, billcancelamount};
                int[] colsWidthArr49 = new int[]{1, 1, 1};
                int[] colsAlign49 = new int[]{0, 1, 2};
                int[] colsSize49 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings49, colsWidthArr49,
                        colsAlign49, colsSize49);

//                String[] strings50 = new String[]{"Bill On Order", "0", "0.00"};
//                int[] colsWidthArr50 = new int[]{1, 1, 1};
//                int[] colsAlign50 = new int[]{0, 1, 2};
//                int[] colsSize50 = new int[]{24, 24, 24};
//                mIminPrintUtils.printColumnsText(strings50, colsWidthArr50,
//                        colsAlign50, colsSize50);


                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(2);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String[] strings51 = new String[]{"Total Bill", billpaidcount, totalbill};
                int[] colsWidthArr51 = new int[]{1, 1, 1};
                int[] colsAlign51 = new int[]{0, 1, 2};
                int[] colsSize51 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings51, colsWidthArr51,
                        colsAlign51, colsSize51);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(10);

                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                String[] strings52 = new String[]{"Item Sales", ""};
                int[] colsWidthArr52 = new int[]{8, 0};
                int[] colsAlign52 = new int[]{0, 2};
                int[] colsSize52 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings52, colsWidthArr52,
                        colsAlign52, colsSize52);

                mIminPrintUtils.printAndFeedPaper(5);

                mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                String[] strings53 = new String[]{"", "Total"};
                int[] colsWidthArr53 = new int[]{0, 8};
                int[] colsAlign53 = new int[]{0, 2};
                int[] colsSize53 = new int[]{24, 24};
                mIminPrintUtils.printColumnsText(strings53, colsWidthArr53,
                        colsAlign53, colsSize53);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                for (int i = 0; i < fooddetails.size(); i++) {
                    String[] strings54 = new String[]{fooddetails.get(i).getFoodname(), fooddetails.get(i).getFoodqty(), fooddetails.get(i).getFoodprice()};
                    int[] colsWidthArr54 = new int[]{2, 1, 1};
                    int[] colsAlign54 = new int[]{0, 1, 2};
                    int[] colsSize54 = new int[]{24, 24, 24};
                    mIminPrintUtils.printColumnsText(strings54, colsWidthArr54,
                            colsAlign54, colsSize54);

                }

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(2);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);


                String[] strings55 = new String[]{"Total ITem Sales", totalitemscount, totalitemsamount};
                int[] colsWidthArr55 = new int[]{2, 1, 1};
                int[] colsAlign55 = new int[]{0, 1, 2};
                int[] colsSize55 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings55, colsWidthArr55,
                        colsAlign55, colsSize55);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());

                mIminPrintUtils.setTextStyle(Typeface.NORMAL);
                String[] strings56 = new String[]{"Printed Date", ":", printeddate};
                int[] colsWidthArr56 = new int[]{2, 1, 2};
                int[] colsAlign56 = new int[]{0, 1, 2};
                int[] colsSize56 = new int[]{24, 24, 24};
                mIminPrintUtils.printColumnsText(strings56, colsWidthArr56,
                        colsAlign56, colsSize56);

                mIminPrintUtils.printText("");

                mIminPrintUtils.printAndFeedPaper(100);
                mIminPrintUtils.partialCut();

            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }


    private byte[] getLineByte() {
        Drawable drawable = getResources().getDrawable(R.drawable.dot_line);
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap inputBmp = Bitmap.createBitmap(640, 4, config);
        Canvas canvas = new Canvas(inputBmp);// 建立对应bitmap的画布
        drawable.setBounds(0, 0, 640, 0);
        drawable.draw(canvas);// 把drawable内容画到画布中
        byte[] bytes = PrintDiskImagefile(inputBmp);
        return bytes;
    }

    public static byte[] PrintDiskImagefile(Bitmap bitmap) {
        byte[] bytes;


//        if (!strPath.substring(strPath.toLowerCase().indexOf(".") + 1).equals("bmp")) {
//            bitmap = convertToBlackWhite(bitmap);
//            int width = bitmap.getWidth();
//            int  heigh = bitmap.getHeight();
//            int iDataLen = width * heigh;
//            int[] pixels = new int[iDataLen];
//            bitmap.getPixels(pixels, 0, width, 0, 0, width, heigh);
//            bytes = PrintDiskImagefile(pixels,width,heigh);
//        }else
//        {
        int width = bitmap.getWidth();
        int heigh = bitmap.getHeight();
        int iDataLen = width * heigh;
        int[] pixels = new int[iDataLen];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, heigh);
        bytes = PrintDiskImagefile(pixels, width, heigh);

//        }

        return bytes;
    }

    public static byte[] PrintDiskImagefile(int[] pixels, int iWidth, int iHeight) {
        int iBw = iWidth / 8;
        int iMod = iWidth % 8;
        if (iMod > 0)
            iBw = iBw + 1;
        int iDataLen = iBw * iHeight;
        byte[] bCmd = new byte[iDataLen + 8];
        int iIndex = 0;
        bCmd[iIndex++] = 0x1D;
        bCmd[iIndex++] = 0x76;
        bCmd[iIndex++] = 0x30;
        bCmd[iIndex++] = 0x0;
        bCmd[iIndex++] = (byte) iBw;
        bCmd[iIndex++] = (byte) (iBw >> 8);
        bCmd[iIndex++] = (byte) iHeight;
        bCmd[iIndex++] = (byte) (iHeight >> 8);

        int iValue1 = 0;
        int iValue2 = 0;
        int iRow = 0;
        int iCol = 0;
        int iW = 0;
        int iValue3 = 0;
        int iValue4 = 0;
        for (iRow = 0; iRow < iHeight; iRow++) {
            for (iCol = 0; iCol < iBw - 1; iCol++) {
                iValue2 = 0;

                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                //Log.d("dzm","=== iValue1 = " + iValue1);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x80;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x40;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x20;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x10;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x8;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x4;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x2;
                iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                if (iValue1 == 1)
                    iValue2 = iValue2 + 0x1;
                if (iValue3 < -1) // w1
                    iValue4 = iValue4 + 0x10;
                bCmd[iIndex++] = (byte) (iValue2);
            }
            iValue2 = 0;
            if (iValue4 > 0)      // w2
                iValue3 = 1;
            if (iMod == 0) {
                for (iCol = 8; iCol > iMod; iCol--) {
                    iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                    if (iValue1 == 1)
                        iValue2 = iValue2 + (1 << iCol);
                }
            } else {
                for (iCol = 0; iCol < iMod; iCol++) {
                    iValue1 = BmpUtils.getPixelBlackWhiteValue(pixels[iW++]);
                    if (iValue1 == 1)
                        iValue2 = iValue2 + (1 << (8 - iCol));
                }
            }
            bCmd[iIndex++] = (byte) (iValue2);
        }
        return bCmd;
    }
}
