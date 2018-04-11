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
        settings.setProjectToken("M2TQZA3cxh7LATcPSe68/lFNVj+Jl82IezRxqU/3hI/xafKebYBE/SyY/p2F4BhP/qpY01Enq9gak4pz4jY/WYpZN4L7Vq8bY7DpJdxIuAzcRGpNQN3BrtwuEItjA75fgDp4IJxSdKlI7ad3LX0aiZeoSkMH3BjOKX6GKUgWl91in1p2sBLieBlvSpK94E/EO5dKResFtLMlLx+WUrLAtAY28FPsqVdQkZktzS3nT5WPlBGydP2NcW5fRLcyuTYHTTUS7oyT1i6PbMOrcofHocgahpMT0dyVauXyXhm1+uiIk3LqlGtudy6fDs7GJTs99o3UxcslQaQNW0WKTgk0o/Qg12ASnkgw6rL8hVKee8iCzTio50B7KUyrYfaB7ELTNeLfGbPitw0BeaeC03Gv478pUr+y2xEy+OsTgsNllJaEzsUBG6UTxlDdWRT+SHKIkJVN1mp63/h4eRBshmxfJo6JtF/P6Esv41SkI9rduDC6BfzafjwoxLxZWqxVp2nIP8S6X/0/twmBZn4kd2Ni7DQ+9R50YEQvEoTxOfcT9Ukqf16pRVJbJKhEcl0z/ptMObrUdU3Q8oJaO3FUuR3rkJVKbTfYTzQlhMHY9UBAZJjKcgreXn8Jzh5yTrin8trPkmkSruf4k9su7vJOP4I23oT6aCahRGFXAs/lMHo2/IE=");
        settings.setPrivateKey("-----BEGIN RSA PRIVATE KEY-----\n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\n-----END RSA PRIVATE KEY-----\n");

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
