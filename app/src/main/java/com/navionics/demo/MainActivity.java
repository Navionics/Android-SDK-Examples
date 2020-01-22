package com.navionics.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.navionics.android.nms.NMSCameraPosition;
import com.navionics.android.nms.NMSCircle;
import com.navionics.android.nms.NMSCoordinateBounds;
import com.navionics.android.nms.NMSEnum;
import com.navionics.android.nms.NMSGroundOverlay;
import com.navionics.android.nms.NMSMapView;
import com.navionics.android.nms.NMSMarker;
import com.navionics.android.nms.NMSMutablePath;
import com.navionics.android.nms.NMSPolygon;
import com.navionics.android.nms.NMSPolyline;
import com.navionics.android.nms.NMSSettings;
import com.navionics.android.nms.NavionicsMobileServices;
import com.navionics.android.nms.core.NSError;
import com.navionics.android.nms.model.CGPoint;
import com.navionics.android.nms.model.NMSColor;
import com.navionics.android.nms.model.NMSLocationCoordinate2D;
import com.navionics.android.nms.ui.NMSMapFragment;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity {
    private static final List<NMSCircle> mCircles = new ArrayList<>();
    private static final List<NMSPolygon> mPolygons = new ArrayList<>();
    private static final List<NMSMarker> mMarkers = new ArrayList<>();
    private static final List<NMSPolyline>mPolylines = new ArrayList<>();
    private static final List<NMSGroundOverlay>mGroundOverlays = new ArrayList<>();
    private static boolean gpsEnabled=false;
    private static boolean downloadEnabled=false;
    private Button zoomIn;
    private Button zoomOut;
    private Button circle;
    private Button polygon;
    private Button polyline;
    private Button marker;
    private Button login;
    private Button download;
    private Button gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NMSSettings settings = NMSSettings.settings();
        settings.setLanguage(NMSEnum.NMSLanguage.NMSLanguageEnglish);
        settings.setMode(NMSEnum.NMSFrameworkMode.NMSFrameworkModeSandbox);
        settings.setProjectToken("M2TQZA3cxh7LATcPSe68/lFNVj+Jl82IezRxqU/3hI/xafKebYBE/SyY/p2F4BhP/qpY01Enq9gak4pz4jY/WYpZN4L7Vq8bY7DpJdxIuAzcRGpNQN3BrtwuEItjA75fgDp4IJxSdKlI7ad3LX0aiZeoSkMH3BjOKX6GKUgWl91in1p2sBLieBlvSpK94E/EO5dKResFtLMlLx+WUrLAtAY28FPsqVdQkZktzS3nT5WPlBGydP2NcW5fRLcyuTYHTTUS7oyT1i6PbMOrcofHocgahpMT0dyVauXyXhm1+uiIk3LqlGtudy6fDs7GJTs99o3UxcslQaQNW0WKTgk0o/Qg12ASnkgw6rL8hVKee8iCzTio50B7KUyrYfaB7ELTNeLfGbPitw0BeaeC03Gv478pUr+y2xEy+OsTgsNllJaEzsUBG6UTxlDdWRT+SHKIkJVN1mp63/h4eRBshmxfJo6JtF/P6Esv41SkI9rduDC6BfzafjwoxLxZWqxVp2nIP8S6X/0/twmBZn4kd2Ni7DQ+9R50YEQvEoTxOfcT9Ukqf16pRVJbJKhEcl0z/ptMObrUdU3Q8oJaO3FUuR3rkJVKbTfYTzQlhMHY9UBAZJjKcgreXn8Jzh5yTrin8trPkmkSruf4k9su7vJOP4I23oT6aCahRGFXAs/lMHo2/IE=");
        settings.setPrivateKey("-----BEGIN RSA PRIVATE KEY-----\n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\n-----END RSA PRIVATE KEY-----\n");

        NSError error = new NSError();
        boolean initResult = NavionicsMobileServices.initializeWithSettings(settings, error);
        if (!initResult) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Mobile SDK Initialization error");
            alertDialog.setMessage("The mobile sdk cannot be initialized, please check the configuration");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
            return;
        }
        setContentView(R.layout.activity_main);

        zoomIn = findViewById(R.id.zoomIn);
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                mapView.zoomInAnimated(true);
            }
        });

        zoomOut = findViewById(R.id.zoomOut);
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                mapView.zoomOutAnimated(true);
            }
        });

        circle = findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mCircles.size()==0) {
                    circle.setBackgroundColor(Color.RED);
                    addCircle(mapView);
                } else {
                    circle.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removeCircle();
                }
            }
        });

        polygon= findViewById(R.id.polygon);
        polygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mPolygons.size()==0) {
                    polygon.setBackgroundColor(Color.RED);
                    addPolygon(mapView);
                } else {
                    polygon.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removePolygon();
                }
            }
        });
        polyline = findViewById(R.id.polyline);
        polyline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mPolylines.size()==0) {

                    polyline.setBackgroundColor(Color.RED);
                    addPolyline(mapView);
                } else {
                    polyline.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removePolyline();
                }
            }
        });
        marker = findViewById(R.id.marker);
        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mMarkers.size()==0) {
                    marker.setBackgroundColor(Color.RED);
                    addMarker(mapView);
                } else {
                    marker.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removeMarker();
                }
            }
        });
        login= findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavionicsMobileServices.navionicsUser();
            }
        });

        download= findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!downloadEnabled) {
                    NavionicsMobileServices.enableDownloadAreaSelector();
                    downloadEnabled=true;
                    download.setBackgroundColor(Color.RED);
                } else {
                    download.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    downloadEnabled=false;
                    NavionicsMobileServices.disableDownloadAreaSelectorAndConfirm(true);
                }

            }
        });
        gps= findViewById(R.id.gps);
        gps.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NMSMapView mapView=getMapView();
                if (!gpsEnabled) {
                    if (NavionicsMobileServices.enableGPSServices()) {
                        gps.setBackgroundColor(Color.RED);
                        gpsEnabled=true;
                    }
                    switch (mapView.getGpsMode()) {
                        case NMSGPSModeUnlinked:
                        case NMSGPSModeCourseUpUnlinked: {
                            mapView.setGpsMode(NMSEnum.NMSGPSMode.NMSGPSModeNorthUp);
                            break;
                        }
                        case NMSGPSModeNorthUp:
                        {
                            mapView.setGpsMode(NMSEnum.NMSGPSMode.NMSGPSModeCompass);

                            break;
                        }
                        case NMSGPSModeCompass:
                        {
                            mapView.setGpsMode(NMSEnum.NMSGPSMode.NMSGPSModeCourseUp);
                            break;
                        }
                        case NMSGPSModeCourseUp:
                        {
                            mapView.setGpsMode(NMSEnum.NMSGPSMode.NMSGPSModeCourseUpUnlinked);
                            break;
                        }
                        default:
                            break;
                    }
                } else {
                    NavionicsMobileServices.disableGPSServices();
                    gps.setBackgroundColor(Color.LTGRAY);
                    gpsEnabled=false;
                }
            }
        });
        reset(savedInstanceState);
    }

    private void reset(Bundle savedInstanceState){
        if (savedInstanceState==null){
            mCircles.clear();
            mMarkers.clear();
            mPolygons.clear();
            mPolylines.clear();
            gpsEnabled=false;
            downloadEnabled=false;
            return;
        }
        if (mCircles.size()>0){
            circle.setBackgroundColor(Color.RED);
        }
        if (mMarkers.size()>0){
            marker.setBackgroundColor(Color.RED);
        }
        if (mPolygons.size()>0){
            polygon.setBackgroundColor(Color.RED);
        }
        if (mPolylines.size()>0){
            polyline.setBackgroundColor(Color.RED);
        }
        if (gpsEnabled){
            gps.setBackgroundColor(Color.RED);
        }
        if (downloadEnabled){
            download.setBackgroundColor(Color.RED);
        }
    }
    private NMSMapView getMapView(){
        //(NMSMapView) findViewById(R.id.mapView);
        NMSMapFragment fg=(NMSMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        assert fg != null;
        return fg.getMapView();//R.id.mapView);
    }
    private void addCircle(NMSMapView view){
        NMSCircle circle=NMSCircle.circleWithPosition(new NMSLocationCoordinate2D(41.814092,-70.5326246),250);
        circle.setFillColor(Color.RED);
        circle.setZIndex(1);
        circle.setMap(view);
        circle.setStrokeWidth(2);
        circle.setStrokeColor(NMSColor.argb(0.5f,0.5f,0.2f,0.8f));
        mCircles.add(circle);
        NMSLocationCoordinate2D circleLocation = new NMSLocationCoordinate2D(41.814092,-70.5326246);
        view.moveToLocation(circleLocation, 16.0f, true);
    }
    private void removeCircle(){
        for (NMSCircle myCircle :mCircles){
            myCircle.setMap(null);
        }

        mCircles.clear();
    }

    private void addPolygon(NMSMapView view){
        NMSMutablePath path = new NMSMutablePath();
        path.addCoordinate(new NMSLocationCoordinate2D(41.5943782,-70.3538668));
        path.addCoordinate(new NMSLocationCoordinate2D(41.573444,-70.3862691));
        path.addCoordinate(new NMSLocationCoordinate2D(41.5528769,-70.3494521));
        path.addCoordinate(new NMSLocationCoordinate2D(41.5624133,-70.3352917));
        NMSPolygon polygon=NMSPolygon.polygonWithPath(path);
        polygon.setFillColor(NMSColor.rgba(75.0f/255.0f,159.0f/255.0f,101.0f/255.0f,0.7f));
        polygon.setStrokeWidth(5.0f);
        polygon.setStrokeColor(NMSColor.colorWithAlphaComponent(Color.BLACK,0.5f));
        polygon.setMap(view);
        mPolygons.add(polygon);
        NMSLocationCoordinate2D polygonLocation = new NMSLocationCoordinate2D(41.5943782,-70.3538668);
        view.moveToLocation(polygonLocation, 12.0f, true);
    }
    private void removePolygon(){

        for (NMSPolygon polygon :mPolygons){
            polygon.setMap(null);
        }
        mPolygons.clear();
    }

    private void addPolyline(NMSMapView view){
        NMSMutablePath path = new NMSMutablePath();

        path.addCoordinate(new NMSLocationCoordinate2D(41.5698391,-70.0216346));
        path.addCoordinate(new NMSLocationCoordinate2D(41.5417418,-70.0273104));
        path.addCoordinate(new NMSLocationCoordinate2D(41.5208243,-69.9901994));
        path.addCoordinate(new NMSLocationCoordinate2D(41.5396177,-69.9076818));
        NMSPolyline polyline= NMSPolyline.polylineWithPath(path);
        polyline.setStrokeColor(NMSColor.colorWithAlphaComponent(Color.GREEN,0.5f));
        polyline.setStrokeWidth (5.0f);
        polyline.setMap(view);
        mPolylines.add(polyline);
        NMSCameraPosition polylineLocation = NMSCameraPosition.cameraWithTarget(new NMSLocationCoordinate2D(41.5698391,-70.0216346),11.0f);
        view.moveToCameraPosition(polylineLocation, true);
    }
    private void removePolyline(){
        for (NMSPolyline polyline :mPolylines){
            polyline.setMap(null);
        }

        mPolylines.clear();
    }

    private void addMarker(NMSMapView view){
        NMSLocationCoordinate2D markerLocation = new NMSLocationCoordinate2D(41.6229011,-70.285697);
        NMSMarker marker= NMSMarker.markerWithPosition(markerLocation);
        marker.setImage(R.drawable.marker_anchor);
        marker.setMap(view);
        mMarkers.add(marker);
        marker.setAnchor(CGPoint.CGPointMake(0.5f, 0.5f));
        marker.setOpacity(0.8f);
        view.moveToLocation(markerLocation, 13.0f, true);
    }

    private void removeMarker(){
        for (NMSMarker marker :mMarkers){
            marker.setMap(null);
        }
        mMarkers.clear();
    }

    private void addGroundOverlay(NMSMapView view){
        if (mGroundOverlays.size()==0){
            NMSCoordinateBounds bounds = NMSCoordinateBounds.initWithCoordinate( new NMSLocationCoordinate2D(41.0,-70.0), new NMSLocationCoordinate2D(41.1,-70.1));
            NMSGroundOverlay overlay = NMSGroundOverlay.groundOverlayWithBounds(bounds,R.drawable.marker_anchor);
            overlay.setMap(view);
            overlay.setOpacity(0.8f);
            mGroundOverlays.add(overlay);
            view.moveToLocation( new NMSLocationCoordinate2D(41.1d,-70.1d),13.0f, true);
        }
    }
    private void removeGroundOverlay(){
        for (NMSGroundOverlay groundOverlay :mGroundOverlays){
            groundOverlay.setMap(null);
        }
        mGroundOverlays.clear();

    }


}
