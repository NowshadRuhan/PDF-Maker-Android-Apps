package com.appsticit1.needpdf;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.itextpdf.text.pdf.BidiOrder.PDF;

public class Pdf extends AppCompatActivity {

    Button btn;
    private InterstitialAd mInterstitialAd;


    EditText nameText, emailtext, mobileText, addressText, regText, chesses_notext;
    EditText engine_noText, typeText, brandText, m_yearText, colorText, monthlyBilltext;
    EditText deviceTypeText, devicePriceText, deviceIMEIText, simText;
    TextView dateText;
    String name, email, mobile, address, reg, chesses_no, engine_no, type, brand,  m_year, color, monthlyBill;
    String deviceType, devicePrice, deviceIMEI, sim;

    String date = "";
    int i = 1;
    int m = 0;
    String date1 = "";


 MobileAds.initialize(this,"ca-app-pub-2180548301155820~6429244384");
    mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-2180548301155820/5635037461");
    AddLoad();

        if (mInterstitialAd.isLoaded()) {
        mInterstitialAd.show();

    }
        else {

        AddLoad();
    }

        mInterstitialAd.setAdListener(new AdListener() {
        @Override
        public void onAdLoaded() {
            // Code to be executed when an ad finishes loading.

            mInterstitialAd.show();
        }

        @Override
        public void onAdFailedToLoad(int errorCode) {
            // Code to be executed when an ad request fails.
            AddLoad();
        }

        @Override
        public void onAdOpened() {
            // Code to be executed when the ad is displayed.
        }

        @Override
        public void onAdLeftApplication() {
            // Code to be executed when the user has left the app.
        }

        @Override
        public void onAdClosed() {
            // Code to be executed when when the interstitial ad is closed.
        }
    });






































    String folder_main = "pdfMomo";
    private static final int PERMISSION_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);

        nameText = findViewById(R.id.ed_nameText);
        name = nameText.getText().toString();

        emailtext = findViewById(R.id.ed_emailtext);
        email = emailtext.getText().toString();

        mobileText = findViewById(R.id.ed_mobileText);
        mobile = mobileText.getText().toString();

        addressText = findViewById(R.id.ed_addressText);
        address = addressText.getText().toString();

        regText = findViewById(R.id.ed_regText);
        reg = regText.getText().toString();

        chesses_notext = findViewById(R.id.ed_chesses_notext);
        chesses_no = chesses_notext.getText().toString();

        engine_noText = findViewById(R.id.ed_engine_noText);
        engine_no = engine_noText.getText().toString();

        typeText = findViewById(R.id.ed_typeText);
        type = typeText.getText().toString();

        brandText = findViewById(R.id.ed_brandText);
        brand = brandText.getText().toString();

        m_yearText = findViewById(R.id.ed_m_yearText);
        m_year = m_yearText.getText().toString();

        colorText = findViewById(R.id.ed_colorText);
        color = colorText.getText().toString();

        monthlyBilltext = findViewById(R.id.ed_monthlyBilltext);
        monthlyBill = monthlyBilltext.getText().toString();

        deviceTypeText = findViewById(R.id.ed_deviceTypeText);
        deviceType = deviceTypeText.getText().toString();

        devicePriceText = findViewById(R.id.ed_devicePriceText);
        devicePrice = devicePriceText.getText().toString();

        deviceIMEIText = findViewById(R.id.ed_deviceIMEIText);
        deviceIMEI = deviceIMEIText.getText().toString();

        simText = findViewById(R.id.ed_simText);
        sim = simText.getText().toString();


        dateText = findViewById(R.id.datetext);

        date = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());

        date1 = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


        dateText.setText(""+date);


        if (ContextCompat.checkSelfPermission(Pdf.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Pdf.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
        btn = findViewById(R.id.submitButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nameText.getText().toString().length() > 5 && emailtext.getText().toString().length() > 7 && mobileText.getText().toString().length() > 11
                        && addressText.getText().toString().length() > 0 && regText.getText().toString().length() > 0
               && chesses_notext.getText().toString().length() > 0 &&  engine_noText.getText().toString().length()>0
               && typeText.getText().toString().length()>0 && brandText.getText().toString().length()>0 &&
                        m_yearText.getText().toString().length()>3 && colorText.getText().toString().length()>0 &&
                monthlyBilltext.getText().toString().length()>0 && deviceTypeText.getText().toString().length()>0 &&
                        devicePriceText.getText().toString().length()>0 && deviceIMEIText.getText().toString().length()>0 &&
                        simText.getText().toString().length()>0) {

                    try {
                        makepdf();
                    } catch (DocumentException | IOException e) {
                        e.printStackTrace();
                    }
                    File pdfFile = new File(Environment.getExternalStorageDirectory(), File.separator + folder_main
                            + File.separator + i + ".pdf");//File path
                    i++;
                    if (pdfFile.exists()) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(File.separator + folder_main + File.separator + "rrr44" + ".pdf"));
                        intent.setDataAndType(Uri.parse("file:///" + pdfFile), "application/pdf");
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "This file not exsits ! ", Toast.LENGTH_SHORT).show();
                    }


                    nameText.setText("");
                    emailtext.setText("");
                    mobileText.setText("");
                    addressText.setText("");
                    regText.setText("");
                    chesses_notext.setText("");
                    engine_noText.setText("");
                    typeText.setText("");
                    brandText.setText("");
                    m_yearText.setText("");
                    colorText.setText("");
                    monthlyBilltext.setText("");
                    deviceTypeText.setText("");
                    devicePriceText.setText("");
                    deviceIMEIText.setText("");
                    simText.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please fill up all!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }





    private void makepdf() throws IOException, DocumentException {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }
        PdfWriter.getInstance(document, new FileOutputStream(Environment.getExternalStorageDirectory()
                + File.separator + folder_main + File.separator + i + ".pdf"));
        document.open();






        Font font2 = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD, BaseColor.BLACK);
        Font font = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD, BaseColor.BLACK);
        Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.BLACK);
        Font font3 = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD, BaseColor.BLACK);


        Paragraph bottomLine = new Paragraph("Finder GPS Tracking, Sylhet", font2);
        bottomLine.setAlignment(Element.ALIGN_CENTER);


        Paragraph bottomLine1 = new Paragraph("Office: Appstic It Ltd. 314,2nd floor, Rangmahal Tower,Bondorbazar,Sylhet", font);
        bottomLine1.setAlignment(Element.ALIGN_CENTER);

        Paragraph bottomLine2 = new Paragraph("Mobile : 01620807282, 01792144333", font);
        bottomLine2.setAlignment(Element.ALIGN_CENTER);


        Paragraph bottomLine3 = new Paragraph("Email : finder.sylhet@gmail.com", font);
        bottomLine3.setAlignment(Element.ALIGN_CENTER);

        Paragraph money_receipt = new Paragraph(""+date, font3);
        money_receipt.setAlignment(Element.ALIGN_RIGHT);

        Paragraph namePdf = new Paragraph("Name : "+nameText.getText().toString(),font1 );
        namePdf.setAlignment(Element.ALIGN_LEFT);

        Paragraph email = new Paragraph("Email : "+emailtext.getText().toString(),font1 );
        email.setAlignment(Element.ALIGN_LEFT);

        Paragraph mobile = new Paragraph("Mobile : "+mobileText.getText().toString(),font1 );
        mobile.setAlignment(Element.ALIGN_LEFT);

        Paragraph address = new Paragraph("Address : "+addressText.getText().toString(),font1 );
        address.setAlignment(Element.ALIGN_LEFT);

        Paragraph reg = new Paragraph("V.Reg.Plate No : "+regText.getText().toString(),font1 );
        reg.setAlignment(Element.ALIGN_LEFT);

        Paragraph chesses_no = new Paragraph("Chassis No : "+chesses_notext.getText().toString(),font1 );
        chesses_no.setAlignment(Element.ALIGN_LEFT);

        Paragraph engine_no = new Paragraph("Engine No : "+engine_noText.getText().toString(),font1 );
        engine_no.setAlignment(Element.ALIGN_LEFT);

        Paragraph type = new Paragraph("Type : "+typeText.getText().toString(),font1 );
        type.setAlignment(Element.ALIGN_LEFT);

        Paragraph brand = new Paragraph("Brand : "+ brandText.getText().toString(),font1 );
        brand.setAlignment(Element.ALIGN_LEFT);

        Paragraph m_year = new Paragraph("M.Year : "+ m_yearText.getText().toString(),font1 );
        m_year.setAlignment(Element.ALIGN_LEFT);

        Paragraph color = new Paragraph("Color : "+ colorText.getText().toString(),font1 );
        color.setAlignment(Element.ALIGN_LEFT);

        Paragraph monthlyBill = new Paragraph("Monthly Bill : "+monthlyBilltext.getText().toString()+" BDT",font1 );
        monthlyBill.setAlignment(Element.ALIGN_LEFT);

        Paragraph deviceType = new Paragraph("Device Type : "+deviceTypeText.getText().toString(),font1 );
        deviceType.setAlignment(Element.ALIGN_LEFT);

        Paragraph devicePrice = new Paragraph("Device Price : "+ devicePriceText.getText().toString()+" BDT",font1 );
        devicePrice.setAlignment(Element.ALIGN_LEFT);

        Paragraph deviceIMEI = new Paragraph("Device IMEI : "+deviceIMEIText.getText().toString(),font1 );
        deviceIMEI.setAlignment(Element.ALIGN_LEFT);

        Paragraph sim = new Paragraph("Sim Number : "+ simText.getText().toString(),font1 );
        sim.setAlignment(Element.ALIGN_LEFT);



        InputStream ims = getAssets().open("logo.png");
        Bitmap bmp = BitmapFactory.decodeStream(ims);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        Image image = Image.getInstance(stream.toByteArray());
        image.setAlignment(Element.ALIGN_CENTER);
        image.scaleAbsolute(100f, 100f);
        document.add(image);




        document.bottom(document.bottomMargin());
        document.add(bottomLine);
        document.add(bottomLine1);
        document.add(bottomLine2);
        document.add(bottomLine3);
        document.add(money_receipt);
        document.add(namePdf);
        document.add(email);
        document.add(mobile);
        document.add(address);
        document.add(reg);
        document.add(chesses_no);
        document.add(engine_no);
        document.add(type);
        document.add(brand);
        document.add(m_year);
        document.add(color);
        document.add(monthlyBill);
        document.add(deviceType);
        document.add(devicePrice);
        document.add(deviceIMEI);
        document.add(sim);
        document.close();

    }

    public PdfPCell getCell(String text, int alignment) {
        PdfPCell cell = new PdfPCell(new Phrase(text));
        cell.setPadding(0);
        cell.setHorizontalAlignment(alignment);
        cell.setBorder(PdfPCell.NO_BORDER);
        return cell;

    }

    public class RotateEvent extends PdfPageEventHelper {
        public void onStartPage(PdfWriter writer, Document document) {
            writer.addPageDictEntry(PdfName.ROTATE, PdfPage.SEASCAPE);
        }
    }
    public void AddLoad(){

        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }
}