package com.navionics.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    static List<NMSCircle> mCircles = new ArrayList<NMSCircle>();
    static List<NMSPolygon> mPolygons = new ArrayList<NMSPolygon>();
    static List<NMSMarker> mMarkers = new ArrayList<NMSMarker>();
    static List<NMSPolyline>mPolylines = new ArrayList<NMSPolyline>();
    static List<NMSGroundOverlay>mGroundOverlays = new ArrayList<NMSGroundOverlay>();
    static boolean gpsEnabled=false;
    static boolean downloadEnabled=false;
    Button zoomIn;
    Button zoomOut;
    Button circle;
    Button polygon;
    Button polyline;
    Button marker;
    Button login;
    Button download;
    Button gps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NMSSettings settings = NMSSettings.settings();
        settings.setLanguage(NMSEnum.NMSLanguage.NMSLanguageEnglish);
        settings.setMode(NMSEnum.NMSFrameworkMode.NMSFrameworkModeSandbox);
        settings.setProjectToken("");//Add here the developer token
        settings.setPrivateKey("");//Add here the developer key

        NSError error = new NSError();
        NavionicsMobileServices.initializeWithSettings(settings, error);

        zoomIn = (Button) findViewById(R.id.zoomIn);
        zoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                mapView.zoomInAnimated(true);
            }
        });

        zoomOut = (Button) findViewById(R.id.zoomOut);
        zoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                mapView.zoomOutAnimated(true);
            }
        });

        circle = (Button) findViewById(R.id.circle);
        circle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mCircles.size()==0) {
                    circle.setBackgroundColor(Color.RED);
                    addCircle(mapView);
                } else {
                    circle.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removeCircle(mapView);
                }
            }
        });

        polygon= (Button) findViewById(R.id.polygon);
        polygon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mPolygons.size()==0) {
                    polygon.setBackgroundColor(Color.RED);
                    addPolygon(mapView);
                } else {
                    polygon.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removePolygon(mapView);
                }
            }
        });
        polyline = (Button) findViewById(R.id.polyline);
        polyline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mPolylines.size()==0) {

                    polyline.setBackgroundColor(Color.RED);
                    addPolyline(mapView);
                } else {
                    polyline.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removePolyline(mapView);
                }
            }
        });
        marker = (Button) findViewById(R.id.marker);
        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NMSMapView mapView = getMapView();
                if (mMarkers.size()==0) {
                    marker.setBackgroundColor(Color.RED);
                    addMarker(mapView);
                } else {
                    marker.setBackgroundColor(getResources().getColor(R.color.darker_gray));
                    removeMarker(mapView);
                }
            }
        });
        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavionicsMobileServices.navionicsUser();
            }
        });

        download=(Button)findViewById(R.id.download);
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
        gps=(Button)findViewById(R.id.gps);
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
                        {
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
                        case NMSGPSModeCourseUpUnlinked:
                        {
                            mapView.setGpsMode(NMSEnum.NMSGPSMode.NMSGPSModeNorthUp);
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
    NMSMapView getMapView(){
        //(NMSMapView) findViewById(R.id.mapView);
        NMSMapFragment fg=(NMSMapFragment)getSupportFragmentManager().findFragmentById(R.id.mapFragment);
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
    private void removeCircle(NMSMapView view){
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
    private void removePolygon(NMSMapView view){

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
    private void removePolyline(NMSMapView view){
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
        //addGroundOverlay(view);
    }

    private void removeMarker(NMSMapView view){
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