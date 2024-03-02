package com.example.foodpos_counter.Utils;

import static android.graphics.Typeface.BOLD;
import static android.graphics.Typeface.DEFAULT_BOLD;

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
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.foodpos_counter.CompletedOrder_Activity;
import com.example.foodpos_counter.Model.CounterFoodlist;
import com.example.foodpos_counter.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imin.printerlib.IminPrintUtils;
import com.imin.printerlib.util.BmpUtils;

import java.util.ArrayList;

public class TestService extends Service {

    String text = "";
    ArrayList<CounterFoodlist> counterFoodlists;
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
            mIminPrintUtils = IminPrintUtils.getInstance(TestService.this);
        }
        mIminPrintUtils.resetDevice();
        counterFoodlists = new ArrayList<>();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        counterFoodlists = new ArrayList<>();
        int connectType = intent.getIntExtra("connectType", 0);
        String companyname = intent.getStringExtra("companyname");
        String companyaddress1 = intent.getStringExtra("companyaddress1");
        String companyaddress2 = intent.getStringExtra("companyaddress2");
        String companyaddress3 = intent.getStringExtra("companyaddress3");
        String orderdate = intent.getStringExtra("orderdate");
        String ordertime = intent.getStringExtra("ordertime");
        String customername = intent.getStringExtra("customername");
        String customertype = intent.getStringExtra("customertype");
        String billbyname = intent.getStringExtra("billbyname");
        String countername = intent.getStringExtra("countername");
        String saleinvoice = intent.getStringExtra("saleinvoice");
        String tableno = intent.getStringExtra("tableno");
        String tokenno = intent.getStringExtra("tokenno");
        String arrayListJson = intent.getStringExtra("jsonArray");
        String foodtotalamount = intent.getStringExtra("foodtotalamount");
        String taxpercentage = intent.getStringExtra("taxpercentage");
        String taxnumber = intent.getStringExtra("taxnumber");
        String totalvatamount = intent.getStringExtra("totalvatamount");
        String servicecharge = intent.getStringExtra("servicecharge");
        String discount = intent.getStringExtra("discount");
        String grandtotal = intent.getStringExtra("grandtotal");
        String paidamount = intent.getStringExtra("paidamount");
        String changedue = intent.getStringExtra("changedue");
        String totalamount = intent.getStringExtra("totalamount");
        String paymenttype = intent.getStringExtra("paymenttype");
        String paymentstatus = intent.getStringExtra("paymentstatus");

        Gson gson = new Gson();
        counterFoodlists= gson.fromJson(arrayListJson, new TypeToken<ArrayList<CounterFoodlist>>() {
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
                mIminPrintUtils.printSingleBitmap(bitmap,1);

                mIminPrintUtils.setTextStyle(1);
                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(40);
                mIminPrintUtils.printText(companyname.toUpperCase(),1);
                mIminPrintUtils.setTextLineSpacing(1.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText(companyaddress1+", "+companyaddress2,1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

//                mIminPrintUtils.setAlignment(1);
//                mIminPrintUtils.setTextSize(22);
//                mIminPrintUtils.printText(companyaddress2, 1);
//                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText(companyaddress3, 1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                String table_name1;
                if (customertype.equals("Takeaway")) {
                    table_name1 =  "Order No :" + saleinvoice;
                } else {
                    table_name1 = "Table no : " + tableno  + "    Order No :" + saleinvoice;
                }

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("Date / Time : " + orderdate +" / " + ordertime,1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("** " + customertype +" **",1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText(table_name1,1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("Counter Name : " +countername,1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                mIminPrintUtils.printText("Tax Number : " + taxnumber,1);
                mIminPrintUtils.setTextLineSpacing(0.5f);

                mIminPrintUtils.setAlignment(1);
                mIminPrintUtils.setTextSize(22);
                if (customername.equals("")){

                }
                else {
                    mIminPrintUtils.printText("Customer Name :  "+customername,1);
                }

                //mIminPrintUtils.setTextStyle(Typeface.NORMAL);
               // mIminPrintUtils.setTextTypeface(Typeface.MONOSPACE);
                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);
                //mIminPrintUtils.setTextTypeface(Typeface.DEFAULT);

                mIminPrintUtils.setTextStyle(Typeface.BOLD);
                String[] strings3 = new String[]{"Item", "Qty", "Price"};
                int[] colsWidthArr3 = new int[]{2, 1, 1};
                int[] colsAlign3 = new int[]{0, 1, 2};
                int[] colsSize3 = new int[]{26, 26, 26};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mIminPrintUtils.printColumnsText(strings3, colsWidthArr3,
                            colsAlign3, colsSize3);
                }

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                for (int i = 0; i < counterFoodlists.size(); i++){
                    mIminPrintUtils.setTextStyle(Typeface.BOLD);
                    String[] strings4 = new String[]{counterFoodlists.get(i).getFoodname(), counterFoodlists.get(i).getFoodqty(), counterFoodlists.get(i).getFoodprice()};
                    int[] colsWidthArr4 = new int[]{2, 1, 1};
                    int[] colsAlign4 = new int[]{0, 1, 2};
                    int[] colsSize4 = new int[]{26, 26, 26};
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        mIminPrintUtils.printColumnsText(strings4, colsWidthArr4,
                                colsAlign4, colsSize4);
                    }
                }

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);

                String food_str = "RM " + foodtotalamount;
                String[] strings32 = new String[]{"Sub Total ：", food_str};
                int[] colsWidthArr32 = new int[]{6, 2};
                int[] colsAlign32 = new int[]{2, 2};
                int[] colsSize32 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings32, colsWidthArr32,
                        colsAlign32, colsSize32);

//                String service_str = "Service Tax("  +taxpercentage+" ) :";
//                String vat_str = "RM " + totalvatamount;
//                String[] strings321 = new String[]{service_str, vat_str};
//                int[] colsWidthArr321 = new int[]{6, 2};
//                int[] colsAlign321 = new int[]{2, 2};
//                int[] colsSize321 = new int[]{26, 26};
//                mIminPrintUtils.printColumnsText(strings321, colsWidthArr321,
//                        colsAlign321, colsSize321);

                String service_str1 = "RM " + servicecharge;
                String[] strings322 = new String[]{"Service Charge  ：", service_str1};
                int[] colsWidthArr322 = new int[]{6, 2};
                int[] colsAlign322 = new int[]{2, 2};
                int[] colsSize322 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings322, colsWidthArr322,
                        colsAlign322, colsSize322);

                String total_str = "RM " + grandtotal;
                String[] strings323 = new String[]{"Grand Total  : ", total_str};
                int[] colsWidthArr323 = new int[]{6, 2};
                int[] colsAlign323 = new int[]{2, 2};
                int[] colsSize323 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings323, colsWidthArr323,
                        colsAlign323, colsSize323);

                mIminPrintUtils.printAndFeedPaper(5);
                mIminPrintUtils.sendRAWData(getLineByte());
                mIminPrintUtils.printAndFeedPaper(5);


                if(paymentstatus.equals("Unpaid")){
                    String[] strings327 = new String[]{paymentstatus,""};
                    int[] colsWidthArr327 = new int[]{8,0};
                    int[] colsAlign327 = new int[]{1,1};
                    int[] colsSize327 = new int[]{26, 26};
                    mIminPrintUtils.printColumnsText(strings327, colsWidthArr327,
                            colsAlign327, colsSize327);
                }
                else {
                    String[] strings324 = new String[]{"Payment Mode : ", paymenttype};
                    int[] colsWidthArr324 = new int[]{5, 3};
                    int[] colsAlign324 = new int[]{2, 2};
                    int[] colsSize324 = new int[]{26, 26};
                    mIminPrintUtils.printColumnsText(strings324, colsWidthArr324,
                            colsAlign324, colsSize324);


                    String[] strings325 = new String[]{"Payment Status : ", paymentstatus};
                    int[] colsWidthArr325 = new int[]{5, 3};
                    int[] colsAlign325 = new int[]{1, 1};
                    int[] colsSize325 = new int[]{26, 26};
                    mIminPrintUtils.printColumnsText(strings325, colsWidthArr325,
                            colsAlign325, colsSize325);

                }
                String[] strings326 = new String[]{"Thank you !!! Visit Again", ""};
                int[] colsWidthArr326 = new int[]{8, 0};
                int[] colsAlign326 = new int[]{1, 1};
                int[] colsSize326 = new int[]{26, 26};
                mIminPrintUtils.printColumnsText(strings326, colsWidthArr326,
                        colsAlign326, colsSize326);

                mIminPrintUtils.printText("");

                mIminPrintUtils.printAndFeedPaper(100);
                mIminPrintUtils.partialCut();

            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }


    private byte[] getLineByte() {
        Drawable drawable = getResources().getDrawable(R.drawable.line);
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
